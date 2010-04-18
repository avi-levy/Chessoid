package edu.utd.chess.pieces;
import edu.utd.chess.board.ChessCoords;
import edu.utd.chess.board.Direction;
import edu.utd.chess.exceptions.CoordsOccupiedException;
import edu.utd.chess.exceptions.InvalidCoordsException;
import edu.utd.chess.game.ChessGame;


/**
 * Abstraction of a chess piece, certain types of chess
 * pieces should inherit from this, e.g. Knight --> ChessPiece
 * @author troy
 *
 */
public abstract class ChessPiece {
	
	public static final String WHITE = "WHITE";
	public static final String BLACK = "BLACK";
	
	//what can chess pieces do, what attributes do they have?
	
	public String alignment;		//e.g. black or white
	public ChessCoords location;	//where am I on the chess board?
	
	/**
	 * Construct a new Chesspiece with specified alignment (black or white)
	 * and location
	 * @param alignment should be one of ChessPiece.BLACK or ChessPiece.WHITE
	 * @param location a ChessCoords object representing initial location on the board
	 */
	public ChessPiece(String alignment, ChessCoords location) {
		this.alignment = alignment;	this.location = location;
	}
	
	/**
	 * Tests if the chess piece can move to the specified coordinates.
	 * If the specified coordinates are blocked by another chess piece
	 * we cannot move there.
	 * (may be blocked by another piece).
	 * @param coords
	 * @return true or false
	 */
	public boolean canMoveTo(ChessCoords coords) {
		return ChessGame.INSTANCE.getChessBoard().isLocationOccupied(coords);
	}
	
//	/**
//	 * Test if the chess piece can move in a certain direction
//	 * a specified distance.  If the specified coordinates are 
//	 * blocked by another chess piece we cannot move there.
//
//	 * @param dir
//	 * @param distance
//	 * @return true if movement in this direction and distance is possible, false if not
//	 */
//	public boolean canMove(Direction dir, int distance);
	
	/**
	 * Move this chess piece to specified location. 
	 * @param newLocation
	 * @throws InvalidCoordsException if the coords provided were invalid
	 * @throws CoordsOccupiedException if the coords provided are occupied by 
	 * another chess piece
	 */
	public void moveTo(ChessCoords newLocation) 
		throws 
			InvalidCoordsException
	{
		if (!canMoveTo(newLocation)) {
			throw new InvalidCoordsException();
		}
		
	}
}
