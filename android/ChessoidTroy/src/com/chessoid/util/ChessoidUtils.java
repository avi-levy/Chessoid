package com.chessoid.util;

import com.chessoid.model.Pieces;

public class ChessoidUtils {

	
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
	 * Translate the letter value for a column A-H into an integer value, e.g
	 * A=1, B=2,...H=8.
	 * 
	 * @param col
	 * @return integer representation of the column specified by the letter col
	 *         or -1 if it does correspond to any column on the chess board
	 */
	public static int translateCol(String col) {
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
	 * Find the SAN (Standard Algebraic Notation) character
	 * that represented the specified type of chess piece.
	 * @param Pieces type from the Pieces enum
	 * @return char representation of the piece, e.g. 'p' for
	 * black pawn, 'P' for white pawn.  Returns '\0' for the 
	 * Pieces.EMPTY type.
	 */
	public static char translateSANPiece(Pieces piece) {
		switch(piece) {
			case BLACK_PAWN:
				return 'p';
			case BLACK_ROOK:
				return 'r';
			case BLACK_KNIGHT:
				return 'n';
			case BLACK_BISHOP:
				return 'b';
			case BLACK_QUEEN:
				return 'q';
			case BLACK_KING:
				return 'k';
			case WHITE_PAWN:
				return 'P';
			case WHITE_ROOK:
				return 'R';
			case WHITE_KNIGHT:
				return 'N';
			case WHITE_BISHOP:
				return 'B';
			case WHITE_QUEEN:
				return 'Q';
			case WHITE_KING:
				return 'K';
			default:
				return '\0';				
		}
	}
	
	/**
	 * Find the Pieces enum type associated with the SAN
	 * (Standard Algebraic Notation) character provied.
	 * e.g. 'p' resolves to Pieces.BLACK_PAWN, 'P' resolves
	 * to Pieces.WHITE_PAWN.
	 * @param sanChar the character that represents a piece in SAN.
	 * @return the Pieces enum representation of the piece type.
	 * Returns Pieces.EMPTY if the char doesn't match any piece type.
	 */
	public static Pieces translateSANPiece(char sanChar) {
			switch (sanChar) {
				case 'p':
					return Pieces.BLACK_PAWN;
				case 'r':
					return Pieces.BLACK_ROOK;
				case 'n':
					return Pieces.BLACK_KNIGHT;
				case 'b':
					return Pieces.BLACK_BISHOP;
				case 'q':
					return Pieces.BLACK_QUEEN;
				case 'k':
					return Pieces.BLACK_KING;
				case 'P':
					return Pieces.WHITE_PAWN;
				case 'R':
					return Pieces.WHITE_ROOK;
				case 'N':
					return Pieces.WHITE_KNIGHT;
				case 'B':
					return Pieces.WHITE_BISHOP;
				case 'Q':
					return Pieces.WHITE_QUEEN;
				case 'K':
					return Pieces.WHITE_KING;
				default:
					return Pieces.EMPTY;
			}
	}
	
	
}
