package leetcode.trie;

import java.util.ArrayList;
import java.util.List;
import leetcode.tag.level.Hard;
import leetcode.tag.type.DFS;
import leetcode.tag.type.Trie;

/*
212. Word Search II

Given a 2D board and a list of words from the dictionary, find all words in the board.

Each word must be constructed from letters of sequentially adjacent cell,
where "adjacent" cells are those horizontally or vertically neighboring.
The same letter cell may not be used more than once in a word.



Example:

Input:
board = [
  ['o','a','a','n'],
  ['e','t','a','e'],
  ['i','h','k','r'],
  ['i','f','l','v']
]
words = ["oath","pea","eat","rain"]

Output: ["eat","oath"]


Note:

All inputs are consist of lowercase letters a-z.
The values of words are distinct.
 */
@Hard
@Trie
@DFS
public class WordSearchII {

  public class TrieNode {
    TrieNode[] next = new TrieNode[26];
    String word;
  }

  public List<String> findWords(char[][] board, String[] words) {

    TrieNode root = new TrieNode();
    for (String w : words) {
      TrieNode p = root;
      for (char c : w.toCharArray()) {
        int i = c - 'a';
        if (p.next[i] == null) p.next[i] = new TrieNode();
        p = p.next[i];
      }
      p.word = w;
    }

    List<String> result = new ArrayList<>();

    for (int i = 0; i < board.length; i++) {
      for (int j = 0; j < board[i].length; j++) {
        search(board, root, i, j, result);
      }
    }

    return result;
  }

  private void search(char[][] board, TrieNode curr, int i, int j, List<String> result) {

    if (i < 0 || j < 0 || i == board.length || j == board[i].length) return;
    char c = board[i][j];
    if (c == '0' || curr.next[c - 'a'] == null) return;


    curr = curr.next[c - 'a'];
    if (curr.word != null) {   // found one
      result.add(curr.word);
      curr.word = null;
    }

    // set this board position to seen.
    // so cache the current value and reset for the other runs
    board[i][j] = '0';

    search(board, curr, i - 1, j, result);
    search(board, curr, i + 1, j, result);
    search(board, curr, i, j + 1, result);
    search(board, curr, i, j - 1, result);

    board[i][j] = c;
  }

  public static void main(String[] args) {
    char[][] test = {
        {'o','a','a','n'},
        {'e','t','a','e'},
        {'i','h','k','r'},
        {'i','f','l','v'}
    };

    String[] words = {"oath","pea","eat","rain"};

    WordSearchII ws = new WordSearchII();

    ws.findWords(test, words);

  }

}
