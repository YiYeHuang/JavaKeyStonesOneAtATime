package leetcode.array;

/**
 * You are given an n x n 2D matrix representing an image.
 * 
 * Rotate the image by 90 degrees (clockwise).
 * 
 * Follow up: Could you do this in-place?
 */
public class rotateMatrix_amazon
{
    /**
     * The idea was firstly transpose the matrix and then flip it symmetrically.
     * 
     * 1 2 3 
     * 4 5 6 
     * 7 8 9 
     * 
     * to
     * 
     * 1 4 7 
     * 2 5 8 
     * 3 6 9 
     * 
     * Then flip the matrix horizontally.
     * 
     * 7 4 1 
     * 8 5 2 
     * 9 6 3
     */
    public void rotate(int[][] matrix)
    {
        for (int i = 0; i < matrix.length; i++)
        {
            for (int j = i; j < matrix[i].length; j++)
            {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }

        for (int i = 0; i < matrix.length; i++)
        {
            for (int j = 0; j < matrix.length / 2; j++)
            {
                int temp = 0;
                temp = matrix[i][j];
                matrix[i][j] = matrix[i][matrix.length - 1 - j];
                matrix[i][matrix.length - 1 - j] = temp;
            }
        }
    }
}
