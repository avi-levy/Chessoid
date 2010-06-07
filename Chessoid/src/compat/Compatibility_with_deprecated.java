package compat;

import internals.app.R;
import internals.ui.ChessTileView;
import internals.ui.Msg;
import models.defaults.DefaultSettings;
import structures.StateData;
import structures.StateDataAtom;
import android.graphics.drawable.Drawable;
import android.util.Log;
import edu.utd.chess.board.ChessBoard;
import edu.utd.chess.board.ChessCoords;
import edu.utd.chess.exceptions.ChessPieceNotFoundException;
import edu.utd.chess.exceptions.HomicideException;
import edu.utd.chess.exceptions.IllegalMoveException;
import edu.utd.chess.exceptions.InvalidCoordsException;
import edu.utd.chess.exceptions.SuicideException;
import edu.utd.chess.exceptions.UnexpectedChessGameException;
import edu.utd.chess.game.ChessGame;
import edu.utd.chess.pieces.Bishop;
import edu.utd.chess.pieces.ChessPiece;
import edu.utd.chess.pieces.King;
import edu.utd.chess.pieces.Knight;
import edu.utd.chess.pieces.Pawn;
import edu.utd.chess.pieces.Queen;
import edu.utd.chess.pieces.Rook;

/**
 * Provides casting from deprecated data formats in chess project
 * 
 * @author avius
 * 
 */
public class Compatibility_with_deprecated {
	public static ChessTileView reference;

	public static StateData cast(ChessBoard b, String s) {
		StateData d = new StateData();// constructs empty statedata
		for (short i = 0; i < DefaultSettings.BoardDimensions[0]; i++) {
			for (short j = 0; j < DefaultSettings.BoardDimensions[1]; j++) {
//				try{
				d.atoms[i][j] = new StateDataAtom(b.board[i][j], new short[] {
						i, j });// implicit
				// cast from
				// short to
				// int
//				} catch(NullPointerException e) {
//					Log.v("NPE","happened in Compat_dep:cast(board,string)");
//				}
			}

		}
		
		d.displayMessage = (s == null) ? null : new String(s);
		return d;
	}

	/**
	 * Casts from the "generic" StateDataAtom convention to a Drawable, using
	 * the method specified in ChessTileView (deprecated)
	 * 
	 * @param atom
	 *            the StateDataAtom
	 * @return Representation of atom as a Drawable
	 */
	public static Integer cast(StateDataAtom atom) {
//		Wished this could have worked:
//		if (reference == null) {
//			reference = new ChessTileView(null, 0, null);
//		}
//		reference.setChessPiece(atom.piece);
//
//		return reference.getBackground();
		//Instead we copy paste from chesstileview...
		ChessPiece piece = atom.piece;
		if (null == piece) {
			return null;
		} else {
			//TODO: Help, I'm ugly!
			if (piece instanceof Pawn) {
				if (piece.alignment.equals("BLACK")) {
					return R.drawable.pawn_black;
				}
				else {
					return (R.drawable.pawn_white);
				}
			}
			else if (piece instanceof Rook) {
				if (piece.alignment.equals("BLACK")) {
					return (R.drawable.rook_black);
				}
				else {
					return (R.drawable.rook_white);
				}
			}
			else if (piece instanceof Bishop) {
				if (piece.alignment.equals("BLACK")) {
					return (R.drawable.bishop_black);
				}
				else {
					return (R.drawable.bishop_white);
				}
			}
			else if (piece instanceof Knight) {
				if (piece.alignment.equals("BLACK")) {
					return (R.drawable.knight_black);
				}
				else {
					return (R.drawable.knight_white);
				}
			}
			else if (piece instanceof Queen) {
				if (piece.alignment.equals("BLACK")) {
					return (R.drawable.queen_black);
				}
				else {
					return (R.drawable.queen_white);
				}
			}
			else if (piece instanceof King) {
				if (piece.alignment.equals("BLACK")) {
					return (R.drawable.king_black);
				}
				else {
					return (R.drawable.king_white);
				}
			
			}
		}
		return null;
	}

	public static ChessCoords cast(short[] s) {
		String letter = "ABCDEFGH";
		String l = Character.toString(letter.charAt(s[0]));

		ChessCoords c = new ChessCoords(l, s[1]+1);
//		Log.v("test","Did a cast from "+s+" to "+c +"!");
		return c;
	}

	public static String emulateMove(ChessCoords from, ChessCoords to) {// cut and
																		// paste (with minor modifications
																		// from parts of ChessGame.processMove code
		// TODO Auto-generated method stub
		try {
			// doMove() will try a capture if possible
			ChessGame.INSTANCE.processMove(from, to);

		} catch (SuicideException se) {
			return (Msg.SUICIDE);
		} catch (HomicideException he) {
			return (Msg.HOMICIDE);
		} catch (IllegalMoveException ime) {
			return (Msg.ILLEGAL_MOVE);
		} catch (InvalidCoordsException ice) {
			return (Msg.INVALID_COORDS);
		} catch (ChessPieceNotFoundException cpnfe) {
//			Log.v("test","Ambled upon line 156 in compat");
			return (Msg.BUG);
		} catch (UnexpectedChessGameException e) {
			// TODO Auto-generated catch block
			return e.getMessage();
		}
		return null;
	}
}
