package com.chessoid.liaison.jni;

import com.chessoid.model.Board;

public class Liaison {
	
	public static final Liaison INSTANCE = new Liaison(); 
	
	boolean debug = false;
	
	Board board = new Board();

	void debug(String msg) {
		if (debug) System.out.println("MOCK LIAISON: ".concat(msg));
	}
	
	void setupBoard() {
		if (null == board) board = new Board();
		board.setPieceAt('r', 1, 8);
		board.setPieceAt('n', 2, 8);
		board.setPieceAt('b', 3, 8);
		board.setPieceAt('q', 4, 8);
		board.setPieceAt('k', 5, 8);
		board.setPieceAt('b', 6, 8);
		board.setPieceAt('n', 7, 8);
		board.setPieceAt('r', 8, 8);
		for (int c=1; c <= 8; c++)
			board.setPieceAt('p', c, 7);
		board.setPieceAt('R', 1, 1);
		board.setPieceAt('N', 2, 1);
		board.setPieceAt('B', 3, 1);
		board.setPieceAt('Q', 4, 1);
		board.setPieceAt('K', 5, 1);
		board.setPieceAt('B', 6, 1);
		board.setPieceAt('N', 7, 1);
		board.setPieceAt('R', 8, 1);
		for (int c=1; c <=8; c++)
			board.setPieceAt('P', c, 2);
	}
	
	public void testliaison() {
		debug("test mock liaison.");
	}
	
	public void debugMode(boolean onOrOff) { 
		if (onOrOff) System.out.println("MOCK LIAISON: debug mode ON.");
		else System.out.println("MOCK LIAISON: debug mode OFF.");
	}
	
	public boolean init_engine() {
		setupBoard();
		debug("mock engine initialized.");
		return true;		
	}
	
	public void show_board() {
		System.out.println(board.toString());
	}
	
	public String board_as_string() {
		return board.toString();
	}
	
	public Board board(Board oldBoard) {
		debug("returning passed in board unchanged.");
		for (int r=1; r <=8; r++) {
			for (int c=1; c <=8; c++) {
				char p = this.board.getPieceAt(c, r);
				oldBoard.setPieceAt(p, c, r);
			}
		}
		return oldBoard;
	}
	
	public boolean validate_move(String sanMove) {
		debug("validating move: ".concat(sanMove.concat(" (mock always returns true)")));
		return true;
	}
	
	public String input_cmd(String engineCommand) {
		debug("mock input command: ".concat(engineCommand));
		return null;
	}
	
	public boolean doMove(String sanMove) {
		debug("mock doMove: ".concat(sanMove).concat(" (doesnt really do anything)"));
		return true;
	}
	
	public void iterate() {
		debug("mock engine iteration...doesnt really do anything");
	}
	
}
