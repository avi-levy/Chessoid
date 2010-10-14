package edu.utd.chess.pieces;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import junit.framework.TestCase;
import edu.utd.chess.board.ChessBoard;
import edu.utd.chess.board.ChessCoords;
import edu.utd.chess.exceptions.CoordsOccupiedException;
import edu.utd.chess.exceptions.IllegalMoveException;
import edu.utd.chess.exceptions.InvalidCoordsException;
import edu.utd.chess.game.ChessGame;

public class TestKnight extends TestCase {
    ChessBoard cb;
    
    public void setUp() {
    	resetBoard();
    }
    
    public void tearDown() {
        cb = null;
    }
    
    public void testValidateMoveValidTargets() throws Exception {
    	List<ChessCoords> validTargets = new ArrayList<ChessCoords>();
    	
    	//two moves at a time...
    	validTargets.add(new ChessCoords("A", 6));
    	validTargets.add(new ChessCoords("B", 8));
    	
    	validTargets.add(new ChessCoords("C", 6));
    	validTargets.add(new ChessCoords("E", 5));
    	
    	Iterator<ChessCoords> iter = validTargets.iterator(); 
    	while (iter.hasNext()) {
    		Knight k = (Knight) cb.getChessPieceAt("B", 8);
    		cb.movePiece(k.location, iter.next());
    		cb.movePiece(k.location, iter.next());
    		resetBoard();
    	}
    }
    
    public void testValidateMoveIllegalTargets() throws Exception {
    	Knight k = (Knight) cb.getChessPieceAt("B", 8);
    	List<ChessCoords> illegalTargets = new ArrayList<ChessCoords>();
    	illegalTargets.add(new ChessCoords("B", 6));
    	illegalTargets.add(new ChessCoords("D", 6));
    	illegalTargets.add(new ChessCoords("H", 3));
    	for (ChessCoords coords : illegalTargets) {
    		try {
    			k.validateMove(coords);
    			fail("Failed to catch excepted illegal target exception");
    		}
    		catch (IllegalMoveException x) {
    			// should get here
    		}
    	}
    }
    
    public void testValidateMoveInvalidTargets() throws Exception {
    	Knight k = (Knight) cb.getChessPieceAt("B", 8);
    	List<ChessCoords> invalidTargets = new ArrayList<ChessCoords>();
    	invalidTargets.add(new ChessCoords("I", 6));
    	invalidTargets.add(new ChessCoords("C", 42));
    	invalidTargets.add(new ChessCoords("K", 88));
    	for (ChessCoords coords: invalidTargets) {
    		try {
    			k.validateMove(coords);
    			fail("failed to throw expected invalid coords");
    		}
    		catch (InvalidCoordsException x) {
    			//should get here
    		}
    	}
    }
    
    public void testMoveToValidTargets() throws Exception {
    	Knight k = (Knight) cb.getChessPieceAt("B", 8);
    	List<ChessCoords> validTargets = new ArrayList<ChessCoords>();
    	validTargets.add(new ChessCoords("C", 6));
    	validTargets.add(new ChessCoords("E", 5));
    	validTargets.add(new ChessCoords("F", 3));
    	validTargets.add(new ChessCoords("E", 5));
    	for (ChessCoords coords : validTargets) {
    		cb.movePiece(k.location, coords);		// <-- implicitly calls Knight.moveTo()
    		assertEquals(coords, k.location);
    	}
    }
   
    public void testMoveToIllegalTargets() throws Exception {
    	Knight k = (Knight) cb.getChessPieceAt("B", 8);
    	List<ChessCoords> illegalTargets = new ArrayList<ChessCoords>();
    	illegalTargets.add(new ChessCoords("B", 6));
    	illegalTargets.add(new ChessCoords("D", 6));
    	illegalTargets.add(new ChessCoords("H", 3));
    	for (ChessCoords coords : illegalTargets) {
    		try {
    			k.moveTo(coords);
    			fail("Failed to catch excepted illegal target exception");
    		}
    		catch (IllegalMoveException x) {
    			// should get here
    		}
    	}
    	
    }
    
    public void testMoveToInvalidTargets() throws Exception {
    	Knight k = (Knight) cb.getChessPieceAt("B", 8);
    	List<ChessCoords> invalidTargets = new ArrayList<ChessCoords>();
    	invalidTargets.add(new ChessCoords("I", 6));
    	invalidTargets.add(new ChessCoords("C", 42));
    	invalidTargets.add(new ChessCoords("K", 88));
    	for (ChessCoords coords: invalidTargets) {
    		try {
    			k.moveTo(coords);
    			fail("failed to throw expected invalid coords");
    		}
    		catch (InvalidCoordsException x) {
    			//should get here
    		}
    	}
    	
    }
    
    public void testMoveToOccupiedTileValidMove() throws Exception {
    	Knight k = (Knight) cb.getChessPieceAt("B", 8);
    	Pawn p = (Pawn) cb.getChessPieceAt("C", 7);
    	cb.movePiece(p.location, new ChessCoords("C", 6));
    	try {
    		cb.movePiece(k.location, new ChessCoords("C", 6));
    		fail("failed to throw expected coords occupied");
    	}
    	catch (CoordsOccupiedException x) {
    		//should get here
    	}
    }
    
    /**
     * should throw illegal move
     * @throws Exception
     */
    public void testMoveToOccupiedTileIllegalMove() throws Exception {
    	Knight k = (Knight) cb.getChessPieceAt("B", 8);
    	Pawn p = (Pawn) cb.getChessPieceAt("G", 7);
    	try {
    		k.moveTo(p.location);
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
