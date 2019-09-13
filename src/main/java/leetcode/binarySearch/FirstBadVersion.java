package leetcode.binarySearch;

import leetcode.tag.level.Easy;
import leetcode.tag.type.BinarySearch;

/**
 * You are a product manager and currently leading a team to develop a new product. Unfortunately, the latest version of your product fails the quality check. Since each version is developed based on the previous version, all the versions after a bad version are also bad.
 *
 * Suppose you have n versions [1, 2, ..., n] and you want to find out the first bad one, which causes all the following ones to be bad.
 *
 * You are given an API bool isBadVersion(version) which will return whether version is bad. Implement a function to find the first bad version. You should minimize the number of calls to the API.
 *
 * Example:
 *
 * Given n = 5, and version = 4 is the first bad version.
 *
 * call isBadVersion(3) -> false
 * call isBadVersion(5) -> true
 * call isBadVersion(4) -> true
 *
 * Then 4 is the first bad version.
 */

@Easy
@BinarySearch
public class FirstBadVersion {

    /**
     * G G G G B B B B B B
     */
    public int firstBadVersion(int n) {

        int low = 0, high = n;
        while(low < high){
            // Prevent (left + right) overflow
            int mid = low + (high - low) / 2;

            if(isBadVersion(mid)) {
                // high = mid -1 might cause skip a version check
                high = mid;
            } else {
                // low = mid + 1 is fine, mid + 1 might be the fist bad version
                low = mid + 1;
            }
        }

        if(!isBadVersion(low))
            return low + 1;
        else
            return low;
    }

    public boolean isBadVersion(int input) {
        return true;
    }
}
