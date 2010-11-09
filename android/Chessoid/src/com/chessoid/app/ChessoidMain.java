package com.chessoid.app;

import android.app.Activity;
import android.os.Bundle;

import com.chessoid.controllers.ChessController;

public class ChessoidMain extends Activity {
	
	ChessController controller;
	
    /** 
     * Called when the activity is first created. 
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        controller = new ChessController(this);
        controller.init();	// TODO should init() happen automatically when controller is constructed?
        this.setContentView(controller.getContentView());
    }
   
}



