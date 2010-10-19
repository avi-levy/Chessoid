package com.chessoid.liaison.jni;

import junit.framework.TestCase;

public class TestLiaison extends TestCase {

	@Override public void setUp() {
		
	}
	
	@Override public void tearDown() {
		
	}
	
	public void testLib() {
		Liaison.testliaison();
				//just tests that we can call code in libchess
	}
	
	public void testInitEngine() {
		boolean success = Liaison.init_engine();
		assertTrue(success);
	}
	
}
