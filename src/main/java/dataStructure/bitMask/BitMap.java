package dataStructure.bitMask;

/**
 * Question:
 * Give a 4 billion random 32 bit int file, find a number not in the file.
 *
 * use index as number, and label 1 or 0 as exist or non-exist
 *
 * use bit for storage, better than boolean or hashmap
 */
public class BitMap {
	private char[] bytes;
	private int nbits;

	public BitMap(int nbits) {
		this.nbits = nbits;
		this.bytes = new char[nbits/8+1];
	}

	public void set(int k) {
		if (k > nbits) return;
		int byteIndex = k / 8;
		int bitIndex = k % 8;
		bytes[byteIndex] |= (1 << bitIndex);
	}

	public boolean get(int k) {
		if (k > nbits) return false;
		int byteIndex = k / 8;
		int bitIndex = k % 8;
		return (bytes[byteIndex] & (1 << bitIndex)) != 0;
	}
}

