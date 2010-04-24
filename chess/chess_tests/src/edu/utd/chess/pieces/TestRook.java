package edu.utd.chess.pieces;

import junit.framework.TestCase;
import edu.utd.chess.board.ChessBoard;
import edu.utd.chess.board.ChessCoords;
import edu.utd.chess.exceptions.CoordsOccupiedException;
import edu.utd.chess.exceptions.IllegalMoveException;
import edu.utd.chess.exceptions.InvalidCoordsException;
import edu.utd.chess.game.ChessGame;

public class TestRook extends TestCase {
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
        Rook r1 = new Rook(ChessPiece.BLACK, a1);
        //move forward by 1
        ChessCoords a2 = new ChessCoords("A", 2);
        r1.validateMove(a2);
        //move forward by 3
        ChessCoords a4 = new ChessCoords("A", 4);
        r1.validateMove(a4);
        //move right by 1
        ChessCoords b1 = new ChessCoords("B", 1);
        r1.validateMove(b1);
        //move right by 3
        ChessCoords d1 = new ChessCoords("D", 1);
        r1.validateMove(d1);
        
        //try to move illegally (not straight or diagonal)
        ChessCoords d2 = new ChessCoords("D", 2);
        try {
            r1.validateMove(d2);
            fail("failed to throw expected exception");
        }
        catch (IllegalMoveException e) {
            //should get here
        }
        // try to move diagonally (illegal)
        ChessCoords b2 = new ChessCoords("B", 2);
        try {
            r1.validateMove(b2);
            fail ("failed to thow expected illegal move exception");
        }
        catch (IllegalMoveException e) {
            //should get here
        }
        ChessCoords e5 = new ChessCoords("E", 5);
        try {
            r1.validateMove(e5);
            fail ("failed to thow expected illegal move exception");
        }
        catch (IllegalMoveException e) {
            //should get here
        }
        // validate move to current location
        try {
            r1.validateMove(r1.location);
            fail("failed to throw expected exception, "
                    + "move to current location");
        }
        catch (IllegalMoveException e) {
            //should get here
        }
    }
    
    public void testMoveTo() throws Exception {
        ChessCoords a1 = new ChessCoords("A", 1);
        ChessCoords h1 = new ChessCoords("H", 1);
        Rook r1 = new Rook(ChessPiece.BLACK, a1);
        Rook r2 = new Rook(ChessPiece.BLACK, h1);
        this.cb.pieces.add(r1);
        this.cb.pieces.add(r2);
       
        // move forward by one (legal)
        ChessCoords a2 = new ChessCoords("A", 2);
        r1.moveTo(a2);
        
        // move to occupied location (illegal)
        try {
            r1.moveTo(r2.location);
            fail("failed to throw expected illegal move exception");
        }
        catch (IllegalMoveException e) {
            //should get here
        }
        // make sure we really didn't move
        assertEquals(a2, r1.location);
        
        // invalid coords
        ChessCoords zz92 = new ChessCoords("ZZ", 92);
        try {
            r2.moveTo(zz92);
            fail("failed to throw expected ivalid coords exception");
        }
        catch (InvalidCoordsException e) {
            //should get here
        }
        
        //illegal moves
        ChessCoords a8 = new ChessCoords("a", 8);
        try {
            r2.moveTo(a8);
            fail("failed to throw expected illegal move exception");
        }
        catch (IllegalMoveException e) {
            //should get here
        }
        try {
            r1.moveTo(r1.location);
            fail("failed to throw illegal move to current coords");
        }
        catch (IllegalMoveException e) {
            //should get here
        }
        
        //make sure we're where we think we are (A2)
        assertEquals(new ChessCoords("A", 2), r1.location);
        ChessCoords c3 = new ChessCoords("C", 3);
        try {
            r1.moveTo(c3);
            fail("failed to throw expected illegal move exception");
        }
        catch (IllegalMoveException e) {
            //should get here
        }
        
        //occupied position
        r2.location = new ChessCoords("b", 2);
        try {
            r1.moveTo(r2.location);
            fail("failed to throw expected occupied exception");
        }
        catch (CoordsOccupiedException e) {
            //should get here
        }
     
        // try to move diagonally (illegal)
        r1 = new Rook(ChessPiece.BLACK, a1);
        ChessCoords b2 = new ChessCoords("B", 2);
        try {
            r1.moveTo(b2);
            fail ("failed to thow expected illegal move exception");
        }
        catch (IllegalMoveException e) {
            //should get here
        }
        r1 = new Rook(ChessPiece.BLACK, a1);
        ChessCoords e5 = new ChessCoords("E", 5);
        try {
            r1.moveTo(e5);
            fail ("failed to thow expected illegal move exception");
        }
        catch (IllegalMoveException e) {
            //should get here
        }
        
        // test move move to current location (illegal)
        r1 = new Rook(ChessPiece.BLACK, a1);
        try {
            r1.moveTo(r1.location);
            fail("failed to throw expected exception, "
                    + "move to current location");
        }
        catch (IllegalMoveException e) {
            //should get here
        }
        
        //try to move illegally (not straight or diagonal)
        ChessCoords d2 = new ChessCoords("D", 2);
        r1 = new Rook(ChessPiece.BLACK, a1);
        try {
            r1.moveTo(d2);
            fail("failed to throw expected exception");
        }
        catch (IllegalMoveException e) {
            //should get here
        }
    }
    
    
}
