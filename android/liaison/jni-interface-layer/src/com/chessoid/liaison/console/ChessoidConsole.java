package com.chessoid.liaison.console;

import java.util.Scanner;

import com.chessoid.liaison.jni.Liaison;

public class ChessoidConsole {
	
	static Scanner scn = new Scanner(System.in);
	
	public static void main(String[] args) {
		System.out.println("Welcome the Chessoid console!");
		Liaison.init_engine();
		System.out.println(Liaison.board_as_string());
		while (true) {
			System.out.println("Enter a move:");
			String input = scn.next();
			if ("quit".equals(input))
				break;
			while (!Liaison.doMove(input)) {
				System.out.println(input + " is not a valid move, try again:");
				input = scn.next();
			}
			System.out.println(Liaison.board_as_string());
			System.out.println("Thinking...");
			Liaison.iterate();
			System.out.println(Liaison.board_as_string());
		}
		System.out.println("Bye.");
	}
	
}
