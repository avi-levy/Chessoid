package edu.utd.chess.board;

import java.util.HashMap;

import edu.utd.chess.pieces.ChessPiece;

public class ChessBoard {
	/**
	 * A list of all the ChessPieces currently on the board. 
	 */
	public HashMap<ChessCoords, ChessPiece> pieces;
	
	/**
	 * ChessBoard constructor.  Requires a list of chess pieces
	 * on the board to be provided
	 * @param pieces a List of <code>ChessPiece</code>s
	 */
	public ChessBoard(HashMap<ChessCoords, ChessPiece> pieces) {
		this.pieces = pieces;
	}
	
	/*
	 * Number of rows and columns on the chess board.  We'll go with
	 * standard chess board dimensions of 8 x 8. 
	 */
	public static int ROWS = 8;
	public static int COLS = 8;

	/**
	 * Test if the location at the specified coordinates has
	 * a chess piece on it that would prevent us from moving there.
	 * BE CAREFUL: returns <b>null</b> if no chess piece at specified
	 * location.
	 * @param location ChessCoords to check
	 * @return the chess piece found at the specified coordinates,
	 * or <b>null</b>, if none was found.
	 */
	public ChessPiece getChessPieceAt(ChessCoords location) {
		//TODO : should we be checking if the specified location is valid and throws exceptions?
		return this.pieces.get(location);
	}
	
	/**
	 * Test that specified coordinates are valid (i.e. they actually
	 * exist on this chess board).
	 * @param coords
	 * @return true if valid, false if not
	 */
	public boolean validateCoords(ChessCoords coords) {
		if (coords.row > ROWS || coords.row <= 0) {
			return false;
		}
		int col = translateCol(coords.column); 
		if (col > COLS || col <= 0) {
			return false;
		}
		return true;
	}
	
	/**
	 * Translate the letter value for a column A-H into
	 * an integer value, e.g A=1, B=2,...H=8.
	 * @param col
	 * @return integer representation of the column specified by the letter col
	 * or -1 if it does correspond to any column on the chess board
	 */    
	public static int translateCol(String col) {
		//TODO : this breaks if the chess board is not of standard size
	    //TODO : should this be here or in ChessCoords ???
		int colnum = -1;
		col = col.toUpperCase();
		if (col.equals("A")) {
			colnum = 1;
		}
		else if (col.equals("B")) {
			colnum = 2;
		}
		else if (col.equals("C")) {
			colnum = 3;
		}
		else if (col.equals("D")) {
			colnum = 4;
		}
		else if (col.equals("E")) {
			colnum = 5;
		}
		else if (col.equals("F")) {
			colnum = 6;
		}
		else if (col.equals("G")) {
			colnum = 7;
		}
		else if (col.equals("H")) {
			colnum = 8;
		}
		return colnum;
	}
	
	/**
	 * Translate the int value for a column A-H into
	 * a String value, e.g A=1, B=2,...H=8, or <b><code>null</b></code>
	 * if there is no match.  Complements translateCol(String).
	 * @param col
	 * @return String representation of the column specified by the int col
	 * or <b><code>null</b></code> if it does correspond to any column on the chess board
	 */
	public static String translateCol(int col) {
		//TODO : this breaks if the chess board is not of standard size
		String colnum = null;
		if (col == 1) {
			colnum = "A";
		}
		else if (col ==2 ) {
			colnum = "B";
		}
		else if (col == 3) {
			colnum = "C";
		}
		else if (col == 4) {
			colnum = "D";
		}
		else if (col == 5) {
			colnum = "E";
		}
		else if (col == 6) {
			colnum = "F";
		}
		else if (col == 7) {
			colnum = "G";
		}
		else if (col == 8) {
			colnum = "H";
		}
		return colnum;
	}
}
