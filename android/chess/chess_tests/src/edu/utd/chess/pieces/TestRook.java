package edu.utd.chess.pieces;

import junit.framework.TestCase;
import edu.utd.chess.board.ChessBoard;
import edu.utd.chess.board.ChessCoords;
import edu.utd.chess.exceptions.CoordsOccupiedException;
import edu.utd.chess.exceptions.IllegalMoveException;
import edu.utd.chess.exceptions.InvalidCoordsException;
import edu.utd.chess.game.ChessGame;

public class TestRook extends TestCase {
    ChessBoard cb;
    
    
    public void setUp() {
    	resetBoard();
    }
    
    public void tearDown() {
        cb = null;
    }    
    
    public void testValidateMoveValidTargets() throws Exception {
    	Rook r = (Rook) cb.getChessPieceAt("A", 8);
    	//get rid of pawn
    	cb.removePiece("A", 7);
    	cb.movePiece(r.location, new ChessCoords("A", 6));
    	assertEquals(new ChessCoords("A", 6), r.location);
    	//down
    	r.validateMove(new ChessCoords("A", 3));
    	//up
    	r.validateMove(new ChessCoords("A", 8));
    	//right
    	cb.movePiece(r.location, new ChessCoords("F", 6));
    	assertEquals(new ChessCoords("F", 6), r.location);
    	//left
    	r.validateMove(new ChessCoords("C", 6));
    }
    
    public void testValidateMoveInvalidTargets() throws Exception {
    	Rook r = (Rook) cb.getChessPieceAt("A", 8);
    	//get rid of pawn
    	cb.removePiece("A", 7);
    	//move rook to a good testing location
    	cb.movePiece(r.location, new ChessCoords("A", 6));
    	cb.movePiece(r.location, new ChessCoords("C", 6));
    	assertEquals(new ChessCoords("C", 6), r.location);
    	try {
    		r.validateMove(new ChessCoords("H", 57));
    		fail("failed throw expected invalid coords");
    	}
    	catch (InvalidCoordsException x) {
    		//should get here
    	}
    }
    
    public void testValidateMoveIllegalTargets() throws Exception {
    	Rook r = (Rook) cb.getChessPieceAt("A", 8);
    	//get rid of pawn
    	cb.removePiece("A", 7);
    	//move rook to a good testing location
    	cb.movePiece(r.location, new ChessCoords("A", 6));
    	cb.movePiece(r.location, new ChessCoords("C", 6));
    	assertEquals(new ChessCoords("C", 6), r.location);
    	try {
    		r.validateMove(new ChessCoords("E", 5));  // <-- illegal diagonal
    		fail("failed throw expected illegal move");
    	}
    	catch (IllegalMoveException x) {
    		//should get here
    	}
    }
    
    public void testMoveToValidTargets() throws Exception {
    	Rook r = (Rook) cb.getChessPieceAt("A", 8);
    	//get rid of pawn
    	cb.removePiece("A", 7);
    	cb.movePiece(r.location, new ChessCoords("A", 6));
    	assertEquals(new ChessCoords("A", 6), r.location);
    	//down
    	cb.movePiece(r.location, new ChessCoords("A", 3));
    	assertEquals(new ChessCoords("A", 3), r.location);
    	//up
    	cb.movePiece(r.location, new ChessCoords("A", 8));
    	assertEquals(new ChessCoords("A", 8), r.location);
    	//right (move back down first)
    	cb.movePiece(r.location, new ChessCoords("A", 5));
    	assertEquals(new ChessCoords("A", 5), r.location);
    	cb.movePiece(r.location, new ChessCoords("D", 5));
    	assertEquals(new ChessCoords("D", 5), r.location);
    	//left
    	cb.movePiece(r.location, new ChessCoords("B", 5));
    	assertEquals(new ChessCoords("B", 5), r.location);
    }
    
    public void testMoveToInvalidTargets() throws Exception {
    	Rook r = (Rook) cb.getChessPieceAt("A", 8);
    	//get rid of pawn
    	cb.removePiece("A", 7);
    	cb.movePiece(r.location, new ChessCoords("A", 6));
    	assertEquals(new ChessCoords("A", 6), r.location);
    	try {
    		r.moveTo("X", 86);
    		fail("failed to throw expected invalid coords");
    	}
    	catch (InvalidCoordsException x) {
    		//should get here
    	}
    }
    
    public void testMoveToIllegalTargets() throws Exception {
    	Rook r = (Rook) cb.getChessPieceAt("A", 8);
    	//get rid of pawn
    	cb.removePiece("A", 7);
    	cb.movePiece(r.location, new ChessCoords("A", 6));
    	assertEquals(new ChessCoords("A", 6), r.location);
    	try {
    		r.moveTo("C", 5);
    		fail("failed to throw expected exception");
    	}
    	catch (IllegalMoveException x) {
    		//should get here
    	}
    }
    
    public void testMoveToCoordsOccupied() throws Exception {
    	Rook r = (Rook) cb.getChessPieceAt("A", 8);
    	try {
    		r.moveTo("A", 7);
    		fail("failed to throw coords occupied");
    	}
    	catch (CoordsOccupiedException x) {
    		//should get here
    	}
    }
    
    public void testMoveToCoordsOccupiedIllegalMove() throws Exception {
    	Rook r = (Rook) cb.getChessPieceAt("A", 8);
    	try {
    		r.moveTo("H", 7);
    		fail("failed to throw illegal move");
    	}
    	catch (IllegalMoveException x) {
    		//should get here
    	}
    }
    
    public void resetBoard() {
    	ChessGame.INSTANCE.initialize();  // <-- resets the board
    	this.cb = ChessGame.INSTANCE.getChessBoard();
    }
}
