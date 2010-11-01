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
		String move = "a3";
		boolean valid = Liaison.validate_move(move);
		assertTrue("Move (" + move + ")expected to be valid, but wasn't", valid);
		move = "a7";
		valid = Liaison.validate_move(move);
		assertFalse("Move (" + move + ")expected to be invalid, but was valid", valid);
		move = "a2";
		valid = Liaison.validate_move(move);
		assertFalse("Move (" + move + ")expected to be invalid, but was valid", valid);
		move = "j10";
		valid = Liaison.validate_move(move);
		assertFalse("Move (" + move + ")expected to be invalid, but was valid", valid);
		move = "z22";
		valid = Liaison.validate_move(move);
		assertFalse("Move (" + move + ")expected to be invalid, but was valid", valid);
	}
	
	public void testInputCmd() {
		assertTrue("Engine init'n failed.", Liaison.init_engine());
		Liaison.input_cmd("help");	// TODO test return string from input_cmd() once implemented
		Liaison.input_cmd("a3");
	}
	
	public void testIterate() {
		assertTrue("Engine init'n failed.", Liaison.init_engine());
		Liaison.iterate();
		assertTrue("Engine init'n failed.", Liaison.init_engine());
		Liaison.input_cmd("a3");
		Liaison.iterate();
	}
}
