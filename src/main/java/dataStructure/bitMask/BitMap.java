package dataStructure.bitMask;

/**
 * Question: 给定一个最多包含40亿个随机排列的32位整数的顺序文件，找出一个不在文件中的32位整数(在文件中至少缺失这样一个数——为什么？)。在具有足够内存的情况下，如何解决该问题
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

