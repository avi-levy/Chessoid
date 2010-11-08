package com.chessoid.util;

import junit.framework.TestCase;

public class ChessoidUtilsTests extends TestCase {

	public void testTranslateColInt() {	
		assertEquals("A", ChessoidUtils.translateCol(1));
		assertEquals("B", ChessoidUtils.translateCol(2));
		assertEquals("C", ChessoidUtils.translateCol(3));
		assertEquals("D", ChessoidUtils.translateCol(4));
		assertEquals("E", ChessoidUtils.translateCol(5));
		assertEquals("F", ChessoidUtils.translateCol(6));
		assertEquals("G", ChessoidUtils.translateCol(7));
		assertEquals("H", ChessoidUtils.translateCol(8));
		assertNull(ChessoidUtils.translateCol(9));
		assertNull(ChessoidUtils.translateCol(-1));
		assertNull(ChessoidUtils.translateCol(4234));
	}
	
}
