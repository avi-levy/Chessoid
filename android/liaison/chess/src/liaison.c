/*
 * liaison.c
 *
 *  Created on: Oct 19, 2010
 *      Author: troy
 */

#include <stdio.h>
#include <string.h>
#include "liaison.h"
#include "common.h"

static char buffer[128];

JNIEXPORT void JNICALL Java_com_chessoid_liaison_jni_Liaison_testliaison
  (JNIEnv *env, jobject jobj)
{
	printf("Hello from liaison.c!\n");
	ShowVersion();
	fflush(stdout);
}

JNIEXPORT jboolean JNICALL Java_com_chessoid_liaison_jni_Liaison_init_1engine
  (JNIEnv *env, jclass thisClass)
{
	printf("Liaison: Initializing engine...\n");
	fflush(stdout);
	flags = ULL(0);		/* <-- initialize control flags (taken from main()) */
	ofp = stdout;		/* <-- output for thinking (taken from main()) */
	Initialize();
	printf("Liaison: Engine initialization complete.\n");
	fflush(stdout);
	return true;
}

JNIEXPORT void JNICALL Java_com_chessoid_liaison_jni_Liaison_show_1board
  (JNIEnv *env, jclass thisClass)
{
	printf("Liaison: printing board as string...\n");
	ShowBoard();
	fflush(stdout);
}

JNIEXPORT jstring JNICALL Java_com_chessoid_liaison_jni_Liaison_board_1as_1string
  (JNIEnv *env, jclass thisClass)
{
	/*
	 * This works in a similar way to ShowBoard() in output.c
	 */
	printf("Liaison: getting board as string\n");

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
	strcat(buffer, "\n");

	fflush(stdout);

	return (*env)->NewStringUTF(env, buffer);
}

/**
 * don't pass in any non-ascii compatible strings
 */
JNIEXPORT jboolean JNICALL Java_com_chessoid_liaison_jni_Liaison_validate_1move
  (JNIEnv *env, jclass thisClass, jstring sanMove)
{
	int valid = true;
	const jbyte *str;
	str = (*env)->GetStringUTFChars(env, sanMove, NULL);
	if (NULL == str)
	{
		return NULL;	/* OutOfMemoryError already thrown */
	}
	printf("Liaison: validating move: %s\n", str);
	if (NULL == ValidateMove(str))
	{
		valid = false;
	}
	(*env)->ReleaseStringUTFChars(env, sanMove, str);
	fflush(stdout);
	return valid;
}

JNIEXPORT jstring JNICALL Java_com_chessoid_liaison_jni_Liaison_input_1cmd
  (JNIEnv *env, jclass thisClass, jstring engCmd)
{
	// XXX: how to get the response from the engine?
	const jbyte *str;
	str = (*env)->GetStringUTFChars(env, engCmd, NULL);
	if (NULL == str)
	{
		return NULL;	/* OutOfMemoryError already thrown */
	}
	printf("Liaison: input engine command: '%s'\n", str);
	InputCmdChessoid(str);
	(*env)->ReleaseStringUTFChars(env, engCmd, str);
	fflush(stdout);
	return NULL;
		// XXX: should we return an empty string here (how to do that?)?
}

JNIEXPORT jboolean JNICALL Java_com_chessoid_liaison_jni_Liaison_doMove
  (JNIEnv *env, jclass thisClass, jstring sanMove)
{
	const jbyte *str;
	str = (*env)->GetStringUTFChars(env, sanMove, NULL);
	if (NULL == str)
	{
		return NULL;	/* OutOfMemoryError already thrown */
	}
	printf("Liaison: asking engine to doMove() for: '%s'\n", str);
	fflush(stdout);
	return DoMoveChessoid(str);
}

JNIEXPORT void JNICALL Java_com_chessoid_liaison_jni_Liaison_iterate
  (JNIEnv *env, jclass thisClass)
{
	printf("Liaison: Allowing engine to iterate (let the computer move)...\n");
	Iterate();
	printf("Liaison: Engine iteration complete.\n");
	fflush(stdout);
}

