package edu.utd.chess.pieces;

import junit.framework.TestCase;
import edu.utd.chess.board.ChessBoard;
import edu.utd.chess.board.ChessCoords;
import edu.utd.chess.exceptions.CoordsOccupiedException;
import edu.utd.chess.exceptions.IllegalMoveException;
import edu.utd.chess.game.ChessGame;

public class TestQueen extends TestCase {
    ChessBoard cb;
    
    public void setUp() {
        cb = ChessGame.INSTANCE.getChessBoard();
        cb.pieces.clear();
    }
    
    public void tearDown() {
        cb.pieces.clear();
    }
    
    public void testValidateMove() throws Exception {
        ChessCoords e1 = new ChessCoords("E", 1);
        Queen q = new Queen(ChessPiece.BLACK, e1);
        // move in straight line
        ChessCoords e8 = new ChessCoords("E", 8);
        q.validateMove(e8);
        // illegal move
        ChessCoords f8 = new ChessCoords("F", 8);
        try {
            q.validateMove(f8);
            fail("failed to throw expected illegal move exception");
        }
        catch (IllegalMoveException e) {
            // should get here
        }
        // move sideways
        ChessCoords a1 = new ChessCoords("A", 1);
        q.validateMove(a1);
        // move diagonally
        ChessCoords h4 = new ChessCoords("H", 4);
        q.validateMove(h4);
        // move to our current location
        try {
            q.validateMove(q.location);
            fail("failed to throw IllegalMoveException moving to current location");
        }
        catch (IllegalMoveException e) {
            // should get here
        }
    }
    
    public void testMoveTo() throws Exception {
        ChessCoords e1 = new ChessCoords("E", 1);
        Queen q = new Queen(ChessPiece.BLACK, e1);
        // move in straight line
        ChessCoords e8 = new ChessCoords("E", 8);
        q.moveTo(e8);
        // illegal move
        ChessCoords f8 = new ChessCoords("F", 8);
        q = new Queen(ChessPiece.BLACK, e1);
        try {
            q.moveTo(f8);
            fail("failed to throw expected illegal move exception");
        }
        catch (IllegalMoveException e) {
            // should get here
        }
        // move sideways
        ChessCoords a1 = new ChessCoords("A", 1);
        q = new Queen(ChessPiece.BLACK, e1);
        q.moveTo(a1);
        // move diagonally
        ChessCoords h4 = new ChessCoords("H", 4);
        q = new Queen(ChessPiece.BLACK, e1);
        q.moveTo(h4);
        // move to our current location
        q = new Queen(ChessPiece.BLACK, e1);
        try {
            q.moveTo(q.location);
            fail("failed to throw IllegalMoveException moving to current location");
        }
        catch (IllegalMoveException e) {
            // should get here
        }
        // move to an occupied cell
        Pawn p = new Pawn(ChessPiece.BLACK, a1);
        q = new Queen(ChessPiece.BLACK, e1);
        this.cb.pieces.add(p);
        try {
            q.moveTo(p.location);
            fail("failed to throw expected CoordsOccupiedException");
        }
        catch (CoordsOccupiedException e) {
            // should get here
        }
    }
}
