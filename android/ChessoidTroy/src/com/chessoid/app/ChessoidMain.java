package com.chessoid.app;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.chessoid.ui.ChessTileView;
import com.chessoid.util.ChessoidUtils;

public class ChessoidMain extends Activity {
	
	/** 
	 * List of references to all tiles on the board. 
	 */
	private List<ChessTileView> chessTiles = new ArrayList<ChessTileView>();
	
	/** 
	 * Status bar at the bottom of the screen. 
	 */
	private TextView statusView;
	
    /** 
     * Called when the activity is first created. 
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initTableLayout();
        init();
    }
    
    /**
     * Start the chess engine and do whatever needs to be done.
     */
    public void init() {
    	//TODO 
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
                
        // 8 rows
        for (int i=8; i >= 1; i--) {
        	TableRow tr = new TableRow(this);
        	tr.setMinimumHeight(35);
        	tl.addView(tr);
        	// each row has 8 cols
        	for (int j=1; j <= 8; j++) {
        		String letter = ChessoidUtils.translateCol(j);
        		ChessTileView tv = new ChessTileView(
        				this, 
        				white ? 0xFFFFFFFF : 0x00000000);
        		chessTiles.add(tv);
        		tv.setText(letter + i);	// e.g. A1, B2, etc
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
        setContentView(tl);
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



