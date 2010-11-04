package com.chessoid.liaison.console;

import java.util.Scanner;

import com.chessoid.liaison.jni.Liaison;

public class ChessoidConsole {
	
	static final String quitcmd = "quit";
	static Scanner scn = new Scanner(System.in);
	static Liaison liaison = new Liaison();
	
	public static void main(String[] args) {
		System.out.println("Welcome the Chessoid console!");
		liaison.init_engine();
		System.out.println(liaison.board_as_string());
		for (boolean quit=false; quit==false;) {
			System.out.println("Enter a move:");
			String input = scn.next();
			if (quitcmd.equals(input)) {
				quit = true;
				break;
			}
			while (!liaison.doMove(input)) {
				System.out.println(input + " is not a valid move, try again:");
				input = scn.next();
				if (quitcmd.equals(input)) {
					quit = true;
					break;
				}
			}
			if (!quit) {
				System.out.println(liaison.board_as_string());
				System.out.println("Thinking...");
				liaison.iterate();
				System.out.println(liaison.board_as_string());
			}
		}
		System.out.println("Bye.");
	}
	
}
