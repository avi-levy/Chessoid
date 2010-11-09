package com.chessoid.ui;

import android.content.Context;
import android.widget.TextView;

import com.chessoid.app.R;
import com.chessoid.model.Pieces;
import com.chessoid.util.ChessoidUtils;

/**
 * Works like a standard TextView but allows us to ask
 * the tile if it is occupied and if so what's on it?
 * @author troy
 *
 */
public class ChessTileView extends TextView {
	private int color;
	private Pieces piece;
	
	public int col, row; 
	
	public ChessTileView(Context context, int color, int col, int row) {
		super(context);
		this.col = col;		
		this.row = row;
		this.setColor(color);
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
	 * @return int representing the color of the tile.
	 */
	public int getColor() {
		return this.color;
	}
	
	public void setChessPiece(Pieces piece) {
		this.piece = piece; 
		switch (piece) {
			case BLACK_PAWN:
				setBackgroundResource(R.drawable.pawn_black);
				break;
			case BLACK_ROOK:
				setBackgroundResource(R.drawable.rook_black);
				break;
			case BLACK_KNIGHT:
				setBackgroundResource(R.drawable.knight_black);
				break;
			case BLACK_BISHOP:
				setBackgroundResource(R.drawable.bishop_black);
				break;
			case BLACK_QUEEN:
				setBackgroundResource(R.drawable.queen_black);
				break;
			case BLACK_KING:
				setBackgroundResource(R.drawable.king_black);
				break;
			case WHITE_PAWN:
				setBackgroundResource(R.drawable.pawn_white);
				break;
			case WHITE_ROOK:
				setBackgroundResource(R.drawable.rook_white);
				break;
			case WHITE_KNIGHT:
				setBackgroundResource(R.drawable.knight_white);
				break;
			case WHITE_BISHOP:
				setBackgroundResource(R.drawable.bishop_white);
				break;
			case WHITE_QUEEN:
				setBackgroundResource(R.drawable.queen_white);
				break;
			case WHITE_KING:
				setBackgroundResource(R.drawable.king_white);
				break;
			default:
				setBackgroundColor(color);
		}
	}
	
	public Pieces getChessPiece() {
		return piece;
	}
	
	public String toString() {
		return ChessoidUtils.translateCol(this.col).concat(""+row);
	}

}
