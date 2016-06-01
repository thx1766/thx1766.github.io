//pieces.java

public class pieces{
	public static void main(String[] args){
	System.out.println("this is a test of driver 3");

final String ANSI_RED = "\u001B[31m";
final String ANSI_RESET = "\u001B[0m";
	System.out.println("  ______ ");
	System.out.println(" |      |");
	System.out.println(ANSI_RED+"8|      |"+ANSI_RESET);
	System.out.println(" |_____w|");
	System.out.println(" |      |");
	System.out.println("7|      |");
	System.out.println(" |_____b|");
	System.out.println(" |      |");
	System.out.println("6|      |");
	System.out.println(" |_____w|");
	System.out.println(" |      |");
	System.out.println("5|      |");
	System.out.println(" |_____b|");
	System.out.println(" |      |");
	System.out.println("4|      |");
	System.out.println(" |_____w|");
	System.out.println(" |      |");
	System.out.println("3|      |");
	System.out.println(" |_____b|");
	System.out.println(" |      |");
	System.out.println("2|      |");
	System.out.println(" |_____w|");
	System.out.println(" |      |");
	System.out.println("1|      |");
	System.out.println(" |_____b|");

//render step: go through array after each movement is validated in model
//
//model view controller
//
//model is data stored in a easy to access and manipulate format (OO)
//view is based in rendering step
//controller is logic to interact and build branch trees of games, menus for playing a game, etc
//
//take a game, and rewind it showing possible branching moves
//
//show possible branching moves and probability heuristics for everything
//
//rendering on the console in C is awesome with color on the terminal


//store information about pieces in two formats
//string format with each cell in a 2d matrix representing a string
//two arrays of pieces
//
//pieceone playerone
//piece(8 pawns, 2 rooks, 2 knights, 2 bishops, 1 queen, one king)
//player(white, black)
//
//
//


//two programs
//
//one in C to render each frame quickly on the console
//
//that network render software can be upgraded with a layer to dump HTML frames
//html frames stored as move1 move2... etc until checkmate
//... for every possible combination of moves
//
//branching from possible moves at start
//pawn 1 forward
//pawn 2 forward
//
//each piece has a branch from starting position to ending positon
//each piece has a fixed starting position and a branching tree of possible movements
//for example, a pawn can move forward on its first move, or depending on how rules are interpereted, can take a move and attack
//theoretical branching is based on 
	}
}
