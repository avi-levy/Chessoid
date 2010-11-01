/*
 * liaison.c
 *
 *  Created on: Oct 19, 2010
 *      Author: troy
 */

#include <stdio.h>
#include "liaison.h"
#include "common.h"


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

//TODO : implement me!
JNIEXPORT jstring JNICALL Java_com_chessoid_liaison_jni_Liaison_board_1as_1string
  (JNIEnv *env, jclass thisClass)
{
	printf("Liaison: getting board as string...TODO\n");
	fflush(stdout);
	return NULL;
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
	// TODO: how to get the response from the engine?
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
		// TODO: should we return an empty string here (how to do that?)?
}

JNIEXPORT void JNICALL Java_com_chessoid_liaison_jni_Liaison_iterate
  (JNIEnv *env, jclass thisClass)
{
	printf("Liaison: Allowing engine to iterate (let the computer move)...\n");
	Iterate();
	printf("Liaison: Engine iteration complete.\n");
	fflush(stdout);
}

