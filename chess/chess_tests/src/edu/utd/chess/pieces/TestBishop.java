package edu.utd.chess.pieces;

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
        cb = ChessGame.INSTANCE.getChessBoard();
        cb.pieces.clear();
    }
    
    public void tearDown() {
        cb.pieces.clear();
    }
	
    public void testValidateMove() throws Exception {
    	ChessCoords e4 = new ChessCoords("E", 4);
    	Bishop b1 = new Bishop(ChessPiece.WHITE, e4);
    	cb.pieces.add(b1);
    	
    	// cannot move to current location
    	try {
    		b1.validateMove(b1.location);
     		fail("failed to throw expected exception moving to current location");
    	}
    	catch (IllegalMoveException e) {
    		// should get here
    	}
    	
    	//valid targets - can move diagonally as many spaces as it wants
    	ChessCoords f5 = new ChessCoords("F", 5);
    	ChessCoords d3 = new ChessCoords("D", 3);
    	ChessCoords g6 = new ChessCoords("G", 6);
    	ChessCoords h7 = new ChessCoords("H", 7);
    	ChessCoords a8 = new ChessCoords("A", 8);
    	ChessCoords b7 = new ChessCoords("B", 7);
    	ChessCoords f3 = new ChessCoords("F", 3);
    	ChessCoords g2 = new ChessCoords("G", 2);
    	
    	ChessCoords[] validTargets = { f5, d3, g6, h7, a8, b7, f3, g2 };
    	
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
    	
       	//invalid targets
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
    
    public void testMoveTo() throws Exception {
       	ChessCoords e4 = new ChessCoords("E", 4);
    	Bishop b1 = new Bishop(ChessPiece.WHITE, e4);
    	cb.pieces.add(b1);
    	
    	// cannot move to current location
    	try {
    		b1.moveTo(b1.location);
     		fail("failed to throw expected exception moving to current location");
    	}
    	catch (IllegalMoveException e) {
    		// should get here
    	}
    	assertEquals(e4, b1.location);
    	
    	//valid targets - can move diagonally as many spaces as it wants
    	ChessCoords f5 = new ChessCoords("F", 5);
    	ChessCoords d3 = new ChessCoords("D", 3);
    	ChessCoords g6 = new ChessCoords("G", 6);
    	ChessCoords h7 = new ChessCoords("H", 7);
    	ChessCoords a8 = new ChessCoords("A", 8);
    	ChessCoords b7 = new ChessCoords("B", 7);
    	ChessCoords f3 = new ChessCoords("F", 3);
    	ChessCoords g2 = new ChessCoords("G", 2);
    	
    	ChessCoords[] validTargets = { f5, d3, g6, h7, a8, b7, f3, g2 };
    	
    	for (ChessCoords coords : validTargets) {
    		try {
    			b1 = new Bishop(ChessPiece.WHITE, e4);
    			b1.moveTo(coords);
    		}
    		catch (IllegalMoveException e) {
    			e.printStackTrace();
    			fail("Unexpected exception: " + e.getClass() 
    					+ " for target coords: " + coords);
    		}
    		assertEquals(coords, b1.location);
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
    			b1 = new Bishop(ChessPiece.WHITE, e4);
	    		b1.moveTo(coords);
	    		fail("failed to throw expected IllegalMoveExpcetion " +
	    				"for move to coords: " + coords);
	    	}
    		catch (IllegalMoveException e) {
    			// should get here
    		}
    		assertEquals(e4, b1.location);
    	}
    	
       	//invalid targets
    	ChessCoords a9 = new ChessCoords("A", 9);
    	ChessCoords z23 = new ChessCoords("Z", 23);
    	ChessCoords aa1 = new ChessCoords("AA", 1);
    	ChessCoords a1_1 = new ChessCoords("A1", 1);
    	
    	ChessCoords[] invalidTargets = { a9, z23, aa1, a1_1 };
    	for (ChessCoords coords : invalidTargets) {
    		try {
    			b1 = new Bishop(ChessPiece.WHITE, e4);
	    		b1.validateMove(coords);
	    		fail("failed to throw expected InvalidCoordsException " +
	    				"for move to coords: " + coords);
	    	}
    		catch (InvalidCoordsException e) {
    			// should get here
    		}
    		assertEquals(e4, b1.location);
    	}
    	
    	//occupied cell - valid move (should throw CoordsOccupied)
    	Pawn p1 = new Pawn(ChessPiece.BLACK, f5);
    	cb.pieces.add(p1);
    	try {
    		b1.moveTo(p1.location);
    		fail("failed to throw expected exception");
    	}
    	catch (CoordsOccupiedException e) {
    		//should get here
    	}
    	
		//occupied cell - illegal move (should throw IllegalMove)
    	Pawn p2 = new Pawn(ChessPiece.WHITE, g7);
    	try {
    		b1.moveTo(p2.location);
    		fail("failed to throw expected exception");
    	}
    	catch (IllegalMoveException e) {
    		//should get here
    	}
    }
}
