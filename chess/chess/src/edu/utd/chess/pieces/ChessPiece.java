package edu.utd.chess.pieces;
import edu.utd.chess.board.ChessCoords;
import edu.utd.chess.exceptions.CoordsOccupiedException;
import edu.utd.chess.exceptions.IllegalMoveException;
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
	 * Cannot move to your current location (illegal move),
	 * cannot move to coordinates that don't exist on the board.  
	 * Throws exceptions if move is not valid.  Subclasses should 
	 * override this method to enforce movement constraints specific
	 * to each type of chess piece.
	 * <br/>
	 * The precedence of exceptions, in the case that there is
	 * more than one reason for a move to be invalid (e.g. a move
	 * to a location that is both off the chess board and would
	 * constitute an illegal move for the piece), is
	 * <br/>
	 * 1. InvalidCoordinatesException
	 * <br/>
	 * 2. IllegalMoveException
	 * @param coords
	 * @throws InvalidCoordsException supplied coords are invalid
	 * @throws IllegalMoveException if this piece is not
	 * allowed to move in this way. 
	 */
	public void validateMove(ChessCoords coords) 
	    throws
	        InvalidCoordsException,
	        IllegalMoveException
	{
	    // cannot move off the board
	    if (!ChessGame.INSTANCE.getChessBoard().validateCoords(coords)) {
	        throw new InvalidCoordsException();
	    }
	    // cannot move to current location
	    if (this.location.equals(coords)) {
	        throw new IllegalMoveException();
	    }
	}
	
//	/**
//	 * Test if the chess piece can move in a certain direction
//	 * a specified distance.  If the specified coordinates are 
//	 * blocked by another chess piece we cannot move there.
//
//	 * @param dir
//	 * @param distance
//	 * @return true if movement in this direction and distance is possible, 
//	 * false if not
//	 */
//	public boolean canMove(Direction dir, int distance);
	
	/**
	 * Move this chess piece to specified location.  If the 
	 * specified location is occupied by another chess piece,
	 * throws a CoordsOccupiedException.  It is the responsibility
	 * of a monitoring class to determine whether a CoordsOccupiedException
	 * indicates an illegal move (e.g. white piece cannot move to
	 * location occupied by another white piece) or should
	 * initiate a capture event.
	 * @param newLocation where you want to move to.
	 * @throws InvalidCoordsException if the coords provided were invalid
	 * @throws CoordsOccupiedException if the coords provided are occupied by 
	 * another chess piece
	 */
	public void moveTo(ChessCoords newLocation)
		throws 
			InvalidCoordsException,
			CoordsOccupiedException,
			IllegalMoveException
	{
	    this.validateMove(newLocation);
	    ChessPiece piece = ChessGame.INSTANCE.getChessBoard().getChessPieceAt(newLocation);
	    if (null != piece) {
	        throw new CoordsOccupiedException();
	    }
	    this.location = newLocation;
	}
	
	public String toString() {
	    return "Chess Piece, Location: " + this.location
	    + " , Class: " + this.getClass();
	}
}
