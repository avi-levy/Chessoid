package edu.utd.chess.pieces;

import java.util.List;
import java.util.ArrayList;

import junit.framework.TestCase;
import edu.utd.chess.board.ChessBoard;
import edu.utd.chess.board.ChessCoords;
import edu.utd.chess.exceptions.CoordsOccupiedException;
import edu.utd.chess.exceptions.IllegalMoveException;
import edu.utd.chess.exceptions.InvalidCoordsException;
import edu.utd.chess.game.ChessGame;

public class TestKing extends TestCase {
    ChessBoard cb;
    
    public void setUp() {
    	resetBoard();
    }
    
    public void tearDown() {
        this.cb = null;
    }
    
    public void testValidateMoveCurrentLocation() throws Exception {
    	ChessCoords origCoords = new ChessCoords("E", 8);
    	King k1 = (King) cb.getChessPieceAt(origCoords);
    	
    	//cannot move to current location
    	try {
    		k1.validateMove(k1.location);
    		fail("failed to throw expected exception moving to current location");
    	}
    	catch (IllegalMoveException e) {
    		// should get here
    	}
    }
    
    public void testValidateMoveValidTargets() throws Exception {
    	ChessCoords origCoords = new ChessCoords("E", 8);
    	ChessPiece k1 = cb.getChessPieceAt(origCoords);
    	ChessCoords e7 = new ChessCoords("E", 7);
    	cb.removePiece(e7);	// remove the pawn
    	cb.movePiece(origCoords, e7);
    	
    	List<ChessCoords> validTargets = new ArrayList<ChessCoords>();
    	
    	//valid targets - should be allowed to move by one in all directions
    	validTargets.add(new ChessCoords("E", 8));
    	validTargets.add(new ChessCoords("F", 8));
    	validTargets.add(new ChessCoords("F", 7));
    	validTargets.add(new ChessCoords("F", 6));
    	validTargets.add(new ChessCoords("E", 6));
    	validTargets.add(new ChessCoords("D", 6));
    	validTargets.add(new ChessCoords("D", 7));
    	
    	for (ChessCoords coords : validTargets) {
    		try {
    			k1.validateMove(coords);
    		}
    		catch (IllegalMoveException e) {
    			e.printStackTrace();
    			fail("Unexpected exception: " + e.getClass() 
    					+ " for target coords: " + coords);
    		}
    	}
    }
    
    public void testValidateMoveIllegalTargets() throws Exception {
    	ChessCoords origCoords = new ChessCoords("E", 8);
    	ChessPiece k1 = cb.getChessPieceAt(origCoords);
    	
    	List<ChessCoords> illegalTargets = new ArrayList<ChessCoords>();
    	illegalTargets.add(new ChessCoords("A", 8));
    	illegalTargets.add(new ChessCoords("G", 7));
    	illegalTargets.add(new ChessCoords("E", 2));
    	illegalTargets.add(new ChessCoords("C", 3));
    	illegalTargets.add(new ChessCoords("F", 6));
    	
    	for (ChessCoords coords : illegalTargets) {
    		try {
	    		k1.validateMove(coords);
	    		fail("failed to throw expected IllegalMoveExpcetion " +
	    				"for move to coords: " + coords);
	    	}
    		catch (IllegalMoveException e) {
    			// should get here
    		}
    	}
    	
    	origCoords = new ChessCoords("E", 1);
    	k1 = cb.getChessPieceAt(origCoords);
    	
    	illegalTargets = new ArrayList<ChessCoords>();
    	illegalTargets.add(new ChessCoords("F", 6));
    	
    	for (ChessCoords coords : illegalTargets) {
    		try {
    			k1.validateMove(coords);
    			fail("failed to throw expected IllegalMoveException" +
    					"for move to coords: " + coords);
    		}
    		catch (IllegalMoveException e) {
    			//should get here
    		}
    	}
    }
    
    public void testValidateMoveInvalidTargets() throws Exception {
    	ChessCoords origCoords = new ChessCoords("E", 8);
    	ChessPiece k1 = cb.getChessPieceAt(origCoords);
    	
    	//invalid targets
    	List<ChessCoords> invalidTargets = new ArrayList<ChessCoords>();
    	invalidTargets.add(new ChessCoords("A", 9));
    	invalidTargets.add(new ChessCoords("Z", 23));
    	invalidTargets.add(new ChessCoords("AA", 1));
    	invalidTargets.add(new ChessCoords("A1", 1));
    	
    	for (ChessCoords coords : invalidTargets) {
    		try {
	    		k1.validateMove(coords);
	    		fail("failed to throw expected InvalidCoordsException " +
	    				"for move to coords: " + coords);
	    	}
    		catch (InvalidCoordsException e) {
    			// should get here
    		}
    	}
    }
    
    public void testMoveTocoordsValidTargets() throws Exception {
    	ChessCoords origCoords = new ChessCoords("E", 8);
    	
    	//valid targets - should be allowed to move by one in all directions
    	List<ChessCoords> validTargets = new ArrayList<ChessCoords>();
    	validTargets.add(new ChessCoords("E", 7));
    	validTargets.add(new ChessCoords("D", 7));
    	validTargets.add(new ChessCoords("F", 7));
    	validTargets.add(new ChessCoords("D", 8));
    	validTargets.add(new ChessCoords("F", 8));
    	
    	for (ChessCoords coords : validTargets) {
    		ChessPiece k1 = cb.getChessPieceAt(origCoords);
    		try {
    			//remove the pawns blocking the way
    			cb.removePiece("E", 7);
    			cb.removePiece("D", 7);
    			cb.removePiece("F", 7);
    			cb.removePiece("D", 8);
    			cb.removePiece("F", 8);
    			k1.moveTo(coords);
    			resetBoard();
    		}
    		catch (IllegalMoveException e) {
    			e.printStackTrace();
    			fail("Unexpected exception: " + e.getClass()
    					+ " for target coords: " + coords);
    		}
    	}
    	
    	resetBoard();
    	//test moving backwards:
    	
    	//remove the pawns blocking the way
		cb.removePiece("E", 7);
		cb.removePiece("D", 7);
		cb.removePiece("F", 7);
		cb.removePiece("D", 8);
		cb.removePiece("F", 8);
		ChessPiece k1 = cb.getChessPieceAt(origCoords);
		//move the piece on the board
		cb.movePiece(new ChessCoords("E", 8), new ChessCoords("E", 7));
		k1.moveTo("E", 8);
    }
    
    public void testMoveToIllegalTargets() throws Exception {
    	ChessCoords origCoords = new ChessCoords("E", 8);
    	King k1 = (King) cb.getChessPieceAt(origCoords);
    	
    	List<ChessCoords> illegalTargets = new ArrayList<ChessCoords>();
    	illegalTargets.add(new ChessCoords("A", 8));
    	illegalTargets.add(new ChessCoords("G", 7));
    	illegalTargets.add(new ChessCoords("E", 2));
    	illegalTargets.add(new ChessCoords("C", 3));
    	
    	for (ChessCoords coords : illegalTargets) {
    		try {	
	    		k1.moveTo(coords);
	    		fail("failed to throw expected IllegalMoveExpcetion " +
	    				"for move to coords: " + coords);
	    	}
    		catch (IllegalMoveException e) {
    			// should get here
    		}
    	}
    }
    
    public void testMoveToInvalidTargets() throws Exception {
    	ChessCoords origCoords = new ChessCoords("E", 8);
    	King k1 = (King) cb.getChessPieceAt(origCoords);
    	
    	List<ChessCoords> invalidTargets = new ArrayList<ChessCoords>();
    	invalidTargets.add(new ChessCoords("A", 9));
    	invalidTargets.add(new ChessCoords("Z", 23));
    	invalidTargets.add(new ChessCoords("AA", 1));
    	invalidTargets.add(new ChessCoords("A1", 1));
    	
    	for (ChessCoords coords : invalidTargets) {
    		try {
	    		k1.moveTo(coords);
	    		fail("failed to throw expected InvalidCoordsException " +
	    				"for move to coords: " + coords);
	    	}
    		catch (InvalidCoordsException e) {
    			// should get here
    		}
    	}
    }
    
    public void testMoveToCurrentLocation() throws Exception {
    	ChessCoords origCoords = new ChessCoords("E", 8);
    	King k1 = (King) cb.getChessPieceAt(origCoords);
    	try {
    		k1.moveTo(k1.location);
    		fail("failed to throw expected exception moving to current location");
    	}
    	catch (IllegalMoveException e) {
    		// should get here
    	}
    }
    
    public void testMoveToOccupiedTileValidMove() throws Exception {
    	ChessCoords origCoords = new ChessCoords("E", 8);
    	King k1 = (King) cb.getChessPieceAt(origCoords);
    	
    	//occupied cell - valid move (should throw CoordsOccupied)
		Pawn p = (Pawn) cb.getChessPieceAt("E", 7);
		try {
			k1.moveTo(p.location);
			fail("failed to throw expected exception");
		}
		catch (CoordsOccupiedException e) {
			// should get here
		}
    }
    
    public void testMoveToOccupiedTileIllegalMove() throws Exception {
    	ChessCoords origCoords = new ChessCoords("E", 8);
    	King k1 = (King) cb.getChessPieceAt(origCoords);
    	
    	//occupied cell - illegal move (should throw IllegalMove)
    	Pawn p = (Pawn) cb.getChessPieceAt("A", 7);
    	
		try {
			k1.moveTo(p.location);
			fail("failed to throw expected exception");
		}
		catch (IllegalMoveException e) {
			// should get here
		}		
    }
    
    public void resetBoard() {
    	ChessGame.INSTANCE.initialize();  // <-- resets the board
    	this.cb = ChessGame.INSTANCE.getChessBoard();
    }
}
