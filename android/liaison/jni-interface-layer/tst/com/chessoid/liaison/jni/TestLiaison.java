package com.chessoid.liaison.jni;

import junit.framework.TestCase;

public class TestLiaison extends TestCase {

	@Override public void setUp() {
		
	}
	
	@Override public void tearDown() {
		//TODO: does anything need to be done to shut the engine down properly?
	}
	
	public void testLib() {
		Liaison.testliaison();
				//just tests that we can call code in libchess
	}
	
	public void testInitEngine() {
		boolean success = Liaison.init_engine();
		assertTrue("Engine init'n failed", success);
	}
	
	public void testShowBoard() {
		assertTrue("Engine init'n failed.", Liaison.init_engine());
		Liaison.show_board();
	}
	
	public void testBoardAsString() {
		assertTrue("Engine init'n failed.", Liaison.init_engine());
		String board = Liaison.board_as_string();
		assertNull(board);	//for now returns null, not implemented yet... TODO: implement board_as_string in the C code
	}
	
	public void testValidateMove() { 
		assertTrue("Engine init'n failed.", Liaison.init_engine());
		boolean valid = Liaison.validate_move("a3");
		System.out.println("Valid: " + valid);
		assertTrue("Move expected to be valid, but wasn't", valid);		 
	}
}
