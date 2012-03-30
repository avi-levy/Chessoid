package com.chessoid.app;

import android.app.Activity;
import android.os.Bundle;

import com.chessoid.controller.ChessController;

public class ChessoidMain extends Activity {
	
	ChessController controller;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        controller = new ChessController(this);
        this.setContentView(controller.contentView);
    }
       
}



