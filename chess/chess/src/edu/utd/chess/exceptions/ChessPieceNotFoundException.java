package edu.utd.chess.exceptions;

/**
 * This exception is thrown e.g when the <code>ChessBoard</code>does
 * not contain a <code>ChessPiece</code> that we asked it for.
 * @author troy
 *
 */
public class ChessPieceNotFoundException extends Exception {

	private static final long serialVersionUID = -5479350300513294390L;
	
	/**
	 * Create a new ChessPieceNotFoundException with
	 * specified localized detail message.
	 * @param msg what exactly caused this exception
	 */
	public ChessPieceNotFoundException(String msg) {
		super(msg);
	}

}
