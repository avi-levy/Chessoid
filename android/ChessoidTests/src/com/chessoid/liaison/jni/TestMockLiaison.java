package com.chessoid.liaison.jni;

import junit.framework.TestCase;

public class TestMockLiaison extends TestCase {
	String initdBoard = 
		"r n b q k b n r \n" +
		"p p p p p p p p \n" +
		". . . . . . . . \n" +
		". . . . . . . . \n" +
		". . . . . . . . \n" +
		". . . . . . . . \n" +
		"P P P P P P P P \n" +
		"R N B Q K B N R \n";
	
	public void testSetupBoard() {
		Liaison l = new Liaison();
		l.setupBoard();
		assertEquals(initdBoard, l.board_as_string());
	}
	
}
