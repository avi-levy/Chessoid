package com.chessoid.ui;

import android.content.Context;
import android.widget.TextView;

import com.chessoid.activities.R;

import edu.utd.chess.pieces.ChessPiece;
import edu.utd.chess.pieces.Pawn;

/**
 * Works like a standard TextView but allows us to ask
 * the tile if it is occupied and if so what's on it?
 * @author troy
 *
 */
public class ChessTileView extends TextView {
	private int color;
	private ChessPiece chessPiece;
	
	public ChessTileView(Context context, int color) {
		super(context);
		this.setColor(color);
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
			//TODO: refactoring - ChessPiece should have method getIcon()
			if (piece instanceof Pawn) {
				if (piece.alignment.equals("BLACK")) {
					this.setBackgroundResource(R.drawable.pawn_black);
				}
				else {
					//placeholder - android robot
					this.setBackgroundResource(R.drawable.android_robot);
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
		return "ChessTileView, color=" + color + " ChessPiece=" + getChessPiece(); 
	}


}
