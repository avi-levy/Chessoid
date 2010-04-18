package edu.utd.chess.pieces;

import edu.utd.chess.board.ChessCoords;
import edu.utd.chess.board.Direction;
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
	
//	@Override
//	public boolean canMove(Direction dir, int distance) {
//		// TODO Auto-generated method stub
//		return false;
//	}

	@Override
	public boolean canMoveTo(ChessCoords coords) {
		//cannot move to our current location
		if (coords.equals(this.location)) {
			return false;
		}
		//coords must actually exist on the board
		if (!ChessGame.INSTANCE.getChessBoard().validateCoords(coords)) {
			return false;
		}
		
		
		
		return true;
	}

	
}
