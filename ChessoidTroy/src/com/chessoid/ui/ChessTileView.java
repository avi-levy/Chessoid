package com.chessoid.ui;

import android.content.Context;
import android.widget.TextView;

import com.chessoid.app.R;

import edu.utd.chess.board.ChessCoords;
import edu.utd.chess.pieces.Bishop;
import edu.utd.chess.pieces.ChessPiece;
import edu.utd.chess.pieces.King;
import edu.utd.chess.pieces.Knight;
import edu.utd.chess.pieces.Pawn;
import edu.utd.chess.pieces.Queen;
import edu.utd.chess.pieces.Rook;

/**
 * Works like a standard TextView but allows us to ask
 * the tile if it is occupied and if so what's on it?
 * @author troy
 *
 */
public class ChessTileView extends TextView {
	private int color;
	private ChessPiece chessPiece;
	private ChessCoords coords;	//TODO: oh man, I'm so nervous about this relationship!
	
	public ChessTileView(Context context, int color, ChessCoords coords) {
		super(context);
		this.setColor(color);
		this.coords = coords;
	}
	
	/**
	 * Returns the ChessPiece that is currently on this tile,
	 * or <code><b>null</b></code> if the tile is empty
	 * @return <code>ChessPiece</code>
	 */
	public ChessPiece getChessPiece() {
		return chessPiece;
	}
	
	/**
	 * Put a specified <code>ChessPiece</code> on this tile.
	 * It is the responsibility of a controlling entity to
	 * make sure the tile is not first occupied.
	 * @param piece
	 */
	public void setChessPiece(ChessPiece piece) {
		chessPiece = piece;
		if (null == piece) {
			this.setBackgroundColor(color);
		} else {
			//TODO: Help, I'm ugly!
			if (piece instanceof Pawn) {
				if (piece.alignment.equals("BLACK")) {
					this.setBackgroundResource(R.drawable.pawn_black);
				}
				else {
					this.setBackgroundResource(R.drawable.pawn_white);
				}
			}
			else if (piece instanceof Rook) {
				if (piece.alignment.equals("BLACK")) {
					this.setBackgroundResource(R.drawable.rook_black);
				}
				else {
					this.setBackgroundResource(R.drawable.rook_white);
				}
			}
			else if (piece instanceof Bishop) {
				if (piece.alignment.equals("BLACK")) {
					this.setBackgroundResource(R.drawable.bishop_black);
				}
				else {
					this.setBackgroundResource(R.drawable.bishop_white);
				}
			}
			else if (piece instanceof Knight) {
				if (piece.alignment.equals("BLACK")) {
					this.setBackgroundResource(R.drawable.knight_black);
				}
				else {
					this.setBackgroundResource(R.drawable.knight_white);
				}
			}
			else if (piece instanceof Queen) {
				if (piece.alignment.equals("BLACK")) {
					this.setBackgroundResource(R.drawable.queen_black);
				}
				else {
					this.setBackgroundResource(R.drawable.queen_white);
				}
			}
			else if (piece instanceof King) {
				if (piece.alignment.equals("BLACK")) {
					this.setBackgroundResource(R.drawable.king_black);
				}
				else {
					this.setBackgroundResource(R.drawable.king_white);
				}
			}
		}
	}
	
	/**
	 * Set the default color for this ChessTile.
	 * @param color
	 */
	public void setColor(int color) {
		this.color = color;
		setBackgroundColor(this.color);
	}
	
	/**
	 * Get the color associated with this ChessTile.
	 * @return
	 */
	public int getColor() {
		return this.color;
	}
	
	public String toString() {
		return "ChessTileView, color=" + color + " Coords: " 
			+ this.coords + " ChessPiece=" + getChessPiece();
	}

	public ChessCoords getChessCoords() {
		return this.coords;
	}
	
	public void setChessCoored(ChessCoords coords) {
		this.coords = coords;
	}

}
