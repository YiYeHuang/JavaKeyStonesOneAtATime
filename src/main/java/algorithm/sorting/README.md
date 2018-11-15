# Sorting
Study notes on soring and analysis on real world case

## Algorithm
- Insertion Sort
- Selection Sort
- Bubble Sort
- Quick Sort
- Bucket Sort
- Counting Sort
- Radix Sort

##Key factor
-  Time complexity: 
    - best case
    - worst case
    - average case
-  Space complexity: in place vs extra structure
-  Stability: very important for real world case. The practice example are usually based on integer, real world object are 
way more complicate, if a list of orders are sort by incoming timestamp, it won't be very nice that the most expensive order
within the section are processed first. Stability sort comes in handy in this case.  

## Compare                                     
| Type                 | Best | Worst   | Average   |
| -----------------------| :------------- | :------------- |:------------- |
| Insertion Sort    | O(n)| O(n^2) | O(n^2)|
| Selection Sort   | O(n^2)| O(n^2) | O(n^2)|
| Bubble Sort   | O(n)| O(n^2) | O(n^2)|
| Quick Sort   | |
| Bucket Sort   | |
| Counting Sort   | |
| Radix Sort   | |
