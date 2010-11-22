/*
 * liaison.c
 *
 *  Created on: Oct 19, 2010
 *      Author: troy
 *
 *      TODO functions in here might need more robust error handling
 *      if something breaks in them, they are very likely to crash the VM.
 */

#include <stdio.h>
#include <string.h>
#include "liaison.h"
#include "common.h"

static char buffer[128];
int b_debug = 0;

void debug(char *msg)
{
	if (b_debug) {
		printf("LIASON: %s\n", msg);
		fflush(stdout);
	}
}

JNIEXPORT void JNICALL Java_com_chessoid_liaison_jni_Liaison_testliaison
  (JNIEnv *env, jobject jobj)
{
	debug("Hello from liaison.c!  Chess engine version follows:");
	ShowVersion();
	fflush(stdout);
}

JNIEXPORT void JNICALL Java_com_chessoid_liaison_jni_Liaison_debugMode
  (JNIEnv *env, jobject liaisonObj, jboolean debugToggle)
{
	b_debug = debugToggle;
	if (b_debug)
		printf("LIASON: Liaison debugging enabled.\n");
	else
		debug("LIASON: debugging disabled.\n");
}

JNIEXPORT jboolean JNICALL Java_com_chessoid_liaison_jni_Liaison_init_1engine
  (JNIEnv *env, jobject liaisonObj)
{
	debug("Initializing engine.");
	flags = ULL(0);		/* <-- initialize control flags (taken from main()) */
	ofp = stdout;		/* <-- output for thinking (taken from main()) */
	Initialize();
	debug("Engine initialization complete.");
	return true;
}

JNIEXPORT void JNICALL Java_com_chessoid_liaison_jni_Liaison_show_1board
  (JNIEnv *env, jobject liaisonObj)
{
	debug("Printing board as string.");
	ShowBoard();
	fflush(stdout);
}

JNIEXPORT jstring JNICALL Java_com_chessoid_liaison_jni_Liaison_board_1as_1string
  (JNIEnv *env, jobject liaisonObj)
{
	/*
	 * This works in a similar way to ShowBoard() in output.c
	 */
	debug("Getting board as string.");

	buffer[0] = '\0';	// clear the buffer

	int r, c, sq;
	for (r=56; r >=0; r -= 8)
	{
		for (c=0; c < 8; c++)
		{
			sq = r + c;
			if (board.b[white][pawn] & BitPosArray[sq])
				strcat (buffer, "P ");
			else if (board.b[white][knight] & BitPosArray[sq])
				strcat(buffer, "N ");
	        else if (board.b[white][bishop] & BitPosArray[sq])
	        	strcat (buffer, "B ");
	        else if (board.b[white][rook]   & BitPosArray[sq])
	        	strcat (buffer, "R ");
	        else if (board.b[white][queen]  & BitPosArray[sq])
	        	strcat (buffer, "Q ");
	        else if (board.b[white][king]   & BitPosArray[sq])
	        	strcat (buffer, "K ");
	        else if (board.b[black][pawn]   & BitPosArray[sq])
	        	strcat (buffer, "p ");
	        else if (board.b[black][knight] & BitPosArray[sq])
	        	strcat (buffer, "n ");
	        else if (board.b[black][bishop] & BitPosArray[sq])
	        	strcat (buffer, "b ");
	        else if (board.b[black][rook]   & BitPosArray[sq])
	        	strcat (buffer, "r ");
	        else if (board.b[black][queen]  & BitPosArray[sq])
	        	strcat (buffer, "q ");
	        else if (board.b[black][king]   & BitPosArray[sq])
	        	strcat (buffer, "k ");
	        else
	        	strcat (buffer, ". ");
		}
		strcat(buffer, "\n");
	}

	return (*env)->NewStringUTF(env, buffer);
}

JNIEXPORT jobject JNICALL Java_com_chessoid_liaison_jni_Liaison_board
  (JNIEnv *env, jobject liaisonObj, jobject jBoard)
{
	/* if jBoard is not null, update it and send it back. */
	if (NULL == jBoard)
		return NULL;
	jclass jBoardClass = (*env)->GetObjectClass(env, jBoard);
	jmethodID mSetPiece = (*env)->GetMethodID(env, jBoardClass, "setPieceAt", "(CII)V");
	if (NULL == mSetPiece)
		return NULL;			/* method not found, exception thrown */

	int r, c, sq;
	for (r=56; r >=0; r-=8)
	{
		for (c=0; c < 8; c++){
			sq = r + c;
			if (board.b[white][pawn] & BitPosArray[sq])
				(*env)->CallVoidMethod(env, jBoard, mSetPiece, 'P', c+1, (r/8)+1);
			else if (board.b[white][knight] & BitPosArray[sq])
				(*env)->CallVoidMethod(env, jBoard, mSetPiece, 'N', c+1, (r/8)+1);
	        else if (board.b[white][bishop] & BitPosArray[sq])
	        	(*env)->CallVoidMethod(env, jBoard, mSetPiece, 'B', c+1, (r/8)+1);
	        else if (board.b[white][rook]   & BitPosArray[sq])
	        	(*env)->CallVoidMethod(env, jBoard, mSetPiece, 'R', c+1, (r/8)+1);
	        else if (board.b[white][queen]  & BitPosArray[sq])
	        	(*env)->CallVoidMethod(env, jBoard, mSetPiece, 'Q', c+1, (r/8)+1);
	        else if (board.b[white][king]   & BitPosArray[sq])
	        	(*env)->CallVoidMethod(env, jBoard, mSetPiece, 'K', c+1, (r/8)+1);
	        else if (board.b[black][pawn]   & BitPosArray[sq])
	        	(*env)->CallVoidMethod(env, jBoard, mSetPiece, 'p', c+1, (r/8)+1);
	        else if (board.b[black][knight] & BitPosArray[sq])
	        	(*env)->CallVoidMethod(env, jBoard, mSetPiece, 'n', c+1, (r/8)+1);
	        else if (board.b[black][bishop] & BitPosArray[sq])
	        	(*env)->CallVoidMethod(env, jBoard, mSetPiece, 'b', c+1, (r/8)+1);
	        else if (board.b[black][rook]   & BitPosArray[sq])
	        	(*env)->CallVoidMethod(env, jBoard, mSetPiece, 'r', c+1, (r/8)+1);
	        else if (board.b[black][queen]  & BitPosArray[sq])
	        	(*env)->CallVoidMethod(env, jBoard, mSetPiece, 'q', c+1, (r/8)+1);
	        else if (board.b[black][king]   & BitPosArray[sq])
	        	(*env)->CallVoidMethod(env, jBoard, mSetPiece, 'k', c+1, (r/8)+1);
	        else
	        	(*env)->CallVoidMethod(env, jBoard, mSetPiece, '\0', c+1, (r/8)+1);
		}
	}

	return jBoard;
}

/**
 * don't pass in any non-ascii compatible strings
 */
JNIEXPORT jboolean JNICALL Java_com_chessoid_liaison_jni_Liaison_validate_1move
  (JNIEnv *env, jobject liaisonObj, jstring sanMove)
{
	int valid = true;
	const jbyte *str;
	str = (*env)->GetStringUTFChars(env, sanMove, NULL);
	if (NULL == str)
	{
		debug("Error converting Java string to C string");
		return false;	/* OutOfMemoryError already thrown */
	}
	debug("Validating move:");
	debug(str);
	if (NULL == ValidateMove(str))
	{
		valid = false;
	}
	(*env)->ReleaseStringUTFChars(env, sanMove, str);

	if (valid) debug("Move is valid.");
	else debug("Move is NOT valid.");

	return valid;
}

JNIEXPORT jstring JNICALL Java_com_chessoid_liaison_jni_Liaison_input_1cmd
  (JNIEnv *env, jobject liaisonObj, jstring engCmd)
{
	// XXX: how to get the response from the engine?
	const jbyte *str;
	str = (*env)->GetStringUTFChars(env, engCmd, NULL);
	if (NULL == str)
	{
		return NULL;	/* OutOfMemoryError already thrown */
	}
	debug("Input engine command:");
	debug(str);
	InputCmdChessoid(str);
	(*env)->ReleaseStringUTFChars(env, engCmd, str);
	return NULL;
		// XXX: should we return an empty string here (how to do that?)?
}

JNIEXPORT jboolean JNICALL Java_com_chessoid_liaison_jni_Liaison_doMove
  (JNIEnv *env, jobject liaisonObj, jstring sanMove)
{
	const jbyte *str;
	str = (*env)->GetStringUTFChars(env, sanMove, NULL);
	if (NULL == str)
	{
		return NULL;	/* OutOfMemoryError already thrown */
	}
	debug("Asking engine to doMove():");
	debug(str);
	jboolean result = DoMoveChessoid(str);
	(*env)->ReleaseStringUTFChars(env, sanMove, str);
	return result;
}

JNIEXPORT void JNICALL Java_com_chessoid_liaison_jni_Liaison_iterate
  (JNIEnv *env, jobject liaisonObj)
{
	debug("Allowing engine to iterate (let the computer move).");
	Iterate();
	debug("Engine iteration complete.");
}

