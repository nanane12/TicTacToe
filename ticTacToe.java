import java.util.Scanner;
import static java.lang.System.out;

public class ticTacToe
{
	static char PLACEHOLDER = '*';
	static Scanner keyboard = new Scanner(System.in);

	public static void drawBoard(char[][] board)
	{
		out.println();
		for (int row=0; row<3; row++){
			out.println(board[row][0]+" | "+board[row][1]+ " | "
					+ board[row][2]);
		}


	}

	public static char[][] createEmptyBoard()
	{
		char[][] gameBoard = new char[3][3];
		for (int row = 0; row < 3; row++)
		{
			for (int col = 0; col < 3; col++)
			{
				gameBoard[row][col] = PLACEHOLDER;
			}
		}
		return gameBoard;
	}
	
	public static boolean isWin(char[][] board)
	{
		return isHorizontalWin(board) || isVerticalWin(board) || isDiagonalWin(board);
	}
	public static boolean isFull(char[][] board) 
	{
		for (int row=0; row<3; row++)
		{
			for (int col=0; col<3; col++){
			
				if (board[row][col] ==PLACEHOLDER){
					return false;
				}
			}
		}
		return true;

	}

	public static boolean isHorizontalWin(char[][] board)
	{
		for (int row = 0; row < 3; row++)
		{
			if (board[row][0] != PLACEHOLDER && board[row][0] == board[row][1]
					&& board[row][1] == board[row][2])
			{
				return true;
			}
		}
		return false;
	}

	public static boolean isVerticalWin(char[][] board)
	{
		for (int col=0; col<3; col++)
		{
			if (board[0][col]!=PLACEHOLDER && board[0][col]==board[1][col]
					&& board[1][col]==board[2][col])
			{
				return true;
			}
		}
		return false;

	}

	public static boolean isDiagonalWin(char[][] board)
	{
	
			if ((board[0][0] !=PLACEHOLDER && board[0][0]==board[1][1]
		&& board [1][1] == board [2][2])||(board[0][2] !=PLACEHOLDER
		&& board[0][2]==board[1][1] &&board[1][1] ==board[2][0]))
			{
				return true;
			}
	return false;
	}

	public static void printWelcome()
	{
		System.out.println("Welcome to Tic Tac Toe!");
		System.out.println("To play, enter a number for which box to play in.");
		System.out.println("1 2 3");
		System.out.println("4 5 6");
		System.out.println("7 8 9");
		System.out.println("You'll need a buddy to play with.  Ready to begin?  Here we go.");
	}

	public static boolean wantsToPlayAgain()
	{
		System.out.println("Would you like to play again? y/n");
		String play=keyboard.next();
		if (play.equals("n"))
		{
		return false;
		}
		return true;

	}
	
	public static char getTokenAtPosition(int position, char[][] board)
	{
		int row = (position - 1) / 3;
		int col = (position - 1) % 3;
		return board[row][col];
	}
	public static void placeToken(int position, char[][] board, boolean isXTurn)
	{
		int row=(position-1)/3;
		int col=(position-1)%3;
		if (isXTurn)
		{
			board[row][col]= 'X';
		}
		else
		{
			board[row][col]='O';
		}
}

	public static void getPositionAndPlaceToken(char[][] board, boolean isXTurn)
	{
		boolean invalidInput = true;
		boolean full = true;
		int position = 0;

		do {
			
			if (isXTurn)
			{
				out.println("Where would you like to place X ?");
				
			}
			else
			{
				out.println("Where would you like to place O ?");
				
			}
			position=keyboard.nextInt();
			invalidInput=(1>position || 9<position);
			if (invalidInput)
			{	
				out.println("The position, "+ position +" is invalid entry.");
	
			}
			
			else
			{
				full=(getTokenAtPosition(position,board)!=PLACEHOLDER);
				if (full){	
				out.println("The position, " + position + " ,is already taken. ");	
				}
			}

		} while (full || invalidInput);

		placeToken(position, board, isXTurn);
	}

	public static void game()
	{
		int xWins = 0;
		int oWins = 0;
		int draws = 0;
		boolean doesXStart = true;
		boolean isXTurn = doesXStart;

		printWelcome();
		
		do // all-games loop
		{
			// initialize for the beginning of a new game.
			char[][] gameBoard = createEmptyBoard();
			isXTurn = doesXStart;
			doesXStart = ! doesXStart;
			boolean gameStillGoing = true;
			drawBoard(gameBoard);

			// play the game until it's complete.  single-game loop.
			do {
				getPositionAndPlaceToken(gameBoard, isXTurn);
			    drawBoard(gameBoard);
				if (isWin(gameBoard)) {
					gameStillGoing = false;
					if (isXTurn) {
						// X made the most recent play, so they must have won that round.
						xWins++;
						System.out.println("X wins!");
					} else {
						// O made the most recent play, so they must have won that round.
						oWins++;
						System.out.println("O wins!");
					}
				} else if (isFull(gameBoard)) {
					gameStillGoing = false;
					// Nobody won, but the board is full - must be a draw.
					draws++;
					System.out.println("The game is a draw.  Nobody wins.");
				} else {
					// board is not full, so continue to next player's turn.
				}
				isXTurn = ! isXTurn;
			} while (gameStillGoing);
			System.out.println("Score: X=" + xWins + ", O=" + oWins + ", draws=" + draws);
			
		} while (wantsToPlayAgain());

		// They're done playing.  Method will return and program will finish.
	}

	public static void main(String[] args)
	{
		game();
		System.out.println("Goodbye!");
	}

}
