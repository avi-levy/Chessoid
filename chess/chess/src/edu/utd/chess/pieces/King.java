package edu.utd.chess.pieces;

import edu.utd.chess.board.ChessBoard;
import edu.utd.chess.board.ChessCoords;
import edu.utd.chess.exceptions.IllegalMoveException;
import edu.utd.chess.exceptions.InvalidCoordsException;

/**
 * The king chess piece.
 * @author troy
 *
 */
public class King extends ChessPiece {

	public King(String alignment, ChessCoords location) {
		super(alignment, location);
	}
	
	/**
	 * Kings can move in any direction but only by one step
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
        if (deltax > 1 || deltay > 1) {
        	throw new IllegalMoveException();
        }
	}
	

}
