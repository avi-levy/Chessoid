package edu.utd.chess.game;

import java.util.ArrayList;
import java.util.List;

import edu.utd.chess.board.ChessBoard;
import edu.utd.chess.board.ChessCoords;
import edu.utd.chess.pieces.Bishop;
import edu.utd.chess.pieces.ChessPiece;
import edu.utd.chess.pieces.King;
import edu.utd.chess.pieces.Knight;
import edu.utd.chess.pieces.Pawn;
import edu.utd.chess.pieces.Queen;
import edu.utd.chess.pieces.Rook;

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
	    initialize();
	}
	
	public ChessGame(ChessBoard chessBoard) {
		this.chessBoard = chessBoard;
	}
	
	/**
	 * (re-) initialize the chess game, i.e. start a new game.
	 */
	public void initialize() {
		chessBoard = new ChessBoard(getDefaultChessSet());
	}
	
	/**
	 * Get the ChessBoard for the current game
	 */
	public ChessBoard getChessBoard() {
		return this.chessBoard;
	}
	
	/**
	 * Creates a set of chess pieces (i.e. an ArrayList) in the standard 
	 * 32 piece configuration (16 black, 16 white).
	 * @return <code>List</code> of <code>ChessPiece</code>s
	 */ //TODO: test me!
	public List<ChessPiece> getDefaultChessSet() {
		ArrayList<ChessPiece> pieces = new ArrayList<ChessPiece>(32);
		
		//black pawns
		byte ascii[] = {65};
		for (int i=0; i < 8; i++) {
			//A7 - H7
			ChessCoords coords = new ChessCoords(new String(ascii), 7);
			Pawn p = new Pawn(ChessPiece.BLACK, coords);
			ascii[0] = (byte) (ascii[0] + (byte) 1);
		}
		
		//the rest
		ChessCoords a8 = new ChessCoords("A", 8);
		Rook r1 = new Rook(ChessPiece.BLACK, a8);
		pieces.add(r1);
		
		ChessCoords b8 = new ChessCoords("B", 8);
		Knight k1 = new Knight(ChessPiece.BLACK, b8);
		pieces.add(k1);
		
		ChessCoords c8 = new ChessCoords("C", 8);
		Bishop b1 = new Bishop(ChessPiece.BLACK, c8);
		pieces.add(k1);
		
		ChessCoords d8 = new ChessCoords("D", 8);
		King k = new King(ChessPiece.BLACK, d8);
		pieces.add(k);
		
		ChessCoords e8 = new ChessCoords("E", 8);
		Queen q = new Queen(ChessPiece.BLACK, e8);
		pieces.add(q);
		
		ChessCoords f8 = new ChessCoords("F", 8);
		Bishop b2 = new Bishop(ChessPiece.BLACK, f8);
		pieces.add(b2);
		
		ChessCoords g8 = new ChessCoords("G", 8);
		Knight k2 = new Knight(ChessPiece.BLACK, g8);
		pieces.add(k2);
		
		ChessCoords h8 = new ChessCoords("H", 8);
		Rook r2 = new Rook(ChessPiece.BLACK, h8);
		pieces.add(r2);
		
		//TODO : add the white pieces
		
		
		return pieces;
	}
}
