package com.chessoid.service;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.os.ResultReceiver;

import com.chessoid.liaison.jni.Liaison;

/**
 * Processes chess engine moves asynchronously 
 * 
 * @author troy
 *
 */
public class ChessEngineService extends IntentService {
	
	public static final String ENGINE_ITERATION_CALLBACK_RECEIVER = "com.chessoid.ENGINE_ITERATION_CALLBACK_RECEIVER";
	
	public static final int STATUS_RUNNING 	= 0x1;
	public static final int STATUS_ERROR 	= 0x2;
	public static final int STATUS_FINISHED	= 0x3;
	
	public ChessEngineService() {
		super("ChessEngineService");
	}
	
	@Override
	public void onHandleIntent(Intent intent) {
		final ResultReceiver receiver = intent.getParcelableExtra(ENGINE_ITERATION_CALLBACK_RECEIVER);
		if (receiver != null) receiver.send(STATUS_RUNNING, Bundle.EMPTY);
		
		// TODO: maybe liaison instance should be passed in or abstracted somehow? maybe not
		Liaison.INSTANCE.iterate();
		
		// TODO: is there any info from the engine we want to send back to the receiever when we're done?
		if (receiver != null) receiver.send(STATUS_FINISHED, Bundle.EMPTY);
		
		stopSelf();
	}
	
}
