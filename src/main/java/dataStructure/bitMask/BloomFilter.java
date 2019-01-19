package dataStructure.bitMask;

import java.io.InvalidObjectException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;
import java.util.zip.CRC32;

/**
 * Allow error ratio, search if an element exist, use multiple hash functions
 */
public class BloomFilter {

	private static final String TAG = "BloomFilter" ;
	// These fields are part of the public interface of this structure.
	// Client code may read these values if desired. Client code MUST NOT
	// modify any of these.
	protected final int entries;
	protected final double error;
	protected final int bits;
	protected final int bytes;
	protected final int hashes;
	protected final static double errorPrecision = 0.000000001;

	// Fields below are private to the implementation. These may go away or
	// change incompatibly at any moment. Client code MUST NOT access or rely
	// on these.
	double bpe;
	byte[] bf;
	int ready;

	/**
	 * Create a blank bloom filter, with a given number of expected entries, and an error rate
	 * @param entries the expected number of entries
	 * @param error the desired error rate
	 */
	public BloomFilter(int entries, double error)
	{
		this(null, entries, error);
	}


	static private final double denom = 0.480453013918201;

	/**
	 * Create a bloom filter from an existing data buffer, created by another instance of this library (probably in another language).
	 * If the length of the data does not fit the number of entries and error rate, we raise RuntimeException
	 * @param data the raw filter data
	 * @param entries the expected number of entries
	 * @param error the desired error rate
	 */
	public BloomFilter(byte []data, int entries, double error) throws RuntimeException
	{
		if (entries < 1 || ( ( 1.0 <= error ) || ( error <= errorPrecision ) ) ) {
			throw new RuntimeException("Invalid params for bloom filter");
		}

		this.entries = entries;
		this.error = error;

		bpe = -(Math.log(error) / denom);
		bits = (int)((double)entries * bpe);
		bytes = (bits / 8) + (bits % 8 != 0 ? 1 : 0);

		if (data != null) {
			if (bytes != data.length) {
				throw new RuntimeException(String.format("Expected %d bytes, got %d", bytes, data.length));
			}
			bf = data;
		} else {
			bf = new byte[bytes];;
		}


		hashes = (int)Math.ceil(0.693147180559945 * bpe);  // ln(2)
	}

	public static short computeChecksum(byte[] data) {
		CRC32 crc = new CRC32();
		crc.update(data);
		long checksum32 = crc.getValue();
		return (short) ((checksum32 & 0xFFFF) ^ (checksum32 >> 16));
	}

	public static BloomFilter load(byte[] bytes) throws InvalidObjectException {
		ByteBuffer bb = ByteBuffer.wrap(bytes);
		bb.order(ByteOrder.BIG_ENDIAN);
		short checksum = bb.getShort();
		short errorRate = bb.getShort();
		int cardinality = bb.getInt();
		final byte[] data = Arrays.copyOfRange(bytes, bb.position(), bytes.length);
		if (computeChecksum(data) != checksum)
			throw new InvalidObjectException("Bad checksum");

		return new BloomFilter(data, cardinality, 1.0 / errorRate);
	}

	public static byte[] dump(BloomFilter bf) {
		// 8 is the size of the header
		byte[] bytes = new byte[bf.bytes + 8];
		ByteBuffer bb = ByteBuffer.wrap(bytes);
		bb.order(ByteOrder.BIG_ENDIAN);

		bb.putShort(computeChecksum(bf.bf));
		bb.putShort((short) (1.0 / bf.error));
		bb.putInt(bf.entries);
		bb.put(bf.bf);

		return bytes;
	}

	private static long unsigned(int i) {
		return i & 0xffffffffl;
	}

	/**
	 * check existence or add an entry
	 * @param key the key to check/add
	 * @param add whether we add or just check the existence
	 * @return true if the key is already in the filter
	 */
	private boolean checkAdd(String key,boolean add) {

		int hits = 0;
		long a = unsigned(hash32(key, 0x9747b28c));
		long b = unsigned(hash32(key, (int) a));



		for (int i = 0; i < hashes; i++) {
			long x = unsigned ((int)(a + i*b)) % bits;
			long bt = x >> 3;

			byte c = bf[(int)bt];        // expensive memory access
			byte mask = (byte)(1 << (x % 8));

			if ((c & mask) != 0) {
				hits++;
			} else {
				if (add) {
					bf[(int)bt] = (byte)(c | mask);
				}
			}


		}

		return hits == hashes;
	}


	/**
	 * Check whether the filter contains a string
	 * @param key the string to check
	 * @return true if it already exists in the filter
	 */
	public boolean contains(String key)  {
		return checkAdd(key, false);
	}


	/**
	 * Add a string to the filter
	 * @param key the string to add
	 * @return true if the string was already in the filter
	 */
	public boolean add(String key) {
		return checkAdd(key, true);
	}

	public static int hash32(String data, int seed) {
		final byte[] bytes = data.getBytes();
		return hash32(bytes, bytes.length, seed);
	}

	/**
	 * Generates 32 bit hash from byte array of the given length and
	 * seed.
	 *
	 * @param data byte array to hash
	 * @param length length of the array to hash
	 * @param seed initial seed value
	 * @return 32 bit hash of the given array
	 */
	public static int hash32(final byte[] data, int length, int seed) {
		// 'm' and 'r' are mixing constants generated offline.
		// They're not really 'magic', they just happen to work well.
		final int m = 0x5bd1e995;
		final int r = 24;

		// Initialize the hash to a random value
		int h = seed^length;
		int length4 = length/4;

		for (int i=0; i<length4; i++) {
			final int i4 = i*4;
			int k = (data[i4+0]&0xff) +((data[i4+1]&0xff)<<8)
					+((data[i4+2]&0xff)<<16) +((data[i4+3]&0xff)<<24);
			k *= m;
			k ^= k >>> r;
			k *= m;
			h *= m;
			h ^= k;
		}

		// Handle the last few bytes of the input array
		switch (length%4) {
			case 3: h ^= (data[(length&~3) +2]&0xff) << 16;
			case 2: h ^= (data[(length&~3) +1]&0xff) << 8;
			case 1: h ^= (data[length&~3]&0xff);
				h *= m;
		}

		h ^= h >>> 13;
		h *= m;
		h ^= h >>> 15;

		return h;
	}

}