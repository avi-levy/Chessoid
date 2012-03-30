package com.chessoid.controller;

import android.view.View;
import android.view.View.OnClickListener;

import com.chessoid.ui.ChessTileView;

/**
 * Works like copy/paste.
 * 
 * When a user clicks on a tile once, that tile goes onto the
 * clipboard, when they click on another tile,  
 * we try to move there.  Let the chess controller
 * if the move is valid or not, don't try to do any of that
 * processing here.  
 */

public class MoveListener implements OnClickListener {
	ChessTileView clipboard = null;
	ChessController controller; 
	
	public MoveListener(ChessController ctrl) {
		super();
		this.controller = ctrl;
	}
	
	@Override
	public void onClick(View view) {
		if (view instanceof ChessTileView) {
			ChessTileView tv = (ChessTileView) view;
			if (null == clipboard || tv == clipboard) {
				clipboard = tv;
				return;
			}
			try {
				controller.doMove(clipboard, tv);
			}
			catch (Exception x) {
				controller.badError(x.toString() + "in class: " 
						+ x.getStackTrace()[0].getClassName() 
						+ " at line: " + x.getStackTrace()[0].getLineNumber());
			}
			clipboard = null;
		}
	}
}
