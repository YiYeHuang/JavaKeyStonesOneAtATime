package leetcode.hashtable;

import leetcode.tag.company.Amazon;
import leetcode.tag.company.Google;
import leetcode.tag.company.Microsoft;
import leetcode.tag.level.Medium;
import leetcode.tag.type.Hash;
import leetcode.tag.type.HashTableTag;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * 535. Encode and Decode TinyURL


 Note: This is a companion problem to the System Design problem: Design TinyURL.
 TinyURL is a URL shortening service where you enter a URL such as https://leetcode.com/problems/design-tinyurl and
 it returns a short URL such as http://tinyurl.com/4e9iAk.

 Design the encode and decode methods for the TinyURL service. There is no restriction on how your
 encode/decode algorithm should work. You just need to ensure that a URL can be encoded to a tiny URL and the tiny URL can be decoded to the original URL.
 */
@Amazon
@Microsoft
@Google

@Medium
@HashTableTag
@Hash
public class EncodeDecodeTinyURL {

	Map<Integer, String> map = new HashMap<>();
	Random r=new Random();
	int key=r.nextInt(10000);

	/**
	 * key: external memory to store key and actual value;
	 */
	public String encode(String longUrl) {
		while(map.containsKey(key))
			key= r.nextInt(10000);
		map.put(key,longUrl);
		return "http://tinyurl.com/"+key;
	}
	public String decode(String shortUrl) {
		return map.get(Integer.parseInt(shortUrl.replace("http://tinyurl.com/", "")));
	}

	/**
	 * ===========hashcode=====================
	 */
	public String encodeHASH(String longUrl) {
		map.put(longUrl.hashCode(),longUrl);
		return "http://tinyurl.com/"+longUrl.hashCode();
	}
	public String decodeHASH(String shortUrl) {
		return map.get(Integer.parseInt(shortUrl.replace("http://tinyurl.com/", "")));
	}
}
