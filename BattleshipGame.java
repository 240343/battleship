package battleship;

import java.util.Scanner;

public class BattleshipGame {

	public static void main(String[] args) {

		Scanner input = new Scanner(System.in);	
		Field f = new Field(11, 11);
		
//		f.printField();
//		f.printGrid();
		
		System.out.println("Welcome to Battleship!");
		
		printHelp();
		
		boolean cont = true;
		int fired = 0;
		int hit = 0;
		int sunk = 0;
		while(cont) {
			System.out.print(">> ");
			String cmd = input.nextLine();
			if(cmd.equals("help")) {
				printHelp();
			} else if(cmd.equals("quit")) {
				cont = false;
			} else if(cmd.equals("view board")) {
				f.printGrid();
			} else if(cmd.equals("view ships")) {
				f.printField();
			} else if(cmd.substring(0,4).equals("fire")) { // 
				int sp1 = cmd.indexOf(" ");
				int sp2 = cmd.indexOf(" ",sp1+1);
				int sp3 = cmd.indexOf(" ",sp2+1);
				if(sp1==-1 || sp2==-1 || sp3!=-1) {
					System.out.println("Illegal command.");
				} else {
					int r = Integer.parseInt(cmd.substring(sp1+1,sp2));
					int c = Integer.parseInt(cmd.substring(sp2+1));
					fired++;
					int res = f.fire(r, c);
					if(res==1) {
						System.out.println("HIT!");
						hit++;
					} else if(res==0) {
						System.out.println("MISS!");
					} else if(res==2) {
						System.out.println("HIT!");
						System.out.println("Sunk!");
						hit++;
						sunk++;
					}
					f.printGrid();
				}
				if(f.allSunk()) {
					System.out.println("All ships sunk! You win the game!");
					cont = false;
				}
			} else if(cmd.equals("stats")) { // 
				printStats(fired, hit, sunk);
			} else {
				System.out.println("Illegal command.");
			}
		
		}
		
		System.out.println("Thank you for playing Battleship!");
		
		input.close();
		
	}
	
	// n: missles fired
	// h: missles hit
	// s: ships sunk
	private static void printStats(int n, int h, int s) { 
		System.out.println();
		System.out.println("Number of missiles fired: "+n);
		System.out.println("Hit ratio:"+(int)((double)h/n*100)+"%");
		System.out.println("Number of ships sunk: "+s);
		System.out.println();
	}

	private static void printHelp() {
		System.out.println();
		System.out.println("view board - displays the user's board");
		System.out.println("view ships - displays the placement of the ships");
		System.out.println("fire r c - fires a missile at the cell at [r,c]");
		System.out.println("stats - prints out the game statistics");
		System.out.println("quit - exits the game");
		System.out.println();
	}

	
	
}
