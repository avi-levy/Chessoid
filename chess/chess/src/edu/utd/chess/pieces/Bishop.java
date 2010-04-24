package edu.utd.chess.pieces;

import edu.utd.chess.board.ChessBoard;
import edu.utd.chess.board.ChessCoords;
import edu.utd.chess.exceptions.IllegalMoveException;
import edu.utd.chess.exceptions.InvalidCoordsException;

/**
 * The bishop chess piece.
 * @author troy
 *
 */
public class Bishop extends ChessPiece {

	public Bishop(String alignment, ChessCoords location) {
		super(alignment, location);
	}
	
	/**
	 * Bishops move an unlimited number of steps, only diagonally.
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
	     if (deltax != deltay) {
	    	 throw new IllegalMoveException();
	     }	
	}

}
