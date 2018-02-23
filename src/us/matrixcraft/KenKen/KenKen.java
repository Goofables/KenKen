package us.matrixcraft.KenKen;


class KenKen {
    int[][] board;
    private int size;

    KenKen(int size) {
        assert size > 0;
        this.size = size;
        generate();
        randomize();
        print();
    }

    void generate() {
        board = new int[size][size];
        for (int c = 0; c < size; c++) {
            for (int r = 0; r < size; r++) {
                board[c][r] = c + r + 1;
                while (board[c][r] > size)
                    board[c][r] -= size;
            }
        }
    }

    void randomize() {
        for (int i = 0; i < size * 2; i++) {
            int c1 = (int) (Math.random() * size);
            int c2 = (int) (Math.random() * size);
            int[] tmp = board[c1];
            board[c1] = board[c2];
            board[c2] = tmp;
        }

        for (int i = 0; i < size * 2; i++) {
            int r1 = (int) (Math.random() * size);
            int r2 = (int) (Math.random() * size);
            for (int c = 0; c < size; c++) {
                int tmp = board[c][r1];
                board[c][r1] = board[c][r2];
                board[c][r2] = tmp;
            }
        }
    }

    void print() {
        for (int c = 0; c < size; c++) {
            for (int r = 0; r < size; r++) {
                System.out.print(board[c][r] + "  ");
            }
            System.out.println();
        }
    }
}


