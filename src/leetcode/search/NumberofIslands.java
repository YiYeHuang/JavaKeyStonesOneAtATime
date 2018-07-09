package leetcode.search;

import Tag.company.*;
import Tag.type.BFS;
import Tag.type.DFS;

/**
 * Given a 2d grid map of '1's (land) and '0's (water), count the number of islands. An island is
 * surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You
 * may assume all four edges of the grid are all surrounded by water.
 * 
 * 
 * 
 * Example 1:
 * 
 * 11110
 * 11010
 * 11000
 * 00000
 * 
 * Answer: 1
 * 
 * 
 * clearRestOfLand solution: 
 * 
 * to under stand that, water == land once notice that there is a land
 * connected, just clean it out to be "water" once reach water, stop
 */

@Amazon
@Facebook
@Microsoft
@Google
@Zenefit
public class NumberofIslands
{

    @BFS
    @DFS
    public static int numIslands(char[][] grid)
    {
        int count = 0;

        for (int i = 0; i < grid.length; i++)
        {
            for (int j = 0; j < grid[i].length; j++)
            {
                if (grid[i][j] == '1')
                {
                    count++;
                    clearRestOfLand(grid, i, j);
                }
            }
        }
        return count;
    }

    private static void clearRestOfLand(char[][] grid, int i, int j)
    {
        // Cleans up the surroundings, act as search and "find" adjacent landing
        if (i < 0 || j < 0 || i >= grid.length || j >= grid[i].length || grid[i][j] == '0')
            return;

        grid[i][j] = '0';
        clearRestOfLand(grid, i + 1, j);
        clearRestOfLand(grid, i - 1, j);
        clearRestOfLand(grid, i, j + 1);
        clearRestOfLand(grid, i, j - 1);
        return;
    }

    public static void main(String[] args)
    {
        char[][] grid =
        {
                {
                        '1', '1', '1', '1', '0' },
                {
                        '1', '1', '1', '0', '0' },
                {
                        '1', '1', '1', '0', '0' },
                {
                        '1', '1', '1', '0', '0' }, };
        Integer a = numIslands(grid);
        a.toString();
    }
}
