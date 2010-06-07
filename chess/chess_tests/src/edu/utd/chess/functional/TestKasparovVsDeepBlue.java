package edu.utd.chess.functional;

import junit.framework.TestCase;
import edu.utd.chess.board.ChessBoard;
import edu.utd.chess.board.ChessCoords;
import edu.utd.chess.game.ChessGame;
import edu.utd.chess.pieces.Knight;
import edu.utd.chess.pieces.Pawn;

/**
 * Functional test, re-enacts the famous
 * match, move per move.  Exercises most of
 * the chess engine functionality. 
 * @author tnichols
 *
 */
public class TestKasparovVsDeepBlue extends TestCase {

	ChessGame game;
	ChessBoard cb;
	
	public void setUp() {
		game = ChessGame.INSTANCE;
		game.initialize();
		cb = game.getChessBoard();
	}
	
	public void tearDown() {
		game = null;
		cb = null;
	}
	
	/*
	 * Notes about the game:
	 * BLACK: Deep Blue
	 * WHITE: Kasparov 
	 */
	
	//TODO: consider inefficiencies of implementing ChessCoords as a class
	//e.g. how many unique instances of these things are getting stored in
	//memory and how long before they get garbage collected.  Runtime memory
	//analysis is probably in order...eventually
	
	public void testRunGame() throws Exception {
		//move 1a (Kasparov) - King's Knight to F3
		Knight whiteQueensKnight = (Knight) cb.getChessPieceAt("G", 1);
		game.processMove(whiteQueensKnight.location, new ChessCoords("F", 3));
		assertEquals(cb.getChessPieceAt("F", 3), whiteQueensKnight);
		assertNull(cb.getChessPieceAt("G", 1));
	
		//move 1b (Deep Blue) - Black Pawn D7 to D5
		Pawn blackp_d = (Pawn) cb.getChessPieceAt("D", 7);
		game.processMove(blackp_d.location, new ChessCoords("D", 5));
		
		//move 2a (Kasparov) - White Pawn G2 to G3
		game.processMove(new ChessCoords("G", 2), new ChessCoords("G", 3));
		
		//move 2b (Deep Blue) - Queen's Bishop to G4
		game.processMove(new ChessCoords("C", 8), new ChessCoords("G", 4));
		
		//move 3a (Kasparov) - Pawn B2 to B3
		game.processMove(new ChessCoords("B", 2), new ChessCoords("B", 3));
		
		//move 3b (Deep Blue) - Queen's Knight to D7
		game.processMove(new ChessCoords("B", 8), new ChessCoords("D", 7));
		
		//move 4a (Kasparov) - Queen's Bishop to B2
		game.processMove(new ChessCoords("C", 1), new ChessCoords("B", 2));
		
		//move 4b (Deep Blue) - Black Pawn E7 to E6
		game.processMove(new ChessCoords("E", 7), new ChessCoords("E", 6));
		
		//move 5a (Kasparov) - King's Bishop to G2
		game.processMove(new ChessCoords("F", 1), new ChessCoords("G", 2));
		
		//move 5b (Deep Blue) - King's Knight to F6
		game.processMove(new ChessCoords("E", 8), new ChessCoords("F", 6));
		
		//move 6a (Kasparov) - Castling - King to G1, King's Rook to F1
		//TODO : probably in flux as castling implentation changes, and we prevent "moving thru" other pieces
		//move king
		game.processMove(new ChessCoords("E", 1), new ChessCoords("G", 1));
		//move rook
		game.processMove(new ChessCoords("H", 1), new ChessCoords("F", 1));
	}
}
