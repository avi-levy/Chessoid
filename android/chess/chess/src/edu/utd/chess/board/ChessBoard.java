package edu.utd.chess.board;

import java.util.ArrayList;
import java.util.Collection;

import edu.utd.chess.exceptions.ChessPieceNotFoundException;
import edu.utd.chess.exceptions.CoordsOccupiedException;
import edu.utd.chess.exceptions.IllegalMoveException;
import edu.utd.chess.exceptions.InvalidCoordsException;
import edu.utd.chess.pieces.ChessPiece;

public class ChessBoard {
	/*
	 * Number of rows and columns on the chess board. We'll go with standard
	 * chess board dimensions of 8 x 8.
	 */
	public static int ROWS = 8;
	public static int COLS = 8;

	/**
	 * Internal data structure for the chess board
	 */
	public ChessPiece[][] board = new ChessPiece[ROWS][COLS];

	/**
	 * ChessBoard constructor. Requires a list of chess pieces on the board to
	 * be provided
	 * 
	 * @param pieces
	 *            a List of <code>ChessPiece</code>s
	 */
	public ChessBoard(Collection<ChessPiece> chessSet) {
		for (ChessPiece piece : chessSet) {
			ChessCoords coords = piece.location;
			board[coords.row-1][translateCol(coords.column)-1] = piece;
		}
	}

	/**
	 * Test if the location at the specified coordinates has a chess piece on it
	 * that would prevent us from moving there. BE CAREFUL: returns <b>null</b>
	 * if no chess piece at specified location.
	 * 
	 * @param location
	 *            ChessCoords to check
	 * @return the chess piece found at the specified coordinates, or
	 *         <b>null</b>, if none was found.
	 */
	public ChessPiece getChessPieceAt(ChessCoords location) {
		return board[location.row-1][translateCol(location.column)-1];
	}
	
	/**
	 * Test if the location at the specified coordinates has a chess piece on it
	 * that would prevent us from moving there. BE CAREFUL: returns <b>null</b>
	 * if no chess piece at specified location.
	 * 
	 * @param row (int)
	 * @param col (String)
	 *          
	 * @return the chess piece found at the specified coordinates, or
	 *         <b>null</b>, if none was found.
	 */
	public ChessPiece getChessPieceAt(String col, int row) {
		if (! validateCoords(row, col)) {
			return null;
		}
		return board[row-1][translateCol(col)-1];
	}

	/**
	 * Test that specified coordinates are valid (i.e. they actually exist on
	 * this chess board).
	 * 
	 * @param coords
	 * @return true if valid, false if not
	 */
	public boolean validateCoords(ChessCoords coords) { // TODO: can throw NPE, prevent?
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
	 * Works the same way as <code>ChessBoards.validateCoords(ChessCoords)</code>
	 * @see ChessBoard#validateCoords(ChessCoords)
	 * @param row
	 * @param column
	 * @return true if valid, false if not
	 */
	public boolean validateCoords(int row, String column) {
		if (row > ROWS || row <= 0) {
			return false;
		}
		int col = translateCol(column);
		if (col > COLS || col <= 0) {
			return false;
		}
		return true;
	}

	/**
	 * Translate the letter value for a column A-H into an integer value, e.g
	 * A=1, B=2,...H=8.
	 * 
	 * @param col
	 * @return integer representation of the column specified by the letter col
	 *         or -1 if it does correspond to any column on the chess board
	 */
	public static int translateCol(String col) {
		// TODO : this breaks if the chess board is not of standard size
		int colnum = -1;
		col = col.toUpperCase();
		if (col.equals("A")) {
			colnum = 1;
		} else if (col.equals("B")) {
			colnum = 2;
		} else if (col.equals("C")) {
			colnum = 3;
		} else if (col.equals("D")) {
			colnum = 4;
		} else if (col.equals("E")) {
			colnum = 5;
		} else if (col.equals("F")) {
			colnum = 6;
		} else if (col.equals("G")) {
			colnum = 7;
		} else if (col.equals("H")) {
			colnum = 8;
		}
		return colnum;
	}

	/**
	 * Translate the int value for a column A-H into a String value, e.g A=1,
	 * B=2,...H=8, or <b><code>null</b></code> if there is no match. Complements
	 * translateCol(String).
	 * 
	 * @param col
	 * @return String representation of the column specified by the int col or
	 *         <b><code>null</b></code> if it does correspond to any column on
	 *         the chess board
	 */
	public static String translateCol(int col) {
		// TODO : this breaks if the chess board is not of standard size
		String colnum = null;
		if (col == 1) {
			colnum = "A";
		} else if (col == 2) {
			colnum = "B";
		} else if (col == 3) {
			colnum = "C";
		} else if (col == 4) {
			colnum = "D";
		} else if (col == 5) {
			colnum = "E";
		} else if (col == 6) {
			colnum = "F";
		} else if (col == 7) {
			colnum = "G";
		} else if (col == 8) {
			colnum = "H";
		}
		return colnum;
	}

	/**
	 * Take a piece of the board, if it's there.
	 * 
	 * @param coords
	 * @throws ChessPieceNotFoundException
	 */
	public void removePiece(ChessCoords coords) throws ChessPieceNotFoundException {
		if (null == board[coords.row-1][translateCol(coords.column)-1]) {
			throw new ChessPieceNotFoundException("Guy wasn't there at: " + coords);
		}
		board[coords.row-1][translateCol(coords.column)-1] = null;
	}

	/**
	 * Move a piece on the board.  This tells the piece
	 * at specified location to update its state reflect
	 * the change to the new location, and updates the 
	 * board's state to reflect the change.
	 * @param from ChessCoords for source location
	 * @param to ChessCoords for destination location
	 * @throws ChessPieceNotFoundException if the source location was empty
	 * @throws CoordsOccupiedException if there is another chess piece occupying
	 * the destination location
	 */ //TODO test me!
	public void movePiece(ChessCoords from, ChessCoords to) 
		throws 
			ChessPieceNotFoundException,
			InvalidCoordsException,
			IllegalMoveException,
			CoordsOccupiedException
	{
		if (null == board[from.row-1][translateCol(from.column)-1]) {
			throw new ChessPieceNotFoundException("Guy wasn't there at: " + from);
		}

		ChessPiece piece = board[from.row-1][translateCol(from.column)-1];

//		if (null != board[to.row-1][translateCol(to.column)-1]) {			
//			throw new CoordsOccupiedException();			
//		}
		
		if (null != piece) {
			piece.moveTo(to);
		}
		else {
			throw new ChessPieceNotFoundException("Not found at: " + from);
		}

		board[from.row-1][translateCol(from.column)-1] = null;
		board[to.row-1][translateCol(to.column)-1] = piece;
		
//		piece.location = to;
	}
	
	/**
	 * Works the same way as <code>ChessBooard.removePiece(ChessCoords)</code>
	 * @see ChessBoard#removePiece(ChessCoords)
	 * @param col String column, i.e. A-H
	 * @param row int row, i.e. 1-8
	 * @throws ChessPieceNotFoundException
	 */
	public void removePiece(String col, int row) throws ChessPieceNotFoundException {
		removePiece(new ChessCoords(col, row));
	}
	
	/**
	 * Get a collection of all the <code>ChessPiece</code>s currently
	 * in the game.  Note that this list must be newly constructed 
	 * each time this method is called (currently builds a collection
	 * for the internal primitive array), so calling this a lot
	 * might be kind of slow
	 * @return Collection of all the chess pieces
	 */
	public Collection<ChessPiece> getAllChessPieces() {
		//TODO there should probably be a piececount state var (maybe per side)
		//if so, use that here instead of hardcode to 32
		ArrayList<ChessPiece> pieces = new ArrayList<ChessPiece>(32);
		for (int row=1; row <= ROWS; row++) {
			for (int col=1; col <= COLS; col++) {
				ChessPiece piece = getChessPieceAt(translateCol(col), row);
				if (null != piece) {
					pieces.add(piece);
				}
			}
		}
		return pieces;
	}
}
