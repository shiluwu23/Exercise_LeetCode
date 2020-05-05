package DFS;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @ author Shilu
 * @ create 2020-05-05  18:08
 * @ TimeComplexity :  O(m * n)  the size of the the graph. In the worst case where it contains only the 'O' cells on the board,
 * we would traverse each cell twice: once during the DFS traversal and the other time during the cell reversion in the last step.
 * @ SpaceComplexity : O(m * n)  the size of the the graph, maximum depth of recursive calls would be m*n
 */
public class Surrounded_Regions {
    public int rows;
    public int cols;
    public int[][] dirs = new int[][]{{1,0}, {-1,0},{0,1},{0,-1}};

    public void solve(char[][] board) {
        if(board == null || board.length < 2 || board[0].length < 2) return;  //0行或0列,1行或1列，均算边界不需要flip

        rows = board.length;
        cols = board[0].length;

        //1. 寻找在边界上的‘O’, 如果O在边界上，则标记成特殊字符后续再变回来，turning O into *
        // 左、右边界
        for(int i = 0; i < rows; i++){
            if(board[i][0] == 'O')  // 某行第一个元素为O
                boundaryDFS(board, i, 0);
            if(board[i][cols - 1] == 'O')   // 某行最后一个元素为O
                boundaryDFS(board, i ,cols - 1);
        }

        //上、下边界
        for(int j = 0; j < cols; j++){
            if(board[0][j] == 'O')  // 某列第一个元素为O
                boundaryDFS(board, 0, j);
            if(board[rows - 1][j] == 'O')  // 某列最后一个元素为O
                boundaryDFS(board, rows - 1, j);
        }

        // 2. Post-prcessing, flipping remaining 'O' into 'X', '*' back to 'O', keep 'X' intact.
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < cols; j++){
                if(board[i][j] == 'O')
                    board[i][j] = 'X';
                else if(board[i][j] == '*')
                    board[i][j] = 'O';
            }
        }
    }

    // 该DFS操作的目的是将边界上的'O'转变为特殊字符*，这样操作会剩下被包围在内部的的‘O’
    public void boundaryDFS(char[][]board, int i, int j){
        // boundary check
        if(i >= rows || i < 0 || j >= cols || j < 0 || board[i][j] != 'O') return;  //to find O

        board[i][j] = '*';  // now 'O' on the border has been found, and turn 'O' into '*',

        for(int[] dir: dirs){
            boundaryDFS(board, i + dir[0], j + dir[1]);
        }
        // 经过DFS操作后，剩下的'O'就是被包围在里面的‘O’,后续会将其转变为‘X’
    }
}
