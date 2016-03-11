package cards;
import java.util.Arrays;
import java.util.Stack;
/** the solution is a sequence of cards placed on the board according to the card positions
    example without border
*/
public class Solution extends Stack<Candidate>
{
    // The board is an 2D array.
	// 0123
	// 0..-.
	// 1---.
	// 2.---
	// 3..-.
	private Candidate[][] board = new Candidate[4][4];
	
	// card positions on the board
	// the first card position on the board are
	// {0,2}, {1,0}. {1,1}
	private int[] row    = { 0, 1, 1, 1, 2, 2, 2, 3 };
	private int[] column = { 2, 0, 1, 2, 1, 2, 3, 2 };
	//  indices of adjacent cards in the solution.
	//                 0   1  2   3   4    5     6    7   
	int [] [] check = {{},{},{1},{0},{2},{3,4},{5,6},{7}};
	
	int [] [] check2 = {{-1,-1},{-1,-1},{1,-1},{0,-1},{2,-1},{3,4},{5,6},{7,-1}}; 	
	
	public Solution(){
	}

	
	 // Checks whether a candidate with card CardChar is in 
	 // an adjacent position of the board position (row, column)
	 // @param row, column, candidate
	 // @return Boolean indicating if cardChar is found.
	 // can be used in the methods fits and isCorrect
	private boolean bordersCard(int row, int column, char cardChar){
	    //TODO
		// above
		if(row-1 > 0) {
			if(board[row-1][column] != null) {
				if(board[row-1][column].getCardChar() == cardChar) {
					return true;
				}
			}
		}
		
		// left
		if(column-1 > 0) {
			if(board[row][column-1] != null) {
				if(board[row][column-1].getCardChar() == cardChar) {
					return true;
				}
			}
		}
		
		// below
		if(row+1 < 3) {
			if(board[row+1][column] != null) {
				if(board[row+1][column].getCardChar() == cardChar) {
					return true;
				}
			}
		}
		
		// right
		if(column+1 < 3) {
			if(board[row][column+1] != null) {
				if(board[row][column+1].getCardChar() == cardChar) {
					return true;
				}
			}
		}
		
		return false;
    }	
	
	/**
	 * Checks whether candidate card of same kind.
	 * Checks whether by placing candidate the solution sofar still complies with the rules
	 * @param candidate
	 * @return boolean indicating whether this candidate can be put in the
	 * next free position.
	 */
	public boolean fits(Candidate candidate){ 
		//TODO
		int index = this.size();
		
		if(bordersCard(row[index],column[index],candidate.getCardChar())) {
			return false;
		}
	    return true;
    }

	public void record(Candidate candidate)
	{
		int i=this.size(); // i= index in this stack of next for the next candidate
		board [row[i]] [column[i]] = candidate; //x=row, y=column
		this.push(candidate);
		
	}

	public boolean complete()
	{
		return this.size()==8;
	}

	public void show()
	{
		System.out.println("Solution: " + toString()); 
	}

	public Candidate eraseRecording()
	{
		int i=this.size()-1;           // i= index of the candidate that is removed from this Stack;
		board[row[i]][column[i]]=null; // remove candidate from board
		return this.pop();
    }
	
	// can be used in method isCorrect
    private char mustBeAdjacentTo(char card)
    {  
      if (card=='A') return 'K'; 
      if (card=='K') return 'Q'; 
      if (card=='Q') return 'J';
      return '?'; //error
    }
	
	/**
	 * Checks whether the rules below are fulfilled
	 * For the positions that can be checked for solution sofar.
	 * Rules:
	 * Elke aas (ace) grenst (horizontaal of verticaal) aan een heer (king).
	 * Elke heer grenst aan een vrouw (queen).
	 * Elke vrouw grenst aan een boer (jack).
	 * @return true if all checks are correct.
	 */
	// uses methods bordersCard and mustBeAdjacent to
	public boolean isCorrect() {
        //TODO
		System.out.println(toString());
		int index = this.size();
		
		for(int i = 0; i < index; i++) {
			int tempInt1 = check2[i][0];
			int tempInt2 = check2[i][1];
			System.out.println("Loop number: " + i);
			System.out.println("temp1 = " + tempInt1);
			System.out.println("temp2 = " + tempInt2);

			if(tempInt1 != -1 && board[row[tempInt1]][column[tempInt1]].getCardChar() != 'J') {
				if(!bordersCard(row[tempInt1],column[tempInt1],mustBeAdjacentTo(board[row[tempInt1]][column[tempInt1]].getCardChar()))) {
					System.out.println(board[row[tempInt1]][column[tempInt1]] + " must be adjacent to " + mustBeAdjacentTo(board[row[tempInt1]][column[tempInt1]].getCardChar()));
					System.out.println("false");
					return false;
				}
				else {
					System.out.println(board[row[tempInt1]][column[tempInt1]] + " must be adjacent to " + mustBeAdjacentTo(board[row[tempInt1]][column[tempInt1]].getCardChar()));
					System.out.println("true");
				}
			}
			if(tempInt2 != -1 && board[row[tempInt2]][column[tempInt2]].getCardChar() != 'J') {
				if(!bordersCard(row[tempInt2],column[tempInt2],mustBeAdjacentTo(board[row[tempInt2]][column[tempInt2]].getCardChar()))) {
					System.out.println(board[row[tempInt2]][column[tempInt2]] + " must be adjacent to " + mustBeAdjacentTo(board[row[tempInt2]][column[tempInt2]].getCardChar()));
					System.out.println("false");
					return false;
				}
				else {
					System.out.println(board[row[tempInt2]][column[tempInt2]] + " must be adjacent to " + mustBeAdjacentTo(board[row[tempInt2]][column[tempInt2]].getCardChar()));
					System.out.println("true");
				}
			}
		}
		
        return true;
     }     
            
	
	/**
	 * @return a representation of the solution on the board
	 */
     public String toString(){
    	 //TODO
    	 return Arrays.deepToString(board);
	}    

}
