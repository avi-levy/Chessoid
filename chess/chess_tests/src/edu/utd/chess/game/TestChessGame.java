package edu.utd.chess.game;

import junit.framework.TestCase;
import edu.utd.chess.board.ChessCoords;
import edu.utd.chess.exceptions.ChessPieceNotFoundException;
import edu.utd.chess.exceptions.HomicideException;
import edu.utd.chess.exceptions.IllegalMoveException;
import edu.utd.chess.pieces.Pawn;
import edu.utd.chess.pieces.Rook;

public class TestChessGame extends TestCase {
	ChessGame game;
	
	public void setUp() {
		game = ChessGame.INSTANCE;
		game.initialize();	// <-- resets everything
	}
	
	public void tearDown() {
		game = null;
	}
	
	public void testProcessMoveOccupiedToEmptyValidMove() throws Exception {
		Pawn p = (Pawn) game.getChessBoard().getChessPieceAt("E", 2);
		game.processMove(p.location, new ChessCoords("E", 4));
	}
	
	public void testProcessMoveOccupiedToEmptyInvalidMove() throws Exception {
		Pawn p = (Pawn) game.getChessBoard().getChessPieceAt("E", 2);
		try {
			game.processMove(p.location, new ChessCoords("G", 5));
			fail("failed to throw illegal move");
		}
		catch (IllegalMoveException x) { }
	}
	
	public void testProcessMoveOccupiedToOccupiedValidMove() throws Exception {
		Rook r = (Rook) game.getChessBoard().getChessPieceAt("A", 1);
		try {
			game.processMove(r.location, new ChessCoords("A", 2));
			fail("failed to throw HomicideException");
		}
		catch (HomicideException x) { }
	}
	
	public void testProcessMoveOccupiedToOccupiedInvalidMove() throws Exception {
		Pawn p = (Pawn) game.getChessBoard().getChessPieceAt("A", 2);
		try {
			game.processMove(p.location, new ChessCoords("B", 2));
			fail("failed to throw HomicideException");
		}
		catch (HomicideException x) { }
	}
	
	public void testProcessMoveEmptyToOccupied() throws Exception {
		try {
			game.processMove(new ChessCoords("A", 5), new ChessCoords("B", 6));
			fail("failed to throw ChessPieceNotFound");
		}
		catch (ChessPieceNotFoundException x) { }
	}
		
}
