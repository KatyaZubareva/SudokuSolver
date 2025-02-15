public class SudokuSolver {

    private static final int GRID_SIZE = 9; // Размер сетки судоку всегда 9x9

    public static void main(String[] args) {
        int[][] sudokuBoard = {
                {4, 0, 9, 0, 2, 0, 0, 7, 1},
                {5, 0, 0, 7, 8, 0, 9, 6, 3},
                {0, 7, 0, 9, 0, 0, 0, 4, 8},
                {0, 0, 0, 0, 9, 0, 0, 0, 0},
                {0, 0, 5, 2, 1, 0, 0, 0, 4},
                {6, 0, 4, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 5, 0, 2, 8, 0, 0},
                {0, 6, 0, 0, 0, 0, 0, 5, 0},
                {9, 0, 0, 0, 4, 0, 0, 0, 7}
        };

        if (solveBoard(sudokuBoard)) {
            System.out.println("Solved successfully!");
        } else {
            System.out.println("Unsolvable board :(");
        }

        printBoard(sudokuBoard);
    }

    private static void printBoard(int[][] board) {
        for (int row = 0; row < GRID_SIZE; row++) {
            if (row % 3 == 0 && row != 0) {
                System.out.println("-----------");
            }
            for (int column = 0; column < GRID_SIZE; column++) {
                if (column % 3 == 0 && column != 0) {
                    System.out.print("|");
                }
                System.out.print(board[row][column]);
            }
            System.out.println();
        }
    }

    private static boolean isNumberInRow(int[][] board, int number, int row) {
        for (int i = 0; i < GRID_SIZE; i++) {
            if (board[row][i] == number) {
                return true;
            }
        }
        return false;
    }

    private static boolean isNumberInColumn(int[][] board, int number, int column) {
        for (int i = 0; i < GRID_SIZE; i++) {
            if (board[i][column] == number) {
                return true;
            }
        }
        return false;
    }

    private static boolean isNumberInBox(int[][] board, int number, int row, int column) {
        int localBoxRow = row - row % 3;
        int localBoxColumn = column - column % 3;

        for (int i = localBoxRow; i < localBoxRow + 3; i++) {
            for (int j = localBoxColumn; j < localBoxColumn + 3; j++) {
                if (board[i][j] == number) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean isValidPlacement(int[][] board, int number, int row, int column) {
        return !isNumberInRow(board, number, row) &&
                !isNumberInColumn(board, number, column) &&
                !isNumberInBox(board, number, row, column);
    }

    private static boolean solveBoard(int[][] board) {
        for (int row = 0; row < GRID_SIZE; row++) {
            for (int column = 0; column < GRID_SIZE; column++) {
                if (board[row][column] == 0) {
                    for (int numberToTry = 1; numberToTry <= GRID_SIZE; numberToTry++) {
                        if (isValidPlacement(board, numberToTry, row, column)) {
                            board[row][column] = numberToTry;

                            if (solveBoard(board)) {
                                return true;
                            } else {
                                board[row][column] = 0; // Backtracking
                            }
                        }
                    }
                    return false;
                }
            }
        }
        return true; // Доска решена
    }
}