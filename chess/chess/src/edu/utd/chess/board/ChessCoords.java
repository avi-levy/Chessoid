package edu.utd.chess.board;


/**
 * Coordinates on a chess board.
 * This is just an encapsulation for the x,y 
 * positions on the board, e.g. A 3 means
 * column 1, row 3 (starting from the bottom of the board).
 * Note that rows  and columns are <b>NOT zero-indexed</b> 
 * so row 1 is really row 1, column 1 is really column 1.
 * 
 * Self doubt: is this *too* object-oriented?
 * 
 * If we were in C this would be a struct not an object.
 * It's just a really simple pair of values that always stick together.
 * 
 * @author troy
 *
 */
public class ChessCoords {
	public String column;
	public int row;
	
	public ChessCoords(String letter, int number) {
		column = letter;	row = number;
	}
	
	public String toString() {
		return "Chess Coords: ROW: " + row + " COL: " + column;
	}
	
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
	
}
