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
	
	public static native void testliaison();
	
	public static native boolean init_engine();
	
	public static native void show_board();
	
	public static native String board_as_string();
	
	public static native boolean validate_move(String sanMove);
	
}
