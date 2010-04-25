package edu.utd.chess.board;


/**
 * Coordinates on a chess board.
 * This is just an encapsulation for the x,y 
 * positions on the board, e.g. A 3 means
 * column 1, row 3 (starting from the bottom left of the board).
 * Note that rows  and columns are <b>NOT zero-indexed</b> 
 * so row 1 is really row 1, column 1 is really column 1.
 * This corresponds to the way real chess boards are usually
 * demarcated.
 * <br/>
 * This structure is just a really simple pair of values that 
 * always stick together.
 * 
 * @author troy
 *
 */
public class ChessCoords {
	
	/*
	 * TODO : consider refactoring the public fields here
	 * a setColumn() method might be used to guarantee that
	 * the String is always in the proper capital case.
	 */
	
	public String column;
	public int row;
	
	/**
	 * Create a new ChessCoords object representing a location
	 * on the ChessBoard, e.g A1 is the bottom-right-most
	 * square on the ChessBoard.
	 * @param letter aka column
	 * @param number aka row
	 */
	public ChessCoords(String letter, int number) {
		column = letter.toUpperCase();	
		row = number;
	}
	
	/**
	 * Override Object.equals(), according to the general
	 * contract for overriding equals().  Ignores case on
	 * column letters, i.e. A1 == a1, H8 == h8, etc.
	 * @param other Object to compare this one to
	 * @return true if other is equivalent to this one
	 */
	public boolean equals(Object other) {
		if (other instanceof ChessCoords) {
			ChessCoords those = (ChessCoords) other;
			if ((those.row == this.row) 
					&& (those.column.toUpperCase().equals(this.column.toUpperCase()))) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Override the Object.hashCode() method, according
	 * to the general contract for overriding hashCode()
	 * @return int
	 */
	public int hashCode() {
		int result = 42;	// arbitrary value
		result = 37 * result + row;	// 37 is odd prime, used by convention
		result = 37 * result + (column == null ? 0 :column.toUpperCase().hashCode());
		return result;		
	}
	
	public String toString() {
		return "Chess Coords: COL: " + column + " ROW: " + row;
	}
	
}
