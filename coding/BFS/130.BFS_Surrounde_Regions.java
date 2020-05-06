package BFS;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @ author Shilu
 * @ create 2020-05-05  18:17
 * @ TimeComplexity :    O(m * n)
 * @ SpaceComplexity :   O(m * n) Within each invocation of BFS() function, we use a queue data structure to hold the cells to be visited.
 * We then need to estimate the upper bound on the size of the queue. Any given moment the queue would contain no more than two layers of cells,
 * which in the worst case might cover almost all cells in the board.
 */
public class BFS_Surrounde_Regions {
    public int rows;
    public int cols;
    public int[][] dirs = new int[][]{{1,0}, {-1,0},{0,1},{0,-1}};

    public void solve(char[][] board) {
        if(board == null || board.length < 2 || board[0].length < 2) return;

        rows = board.length;
        cols = board[0].length;

        // 1. find the 'O' on the border, and turn it into *
        for(int i = 0; i < rows; i++){
            if(board[i][0] == 'O')  bfs(board, i, 0);
            if(board[i][cols - 1] == 'O') bfs(board, i, cols - 1);
        }
        for(int j = 0; j < cols; j++){
            if(board[0][j] == 'O') bfs(board, 0, j);
            if(board[rows - 1][j] == 'O') bfs(board, rows - 1, j);
        }

        // 2. flipping remaining O into X, * to O
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < cols; j++){
                if(board[i][j] == 'O')  board[i][j] = 'X';
                if(board[i][j] == '*')  board[i][j] = 'O';
            }
        }
    }

    public void bfs(char[][] board, int i, int j){

        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{i, j});
        board[i][j] = '*';
        
        while(!queue.isEmpty()){
            int[] curr = queue.poll();

            for (int[] dir : dirs) {
                int r = curr[0] + dir[0];
                int c = curr[1] + dir[1];
                if(r  < 0 || r >= rows || c < 0 || c >= cols || board[r][c] != 'O') continue;
                  queue.offer(new int[] { r, c});
                  board[r][c] = '*';   
            }
        }
//         while(!queue.isEmpty()){
//             int[] curr = queue.poll();
//             int r = curr[0];
//             int c = curr[1];
//             board[r][c] = '*';
//             // if(r < 0 || r >= rows || c < 0 || c >= cols || board[r][c] != 'O') continue;
//             if(r > 0 && board[r - 1][c] == 'O') {
//                 board[r - 1][c] = '*';
//                 queue.offer(new int[]{r-1, c});
//             }
//             if(r < rows - 1 && board[r + 1][c] == 'O'){
//                 board[r + 1][c] = '*';
//                 queue.offer(new int[]{r+1, c});
//             }
//             if(c > 0 && board[r][c - 1] == 'O'){
//                 board[r][c - 1] = '*';
//                 queue.offer(new int[]{r, c - 1});
//             }
//             if(c < cols - 1 && board[r][c + 1] == 'O'){
//                 board[r][c + 1] = '*';
//                 queue.offer(new int[]{r, c + 1});
//             }
//         }
    }
}
