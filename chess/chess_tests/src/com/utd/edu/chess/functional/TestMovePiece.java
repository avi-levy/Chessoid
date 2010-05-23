package com.utd.edu.chess.functional;

import junit.framework.TestCase;
import edu.utd.chess.board.ChessCoords;
import edu.utd.chess.game.ChessGame;
import edu.utd.chess.pieces.ChessPiece;

public class TestMovePiece extends TestCase {
	ChessGame game;
	
	public void setUp() {
		game = ChessGame.INSTANCE;
		game.initialize();
	}
	
	public void cleanUp() {
		game = null;
	}
	
	public void testValidMoveToUnOccupiedLocation() throws Exception {
		ChessPiece pawn = game.getChessBoard().getChessPieceAt("A", 2);
		assertNotNull(pawn);
		game.processMove(pawn.location, new ChessCoords("A", 3));
	}
	
	public void testValidMoveToOccupiedLocation() throws Exception {
		ChessPiece rook = game.getChessBoard().getChessPieceAt("A", 1);
		try {
			game.processMove(rook.location, new ChessCoords("A", 2));
			fail ("did not catch expected exception");
		}
		catch (Exception e) {
			//should get here
		}
	}
}
