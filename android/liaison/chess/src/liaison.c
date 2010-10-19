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
	Initialize();
	printf("Liaison: Engine initialization complete.\n");
	return 0;
}
