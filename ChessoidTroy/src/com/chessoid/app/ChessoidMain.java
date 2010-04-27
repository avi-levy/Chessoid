package com.chessoid.app;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.chessoid.ui.ChessTileView;
import com.chessoid.ui.Msg;

import edu.utd.chess.board.ChessBoard;
import edu.utd.chess.board.ChessCoords;
import edu.utd.chess.exceptions.ChessPieceNotFoundException;
import edu.utd.chess.exceptions.HomicideException;
import edu.utd.chess.exceptions.IllegalMoveException;
import edu.utd.chess.exceptions.InvalidCoordsException;
import edu.utd.chess.exceptions.SuicideException;
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
	
	private TextView statusView;
	
	
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initTableLayout();
        initChessBoard();
    }
    
    /**
     * Move a piece from one tile to another
     * @param v
     * @return true if a move was made, false if not
     */
    public void moveIcon(View v) {
    	if (v instanceof ChessTileView) {
    		//TODO: this functionality may need to be decomposed into various smaller methods or use handler classes
    		ChessTileView tile = (ChessTileView) v;
    		ChessPiece piece = tile.getChessPiece();
    		if (null != piece) {
    			//if clipboard is null place the piece on the clipboard (select this piece to move next)
    			if (null == clipboard) {
	    			//put piece on the clipboard to be dropped on next tile clicked
	    			clipboard = tile;
	    			//TODO: animate the currently selected chess piece
    			}
    			else {
//    				//we're trying to move to a tile occupied by another piece, try to capture
//    				ChessPiece attacker = clipboard.getChessPiece();
//    				ChessPiece victim = piece;
//    				if (null != attacker) {
//    					try {
//    						attacker.moveTo(tile.getChessCoords());	// <-- will throw CoordsOccupiedException
//    					}
//    					catch (CoordsOccupiedException coe) {
//    						//should get here
//    						try {
//    							ChessGame.INSTANCE.doCapture(attacker, victim);
//    							tile.setChessPiece(null);
//    							//should succeed now
//    							attacker.moveTo(tile.getChessCoords());
//    						}
//    						catch (SuicideException se) {
//    							updateStatusView(Msg.SUICIDE);
//    						}
//    						catch (HomicideException he) {
//    							updateStatusView(Msg.HOMICIDE);
//    						}
//    						catch (Exception unexpected) {	//TODO: maybe need an abstract ChessException here so we're not covering up worse errors?
//    							updateStatusView(
//    									Msg.BUG + unexpected);
//    						}
//    					}
//    					catch (IllegalMoveException ime) {
//    						updateStatusView(Msg.ILLEGAL_MOVE);
//    					}
//    					catch (InvalidCoordsException ice) {
//    						updateStatusView(Msg.INVALID_COORDS);
//    					}
//    				}
    				
    				//we're trying to move to a tile occupied by another piece, try to capture
    				ChessPiece attacker = clipboard.getChessPiece();
    				ChessPiece victim = piece;
    				try {
    					// doMove() will try a capture if possible
    					ChessGame.INSTANCE.doMove(attacker.location, victim.location, null);
    					tile.setChessPiece(attacker);
    					clipboard = null;
    				}
    				catch (SuicideException se) {
    					updateStatusView(Msg.SUICIDE);
    				}
    				catch (HomicideException he) {
    					updateStatusView(Msg.HOMICIDE);
    				}
    				catch (IllegalMoveException ime) {
    					updateStatusView(Msg.ILLEGAL_MOVE);
    				}
    				catch (InvalidCoordsException ice) {
    					updateStatusView(Msg.INVALID_COORDS);
    				}
    				catch (ChessPieceNotFoundException cpnfe) {
    					updateStatusView(Msg.BUG);
    				}
    			}
    		}
    		else {
    			if (null != clipboard) {
	    			ChessPiece queuedPiece = clipboard.getChessPiece();
    				if (queuedPiece != null) {
	    				//try to drop piece from clipboard at new tile
	    				try {
	    					//queuedPiece.moveTo(tile.getChessCoords());
	    					ChessGame.INSTANCE.doMove(queuedPiece.location, tile.getChessCoords(), queuedPiece);
	    					
	    					tile.setChessPiece(queuedPiece);
		    				clipboard.setChessPiece(null);	// <-- clear the previous tile
		    				//null out clipboard, we're done with this move
		    				clipboard = null;
		    				updateStatusView("");
	    				}
	    				catch (IllegalMoveException ime) {
	    					updateStatusView(Msg.ILLEGAL_MOVE);
	    				}
	    				catch (InvalidCoordsException ice) {
	    					updateStatusView(Msg.INVALID_COORDS);
	    				}
	    				catch (ChessPieceNotFoundException cpnfe) {
	    					updateStatusView(Msg.BUG);
	    				}
	    				catch (SuicideException se) {
	    					updateStatusView(Msg.SUICIDE);
	    				}
	    				catch (HomicideException he) {
	    					updateStatusView(Msg.HOMICIDE);
	    				}
	    			}
    			}
    		}
    	}
    }
    
    /**
     * Create a TableLayout and add it to this Activity as the
     * content view (setContentView()).  Adds 8 rows and columns
     * and sets up the chess board layout with alternating black
     * and white tiles.
     */
    public void initTableLayout() {
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
        
        // status view at bottom
        tl.addView(getStatusView());
        updateStatusView("Welcome to Chessoid!");
        tl.setStretchAllColumns(true);
        tl.setPadding(3, 3, 3, 3);
        setContentView(tl);
    }
    
    /**
     * Set up the chess pieces in the default position
     */
    public void initChessBoard() {
    	HashMap<ChessCoords, ChessPiece> chessSet = ChessGame.INSTANCE.getChessBoard().pieces; 
    	for (ChessTileView tile : chessTiles) {
    		tile.setChessPiece(chessSet.get(tile.getChessCoords()));
    	}
    }
    
    public TextView getStatusView() {
    	if (null == this.statusView) {
    		statusView = new TextView(this);
    	}
    	return this.statusView;
    }
    
    public void updateStatusView(String status) {
    	getStatusView().setText(status);
    }
   
}



