package com.chessoid.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import edu.utd.chess.board.ChessBoard;

public class ChessoidMain extends Activity {
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
        	v.setBackgroundResource(R.drawable.icon);
        	
        	}
        };
        
        // 8 rows
        for (int i=1; i <= 8; i++) {
        	TableRow tr = new TableRow(this);
        	tr.setMinimumHeight(35);
        	tl.addView(tr);
        	// each row had 8 cols
        	for (int j=1; j <= 8; j++) {
        		TextView tv = new TextView(this);
        		tv.setText(ChessBoard.translateCol(i) + j);	// e.g. A1, B2, etc
        		tv.setHeight(35);
        		if (white) tv.setBackgroundColor(0xFFFFFFFF);
        		white = !white;
        		tv.setClickable(true);
        		tv.setOnClickListener(ocl);
        		tr.addView(tv);
        	}
        	white = !white;
        }
        
        tl.setStretchAllColumns(true);
        tl.setPadding(3, 3, 3, 3);
        setContentView(tl);
        
    }
   
}