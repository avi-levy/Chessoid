package edu.utd.chess.pieces;

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
        cb = ChessGame.INSTANCE.getChessBoard();
        cb.pieces.clear();
    }
    
    public void tearDown() {
        cb.pieces.clear();
    }
    
    public void testValidateMove() throws Exception {
    	ChessCoords e4 = new ChessCoords("E", 4);
    	King k1 = new King(ChessPiece.BLACK, e4);
    	cb.pieces.add(k1);
    	
    	//cannot move to current location
    	try {
    		k1.validateMove(k1.location);
    		fail("failed to throw expected exception moving to current location");
    	}
    	catch (IllegalMoveException e) {
    		// should get here
    	}
    	
    	//valid targets - should be allowed to move by one in all directions
    	ChessCoords e5 = new ChessCoords("E", 5);
    	ChessCoords e3 = new ChessCoords("E", 3);
    	ChessCoords d5 = new ChessCoords("D", 5);
    	ChessCoords d4 = new ChessCoords("D", 4);
    	ChessCoords d3 = new ChessCoords("D", 3);
    	ChessCoords f5 = new ChessCoords("F", 5);
    	ChessCoords f4 = new ChessCoords("F", 4);
    	ChessCoords f3 = new ChessCoords("F", 3);
    	
    	ChessCoords[] validTargets = { e5, e3, d5, d4, d3, f5, f4, f3 };
    	
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
    	
    	//illegal targets
    	ChessCoords a8 = new ChessCoords("A", 8);
    	ChessCoords g7 = new ChessCoords("G", 7);
    	ChessCoords e2 = new ChessCoords("E", 2);
    	ChessCoords c3 = new ChessCoords("C", 3);
    	
    	ChessCoords[] illegalTargets = { a8, g7, e2, c3 };
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
    	
    	//invalid targets
    	ChessCoords a9 = new ChessCoords("A", 9);
    	ChessCoords z23 = new ChessCoords("Z", 23);
    	ChessCoords aa1 = new ChessCoords("AA", 1);
    	ChessCoords a1_1 = new ChessCoords("A1", 1);
    	
    	ChessCoords[] invalidTargets = { a9, z23, aa1, a1_1 };
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
    
    public void testMoveTo() throws Exception {
    	ChessCoords e4 = new ChessCoords("E", 4);
    	King k1;
    	
    	//valid targets - should be allowed to move by one in all directions
    	ChessCoords e5 = new ChessCoords("E", 5);
    	ChessCoords e3 = new ChessCoords("E", 3);
    	ChessCoords d5 = new ChessCoords("D", 5);
    	ChessCoords d4 = new ChessCoords("D", 4);
    	ChessCoords d3 = new ChessCoords("D", 3);
    	ChessCoords f5 = new ChessCoords("F", 5);
    	ChessCoords f4 = new ChessCoords("F", 4);
    	ChessCoords f3 = new ChessCoords("F", 3);
    	
    	ChessCoords[] validTargets = { e5, e3, d5, d4, d3, f5, f4, f3 };
    	
    	for (ChessCoords coords : validTargets) {
    		try {
    			k1 = new King(ChessPiece.BLACK, e4);
    			k1.moveTo(coords);
    		}
    		catch (IllegalMoveException e) {
    			e.printStackTrace();
    			fail("Unexpected exception: " + e.getClass() 
    					+ " for target coords: " + coords);
    		}
    	}
    	
    	//illegal targets
    	ChessCoords a8 = new ChessCoords("A", 8);
    	ChessCoords g7 = new ChessCoords("G", 7);
    	ChessCoords e2 = new ChessCoords("E", 2);
    	ChessCoords c3 = new ChessCoords("C", 3);
    	
    	ChessCoords[] illegalTargets = { a8, g7, e2, c3 };
    	for (ChessCoords coords : illegalTargets) {
    		try {
    			k1 = new King(ChessPiece.BLACK, e4);
	    		k1.moveTo(coords);
	    		fail("failed to throw expected IllegalMoveExpcetion " +
	    				"for move to coords: " + coords);
	    	}
    		catch (IllegalMoveException e) {
    			// should get here
    		}
    	}
    	
    	//invalid targets
    	ChessCoords a9 = new ChessCoords("A", 9);
    	ChessCoords z23 = new ChessCoords("Z", 23);
    	ChessCoords aa1 = new ChessCoords("AA", 1);
    	ChessCoords a1_1 = new ChessCoords("A1", 1);
    	
    	ChessCoords[] invalidTargets = { a9, z23, aa1, a1_1 };
    	for (ChessCoords coords : invalidTargets) {
    		try {
    			k1 = new King(ChessPiece.BLACK, e4);
	    		k1.moveTo(coords);
	    		fail("failed to throw expected InvalidCoordsException " +
	    				"for move to coords: " + coords);
	    	}
    		catch (InvalidCoordsException e) {
    			// should get here
    		}
    	}
    	
    	//cannot move to current location
    	try {
    		k1 = new King(ChessPiece.BLACK, e4);
    		k1.moveTo(k1.location);
    		fail("failed to throw expected exception moving to current location");
    	}
    	catch (IllegalMoveException e) {
    		// should get here
    	}
    	
    	//occupied cell - valid move (should throw CoordsOccupied)
    	k1 = new King(ChessPiece.BLACK, e4);
		Pawn p = new Pawn(ChessPiece.WHITE, e5);
		// register pieces with the board for this to work
		cb.pieces.add(k1);	cb.pieces.add(p);
		try {
			k1.moveTo(p.location);
			fail("failed to throw expected exception");
		}
		catch (CoordsOccupiedException e) {
			// should get here
		}
		
		//occupied cell - illegal move (should throw IllegalMove)
		ChessCoords a7 = new ChessCoords("A", 7);
		Pawn p2 = new Pawn(ChessPiece.BLACK, a7); // <-- unreachable
		cb.pieces.add(p2);
		try {
			k1.moveTo(p2.location);
			fail("failed to throw expected exception");
		}
		catch (IllegalMoveException e) {
			// should get here
		}		
    }
}
