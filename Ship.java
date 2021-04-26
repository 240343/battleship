package battleship;

public class Ship {
	private char symbol;
	private int length;
	private boolean isHorizontal;
	private int row, col;
	private boolean isSunk;
	public Ship(char s, int len) {
		symbol = s;
		length = len;
		isHorizontal = true;
		row = 0;
		col = 0;
		isSunk = false;
	}
	public void update(char[][] f) {
		boolean sunk = true;
		for(int i=0; i<length; i++) {
			if(isHorizontal) {
				if(f[row][col+i]!='X') {
					sunk = false;
				}
			}else {
				if(f[row+i][col]!='X') {
					sunk = false;
				}
			}
		}
		if(sunk) isSunk = true;
		else isSunk = false;
	}
	public boolean sunk() {
		return isSunk;
	}
	
	public boolean onShip(int r, int c) {
		for(int i=0; i<length; i++) {
			if(isHorizontal) {
				if(row==r && col+i==c) {
					return true;
				}
			}else {
				if(row+i==r && col==c) {
					return true;
				}
			}
		}
		return false;
	}
	
	public int size() {
		return length;
	}
	public char symbol() {
		return symbol;
	}
	public int row() {
		return row;
	}
	public int col() {
		return col;
	}
	public boolean isHorizontal() {
		return isHorizontal;
	}
	
	public void setHorizontal(boolean b) {
		isHorizontal = b;
	}
	public void setCoords(int r, int c) {
		row = r;
		col = c;
	}
}
