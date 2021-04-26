package battleship;

import java.util.ArrayList;

public class Field {

	private char[][] field; // where ships are;
	private char[][] grid; // user's view
	private ArrayList<Ship> ships;
	private int ROWS, COLS;
	
	public Field(int rows, int cols) {
		ROWS = rows;
		COLS = cols;
		field = new char[rows][cols];
		grid = new char[rows][cols];
		for(int r=0; r<rows; r++) {
			for(int c=0; c<cols; c++) {
				field[r][c] = '-';
				grid[r][c] = '-';
			}
		}
		ships = new ArrayList<Ship>();
		placeShips();
	}
	
	public boolean allSunk() {
		for(int i=0; i<ships.size();i++) {
			if(!ships.get(i).sunk()) {
				return false;
			}
		}
		return true;
	}
	
	// return value: 0 if miss, 1 if hit, 2 if sunk
	// changes the grid
	// marks ship as sunk(if sunk)
	public int fire(int r, int c) {
		if(field[r][c]=='-') { // no ships, miss
			grid[r][c] = 'O';
			return 0;
		} else { // ship there
			if(grid[r][c]=='X'){ // hit before
				return 0;
			} else { // if(grid[r][c]=='-') // not hit before
				grid[r][c] = 'X';
				Ship s = findShip(r,c);
				s.update(grid);
				if(s.sunk()) return 2;
				else return 1;
			}
		}
	}   
	
	private Ship findShip(int r, int c) {
		for(int i=0; i<ships.size(); i++) {
			if(ships.get(i).onShip(r, c)) {
				return ships.get(i);
			}
		}
		return null;
	}

	public void printField() {
		printField(field);
	}
	public void printGrid() {
		printField(grid);
	}
	
	private void printField(char[][] arr) {
		System.out.print("   ");
		for (int j = 0; j < arr[0].length; j++) {
			System.out.print(j);
			if(j<10) {
				System.out.print("  ");
			} else if(j<100) {
				System.out.print(" ");
			}
        }
		System.out.println();
		
        for (int i = 0; i < arr.length; i++) {
        	System.out.print(i);
        	if(i<10) {
				System.out.print("  ");
			} else if(i<100) {
				System.out.print(" ");
			}
            for (int j = 0; j < arr[0].length; j++) {
                System.out.print(arr[i][j]+"  ");
            }
            System.out.println();
        }
        
        System.out.println();
    }
	
	private void fitShipH(Ship s) {
		boolean placed = false;
    	while(!placed) {
    		int r = (int)(Math.random()*ROWS); 
    		int c = (int)(Math.random()*(COLS-s.size()+1)); 
    		boolean check = true;
    		for(int i=0; i<s.size(); i++) {
    			if(field[r][c+i]!='-') {
    				check = false;
    			}
    		}
    		if(check) {
    			for(int i=0; i<s.size(); i++) {
    				field[r][c+i]=s.symbol();
    			}
    			s.setCoords(r,c);
    			s.setHorizontal(true);
    			ships.add(s);
    			placed = true;
    		}
    	}
	}
	
	private void fitShipV(Ship s) {
		boolean placed = false;
    	while(!placed) {
    		int r = (int)(Math.random()*(ROWS-s.size()+1)); 
    		int c = (int)(Math.random()*COLS); 
    		boolean check = true;
    		for(int i=0; i<s.size(); i++) {
    			if(field[r+i][c]!='-') {
    				check = false;
    			}
    		}
    		if(check) {
    			for(int i=0; i<s.size(); i++) {
    				field[r+i][c]=s.symbol();
    			}
    			s.setCoords(r,c);
    			s.setHorizontal(false);
    			ships.add(s);
    			placed = true;
    		}
    	}
	}
	
    private void placeShips() {
    	Ship s;
    	// place carrier horizontally
    	s = new Carrier();
    	fitShipH(s);
    	// place bs vertically
    	s = new Battleship();
    	fitShipV(s);
    	// place cruiser vertically
    	s = new Cruiser();
    	fitShipV(s);
    	// place 2 destroyers horizontally
    	s = new Destroyer();
    	fitShipH(s);	
    	s = new Destroyer();
    	fitShipH(s);		
   }

	
}
