package leetcode.dfs;

import leetcode.tag.level.Medium;
import leetcode.tag.type.BackTrack;
import leetcode.tag.type.DFS;

/*
79. Word Search

Given a 2D board and a word, find if the word exists in the grid.

The word can be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring. The same letter cell may not be used more than once.

Example:

board =
[
  ['A','B','C','E'],
  ['S','F','C','S'],
  ['A','D','E','E']
]

Given word = "ABCCED", return true.
Given word = "SEE", return true.
Given word = "ABCB", return false.
 */

@Medium
@DFS
@BackTrack
public class WordSearch {


    public boolean exist(char[][] board, String word) {

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (search(board, word, 0,  i, j )) {
                    return true;
                }
            }
        }

        return false;
    }

    private boolean search(char[][] board, String word, int currentIndex, int i, int j) {

        if (currentIndex == word.length()) return true;

        if (i < 0 || j < 0 || i == board.length || j == board[i].length || word.charAt(currentIndex) != board[i][j]) {
            return false;
        }


        // set this board position to seen.
        // so cache the current value and reset for the other runs
        char tmp = board[i][j];
        board[i][j] = '0';

        if(     search(board, word, currentIndex + 1, i + 1, j) ||
                search(board, word, currentIndex + 1, i - 1, j) ||
                search(board, word, currentIndex + 1, i, j + 1) ||
                search(board, word, currentIndex + 1 , i, j - 1)) {
            return true;
        }
        board[i][j] = tmp;

        return false;
    }
}
