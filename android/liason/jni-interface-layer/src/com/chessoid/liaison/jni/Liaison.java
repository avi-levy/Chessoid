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
	
	
	
}
