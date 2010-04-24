package edu.utd.chess.pieces;

import edu.utd.chess.board.ChessCoords;
import edu.utd.chess.exceptions.CoordsOccupiedException;
import edu.utd.chess.exceptions.IllegalMoveException;
import edu.utd.chess.exceptions.InvalidCoordsException;
import edu.utd.chess.game.ChessGame;

/**
 * The pawn chess piece.
 * @author troy
 *
 */
public class Pawn extends ChessPiece {
	private boolean firstMove = true;
	
	public Pawn(String alignment, ChessCoords location) {
		super(alignment, location);
	}
	
	/**
	 * Pawns can only move forward one space, except on their
	 * first move when they can optionally move two spaces.
	 * Pawns must move diagonally when capturing, but cannot
	 * move diagonally unless capturing.
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
		//pawns can only move straight ahead unless capturing
	    if (this.location.column != coords.column) {
	    	ChessPiece piece = ChessGame.INSTANCE.getChessBoard().getChessPieceAt(coords);
	    	if (null == piece) {
	    		//if we're trying to move diagonally and there is nothing to try
	    		//and capture, then it's an illegal move
	        	throw new IllegalMoveException();
	    	}
	    }
	    //can't move backwards
	    if (coords.row < this.location.row) {
	        throw new IllegalMoveException();
	    }
	    //can't move more than 2 ahead on first move, or 
	    //more than 1 ahead on any subsequent moves
		if (firstMove 
		        ? coords.row - this.location.row > 2
		        : coords.row - this.location.row > 1)
		{
		    throw new IllegalMoveException();
		}
	}

	/**
	 * Calls super.moveTo(), and sets a state variable
	 * to indicate that this Pawn has moved at least once
	 * (since Pawns can only move two spaces on their 
	 * first move). 
	 */
	@Override
	public void moveTo(ChessCoords newLocation)
	    throws
	        InvalidCoordsException,
	        CoordsOccupiedException,
	        IllegalMoveException
	{
	    super.moveTo(newLocation);
	    firstMove = false;
	}
	
}
