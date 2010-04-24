package edu.utd.chess.pieces;

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
        cb = ChessGame.INSTANCE.getChessBoard();
        cb.pieces.clear();
    }
    
    public void tearDown() {
        cb.pieces.clear();
    }
    
    public void testValidateMove() throws Exception {
       // test valid moves
       ChessCoords h8 = new ChessCoords("H", 8);
       Knight k1 = new Knight(ChessPiece.BLACK, h8);
       cb.pieces.add(k1);
       ChessCoords g6 = new ChessCoords("G", 6);
       k1.validateMove(g6);
       ChessCoords f7 = new ChessCoords("F", 7);
       k1.validateMove(f7);
       // move in a straight line (illegal)
       ChessCoords h6 = new ChessCoords("H", 6);
       try {
           k1.validateMove(h6);
           fail ("failed to throw expected illegal move exception");
       }
       catch (IllegalMoveException e) {
           //should get here
       }
       try {
           k1.validateMove(h6);
           fail ("failed to throw expected illegal move exception");
       }
       catch (IllegalMoveException e) {
           //should get here
       }
       // illegal diagonal
       ChessCoords a1 = new ChessCoords("A", 1);
       try {
           k1.validateMove(a1);
           fail ("failed to throw expected illegal move exception");
       }
       catch (IllegalMoveException e) {
           //should get here
       }
       ChessCoords e5 = new ChessCoords("E", 5);
       try {
           k1.validateMove(e5);
           fail ("failed to throw expected illegal move exception");
       }
       catch (IllegalMoveException e) {
           //should get here
       }
       // validate move to current location
       try {
           k1.validateMove(k1.location);
           fail ("Failed to throw expected IllegalMove exception, " +
           "moved to current location");
       }
       catch (IllegalMoveException e) {
           //should get here
       }
    }
    
    public void testMoveTo() throws Exception {
        ChessCoords h8 = new ChessCoords("H", 8);
        Knight k1 = new Knight(ChessPiece.BLACK, h8);
        ChessCoords g6 = new ChessCoords("G", 6);
        k1.validateMove(g6);
        ChessCoords f7 = new ChessCoords("F", 7);
        k1.moveTo(f7);
        
        // move in a straight line (illegal)
        ChessCoords h6 = new ChessCoords("H", 6);
        k1 = new Knight(ChessPiece.BLACK, h8);
        try {
            k1.validateMove(h6);
            fail ("failed to throw expected illegal move exception");
        }
        catch (IllegalMoveException e) {
            //should get here
        }
        k1 = new Knight(ChessPiece.BLACK, h8);
        try {
            k1.validateMove(h6);
            fail ("failed to throw expected illegal move exception");
        }
        catch (IllegalMoveException e) {
            //should get here
        }
        
        // illegal diagonal
        ChessCoords a1 = new ChessCoords("A", 1);
        k1 = new Knight(ChessPiece.BLACK, h8);
        try {
            k1.validateMove(a1);
            fail ("failed to throw expected illegal move exception");
        }
        catch (IllegalMoveException e) {
            //should get here
        }
        k1 = new Knight(ChessPiece.BLACK, h8);
        ChessCoords e5 = new ChessCoords("E", 5);
        try {
            k1.validateMove(e5);
            fail ("failed to throw expected illegal move exception");
        }
        catch (IllegalMoveException e) {
            //should get here
        }
        
        // validate moving to occupied coords
        k1 = new Knight(ChessPiece.BLACK, h8);
        Knight k2  = new Knight(ChessPiece.BLACK, g6);
        // add pieces to board:
        this.cb.pieces.add(k1); this.cb.pieces.add(k2);
        try {
            k1.moveTo(k2.location);
            fail ("Failed to throw expected CoordsOccupiedException");
        }
        catch (CoordsOccupiedException e) {
            //should get here
        }
        
        // test moving to current location (illegal)
        try {
            k1.moveTo(k1.location);
            fail ("Failed to throw expected IllegalMove exception, " +
                    "moved to current location");
        }
        catch (IllegalMoveException e) {
            //should get here
        }
        
        //invalid coords
        ChessCoords xv52 = new ChessCoords("XV", 52);
        k1 = new Knight(ChessPiece.WHITE, e5);
        try {
            k1.moveTo(xv52);
            fail ("failed to throw expected invalid coords exception");
        }
        catch (InvalidCoordsException e) {
            //should get here
        }
        assertEquals(e5, k1.location);
    }
}
