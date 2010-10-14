package edu.utd.chess.board;

import junit.framework.TestCase;

public class TestChessCoords extends TestCase {

	
	public void testEquals() {
		ChessCoords cc1 = new ChessCoords("A", 1);
		ChessCoords cc2 = new ChessCoords("A", 1);
		assertTrue(cc1.equals(cc2));
		cc2.column = "a";
		assertTrue(cc1.equals(cc2));
		cc2.column = "B";
		assertFalse(cc1.equals(cc2));
		cc2.column = "A";
		cc2.row = 2;
		assertFalse(cc1.equals(cc2));
	}
	
	public void testHashCode() {
		// valid coords
		ChessCoords c1 = new ChessCoords("A", 1);
		ChessCoords c2 = new ChessCoords("A", 1);
		ChessCoords c3 = new ChessCoords("B", 1);
		ChessCoords c4 = new ChessCoords("B", 2);
		ChessCoords c5 = new ChessCoords("b", 2);
		
		assertTrue(c1.hashCode() == c2.hashCode());
		assertTrue(c4.hashCode() == c5.hashCode());
		assertFalse(c1.hashCode() == c3.hashCode());
		assertFalse(c3.hashCode() == c4.hashCode());
		
		// invalid coords
		c1 = new ChessCoords("Z", 1);
		c2 = new ChessCoords("Z", 1);
		c3 = new ChessCoords("XX", 1);
		c4 = new ChessCoords("XX", 2);
		c5 = new ChessCoords("xx", 2);
		
		assertTrue(c1.hashCode() == c2.hashCode());
		assertTrue(c4.hashCode() == c5.hashCode());
		assertFalse(c1.hashCode() == c3.hashCode());
		assertFalse(c3.hashCode() == c4.hashCode());		
	}
}
