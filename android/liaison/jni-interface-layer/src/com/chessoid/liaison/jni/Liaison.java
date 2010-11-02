package com.chessoid.liaison.jni;

/**
 * The JNI interface layer between the GNU chess engine (in C)
 * and the Java application.
 * 
 * @author troy
 *
 */
public class Liaison {

	static {
		System.loadLibrary("chess");
	}
	
	/**
	 * Method to test that we can connect to the engine.  Asks the engine
	 * to print its version number to stdout.
	 */
	public static native void testliaison();
	
	/**
	 * Initialize the engine.  Sets up the engine, starts a new game
	 * and gets the engine ready to accept commands.
	 * @return true if successful
	 */	
	public static native boolean init_engine();
	
	/**
	 * Print a string representation of the current chess board
	 * and the locations of all the pieces to stdout.
	 */
	public static native void show_board();
	
	/**
	 * Get a String representation of the current chess board.
	 * @return String representing the current chess board
	 * and all the pieces
	 */
	public static native String board_as_string();
	
	/**
	 * Ask the engine to test if a specified move is valid on the current
	 * chess board.
	 * @param sanMove String of the move to test, in SAN (Standard
	 * Algebraic Notation) format.
	 * @return true if the move is valid
	 */
	public static native boolean validate_move(String sanMove);
	
	/**
	 * Ask the engine to run the specified command string.  This isn't
	 * very well-tested.
	 * @param engineCommand String representing an engine command, e.g 
	 * a SAN (Standard Algebraic Notation) move.
	 * @return response from the engine as a String
	 * TODO native implementation does not currently return the string
	 * only prints to stdout 
	 */
	public static native String input_cmd(String engineCommand);
	
	/**
	 * Ask the engine to make the specified move.
	 * @param sanMove String of the move to test, in SAN (Standard
	 * Algebraic Notation) format.
	 */
	public static native boolean doMove(String sanMove);
	
	/**
	 * Allow the computer to make a move.
	 */
	public static native void iterate();
	
}
