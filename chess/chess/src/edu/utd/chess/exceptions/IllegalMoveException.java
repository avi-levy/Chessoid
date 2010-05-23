package edu.utd.chess.exceptions;


/**
 * Thrown when a piece tries to move in a way 
 * which its class is not allowed to, e.g moving
 * a King forward by 3 places (only allowed 1).
 * @author troy
 *
 */
public class IllegalMoveException extends Exception {

    private static final long serialVersionUID = 382359780959713367L;

}
