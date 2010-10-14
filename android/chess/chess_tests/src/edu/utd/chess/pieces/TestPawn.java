package edu.utd.chess.pieces;

import junit.framework.TestCase;
import edu.utd.chess.board.ChessBoard;
import edu.utd.chess.board.ChessCoords;
import edu.utd.chess.exceptions.CoordsOccupiedException;
import edu.utd.chess.exceptions.IllegalMoveException;
import edu.utd.chess.exceptions.InvalidCoordsException;
import edu.utd.chess.game.ChessGame;

public class TestPawn extends TestCase {
    ChessBoard cb;

    public void setUp() {
    	resetBoard();
    }
    
    public void tearDown() {
        cb = null;
    }

    public void testMoveToValidTargets() throws Exception {
      	Pawn p = (Pawn) cb.getChessPieceAt("A", 7);
    	//can move by 2 on first move (straight ahead)
    	cb.movePiece(p.location, new ChessCoords("A", 5));
    	//or can move by 1 (straight ahead)
    	Pawn p2 = (Pawn) cb.getChessPieceAt("B", 7);
    	p2.moveTo("B", 6);
    	//can move diagonally if we're about to capture
    	Pawn victim = (Pawn) cb.getChessPieceAt("B", 2);
    	cb.movePiece(victim.location, new ChessCoords("B", 4));
    	try {
    		//should throw CoordsOccupied, not illegal move
    		p.moveTo(victim.location);
    		fail("failed to throw expected CoordsOccupied");
    	}
    	catch (CoordsOccupiedException x) {
    		//should get here
    	}
    }
    
    public void testMoveToInvalidTargets() throws Exception {
    	Pawn p = (Pawn) cb.getChessPieceAt("A", 7);
    	try {
    		p.moveTo("X", 2);
    		fail("failed to throw expected invalid coords");
    	}
    	catch (InvalidCoordsException x) {
    		//should get here
    	}
    }
    
    public void testMoveToCoodsOccupied() throws Exception {
    	Pawn p = (Pawn) cb.getChessPieceAt("A", 7);
    	//move rook into the way
    	cb.removePiece("H", 7);
    	Rook r = (Rook) cb.getChessPieceAt("H", 8);
    	cb.movePiece(r.location, new ChessCoords("H", 6));
    	cb.movePiece(r.location, new ChessCoords("A", 6));
    	try {
    		p.moveTo("A", 6);
    		fail("failed to throw expected coords occupied");
    	}
    	catch (CoordsOccupiedException x) {
    		//should get here
    	}
    	//should behave same for diagonal (attempt to capture)
    	cb.movePiece(new ChessCoords("A", 6), new ChessCoords("B", 6));
    	try {
    		p.moveTo("B", 6);
    		fail("failed to throw expected coords occupied");
    	}
    	catch (CoordsOccupiedException x) {
    		//should get here
    	}
    }
    
    public void testMoveToCoordsOccupiedIllegalTarget() throws Exception {
    	Pawn p = (Pawn) cb.getChessPieceAt("A", 7);
    	//move rook into the way
    	cb.removePiece("H", 7);
    	Rook r = (Rook) cb.getChessPieceAt("H", 8);
    	cb.movePiece(r.location, new ChessCoords("H", 6));
    	cb.movePiece(r.location, new ChessCoords("C", 6));
    	try {
    		p.moveTo(r.location);
    		fail("failed to throw expected illegal target");
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
