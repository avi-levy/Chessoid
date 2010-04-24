package edu.utd.chess.board;

import junit.framework.TestCase;
import edu.utd.chess.game.ChessGame;
import edu.utd.chess.pieces.ChessPiece;
import edu.utd.chess.pieces.Pawn;

public class TestChessBoard extends TestCase {
	ChessBoard cb;
	
	public void setUp() {
		cb = ChessGame.INSTANCE.getChessBoard();
	}
	
	public void tearDown() {
		cb = null;
	}
	
	public void testGetChessPieceAt() {
		Pawn p = new Pawn(ChessPiece.BLACK, new ChessCoords("A", 1));
	    cb.pieces.add(p);
		assertNull(cb.getChessPieceAt(new ChessCoords("B", 2)));
		assertEquals(p, cb.getChessPieceAt(new ChessCoords("A", 1)));
	}
	
	public void testValidateValidCoordinates() {
		ChessCoords coords = new ChessCoords("A", 1);
		assertTrue(cb.validateCoords(coords));
		coords = new ChessCoords("B", 1);
		assertTrue(cb.validateCoords(coords));
		coords = new ChessCoords("C", 1);
		assertTrue(cb.validateCoords(coords));
		coords = new ChessCoords("D", 1);
		assertTrue(cb.validateCoords(coords));
		coords = new ChessCoords("E", 1);
		assertTrue(cb.validateCoords(coords));
		coords = new ChessCoords("F", 1);
		assertTrue(cb.validateCoords(coords));
		coords = new ChessCoords("G", 1);
		assertTrue(cb.validateCoords(coords));
		coords = new ChessCoords("H", 1);
		assertTrue(cb.validateCoords(coords));
		coords = new ChessCoords("A", 1);
		assertTrue(cb.validateCoords(coords));
		coords = new ChessCoords("H", 2);
		assertTrue(cb.validateCoords(coords));
		coords = new ChessCoords("H", 3);
		assertTrue(cb.validateCoords(coords));
		coords = new ChessCoords("H", 4);
		assertTrue(cb.validateCoords(coords));
		coords = new ChessCoords("H", 5);
		assertTrue(cb.validateCoords(coords));
		coords = new ChessCoords("H", 6);
		assertTrue(cb.validateCoords(coords));
		coords = new ChessCoords("H", 7);
		assertTrue(cb.validateCoords(coords));
		coords = new ChessCoords("H", 8);
		assertTrue(cb.validateCoords(coords));
	}
	
	public void testValidateInvalidCoordinates() {
		ChessCoords coords = new ChessCoords("Z", 0);
		assertFalse(cb.validateCoords(coords));
		coords = new ChessCoords("Z", 1);
		assertFalse(cb.validateCoords(coords));
		coords = new ChessCoords("A", 0);
		assertFalse(cb.validateCoords(coords));
		coords = new ChessCoords("Z", 9);
		assertFalse(cb.validateCoords(coords));
		coords = new ChessCoords("A", 234235);
		assertFalse(cb.validateCoords(coords));
		coords = new ChessCoords("AA", 2);
		assertFalse(cb.validateCoords(coords));

	}
	
	public void testTranslateCol() {
		assertEquals(1, ChessBoard.translateCol("A"));
		assertEquals(2, ChessBoard.translateCol("B"));
		assertEquals(3, ChessBoard.translateCol("C"));
		assertEquals(4, ChessBoard.translateCol("D"));
		assertEquals(5, ChessBoard.translateCol("E"));
		assertEquals(6, ChessBoard.translateCol("F"));
		assertEquals(7, ChessBoard.translateCol("G"));
		assertEquals(8, ChessBoard.translateCol("H"));
		assertEquals(1, ChessBoard.translateCol("a"));
		assertEquals(-1, ChessBoard.translateCol("Z"));
	}

}
