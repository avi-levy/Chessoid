package edu.utd.chess.board;

import java.util.Collection;

import junit.framework.TestCase;
import edu.utd.chess.exceptions.ChessPieceNotFoundException;
import edu.utd.chess.exceptions.CoordsOccupiedException;
import edu.utd.chess.game.ChessGame;
import edu.utd.chess.pieces.Bishop;
import edu.utd.chess.pieces.ChessPiece;
import edu.utd.chess.pieces.King;
import edu.utd.chess.pieces.Knight;
import edu.utd.chess.pieces.Pawn;
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
	
	public void testGetAllChessPieces() {
		Collection<ChessPiece> all = cb.getAllChessPieces();
		int whitePawns = 0,	whiteRooks = 0,	whiteBishops = 0,
		whiteKnights = 0, 	whiteKings = 0,	whiteQueens = 0,
		blackPawns = 0,		blackRooks = 0, blackBishops = 0,
		blackKnights = 0,	blackKings = 0, blackQueens = 0;
		for (ChessPiece piece : all) {
			if (piece.alignment.equals(ChessPiece.WHITE)) {
				if (piece instanceof Pawn) {
					whitePawns++;
				}
				else if (piece instanceof Rook) {
					whiteRooks++;
				}
				else if (piece instanceof Bishop) {
					whiteBishops++;
				}
				else if (piece instanceof Queen) {
					whiteQueens++;
				}
				else if (piece instanceof King) {
					whiteKings++;
				}
				else if (piece instanceof Knight) {
					whiteKnights++;
				}
			}
			else if (piece.alignment.equals(ChessPiece.BLACK)) {
				if (piece instanceof Pawn) {
					blackPawns++;
				}
				else if (piece instanceof Rook) {
					blackRooks++;
				}
				else if (piece instanceof Bishop) {
					blackBishops++;
				}
				else if (piece instanceof Queen) {
					blackQueens++;
				}
				else if (piece instanceof King) {
					blackKings++;
				}
				else if (piece instanceof Knight) {
					blackKnights++;
				}
			}
		}
		
		assertEquals(32, whiteBishops + whiteKings + whiteKnights + whitePawns + whiteQueens + whiteRooks
				+ blackBishops + blackKings + blackKnights + blackPawns + blackQueens + blackRooks);
		assertEquals(8, whitePawns);	assertEquals(8, blackPawns);
		assertEquals(2, whiteRooks);	assertEquals(2, blackRooks);
		assertEquals(2, whiteBishops);	assertEquals(2, blackBishops);
		assertEquals(2, whiteKnights);	assertEquals(2, blackKnights);
		
	}
	
	public void testRemovePieceChessCoords() throws Exception {
		ChessPiece p = cb.getChessPieceAt("D", 2);
		cb.removePiece(p.location);
		assertFalse(cb.getAllChessPieces().contains(p));
		try {
			cb.removePiece(p.location);
			fail("failed to throw expected ChessPieceNotFound");
		}
		catch (ChessPieceNotFoundException x) {
		}
	}
	
	public void testRemovePieceStringString() throws Exception {
		ChessPiece p = cb.getChessPieceAt("D", 2);
		cb.removePiece("D", 2);
		assertFalse(cb.getAllChessPieces().contains(p));
		try {
			cb.removePiece("D", 2);
			fail("failed to throw expected ChessPieceNotFound");
		}
		catch (ChessPieceNotFoundException x) {
		}
	}
	
	public void testMovePieceFromOccupiedToEmpty() throws Exception {
		ChessPiece piece = cb.getChessPieceAt("A", 7);
		ChessCoords to = new ChessCoords("A", 6);
		cb.movePiece(piece.location, to);
		assertEquals(to, piece.location);
	}
	
	public void testMovePieceFromOccupiedToOccupied() throws Exception {
		ChessCoords orig = new ChessCoords("A", 8);
		ChessPiece piece = cb.getChessPieceAt(orig);
		ChessCoords to = new ChessCoords("A", 7);
		try {
			cb.movePiece(piece.location, to);
			fail("failed to throw expected coords occupied");
		}
		catch (CoordsOccupiedException x) {
		}
		assertEquals(orig, piece.location);
	}
	
	public void testMovePieceFromEmptyToEmpty() throws Exception {
		ChessCoords empty1 = new ChessCoords("A", 5);
		ChessCoords empty2 = new ChessCoords("B", 5);
		try {
			cb.movePiece(empty1, empty2);
			fail("failed to throw expected ChessPieceNotFound");
		}
		catch (ChessPieceNotFoundException x) {
		}
	}
	
	public void testMovePieceFromEmptyToOccupied() throws Exception {
		ChessCoords orig = new ChessCoords("A", 7);
		ChessPiece p = cb.getChessPieceAt(orig);
		ChessCoords empty = new ChessCoords("H", 5);
		assertNull(cb.getChessPieceAt(empty));
		try {
			cb.movePiece(empty, p.location);
			fail("failed to throw expected ChessPieceNotFound");
		}
		catch (ChessPieceNotFoundException x) {
		}
		assertNull(cb.getChessPieceAt(empty));
		assertEquals(orig, p.location);
	}

}
