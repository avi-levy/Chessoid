package edu.utd.chess.exceptions;


/**
 * Thrown e.g. when you try to move a chess piece into a coordinate
 * that does not exist on the chess board, such as H 9.
 * @author troy
 *
 */
public class InvalidCoordsException extends Exception {

	private static final long serialVersionUID = 3224558840108733367L;
	
}
