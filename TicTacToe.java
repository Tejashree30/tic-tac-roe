import java.util.Scanner;

public class TicTacToe {
    private static final char EMPTY = ' ';
    private static final int SIZE = 3;
    private static char[][] board = new char[SIZE][SIZE];
    private static Scanner scanner = new Scanner(System.in);
    private static int player1Wins = 0, player2Wins = 0, draws = 0;

    public static void main(String[] args) {
        System.out.println("🎮 Welcome to Tic-Tac-Toe Game (Cognifyz Advanced Project)");

        boolean playAgain = true;
        while (playAgain) {
            initializeBoard();
            playGame();
            displayBoard();
            checkWinnerAndDisplay();
            playAgain = askReplay();
        }

        showScoreboard();
        System.out.println("Thanks for playing! 👋");
    }

    private static void initializeBoard() {
        for (int i = 0; i < SIZE; i++)
            for (int j = 0; j < SIZE; j++)
                board[i][j] = EMPTY;
    }

    private static void displayBoard() {
        System.out.println("\nCurrent Board:");
        for (int i = 0; i < SIZE; i++) {
            System.out.print(" ");
            for (int j = 0; j < SIZE; j++) {
                System.out.print(board[i][j]);
                if (j < SIZE - 1) System.out.print(" | ");
            }
            System.out.println();
            if (i < SIZE - 1)
                System.out.println("---+---+---");
        }
    }

    private static void playGame() {
        char currentPlayer = 'X';
        int moves = 0;

        while (moves < SIZE * SIZE) {
            displayBoard();
            System.out.println("Player " + (currentPlayer == 'X' ? "1" : "2") + " (" + currentPlayer + ") turn:");
            int row = getMove("Row (1-3):") - 1;
            int col = getMove("Column (1-3):") - 1;

            if (isValidMove(row, col)) {
                board[row][col] = currentPlayer;
                moves++;
                if (checkWin(currentPlayer)) {
                    if (currentPlayer == 'X') player1Wins++;
                    else player2Wins++;
                    return;
                }
                currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
            } else {
                System.out.println("Invalid move! Try again.");
            }
        }
        draws++;
    }

    private static int getMove(String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextInt()) {
            System.out.print("Enter a number between 1 and 3: ");
            scanner.next();
        }
        return scanner.nextInt();
    }

    private static boolean isValidMove(int row, int col) {
        return row >= 0 && row < SIZE && col >= 0 && col < SIZE && board[row][col] == EMPTY;
    }

    private static boolean checkWin(char player) {
        // Check rows, columns, and diagonals
        for (int i = 0; i < SIZE; i++)
            if ((board[i][0] == player && board[i][1] == player && board[i][2] == player) ||
                (board[0][i] == player && board[1][i] == player && board[2][i] == player))
                return true;

        if ((board[0][0] == player && board[1][1] == player && board[2][2] == player) ||
            (board[0][2] == player && board[1][1] == player && board[2][0] == player))
            return true;

        return false;
    }

    private static void checkWinnerAndDisplay() {
        if (checkWin('X')) {
            System.out.println("🎉 Player 1 (X) Wins!");
        } else if (checkWin('O')) {
            System.out.println("🎉 Player 2 (O) Wins!");
        } else {
            System.out.println("🤝 It's a Draw!");
        }
    }

    private static boolean askReplay() {
        System.out.print("\nDo you want to play another round? (yes/no): ");
        String response = scanner.next().toLowerCase();
        return response.startsWith("y");
    }

    private static void showScoreboard() {
        System.out.println("\n📊 Final Scoreboard:");
        System.out.println("Player 1 Wins: " + player1Wins);
        System.out.println("Player 2 Wins: " + player2Wins);
        System.out.println("Draws: " + draws);
    }
}
