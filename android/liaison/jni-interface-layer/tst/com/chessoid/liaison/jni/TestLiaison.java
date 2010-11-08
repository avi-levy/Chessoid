package com.chessoid.liaison.jni;

import junit.framework.TestCase;

import com.chessoid.model.Board;

public class TestLiaison extends TestCase {
	Liaison liaison;
	String initdBoard = 
		"r n b q k b n r \n" +
		"p p p p p p p p \n" +
		". . . . . . . . \n" +
		". . . . . . . . \n" +
		". . . . . . . . \n" +
		". . . . . . . . \n" +
		"P P P P P P P P \n" +
		"R N B Q K B N R \n";
	
	@Override public void setUp() {
		liaison = new Liaison();
	}
	
	@Override public void tearDown() {
		liaison = null;
		//TODO: does anything need to be done to shut the engine down properly?
	}
	
	public void testLib() {
		liaison.testliaison();
	}
	
	public void testInitEngine() {
		boolean success = liaison.init_engine();
		assertTrue("Engine init'n failed", success);
	}
	
	public void testShowBoard() {
		assertTrue("Engine init'n failed.", liaison.init_engine());
		liaison.show_board();
	}
	
	public void testBoardAsString() {
		assertTrue("Engine init'n failed.", liaison.init_engine());
		String 
		board = liaison.board_as_string();
		board = liaison.board_as_string();
		board = liaison.board_as_string();
		board = liaison.board_as_string();
		assertEquals(initdBoard, board);
	}
	
	public void testBoardAsStringEqualsBoardToString() {
		assertTrue("Engine init'n failed.", liaison.init_engine());
		String boardAsString = liaison.board_as_string();
		Board board = new Board();
		liaison.board(board);
		assertEquals(board.toString(), boardAsString);
	}
	
	public void testBoard() {
		assertTrue("Engine init'n failed.", liaison.init_engine());
		Board board = new Board();
		board = liaison.board(board);
		assertEquals('r', board.getPieceAt('a', 8));
		assertEquals('r', board.getPieceAt('A', 8));
		assertEquals('N', board.getPieceAt('G', 1));
		assertEquals('N', board.getPieceAt('g', 1));
		assertEquals(initdBoard, board.toString());
		board.setPieceAt('X', 'D', 4);	// should override local changes when we call call board() again
		liaison.board(board);
		assertEquals(initdBoard, board.toString());
		assertEquals('r', board.getPieceAt('a', 8));
		assertEquals('r', board.getPieceAt('A', 8));
		assertEquals('N', board.getPieceAt('G', 1));
		assertEquals('N', board.getPieceAt('g', 1));
		assertTrue("Unexpected failure moving a piece.", liaison.doMove("a4"));
		liaison.board(board);
		assertEquals('P', board.getPieceAt('a', 4));
		assertNull(liaison.board(null));
	}
	
	public void testValidateMove() {
		assertTrue("Engine init'n failed.", liaison.init_engine());
		String move = "a3";
		boolean valid = liaison.validate_move(move);
		assertTrue("Move (" + move + ")expected to be valid, but wasn't", valid);
		move = "a7";
		valid = liaison.validate_move(move);
		assertFalse("Move (" + move + ")expected to be invalid, but was valid", valid);
		move = "a2";
		valid = liaison.validate_move(move);
		assertFalse("Move (" + move + ")expected to be invalid, but was valid", valid);
		move = "j10";
		valid = liaison.validate_move(move);
		assertFalse("Move (" + move + ")expected to be invalid, but was valid", valid);
		move = "z22";
		valid = liaison.validate_move(move);
		assertFalse("Move (" + move + ")expected to be invalid, but was valid", valid);
		move = "pa4";
		valid = liaison.validate_move(move);
		assertFalse("Move (" + move + ")expected to be invalid, but was valid", valid);
		move = "Pa4";
		valid = liaison.validate_move(move);
		assertFalse("Move (" + move + ")expected to be invalid, but was valid", valid);
		move = "B4";
		valid = liaison.validate_move(move);
		assertFalse("Move (" + move + ")expected to be invalid, but was valid", valid);

	}
	
	public void testInputCmd() {	// TODO this whole input cmd stuff doesn't work well, do we even need it?
		assertTrue("Engine init'n failed.", liaison.init_engine());
		liaison.input_cmd("help");	// TODO test return string from input_cmd() once implemented
		liaison.input_cmd("a3");
	}
	
	public void testDoMove() {
		assertTrue("Engine init'n failed.", liaison.init_engine());
		assertTrue(liaison.doMove("a3"));
		liaison.show_board();
	}
	
	public void testIterate() {
		assertTrue("Engine init'n failed.", liaison.init_engine());
		System.out.println("Test move:");
		liaison.doMove("a3");
		liaison.show_board();
		System.out.println("Chessoid's move:");
		liaison.iterate();
	}
	
	public void testDebug() throws Exception {
		liaison.debugMode(true);
		System.out.println("Liason test with debugging ON:");
		liaison.testliaison();
		liaison.debugMode(false);
		System.out.println("Liaison test with debugging OFF");
		liaison.testliaison();
		liaison.init_engine();
		liaison.show_board();
	}
}
