## BackTrack LeetCode Notes

The purpose of backtrack solution is to find all solution, with the nature of go back
and check, backtrack solutions are usually implemented by recursion. Usually not to find optimized solution

### All possible answer with output, recursion solution
```java
    public static List<List<Integer>> allAnswer(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        backtrack(list, new ArrayList<Integer>(), nums);
        return list;
    }

    // most basic version
    public static void backtrack1(List<List<Integer>> result, List<Integer> temp, int[] nums) {
        /* no ending logic meaning to get all possible result*/
        if (/* ending logic here */) {
            // make sure add new collection
            result.add(new ArrayList<>(temp));
            return;
        } else {
            // goes through all the result list all the time
            for (int i = 0; i < nums.length; i++) {
                if (/* jump or skipping logic here*/) {
                    continue;
                }
                temp.add(nums[i]);
                backtrack(result, temp, nums);
                // removing last result to keep the building continue
                temp.remove(temp.size() - 1);
            }
        }
    }
    
    // with help of starting index to better skip logic
    public void backtrack2(List<List<Integer>> result,List<Integer> temp, int[] candidates, int remains ,int startIndex) {
        if (/* ending logic here */) result.add(new ArrayList<>(temp));
        
        for (int i = startIndex; i < candidates.length; i++) {
            temp.add(candidates[i]);
            // move position up 1
            backtrack(result, temp, candidates, remains - candidates[i],  i);
            // removing last result to keep the building continue
            temp.remove(temp.size() - 1);
        }
    }
```

