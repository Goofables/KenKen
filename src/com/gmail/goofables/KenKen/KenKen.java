package com.gmail.goofables.KenKen;
/*
 * Created on 2/15/18 at 10:19 PM by Gaelin Shupe
 * 
 * Created in KenKen (com.gmail.goofables.KenKen)
 * 
 */

class KenKen {
    
    int[][] board;
    private int size;
    
    KenKen(int size) {
        assert size > 0;
        this.size = size;
        fill();
        randomize();
    }
    
    void fill() {
        board = new int[size][size];
        for (int c = 0; c < size; c++) {
            for (int r = 0; r < size; r++) {
                board[c][r] = c + r + 1;
                while (board[c][r] > size)
                    board[c][r] -= size;
            }
        }
    }
    
    private void randomize() {
        
        for (int i = 0; i < 2 * size; i++) {
            int i1 = (int) (Math.random() * size);
            int i2 = (int) (Math.random() * size);
            int[] tmp = board[i1];
            board[i1] = board[i2];
            board[i2] = tmp;
        }
        
        for (int i = 0; i < 2 * size; i++) {
            int i1 = (int) (Math.random() * size);
            int i2 = (int) (Math.random() * size);
            for (int c = 0; c < size; c++) {
                int tmp = board[c][i1];
                board[c][i1] = board[c][i2];
                board[c][i2] = tmp;
            }
        }
        
        /*int add = (int) (Math.random() * size);
        for (int r = 0; r < size; r++) {
            for (int c = 0; c < size; c++) {
                board[r][c] += add;
                while (board[r][c] > size) {
                    board[r][c] -= size;
                }
            }
        }*/
    }
    
    private boolean valid(int row, int col) {
        for (int r = 0; r < size; r++)
            if (board[r][col] == board[row][col] && r != row) return false;
        
        for (int c = 0; c < size; c++)
            if (board[row][c] == board[row][col] && c != col) return false;
        
        return (board[row][col] != 0);
    }
    
    void print() {
        for (int r = 0; r < size; r++) {
            for (int c = 0; c < size; c++) {
                System.out.print(board[r][c] + "  ");
            }
            System.out.println();
        }
    }
}
