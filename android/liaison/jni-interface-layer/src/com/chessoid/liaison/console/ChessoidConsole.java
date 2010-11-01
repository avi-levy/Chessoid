package com.chessoid.liaison.console;

import java.util.Scanner;

import com.chessoid.liaison.jni.Liaison;

public class ChessoidConsole {
	
	static Scanner scn = new Scanner(System.in);
	
	public static void main(String[] args) {
		System.out.println("Welcome the Chessoid console!");
		Liaison.init_engine();
		while (true) {
			Liaison.show_board();
			System.out.println("Enter a move:");
			String input = scn.next();
			if ("quit".equals(input))
				break;
			Liaison.input_cmd(input);
			Liaison.iterate();
		}
		System.out.println("Bye.");
	}
	
}
