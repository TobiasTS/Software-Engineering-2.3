
import junit.framework.TestCase;

/**
 * TestCase for the TicTacToe functions
 * @author Tobias Schlichter
 * @version 1.0
 */
public class TicTacToeTest extends TestCase {

	private TicTacToeGame game;

	public TicTacToeTest(String arg0) {
		super(arg0);
		game = new TicTacToeGame();
	}
	
	/**
	 * Case to test the isAWin method
	 */
	public void testPositionValue() {
		game.setComputerPlays();
		
		// Create a game where the first player wins
		game.playMove(0);
		game.playMove(3);
		game.playMove(2);
		game.playMove(1);
		game.playMove(4);
		game.playMove(6);
		game.playMove(8); 
		
		
		// Testing positionValue() method and clear the board
		assertEquals(game.COMPUTER_WIN, game.positionValue());
		game.clearBoard();
	}
	
	/**
	 * Case to test the isAWin method
	 */
	public void testIsAWin() {
		game.setComputerPlays();
		
		// Create a game where the computer wins
		game.playMove(0);
		game.playMove(3);
		game.playMove(2);
		game.playMove(1);
		game.playMove(4);
		game.playMove(6);
		game.playMove(8); 
		
		
		// Testing isAWin() method and clear the board	
		assertTrue(game.isAWin(TicTacToeGame.COMPUTER));
		assertFalse(game.isAWin(TicTacToeGame.HUMAN));
		game.clearBoard();
	}
	
	/**
	 * Case to test the chooseMove method
	 */
	public void testChooseMove() {
		game.setComputerPlays();
		
		game.playMove(0);
		game.playMove(1);
		game.playMove(6);
		game.playMove(3);
		
		// Test if chooseMove chooses 4
		assertEquals(4, game.chooseMove());
	}
	
}
