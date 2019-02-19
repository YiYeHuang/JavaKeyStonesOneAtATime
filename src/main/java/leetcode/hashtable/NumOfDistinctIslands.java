package leetcode.hashtable;

import leetcode.tag.level.Medium;
import leetcode.tag.type.DFS;
import leetcode.tag.type.HashTableTag;

import java.util.HashSet;
import java.util.Set;

/**

 694. Number of Distinct Islands

 Given a non-empty 2D array grid of 0's and 1's, an island is a group of 1's (representing land)
 connected 4-directionally (horizontal or vertical.) You may assume all four edges of the grid are surrounded by water.

 Count the number of distinct islands. An island is considered to be the same as another if and only if one island
 can be translated (and not rotated or reflected) to equal the other.

 Example 1:
 11000
 11000
 00011
 00011
 Given the above grid map, return 1.
 Example 2:
 11011
 10000
 00001
 11011
 Given the above grid map, return 3.

 Notice that:
 11
 1
 and
 1
 11
 are considered different island shapes, because we do not consider reflection / rotation.
 Note: The length of each dimension in the given grid does not exceed 50.
 */

@Medium
@HashTableTag
@DFS
public class NumOfDistinctIslands {

	public int numDistinctIslands(int[][] grid)
	{
		int count = 0;

		Set<String> maps = new HashSet<>();
		for (int i = 0; i < grid.length; i++)
		{
			for (int j = 0; j < grid[i].length; j++)
			{
				if (grid[i][j] == 1)
				{
					StringBuilder result = new StringBuilder();
					clearRestOfLand(grid, i, j, result, 'o');
					if (!maps.contains(result.toString())) {
						count++;
						maps.add(result.toString());
					}
				}
			}
		}
		return count;
	}

	private void clearRestOfLand(int[][] grid, int i, int j, StringBuilder sb, char chara)
	{
		// Cleans up the surroundings, act as search and "find" adjacent landing
		if (i < 0 || j < 0 || i >= grid.length || j >= grid[i].length || grid[i][j] == 0)
			return;

		sb.append(chara);
		grid[i][j] = 0;
		// clean until it hits 0 or boundary
		clearRestOfLand(grid, i + 1, j, sb,'r');
		clearRestOfLand(grid, i - 1, j, sb,'l');
		clearRestOfLand(grid, i, j + 1, sb, 'u');
		clearRestOfLand(grid, i, j - 1, sb, 'd');

		// indicate the map ended
		sb.append("b");
		return;
	}
}
