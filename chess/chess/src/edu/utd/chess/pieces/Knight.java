package edu.utd.chess.pieces;

import edu.utd.chess.board.ChessBoard;
import edu.utd.chess.board.ChessCoords;
import edu.utd.chess.exceptions.IllegalMoveException;
import edu.utd.chess.exceptions.InvalidCoordsException;

/**
 * The knight chess piece.
 * @author troy
 *
 */
public class Knight extends ChessPiece {

    public Knight(String alignment, ChessCoords location) {
        super(alignment, location);
    }
    
    /**
     * Knights can move x=(=/-)2,y=(+/-)1 or 
     * x=(+/-)1,y=(+/-)2 places
     * from their current location.
     * @param coords target coordinates on the chess board
     * @throws InvalidCoordsException supplied coords are invalid
	 * @throws IllegalMoveException if this piece is not
	 * allowed to move in this way. 
     */
    @Override
    public void validateMove(ChessCoords coords) 
        throws
            InvalidCoordsException,
            IllegalMoveException
    {
        super.validateMove(coords);
        // knights can never move in a straight line
        if (coords.column.equals(this.location.column)
                || coords.row == this.location.row)
        {
            throw new IllegalMoveException();
        }
        // x or y can never be > 2
        // if x = 2 then y must be 1, if y = 2, then x must be 1
        int deltaX = Math.abs(this.location.row - coords.row);
        int deltaY = Math.abs(
                ChessBoard.translateCol(this.location.column)
                - ChessBoard.translateCol(coords.column));
        if (! ((deltaX <= 2 && deltaY <= 1) || deltaX <= 1 && deltaY <= 2)) {
            throw new IllegalMoveException();
        }
    }

}
