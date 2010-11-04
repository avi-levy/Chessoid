package com.chessoid.liaison.jni;

import com.chessoid.model.Board;

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
	public native void testliaison();
	
	/**
	 * Toggle engine liaison debugging.  
	 * @param onOrOff if true, the JNI liaison layer prints debug
	 * messasges to stdout.  false to turn debugging off. 
	 */
	public native void debugMode(boolean onOrOff);
	
	/**
	 * Initialize the engine.  Sets up the engine, starts a new game
	 * and gets the engine ready to accept commands.  
	 * 
	 * You should call init_engine() before trying to interact with the 
	 * engine or you risk crashing the VM.
	 * 
	 * @return true if successful
	 */	
	public native boolean init_engine();
	
	/**
	 * Print a string representation of the current chess board
	 * and the locations of all the pieces to stdout.
	 */
	public native void show_board();
	
	/**
	 * Get a String representation of the current chess board.
	 * @return String representing the current chess board
	 * and all the pieces
	 */
	public native String board_as_string();
	
	/**
	 * Query the engine and build a model of the chess board and pieces
	 * @param oldBoard Board object that will be updated and sync'd with
	 * the engine's chess board model.  Passing in null will cause board()
	 * to return null.
	 * @return a Board object which matches the state of the engine's
	 * chess board.  This is only for convenience; the board
	 * instance passed in is really the same instance as the one
	 * returned.
	 */
	public native Board board(Board oldBoard);
	
	
	/**
	 * Ask the engine to test if a specified move is valid on the current
	 * chess board.
	 * @param sanMove String of the move to test, in SAN (Standard
	 * Algebraic Notation) format.
	 * @return true if the move is valid
	 */
	public native boolean validate_move(String sanMove);
	
	/**
	 * Ask the engine to run the specified command string.  This isn't
	 * very well-tested.
	 * @param engineCommand String representing an engine command, e.g 
	 * a SAN (Standard Algebraic Notation) move.
	 * @return response from the engine as a String
	 * TODO native implementation does not currently return the string
	 * only prints to stdout 
	 */
	public native String input_cmd(String engineCommand);
	
	/**
	 * Ask the engine to make the specified move.
	 * @param sanMove String of the move to test, in SAN (Standard
	 * Algebraic Notation) format.
	 */
	public native boolean doMove(String sanMove);
	
	/**
	 * Allow the computer to make a move.
	 */
	public native void iterate();
	
}
