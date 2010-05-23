package edu.utd.chess.exceptions;

public class UnexpectedChessGameException extends Exception {
	private static final long serialVersionUID = -2153047464698819672L;
	
	public UnexpectedChessGameException(String msg) {
		super(msg);
	}
}
