## Hash Leetcode Notes

### Type 1: hash letter or number to int[]
used to do letter counting map or compare two pair of words

#### 500. Keyboard Row
- hard code row "QWERTYUIOP" "ASDFGHJKL" "ZXCVBNM"
- code row number in a int[26] with row 1, 2, 3
- go through the word list, mark the letter 1 as the checking number
- if any on the other row, don't added

#### 389. Find the Difference: return the diff char
- go through the word one and mark frequency in int[26]
- go through the word two and mark down the frequency in int[26]
- go through the list again and find the diff

#### 242. Valid Anagram: s = "anagram", t = "nagaram"
- go through the word one and mark frequency in int[26]
- go through the word two and mark down the frequency in int[26]
- go through the list again and find if there is a diff

#### 266. Palindrome Permutation: find if a word itself can form a Palindrome
- this question both lower case and higher case all acceptable
- trick is to find if the number of distinct letter is more than 1
- go through word and mark work in both lowercase int[26] and upper case int[26]
- count the lowercase int[26] and upper case int[26], if number odd is more than 2, false.

#### 268. Missing Number
- create int[] length of number length + 1, filled with -1
- push the number in 
- find the -1

#### 409. Longest Palindrome
- similar to  266, count the number of even number
- if length same to string length, return, else, means it is shorter than string, 
- plus 1 and return

