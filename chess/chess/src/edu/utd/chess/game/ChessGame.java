package edu.utd.chess.game;

import java.util.ArrayList;

import edu.utd.chess.board.ChessBoard;
import edu.utd.chess.pieces.ChessPiece;

/**
 * Singleton
 * This will be the "main" class for the game.
 * You ask the ChessGame for the ChessBoard, the current
 * state of the game, whose turn it is to go next, etc.
 */
public class ChessGame {
	ChessBoard chessBoard;
	
	public static final ChessGame INSTANCE = new ChessGame();
	
	private ChessGame() {
		//TODO : init logic create chess pieces when a new board is created
		chessBoard = new ChessBoard(new ArrayList<ChessPiece>());
	}
	
	public ChessGame(ChessBoard chessBoard) {
		this.chessBoard = chessBoard;
	}
	
	/**
	 * Get the ChessBoard for the current game
	 */
	public ChessBoard getChessBoard() {
		return this.chessBoard;
	}
}
