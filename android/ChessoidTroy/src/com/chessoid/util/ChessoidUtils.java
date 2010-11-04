package com.chessoid.util;

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
}
