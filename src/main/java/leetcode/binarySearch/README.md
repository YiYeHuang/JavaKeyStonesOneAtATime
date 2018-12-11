## Binary Search Notes

```java
    public static int indexOf(int[] a, int key)
    {
        int lo = 0;
        int hi = a.length - 1;
        while (lo <= hi)
        {
            // Key is in a[lo..hi] or not present.
            int mid = lo + (hi - lo) / 2; // do this to avoid overflow
            if (key < a[mid])
            {
                hi = mid - 1;
            }
            else if (key > a[mid])
            {

                lo = mid + 1;
            }
            else
            {
                return mid;
            }
        }
        return -1;
    }
```

Binary Search usage requirement is very hight.
- sorted list
- array

Use Binary Search
- Data size is large
- compare cost is high
- Target is mostly static, not too much write happens

Not to Use Binary Search
- Data size is too small
- Data size is too too large: require continuous memory 
