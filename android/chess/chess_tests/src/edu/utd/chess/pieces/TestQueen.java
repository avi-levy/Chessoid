package edu.utd.chess.pieces;

import junit.framework.TestCase;
import edu.utd.chess.board.ChessBoard;
import edu.utd.chess.board.ChessCoords;
import edu.utd.chess.exceptions.CoordsOccupiedException;
import edu.utd.chess.exceptions.IllegalMoveException;
import edu.utd.chess.exceptions.InvalidCoordsException;
import edu.utd.chess.game.ChessGame;

public class TestQueen extends TestCase {
    ChessBoard cb;
    
    public void setUp() {
    	resetBoard();
    }
    
    public void tearDown() {
        cb = null;
    }    
    
    public void testValidateMoveInvalidTargets() throws Exception {
    	Queen q = (Queen) cb.getChessPieceAt("D", 8);
    	//get pawn out of the way
    	cb.removePiece("D", 7);
    	cb.movePiece(q.location, new ChessCoords("D", 6));
    	try {
    		q.validateMove(new ChessCoords("H", 53));	// invalid location, does not exist
    		fail("failed to throw expected invalid coords");
    	}
    	catch (InvalidCoordsException x) {
    		//should get here
    	}
    }
    
    public void testValidateMoveIllegalTargets() throws Exception {
    	Queen q = (Queen) cb.getChessPieceAt("D", 8);
    	//get pawn out of the way
    	cb.removePiece("D", 7);
    	cb.movePiece(q.location, new ChessCoords("D", 6));
    	try {
    		//illegal angular move
    		q.validateMove(new ChessCoords("E", 8));	// invalid location, does not exist
    		fail("failed to throw expected illegal move");
    	}
    	catch (IllegalMoveException x) {
    		//should get here
    	}
    }
    
    public void testValidateMoveValidTargets() throws Exception {
       Queen q = (Queen) cb.getChessPieceAt("D", 8);
       //get pawn out of the way
       cb.removePiece("D", 7);
       q.validateMove(new ChessCoords("D", 7));
       q.validateMove(new ChessCoords("D", 4));
       cb.movePiece(q.location, new ChessCoords("D", 5));
       assertEquals(new ChessCoords("D", 5), q.location);
       //should be able to move in any direction, any num spaces
       //left
       q.validateMove(new ChessCoords("A", 5));
       //right
       q.validateMove(new ChessCoords("G", 5));
       //up
       q.validateMove(new ChessCoords("D", 8));
    }
    
    public void testMoveToValidTargets() throws Exception {
        Queen q = (Queen) cb.getChessPieceAt("D", 8);
        //get pawn out of the way
        cb.removePiece("D", 7);
        q.validateMove(new ChessCoords("D", 7));
        q.validateMove(new ChessCoords("D", 4));
        cb.movePiece(q.location, new ChessCoords("D", 5));
        assertEquals(new ChessCoords("D", 5), q.location);
        //should be able to move in any direction, any num spaces
        //left
        cb.movePiece(q.location, new ChessCoords("A", 5));
        cb.movePiece(q.location, new ChessCoords("D", 5));
        //right
        cb.movePiece(q.location, new ChessCoords("G", 5));
        cb.movePiece(q.location, new ChessCoords("D", 5));
        //up
        cb.movePiece(q.location, new ChessCoords("D", 8));
    }
    
    public void testMoveToInvalidTargets() throws Exception {
    	Queen q = (Queen) cb.getChessPieceAt("D", 8);
    	//get pawn out of the way
    	cb.removePiece("D", 7);
    	cb.movePiece(q.location, new ChessCoords("D", 6));
    	try {
    		q.moveTo(new ChessCoords("H", 53));	// invalid location, does not exist
    		fail("failed to throw expected invalid coords");
    	}
    	catch (InvalidCoordsException x) {
    		//should get here
    	}
    }
    
    public void testMoveToIllegalTargets() throws Exception {
    	Queen q = (Queen) cb.getChessPieceAt("D", 8);
    	//get pawn out of the way
    	cb.removePiece("D", 7);
    	cb.movePiece(q.location, new ChessCoords("D", 6));
    	try {
    		//illegal angular move
    		q.moveTo(new ChessCoords("E", 8));	// invalid location, does not exist
    		fail("failed to throw expected illegal move");
    	}
    	catch (IllegalMoveException x) {
    		//should get here
    	}

    }
    
    public void testMoveToCoordsOccupied() throws Exception {
    	Queen q = (Queen) cb.getChessPieceAt("D", 8);
    	try {
    		q.moveTo("D", 7);
    		fail("failed to throw expected coords occupied");
    	}
    	catch (CoordsOccupiedException x) {
    		//should get here
    	}
    }
    
    public void testMoveToCoordsOccupiedIllegalMove() throws Exception {
    	Queen q = (Queen) cb.getChessPieceAt("D", 8);
    	cb.removePiece("D", 7);
    	cb.movePiece(new ChessCoords("C", 7), new ChessCoords("C", 6));
    	assertTrue( cb.getChessPieceAt("C", 6) instanceof Pawn );
    	try {
    		q.moveTo("C", 6);
    		fail("failed to throw expected illegal move");
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
