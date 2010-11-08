package com.chessoid.model;

/**
 * A wrapper data structure representing a chess board
 * and the chess pieces on it.
 * 
 * Uses SAN (Standard Algebraic Notation) to represent
 * pieces as characters on the chessboard, e.g. the default
 * starting layout is:
 * 
 * <pre>
 * r n b q k b n r
 * p p p p p p p p
 * . . . . . . . .
 * . . . . . . . .
 * . . . . . . . .
 * . . . . . . . .
 * P P P P P P P P
 * R N B Q K B N R
 * </pre>
 * 
 * Dots are used to represent empty spaces above, but
 * really empty "squares" should be represented by
 * the '\0' character.
 * 
 * Note that lowercase are black and uppercase
 * are white.  This convention should be followed
 * when setting the locations of pieces.
 * 
 * 
 * @author troy
 *
 */
public class Board {

	public static final int COLS = 8;
	public static final int ROWS = 8;
	
	char[][] board = new char[COLS][ROWS];
	
	/**
	 * e.g. setChessPieceAt('K', 5, 1) to put the white King in
	 * its standard location at e1.
	 * @param piece char representing the piece, in SAN.
	 * @param col int column
	 * @param row
	 */
	public void setPieceAt(char piece, int col, int row) {
		board[col-1][row-1] = piece;
	}
	
	/**
	 * e.g. setChessPieceAt('K', 'e', 1) to put the white King in
	 * its standard location at e1.
	 * @param piece char representing the piece, in SAN.
	 * @param col char column, accepts upper or lower case
	 * @param row int row
	 */
	public void setPieceAt(char piece, char col, int row) {
		setPieceAt(piece, translateCol(col), row);
	}
	
	/**
	 * get a chess pieces at specified coords.
	 * @param col column as int
	 * @param row column as int
	 * @return the SAN character representing the piece, or
	 * '\0' (null char) if location is empty.
	 */
	public char getPieceAt(int col, int row) {
		return board[col-1][row-1];
	}
	
	/**
	 * get a chess pieces at specified coords.
	 * @param col column as char, accepts upper or lower case
	 * @param row column as int
	 * @return the SAN character representing the piece, or
	 * '\0' (null char) if location is empty.
	 */
	public char getPieceAt(char col, int row) {
		return board[translateCol(col)-1][row-1];
	}
	
	/**
	 * convert column as char to column as int.  Note that this
	 * only converts the char representation to an int representation
	 * of the same column (i.e. 'a' becomes 1, 'b' becomes 2, etc),
	 * does not account for index offsets.
	 * @param col char column to translate to int
	 * @return int representation of the column
	 * or -1 if no match
	 */
	public static int translateCol(char col) {
		col = Character.toLowerCase(col);
		if ('a' == col)
			return 1;
		if ('b' == col)
			return 2;
		if ('c' == col)
			return 3;
		if ('d' == col)
			return 4;
		if ('e' == col)
			return 5;
		if ('f' == col)
			return 6;
		if ('g' == col)
			return 7;
		if ('h' == col)
			return 8;
		return -1;
	}
	
	/**
	 * Get a String representation of the chess board
	 * and the pieces on it, e.g.
	 * <pre>
	 * r n b q k b n r
	 * p p p p p p p p
	 * . . . . . . . .
	 * . . . . . . . .
	 * . . . . . . . .
	 * . . . . . . . .
	 * P P P P P P P P
	 * R N B Q K B N R
	 * </pre> 
	 */
	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		for (int r=ROWS; r >= 1; r--) {
			for (int c=1; c <= COLS; c++) {
				if ('\0' == getPieceAt(c, r)) {
					str.append(". ");
				} else { 
					str.append(getPieceAt(c, r));
					str.append(' ');
				}
			}
			str.append("\n");
		}
		return str.toString();
	}
	
}
