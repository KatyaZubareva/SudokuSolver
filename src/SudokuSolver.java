/**
 * SudokuSolver - класс для решения головоломки Судоку с использованием метода
 *    проб и откатов (backtracking).
 */
public class SudokuSolver {

    /** Размер сетки судоку (9x9). */
    private static final int GRID_SIZE = 9;

    /**
     * Основной метод для запуска программы.
     * @param args аргументы командной строки (не используются).
     */
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

    /**
     * Выводит текущее состояние судоку на консоль.
     * @param board игровое поле 9x9.
     */
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

    /**
     * Проверяет, присутствует ли число в указанной строке.
     * @param board игровое поле 9x9.
     * @param number проверяемое число.
     * @param row номер строки.
     * @return true, если число уже есть в строке, иначе false.
     */
    private static boolean isNumberInRow(int[][] board, int number, int row) {
        for (int i = 0; i < GRID_SIZE; i++) {
            if (board[row][i] == number) {
                return true;
            }
        }
        return false;
    }

    /**
     * Проверяет, присутствует ли число в указанном столбце.
     * @param board игровое поле 9x9.
     * @param number проверяемое число.
     * @param column номер столбца.
     * @return true, если число уже есть в столбце, иначе false.
     */
    private static boolean isNumberInColumn(int[][] board, int number, int column) {
        for (int i = 0; i < GRID_SIZE; i++) {
            if (board[i][column] == number) {
                return true;
            }
        }
        return false;
    }

    /**
     * Проверяет, присутствует ли число в 3x3-блоке.
     * @param board игровое поле 9x9.
     * @param number проверяемое число.
     * @param row номер строки.
     * @param column номер столбца.
     * @return true, если число уже есть в блоке, иначе false.
     */
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

    /**
     * Проверяет, возможно ли поставить число в указанную клетку без нарушения правил судоку.
     * @param board игровое поле 9x9.
     * @param number проверяемое число.
     * @param row номер строки.
     * @param column номер столбца.
     * @return true, если установка числа возможна, иначе false.
     */
    private static boolean isValidPlacement(int[][] board, int number, int row, int column) {
        return !isNumberInRow(board, number, row) &&
                !isNumberInColumn(board, number, column) &&
                !isNumberInBox(board, number, row, column);
    }

    /**
     * Решает судоку методом backtracking (проб и откатов).
     * @param board игровое поле 9x9.
     * @return true, если судоку можно решить, иначе false.
     */
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
                                board[row][column] = 0; // Откат
                            }
                        }
                    }
                    return false;
                }
            }
        }
        return true; // Судоку решено
    }
}
