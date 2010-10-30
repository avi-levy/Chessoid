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
}

JNIEXPORT jboolean JNICALL Java_com_chessoid_liaison_jni_Liaison_init_1engine
  (JNIEnv *env, jclass thisClass)
{
	printf("Liaison: Initializing engine...\n");
	flags = ULL(0);		/* <-- initialize control flags (taken from main()) */
	ofp = stdout;		/* <-- output for thinking (taken from main()) */
	Initialize();
	printf("Liaison: Engine initialization complete.\n");
	return true;
}

JNIEXPORT void JNICALL Java_com_chessoid_liaison_jni_Liaison_show_1board
  (JNIEnv *env, jclass thisClass)
{
	printf("Liaison: printing board as string...\n");
	ShowBoard();
}

JNIEXPORT jstring JNICALL Java_com_chessoid_liaison_jni_Liaison_board_1as_1string
  (JNIEnv *env, jclass thisClass)
{
	printf("Liaison: getting board as string...\n");
	return NULL;
}

/**
 * don't pass in any non-ascii compatible strings
 */
JNIEXPORT jboolean JNICALL Java_com_chessoid_liaison_jni_Liaison_validate_1move
  (JNIEnv *env, jclass thisClass, jstring sanMove)
{
	const jbyte *str;
	str = (*env)->GetStringUTFChars(env, sanMove, NULL);
	if (str == NULL)
	{
		return NULL;	/* OutOfMemoryError already thrown */
	}
	printf("Liaison: validating move: %s\n", str);
	(*env)->ReleaseStringUTFChars(env, sanMove, str);
	return true;
}

