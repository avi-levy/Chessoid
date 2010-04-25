package edu.utd.chess.game;

import java.util.HashMap;

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
	
	private HashMap<ChessCoords, ChessPiece> defaultChessSet;
	
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
	public HashMap<ChessCoords, ChessPiece> getDefaultChessSet() {
		if (null == this.defaultChessSet) {
			HashMap<ChessCoords, ChessPiece> pieces = new HashMap<ChessCoords, ChessPiece>(32);
			
			//black pawns
			byte ascii[] = {65};
			for (int i=0; i < 8; i++) {
				//A7 - H7
				ChessCoords coords = new ChessCoords(new String(ascii), 7);
				Pawn p = new Pawn(ChessPiece.BLACK, coords);
				ascii[0] = (byte) (ascii[0] + (byte) 1);
				pieces.put(coords, p);
			}
			
			//the rest
			ChessCoords a8 = new ChessCoords("A", 8);
			Rook r1 = new Rook(ChessPiece.BLACK, a8);
			pieces.put(a8, r1);
			
			ChessCoords b8 = new ChessCoords("B", 8);
			Knight k1 = new Knight(ChessPiece.BLACK, b8);
			pieces.put(b8, k1);
			
			ChessCoords c8 = new ChessCoords("C", 8);
			Bishop b1 = new Bishop(ChessPiece.BLACK, c8);
			pieces.put(c8, k1);
			
			ChessCoords d8 = new ChessCoords("D", 8);
			King k = new King(ChessPiece.BLACK, d8);
			pieces.put(d8, k);
			
			ChessCoords e8 = new ChessCoords("E", 8);
			Queen q = new Queen(ChessPiece.BLACK, e8);
			pieces.put(e8, q);
			
			ChessCoords f8 = new ChessCoords("F", 8);
			Bishop b2 = new Bishop(ChessPiece.BLACK, f8);
			pieces.put(f8, b2);
			
			ChessCoords g8 = new ChessCoords("G", 8);
			Knight k2 = new Knight(ChessPiece.BLACK, g8);
			pieces.put(g8, k2);
			
			ChessCoords h8 = new ChessCoords("H", 8);
			Rook r2 = new Rook(ChessPiece.BLACK, h8);
			pieces.put(h8, r2);
			
			//white pawns
			for (int i=0; i < 8; i++) {
				//A2 - H2
				ChessCoords coords = new ChessCoords(new String(ascii), 2);
				Pawn p = new Pawn(ChessPiece.WHITE, coords);
				ascii[0] = (byte) (ascii[0] + (byte) 1);
				pieces.put(coords, p);
			}
			
			ChessCoords a1 = new ChessCoords("A", 1);
			r1 = new Rook(ChessPiece.WHITE, a1);
			pieces.put(a1, r1);
			
			ChessCoords b1_coords = new ChessCoords("B", 1);
			k1 = new Knight(ChessPiece.WHITE, b1_coords);
			pieces.put(b1_coords, k1);
			
			ChessCoords c1 = new ChessCoords("C", 1);
			b1 = new Bishop(ChessPiece.WHITE, c1);
			pieces.put(c1, b1);
			
			ChessCoords d1 = new ChessCoords("D", 1);
			k = new King(ChessPiece.WHITE, d1);
			pieces.put(d1, k);
			
			ChessCoords e1 = new ChessCoords("E", 1);
			q = new Queen(ChessPiece.WHITE, e1);
			pieces.put(e1, q);
			
			ChessCoords f1 = new ChessCoords("F", 1);
			b2 = new Bishop(ChessPiece.WHITE, f1);
			pieces.put(f1, b2);
			
			ChessCoords g1 = new ChessCoords("G", 1);
			k2 = new Knight(ChessPiece.WHITE, g1);
			pieces.put(g1, k2);
			
			ChessCoords h1 = new ChessCoords("H", 1);
			r2 = new Rook(ChessPiece.WHITE, h1);
			pieces.put(h1, r2);
			
			this.defaultChessSet = pieces;
		}
		return this.defaultChessSet;
	}
}
