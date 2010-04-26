package com.chessoid.activities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TableLayout;
import android.widget.TableRow;

import com.chessoid.ui.ChessTileView;

import edu.utd.chess.board.ChessBoard;
import edu.utd.chess.board.ChessCoords;
import edu.utd.chess.game.ChessGame;
import edu.utd.chess.pieces.ChessPiece;

public class ChessoidMain extends Activity {
	
	/**
	 * List of references to all tiles on the board.
	 * For internal use only, not to be referenced outside this class.
	 */
	private List<ChessTileView> chessTiles = new ArrayList<ChessTileView>();
	
	/**
	 * Pieces in the clipboard are waiting to be moved.
	 */
	private ChessTileView clipboard;
	
	
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.main);
        
        TableLayout tl = new TableLayout(this);
        
        boolean white = true;
        
        OnClickListener ocl = new OnClickListener() {
        	@Override
        	public void onClick(View v) {        	
        		moveIcon(v);
        	}
        };
        
        // 8 rows
        for (int i=8; i >= 1; i--) {
        	TableRow tr = new TableRow(this);
        	tr.setMinimumHeight(35);
        	tl.addView(tr);
        	// each row has 8 cols
        	for (int j=1; j <= 8; j++) {
        		String letter = ChessBoard.translateCol(j);
        		ChessCoords coords = new ChessCoords(letter, i);
        		ChessTileView tv = new ChessTileView(
        				this, 
        				white ? 0xFFFFFFFF : 0x00000000,
        				coords);
        		chessTiles.add(tv);
        		tv.setText(letter + i);	// e.g. A1, B2, etc
        		tv.setMinHeight(40);
        		tv.setMinWidth(40);
        		tv.setClickable(true);
        		tv.setOnClickListener(ocl);
        		tr.addView(tv);	
        		white = !white;	// <-- toggle between black and white tile while creating table
        	}
        	white = !white;	// <-- alternate the starting color for each row
        }
        
        initChessBoard();
        
        tl.setStretchAllColumns(true);
        tl.setPadding(3, 3, 3, 3);
        setContentView(tl);
        
    }
    
    /**
     * Move a piece from one tile to another
     * @param v
     * @return true if a move was made, false if not
     */
    public boolean moveIcon(View v) {
    	if (v instanceof ChessTileView) {
    		ChessTileView tile = (ChessTileView) v;
    		ChessPiece piece = tile.getChessPiece();
    		if (null != piece) {
    			//put piece on the clipboard to be dropped on next tile clicked
    			clipboard = tile;
    		}
    		else {
    			if (null != clipboard) {
	    			if (clipboard.getChessPiece() != null) {
	    				//drop piece from clipboard at new tile
	    				tile.setChessPiece(clipboard.getChessPiece());
	    				clipboard.setChessPiece(null);
	    				//null out clipboard, we're done with this move
	    				clipboard = null;
	    			}
    			}
    		}
    	}
    	return false;
    }
    
    /**
     * Set up the chess pieces in the default position
     */
    public void initChessBoard() {
    	HashMap<ChessCoords, ChessPiece> chessSet = ChessGame.INSTANCE.getDefaultChessSet(); 
    	for (ChessTileView tile : chessTiles) {
    		tile.setChessPiece(chessSet.get(tile.getChessCoords()));
    	}
    }
   
}



