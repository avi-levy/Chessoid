package edu.utd.chess.board;

import junit.framework.TestCase;

public class TestChessCoords extends TestCase {

	
	public void testEquals() {
		ChessCoords cc1 = new ChessCoords("A", 1);
		ChessCoords cc2 = new ChessCoords("A", 1);
		assertTrue(cc1.equals(cc2));
		cc2.column = "B";
		assertFalse(cc1.equals(cc2));
		cc2.column = "A";
		cc2.row = 2;
		assertFalse(cc1.equals(cc2));
	}
}
