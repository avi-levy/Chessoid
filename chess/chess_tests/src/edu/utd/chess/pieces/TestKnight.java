package edu.utd.chess.pieces;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import junit.framework.TestCase;
import edu.utd.chess.board.ChessBoard;
import edu.utd.chess.board.ChessCoords;
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
    	
    }
    
    public void testValidateMoveInvalidTargets() throws Exception {
    	
    }
    
    public void testMoveToCurrentLocation() throws Exception {

    }
    
    public void testMoveToValidTargets() throws Exception {
    	
    }
    
    public void testMoveToIllegalTargets() throws Exception {
    	
    }
    
    public void testMoveToInvalidTargets() throws Exception {
    	
    }
    
    public void testMoveToOccupiedTileValidMove() throws Exception {
    	
    }
    
    public void testMoveToOccupiedTileIllegalMove() throws Exception {
    	
    }
    
    public void resetBoard() {
    	ChessGame.INSTANCE.initialize();  // <-- resets the board
    	this.cb = ChessGame.INSTANCE.getChessBoard();
    }
}
