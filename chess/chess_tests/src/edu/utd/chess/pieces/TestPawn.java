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
        cb = ChessGame.INSTANCE.getChessBoard();
        cb.pieces.clear();
    }

    public void tearDown() {
        cb.pieces.clear();
    }

    public void testValidateMove() throws Exception {
        ChessCoords a1 = new ChessCoords("A", 1);
        Pawn p = new Pawn(ChessPiece.BLACK, a1);
        // first move, we should be allowed to move 2 places
        ChessCoords a3 = new ChessCoords("A", 3);
        p.validateMove(a3);
        p.moveTo(a3);
        assertTrue(p.location.equals(a3));
        // now we should not be allowed to move by 2
        ChessCoords a5 = new ChessCoords("A", 5);
        try {
            p.validateMove(a5);
            fail("failed to throw expected exception");
        }
        catch (IllegalMoveException e) {
            //should get here
        }
        // cannot go sideways
        ChessCoords b3 = new ChessCoords("B", 3);
        try {
            p.validateMove(b3);
            fail("failed to throw expected exception");
        }
        catch (IllegalMoveException e) {
            //should get here
        }
        
        // cannot go diagonally
        ChessCoords b4 = new ChessCoords("B", 4);
        try {
            p.validateMove(b4);
            fail("failed to throw expected exception");
        }
        catch (IllegalMoveException e) {
            //should get here
        }
        // can move by one after first move
        ChessCoords a4 = new ChessCoords("A", 4);
        p.validateMove(a4);
        
        //cannot move to our current location
        try {
        	p.validateMove(p.location);
        	fail("failed to throw expected exception");
        }
        catch (IllegalMoveException e) {
        	//should get here
        }
        
        //cannot move illegally (not diag or straight)
        p = new Pawn(ChessPiece.BLACK, a5);
        ChessCoords c6 = new ChessCoords("C", 6);
        try {
        	p.moveTo(c6);
        	fail("failed to throw expected exception");
        }
        catch (IllegalMoveException e) {
        	//should get here
        }
    }

    public void testMoveTo() throws Exception {
        //TODO : are we going to need a factory that creates
        //a piece and registers it with the board?
        //or should a ChessPiece have an explicit association
        //with a ChessBoard and throw NotInitialzed exceptions
        //if it tries to move and hasn't yet been associated
        //with a board?
        ChessCoords a1 = new ChessCoords("A", 1);
        Pawn p1 = new Pawn(ChessPiece.WHITE, a1);
        this.cb.pieces.add(p1);
        // move up by two (allowed on first move only)
        ChessCoords a3 = new ChessCoords("A", 3);
        // try to move forward by too many (illegal move)
        ChessCoords a8 = new ChessCoords("A", 8);
        p1.moveTo(a3);
        try {
            p1.moveTo(a8);
            fail("failed to throw expected exception");
        } 
        catch (IllegalMoveException x) {
            // we should get here
        }
        // test trying to move to occupied location
        ChessCoords a4 = new ChessCoords("A", 4);
        Pawn p2 = new Pawn(ChessPiece.WHITE, a4);
        this.cb.pieces.add(p2);
        try {
            p1.moveTo(a4);
            fail("failed to throw expected exception");
        }
        catch (CoordsOccupiedException x) {
            // we should get here
        }
        // try to go off the edge of the board
        assertEquals(a3, p1.location);
        ChessCoords z87 = new ChessCoords("Z", 87);
        try {
            p1.moveTo(z87);
            fail("failed to throw expected exception");
        }
        catch (InvalidCoordsException x) {
            //we should get here
        }
        assertEquals(a3, p1.location);
        
        //move to current location
        try {
            p1.moveTo(p1.location);
            fail("failed to throw expected exception");
        }
        catch (IllegalMoveException e) {
            //should get here
        }
    }
}
