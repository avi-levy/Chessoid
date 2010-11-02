package com.chessoid.ui;

import android.content.Context;
import android.widget.TextView;

/**
 * Works like a standard TextView but allows us to ask
 * the tile if it is occupied and if so what's on it?
 * @author troy
 *
 */
public class ChessTileView extends TextView {
	private int color;
	
	public ChessTileView(Context context, int color) {
		super(context);
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
	 * @return
	 */
	public int getColor() {
		return this.color;
	}
	
}
