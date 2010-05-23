package edu.utd.chess.board;

import junit.framework.TestCase;
import edu.utd.chess.game.ChessGame;
import edu.utd.chess.pieces.Bishop;
import edu.utd.chess.pieces.ChessPiece;
import edu.utd.chess.pieces.Knight;
import edu.utd.chess.pieces.Queen;
import edu.utd.chess.pieces.Rook;

public class TestChessBoard extends TestCase {
	ChessBoard cb;
	
	public void setUp() {
		cb = new ChessBoard(ChessGame.INSTANCE.createDefaultChessSet());
	}
	
	public void tearDown() {
		cb = null;
	}
	
	public void testGetChessPieceAtChessCoords() {
		ChessCoords coords = new ChessCoords("A", 1);
		ChessPiece whiterook = cb.getChessPieceAt(coords);
		assertTrue(whiterook instanceof Rook);
		assertTrue(whiterook.alignment.equals(ChessPiece.WHITE));
		assertEquals(coords, whiterook.location);
		coords = new ChessCoords("D", 8);
		ChessPiece blackqueen = cb.getChessPieceAt(coords);
		assertTrue(blackqueen instanceof Queen);
		assertEquals(ChessPiece.BLACK, blackqueen.alignment);
		assertEquals(coords, blackqueen.location);
	}
	
	public void testGetChessPieceAtStringString() {
		ChessPiece whiteknight = cb.getChessPieceAt("G", 1);
		assertTrue(whiteknight instanceof Knight);
		assertEquals(ChessPiece.WHITE, whiteknight.alignment);
		assertEquals(new ChessCoords("G", 1), whiteknight.location);
		ChessPiece blackbishop = cb.getChessPieceAt("F", 8);
		assertTrue(blackbishop instanceof Bishop);
		assertEquals(ChessPiece.BLACK, blackbishop.alignment);
		assertEquals(new ChessCoords("F", 8), blackbishop.location);
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
