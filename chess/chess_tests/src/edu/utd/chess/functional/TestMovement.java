package edu.utd.chess.functional;

import junit.framework.TestCase;
import edu.utd.chess.board.ChessCoords;
import edu.utd.chess.exceptions.HomicideException;
import edu.utd.chess.game.ChessGame;
import edu.utd.chess.pieces.ChessPiece;
import edu.utd.chess.pieces.Queen;

public class TestMovement extends TestCase {
	ChessGame game;
	
	public void setUp() {
		game = ChessGame.INSTANCE;
		game.initialize();		// <--resets the board, etc
	}
	
	public void cleanUp() {
		game = null;
	}
	
	public void testValidMoveToUnoccupiedLocation() throws Exception {
		ChessPiece pawn = game.getChessBoard().getChessPieceAt("A", 2);
		assertNotNull(pawn);
		game.processMove(pawn.location, new ChessCoords("A", 3));
	}
	
	/**
	 * throw and catch homicide exception
	 * @throws Exception
	 */
	public void testValidMoveToOccupiedLocationSameAlignment() throws Exception {
		ChessPiece rook = game.getChessBoard().getChessPieceAt("A", 1);
		try {
			game.processMove(rook.location, new ChessCoords("A", 2));
			fail ("did not catch expected exception");
		}
		catch (HomicideException e) {
			//should get here
		}
	}
	
	public void testCapture() throws Exception {
		//setup the board to where a capture is possible
		Queen q = (Queen) game.getChessBoard().getChessPieceAt("D", 8);
		game.getChessBoard().removePiece("D", 7);
		//do the capture (black queen >> pawn at D2)
		ChessPiece victim = game.getChessBoard().getChessPieceAt("D", 2);
		game.processMove(q.location, victim.location);
		//make sure it's gone
		assertFalse(game.getChessBoard().getAllChessPieces().contains(victim));
	}
	

}
