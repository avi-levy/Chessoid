package com.chessoid.controller;

import com.chessoid.service.ChessEngineService;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;

/**
 * Establishes a callback that should happen in response to
 * the chess engine having done something (e.g. method that
 * gets called after the chess engine makes a move)
 * 
 * @author troy
 *
 */
public class ChessEngineResultReceiver extends ResultReceiver {

	ChessController controller;
	
	public ChessEngineResultReceiver(ChessController controller) {
		super(new Handler());
		this.controller = controller;
	}
	
	@Override
	public void onReceiveResult(int resultCode, Bundle resultData) {
		switch (resultCode) {
			case ChessEngineService.STATUS_RUNNING:
				// nothing to do
				break;
			case ChessEngineService.STATUS_FINISHED:
				controller.updateStatusView("");
				controller.syncModels();
				break;
			case ChessEngineService.STATUS_ERROR:
				controller.badError(resultData.getString(Intent.EXTRA_TEXT));
				break;
		}		
	}
	
}
