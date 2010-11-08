package com.chessoid.model;

import junit.framework.TestCase;

public class TestBoard extends TestCase {
	
	public void testCreateBoard() {
		Board b = new Board();
		for (int i=1; i <= 8; i++) {
			for (int j=1; j <= 8; j++) {
				assertEquals('\0', b.getPieceAt(i, j));
			}
		}
	}
	
	public void testSetPieceCharIntInt() {
		Board b = new Board();
		b.setPieceAt('K', 5, 1);
		assertEquals('K', b.getPieceAt(5, 1));
	}
	
	public void testSetPieceCharCharInt() {
		Board b = new Board();
		b.setPieceAt('Q', 'd', 1);
		assertEquals('Q', b.getPieceAt('d', 1));
		b.setPieceAt('q', 'D', 8);
		assertEquals('q', b.getPieceAt('D', 8));
	}
	
	public void testGetPieceAtIntInt() {
		Board b = new Board();
		assertEquals('\0', b.getPieceAt('G', 6));
		assertEquals('\0', b.getPieceAt('g', 6));
		b.setPieceAt('p', 'G', 6);
		assertEquals('p', b.getPieceAt('G', 6));
		assertEquals('p', b.getPieceAt('g', 6));
	}
	
	public void testToString() {
		Board b = new Board();
		String emptyBoard = 
			". . . . . . . . \n" +
			". . . . . . . . \n" +
			". . . . . . . . \n" +
			". . . . . . . . \n" +
			". . . . . . . . \n" +
			". . . . . . . . \n" +
			". . . . . . . . \n" +
			". . . . . . . . \n";
		assertEquals(emptyBoard, b.toString());
		String pA1Board =
			". . . . . . . . \n" +
			". . . . . . . . \n" +
			". . . . . . . . \n" +
			". . . . . . . . \n" +
			". . . . . . . . \n" +
			". . . . . . . . \n" +
			". . . . . . . . \n" +
			"p . . . . . . . \n";
		b.setPieceAt('p', 'a', 1);
		assertEquals(pA1Board, b.toString());
	}
	
}
