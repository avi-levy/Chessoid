package edu.utd.chess.pieces;

import edu.utd.chess.board.ChessBoard;
import edu.utd.chess.board.ChessCoords;
import edu.utd.chess.exceptions.IllegalMoveException;
import edu.utd.chess.exceptions.InvalidCoordsException;

/**
 * The queen chess piece.
 * @author troy
 *
 */
public class Queen extends ChessPiece {

    public Queen(String alignment, ChessCoords location) {
        super(alignment, location);
    }

    /**
     * Queen can move any number of steps forward,
     * backward, or diagonally.
     * @see ChessPiece#validateMove(ChessCoords)
     * @param coords target coordinates on the chess board
     * @throws InvalidCoordsException
     * @throws IllegalMoveException
     */
    @Override
    public void validateMove(ChessCoords coords) 
        throws
            InvalidCoordsException,
            IllegalMoveException
    {
        super.validateMove(coords);
        int deltax = Math.abs(this.location.row - coords.row);
        int deltay = Math.abs (
                ChessBoard.translateCol(this.location.column)
                - ChessBoard.translateCol(coords.column));
        if (! (deltax == 0 || deltay == 0)) {
            if (deltax != deltay) {
                throw new IllegalMoveException();
            }
        }
    }
    
}
