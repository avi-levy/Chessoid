package edu.utd.chess.pieces;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;
import edu.utd.chess.board.ChessBoard;
import edu.utd.chess.board.ChessCoords;
import edu.utd.chess.exceptions.CoordsOccupiedException;
import edu.utd.chess.exceptions.IllegalMoveException;
import edu.utd.chess.exceptions.InvalidCoordsException;
import edu.utd.chess.game.ChessGame;

public class TestBishop extends TestCase {
    ChessBoard cb;
    
    public void setUp() {
    	resetBoard();
    }
    
    public void tearDown() {
        cb = null;
    }
	
    public void testValidateMove() throws Exception {
    	ChessPiece b1 = (Bishop) cb.getChessPieceAt("C", 1);

    	
    	// cannot move to current location
    	try {
    		b1.validateMove(b1.location);
     		fail("failed to throw expected exception moving to current location");
    	}
    	catch (IllegalMoveException e) {
    		// should get here
    	}
    	
    	// valid targets - can move diagonally as many spaces as it wants
    	// (D2 and B2 will be occupied by pawns)
    	// TODO: eventually this should fail because the pawns should have
    	// to get out of the way
    	List<ChessCoords> validTargets = new ArrayList<ChessCoords>();
    	validTargets.add(new ChessCoords("E", 3));
    	validTargets.add(new ChessCoords("F", 4));
    	validTargets.add(new ChessCoords("G", 5));
    	validTargets.add(new ChessCoords("H", 6));
    	validTargets.add(new ChessCoords("A", 3));
    	
    	for (ChessCoords coords : validTargets) {
    		try {
    			b1.validateMove(coords);
    		}
    		catch (IllegalMoveException e) {
    			e.printStackTrace();
    			fail("Unexpected exception: " + e.getClass() 
    					+ " for target coords: " + coords);
    		}
    	}
    	
      	//illegal targets
    	ChessCoords a7 = new ChessCoords("A", 7);
    	ChessCoords g7 = new ChessCoords("G", 7);
    	ChessCoords e2 = new ChessCoords("E", 2);
    	ChessCoords c3 = new ChessCoords("C", 3);
    	ChessCoords a4 = new ChessCoords("A", 4);
    	ChessCoords e1 = new ChessCoords("E", 1);
    	
      	ChessCoords[] illegalTargets = { a7, g7, e2, c3, a4, e1 };
    	for (ChessCoords coords : illegalTargets) {
    		try {
	    		b1.validateMove(coords);
	    		fail("failed to throw expected IllegalMoveExpcetion " +
	    				"for move to coords: " + coords);
	    	}
    		catch (IllegalMoveException e) {
    			// should get here
    		}
    	}
    	
       	//invalid targets (don't exist on board)
    	ChessCoords a9 = new ChessCoords("A", 9);
    	ChessCoords z23 = new ChessCoords("Z", 23);
    	ChessCoords aa1 = new ChessCoords("AA", 1);
    	ChessCoords a1_1 = new ChessCoords("A1", 1);
    	
    	ChessCoords[] invalidTargets = { a9, z23, aa1, a1_1 };
    	for (ChessCoords coords : invalidTargets) {
    		try {
	    		b1.validateMove(coords);
	    		fail("failed to throw expected InvalidCoordsException " +
	    				"for move to coords: " + coords);
	    	}
    		catch (InvalidCoordsException e) {
    			// should get here
    		}
    	}
    }
    
    public void testMoveToCurrentLocation() throws Exception {
    	ChessCoords c8 = new ChessCoords("C", 8);
    	Bishop b1 = (Bishop) cb.getChessPieceAt(c8);    	
    	// cannot move to current location
    	try {
    		b1.moveTo(b1.location);
     		fail("failed to throw expected exception moving to current location");
    	}
    	catch (IllegalMoveException e) {
    		// should get here
    	}
    	assertEquals(c8, b1.location);
    }
    
    public void testMoveToValidTargets() throws Exception {
    	ChessCoords origCoords = new ChessCoords("C", 8);
    	
    	//valid targets - can move diagonally as many spaces as it wants
    	List<ChessCoords> validTargets = new ArrayList<ChessCoords>();
    	validTargets.add(new ChessCoords("A", 6));
    	validTargets.add(new ChessCoords("E", 6));
    	validTargets.add(new ChessCoords("F", 5));
    	validTargets.add(new ChessCoords("G", 4));
    	validTargets.add(new ChessCoords("H", 3));
    	
    	for (ChessCoords coords : validTargets) {
    		Bishop b1 = (Bishop) cb.getChessPieceAt(origCoords);
    		try {
    			b1.moveTo(coords);
    			resetBoard();
    			assertEquals(coords, b1.location);
    		}
    		catch (IllegalMoveException e) {
    			e.printStackTrace();
    			fail("Unexpected exception: " + e.getClass() 
    					+ " for target coords: " + coords);
    		}
    	}
    }
    
    public void testMoveToIllegalTargets() throws Exception {
    	ChessCoords origCoords = new ChessCoords("C", 8);
    	
      	//illegal targets
    	ChessCoords a7 = new ChessCoords("A", 7);
    	ChessCoords g7 = new ChessCoords("G", 7);
    	ChessCoords e2 = new ChessCoords("E", 2);
    	ChessCoords c3 = new ChessCoords("C", 3);
    	ChessCoords a4 = new ChessCoords("A", 4);
    	ChessCoords e1 = new ChessCoords("E", 1);
    	
      	ChessCoords[] illegalTargets = { a7, g7, e2, c3, a4, e1 };
    	for (ChessCoords coords : illegalTargets) {
    		Bishop b1 = (Bishop) cb.getChessPieceAt(origCoords);
    		try {
	    		b1.moveTo(coords);
	    		fail("failed to throw expected IllegalMoveExpcetion " +
	    				"for move to coords: " + coords);
	    	}
    		catch (IllegalMoveException e) {
    			// should get here
    		}
    		assertEquals(origCoords, b1.location);	// <-- should not have moved
    	}
    }
    
    public void testMoveToInvalidTargets() throws Exception {
    	ChessCoords origCoords = new ChessCoords("C", 8);
    	
       	//invalid targets
    	List<ChessCoords> invalidTargets  = new ArrayList<ChessCoords>();
    	invalidTargets.add(new ChessCoords("A", 9));
    	invalidTargets.add(new ChessCoords("Z", 23));
    	invalidTargets.add(new ChessCoords("AA", 1));
    	invalidTargets.add(new ChessCoords("A1", 1));
    	
    	for (ChessCoords coords : invalidTargets) {
    		Bishop b1 = (Bishop) cb.getChessPieceAt(origCoords);
    		try {
	    		b1.validateMove(coords);
	    		fail("failed to throw expected InvalidCoordsException " +
	    				"for move to coords: " + coords);
	    	}
    		catch (InvalidCoordsException e) {
    			// should get here
    		}
    		assertEquals(origCoords, b1.location);	// <-- should not have moved
    	}
    }
    
    public void testMoveToOccupiedTile() throws Exception {
    	ChessCoords origCoords = new ChessCoords("C", 8);
    	Bishop b1 = (Bishop) cb.getChessPieceAt(origCoords);
    	
    	//occupied cell - valid move (should throw CoordsOccupied)
    	Pawn p1 = (Pawn) cb.getChessPieceAt("B", 7);
    	try {
    		b1.moveTo(p1.location);
    		fail("failed to throw expected exception");
    	}
    	catch (CoordsOccupiedException e) {
    		//should get here
    	}
    	assertEquals(origCoords, b1.location);	// <-- should not have moved
    	
		//occupied cell - illegal move (should throw IllegalMove)
    	Pawn p2 = (Pawn) cb.getChessPieceAt("E", 7);
    	try {
    		b1.moveTo(p2.location);
    		fail("failed to throw expected exception");
    	}
    	catch (IllegalMoveException e) {
    		//should get here
    	}
    }
    
    public void testMoveToOccupiedTileIllegalMove() throws Exception {
    	Bishop b = (Bishop) cb.getChessPieceAt("C", 8);
    	try {
    		//occupied cell - illegal move (should throw IllegalMove)
    		b.moveTo("C", 7);
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
