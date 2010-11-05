package com.chessoid.controllers;

import java.util.HashMap;
import java.util.Map;

import android.content.Context;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.chessoid.app.R;
import com.chessoid.liaison.jni.Liaison;
import com.chessoid.model.Board;
import com.chessoid.model.Pieces;
import com.chessoid.ui.ChessTileView;
import com.chessoid.util.ChessoidUtils;

/**
 * Controls interactions between the view layer (Android-side) 
 * and the underlying models.
 * 
 * @author troy
 *
 */
public class ChessController {
	
	/**
	 * An application context that this controller is associated with.
	 */
	Context context;
	
	/**
	 * The view associated with this controller.
	 */
	View contentView;
	
	/**
	 * A chessboard model.
	 */
	Board board;
	
	/** 
	 * Map of all the tiles on the board, keyed by strings
	 * representing the coords on the grid, e.g. 'E6'
	 */
	private Map<String, ChessTileView> chessTiles;
	
	/** 
	 * Status bar at the bottom of the screen. 
	 */
	private TextView statusView;
	
	/**
	 * Construct a controller for the specified context.
	 * TODO should this automatically initialize (call init()) on creation?
	 * @param ctx
	 */
	public ChessController(Context ctx) {
		super();
		this.context = ctx;
	}

	/**
	 * Initialize the controller.  Makes sure the chess
	 * engine is ready to go, starts a new game, etc.
	 */
	public void init() {
		Liaison.INSTANCE.init_engine();
		board = new Board();
		initContentView();
		syncModels();
	}
	
    /**
     * Set up the chess board view.
     * 
     * Create a TableLayout and add it to this Activity as the
     * content view (setContentView()).  Adds 8 rows and columns
     * and sets up the chess board layout with alternating black
     * and white tiles.
     */
    public void initContentView() {
    	chessTiles = new HashMap<String, ChessTileView>();
    	MoveListener ml = new MoveListener(this);
        TableLayout tl = new TableLayout(context);
        
        boolean white = true;
                
        // 8 rows
        for (int row=8; row >= 1; row--) {
        	TableRow tr = new TableRow(context);
        	tr.setMinimumHeight(35);
        	tl.addView(tr);
        	// each row has 8 cols
        	for (int col=1; col <= 8; col++) {
        		String letter = ChessoidUtils.translateCol(col).toUpperCase();
        		ChessTileView tv = new ChessTileView(
        				context, 
        				white ? 0xFFFFFFFF : 0x00000000,
        				col, row);
        		chessTiles.put(letter + row, tv);
        		tv.setOnClickListener(ml);
        		tv.setText(letter + row);	// e.g. A1, B2, etc
        		tv.setMinHeight(40);
        		tv.setMinWidth(40);
        		tv.setClickable(true);
        		tr.addView(tv);	
        		white = !white;
        	}
        	white = !white;	// alternate the starting color for each row
        }
        
        // status view at bottom
        tl.addView(getStatusView());
        updateStatusView("Welcome to Chessoid!");
        tl.setStretchAllColumns(true);
        tl.setPadding(3, 3, 3, 3);
        contentView = tl;
    }
    
    /**
     * Get the view associated with this controller
     * @return whole View with the chessboard on it and all that 
     */
    public View getContentView() {
    	return this.contentView;
    }
    
    /**
     * Get the status view at the bottom of the screen
     * @return TextView
     */
    public TextView getStatusView() {
    	if (null == this.statusView) {
    		statusView = new TextView(context);
    	}
    	return this.statusView;
    }

    /**
     * Update the status view at the bottom of the screen
     * @param status new status message
     */
    public void updateStatusView(String status) {
    	getStatusView().setText(status);
    }
	
    /**
     * Update the UI to match what's in the chess board model.
     */
    public void syncModels() {
    	Liaison.INSTANCE.board(board);
    	for (int r=1; r <= 8; r++) {
    		for (int c=1; c <=8; c++) {
    			chessTiles.get(ChessoidUtils.translateCol(c)+r)
    				.setChessPiece(
    						ChessoidUtils.translateSANPiece(
    								board.getPieceAt(c, r)));
    		}
    	}
    }
    
	/**
	 * Try to create a SAN move string and pass along the request to the
	 * chess engine, via the JNI liaison layer.
	 */
	// TODO account for castling and en passant
	// TODO does this fail if clipboard is null or empty ?
    // TODO initiate engine iteration after successful move
    public void doMove(ChessTileView from, ChessTileView to) {
		// init sanMove w/ char representing the piece at the source location
		Pieces pieceToMove = from.getChessPiece();
		StringBuilder sanMove = new StringBuilder(Character.toString(ChessoidUtils.translateSANPiece(pieceToMove)));
		Pieces victim = to.getChessPiece();
		if (victim != Pieces.EMPTY) {
			sanMove.append("x");
		}
		sanMove.append(ChessoidUtils.translateCol(to.col));	// XXX remove P for pawns ?
		sanMove.append(to.row);
		if (!Liaison.INSTANCE.doMove(sanMove.toString())) {
			updateStatusView(context.getString(R.string.invalid_move).concat(": ").concat(sanMove.toString()));
		}
		else {
			updateStatusView("");
		}
		syncModels();
	}

}
