package edu.utd.chess.exceptions;

/**
 * Exception type indicating that something went wrong inside the chess engine.
 * Usually indicates some unexpected error condition, as opposed to expected
 * exception types that might get thrown during normal processing.  In other 
 * words, if this type is thrown, something bad probably happened.
 * @author troy
 */
public class CoreChessGameException extends Exception {
	private static final long serialVersionUID = -2153047464698819672L;
	
	public CoreChessGameException(String msg) {
		super(msg);
	}
}
