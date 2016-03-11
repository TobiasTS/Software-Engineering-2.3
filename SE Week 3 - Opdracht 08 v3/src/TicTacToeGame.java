
import java.util.Random;
class TicTacToeGame
{
	public static final int HUMAN        = 0; 
	public static final int COMPUTER     = 1; 
	public  static final int EMPTY        = 2;

	public  static final int HUMAN_WIN    = 0;
	public  static final int DRAW         = 1;
	public  static final int UNCLEAR      = 2;
	public  static final int COMPUTER_WIN = 3;

	private int [ ] [ ] board = new int[ 3 ][ 3 ];
    private Random random=new Random();  
	private int side=random.nextInt(2);  
	private int position=UNCLEAR;
	private char computerChar,humanChar;

	// Constructor
	public TicTacToeGame( )
	{
		clearBoard( );
		initSide();
	}
	
	private void initSide()
	{
	    if (this.side==COMPUTER) { computerChar='X'; humanChar='O'; }
		else                     { computerChar='O'; humanChar='X'; }
    }
    
    public void setComputerPlays()
    {
        this.side=COMPUTER;
        initSide();
    }
    
    public void setHumanPlays()
    {
        this.side=HUMAN;
        initSide();
    }

	public boolean computerPlays()
	{
	    return side==COMPUTER;
	}

	public int chooseMove()
	{
	    Best best=chooseMove(COMPUTER);
	    return best.row*3+best.column;
	    //return 0;
    }
    
    // Find optimal move
	private Best chooseMove( int side )
	{
		int opp;              // The other side
		Best reply;           // Opponent's best reply
		int simpleEval;       // Result of an immediate evaluation
		int bestRow = 0;
		int bestColumn = 0;
		int value = 0;
		
		if(side == COMPUTER) {
			opp = HUMAN;
		}
		else {
			opp = COMPUTER;
		}

		if( ( simpleEval = positionValue( ) ) != UNCLEAR )
			return new Best( simpleEval );

		// TODO: implementeren m.b.v. recursie/backtracking
		
		if(isAWin(COMPUTER)) {
			return new Best(10);
		}
		else if(isAWin(HUMAN)) {
			return new Best(-10);
		}
		else if(positionValue() == DRAW) {
			if(side == COMPUTER) {
				return new Best(2);
			}
			else {
				return new Best(-2);
			}
		}
		else {
			if(side == COMPUTER) {
				value = -1000;
				for(int i = 0; i < 9; i++) {
					if(moveOk(i)) {
						place(i/3, i%3, side);
						int tempValue = chooseMove(opp).val;
						if(tempValue > value) {
							value = tempValue;
							bestRow = i/3;
							bestColumn = i%3;
						}
						place(i/3, i%3, EMPTY);
					}
				}
			}
			else {
				value = 1000;
				for(int i = 0; i < 9; i++) {
					if(moveOk(i)) {
						place(i/3, i%3, side);
						int tempValue = chooseMove(opp).val;
						if(tempValue < value) {
							value = tempValue;
							bestRow = i/3;
							bestColumn = i%3;
						}
						place(i/3, i%3, EMPTY);
					}
				}
			}
		}		
	    return new Best(value, bestRow, bestColumn);
    }

   
    //check if move ok
    public boolean moveOk(int move)
    {
    	return ( move>=0 && move <=8 && board[move/3 ][ move%3 ] == EMPTY );
 		//return true;
    }
    
    // play move
    public void playMove(int move)
    {
		board[move/3][ move%3] = this.side;
		if (side==COMPUTER) this.side=HUMAN;  else this.side=COMPUTER;
	}


	// Simple supporting routines
	public void clearBoard( )
	{
		//TODO:
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				board[i][j] = EMPTY;
			}
		}
	}


	private boolean boardIsFull( )
	{
		//TODO:
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				if(board[i][j] == EMPTY) {
					return false;
				}
			}
		}
		return true;
	}

	// Returns whether 'side' has won in this position
	public boolean isAWin( int side )
	{
	    //TODO:
		int currentPosition = positionValue();
		if(currentPosition == COMPUTER_WIN && side == COMPUTER 
				|| currentPosition == HUMAN_WIN && side == HUMAN) {
			return true;
		}
		return false;
		
	    //return true;
    }

	// Play a move, possibly clearing a square
	private void place( int row, int column, int piece )
	{
		board[ row ][ column ] = piece;
	}

	private boolean squareIsEmpty( int row, int column )
	{
		return board[ row ][ column ] == EMPTY;
	}

	// Compute static value of current position (win, draw, etc.)
	public int positionValue( )
	{
		// TODO:
		// Array's for each different way to win filled with empty Strings
		// 3 for column and row wins, 2 for diagonal wins
		String[] columns = new String[]{"", "", ""};
		String[] rows = new String[]{"", "", ""};
		String[] diagonals = new String[]{"", ""};
		
		// Fill the array's with the data from the board
		// Adds all values on the same column/ row/ diagonal to 1 String
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				columns[i] += board[j][i];
				rows[i] += board[i][j];
			}
			diagonals[0] += board[i][i];
			diagonals[1] += board[i][2-i];
		}
		
		// Check for column and row wins
		for (int i = 0; i < 3; i++) {
			if (rows[i].equals("111") || columns[i].equals("111")) {
				return COMPUTER_WIN;
			} 
			else if (rows[i].equals("000") || columns[i].equals("000")) {
				return HUMAN_WIN;
			}
		}
		
		// Check for diagonal wins
		for (int i = 0; i < 2; i++) {
			if (diagonals[i].equals("111")) {
				return COMPUTER_WIN;
			} 
			else if (diagonals[i].equals("000")) {
				return HUMAN_WIN;
			}
		}
		
		// Check for draw
		if(boardIsFull()) {
			return DRAW;
		}
		
		return UNCLEAR;
	}
	
	
	public String toString()
	{
	    //TODO:
		String s = "";
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				if(board[i][j] == HUMAN) {
					s = s + humanChar;
				}
				else if(board[i][j] == COMPUTER) {
					s = s + computerChar;
				}
				else {
					s = s + ".";
				}
			}
			s = s + "\n";
		}
		return s;
		//return "...\n...\n...\n";   
	}  
	
	public boolean gameOver()
	{
	    this.position=positionValue();
	    return this.position!=UNCLEAR;
    }
    
    public String winner()
    {
        if      (this.position==COMPUTER_WIN) return "computer";
        else if (this.position==HUMAN_WIN   ) return "human";
        else                                  return "nobody";
    }
    
	
	private class Best
    {
       int row;
       int column;
       int val;

       public Best( int v )
         { this( v, 0, 0 ); }
      
       public Best( int v, int r, int c )
        { val = v; row = r; column = c; }
    } 
	
	
}

