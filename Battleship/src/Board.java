
/**
 * Battleship board using a 2D array.
 * @author nozymko
 *
 */
public class Board {

	/**
	 * 2D char array of all board chars.
	 */
	private char[][] board;
	
	/**
	 * List of all ships for a board.
	 */
	public Ship[] ships;
	
	/**
	 * Initializes the board array to an empty board.
	 * Creates ships for the board.
	 */
	public Board() {
		this.board = new char[9][9];
		this.ships = new Ship[5];
		for (int i = 0; i < board.length; i++) {
			for (int x = 0; x < board[0].length; x++) {
				this.board[i][x] = ' ';
			}
		}
		ships[0] = new Ship(5);
		ships[1] = new Ship(4);
		ships[2] = new Ship(3);
		ships[3] = new Ship(3);
		ships[4] = new Ship(2);
	}
	
	/**
	 * Places a ship on the board.
	 * x and y should be between 0 and 8
	 * @param x location
	 * @param y location
	 * @param ship to place
	 * @param vertical if ship is placed vertically
	 * @return if ship successfully placed
	 */
	public boolean setShip(int x, int y, Ship ship, boolean vertical) {
		if (x > 8 || x < 0) {
			System.out.println("Invalid location");
			return false;
		}
		if (y > 8 || y < 0) {
			System.out.println("Invalid location");
			return false;
		}
		int shipLength = ship.getLength();
		if (vertical) {
			if (x + shipLength > 9) {
				System.out.println("Invalid location");
				return false;
			}
		} else {
			if (y + shipLength > 9) {
				System.out.println("Invalid location");
				return false;
			}
		}
		if (vertical) {
			for (int i = x; i < x + shipLength; i++) {
				if (this.getBoard(i, y) == 'X') {
					System.out.println("There is already a ship at this location.");
					return false;
				}
			}
		} else {
			for (int i = y; i < y + shipLength; i++) {
				if (this.getBoard(x, i) == 'X') {
					System.out.println("There is already a ship at this location.");
					return false;
				}
			}
		}
		if (vertical) {
			for (int i = x; i < x + shipLength; i++) {
				this.setBoard(i, y, 'X');
				int[] coord = {i, y};
				ship.setCoords(coord, i - x);
			}
		} else {
			for (int i = y; i < y + shipLength; i++) {
				this.setBoard(x, i, 'X');
				int[] coord = {x, i};
				ship.setCoords(coord, i - y);
			}
		}
		System.out.println("Ship successfully placed.");
		return true;
	}
	
	/**
	 * Sets board at a given location.
	 * @param x location of board
	 * @param y location of board
	 * @param toSet what to set to
	 */
	public void setBoard(int x, int y, char toSet) {
		this.board[x][y] = toSet;
	}
	
	/**
	 * Returns the char at a given location
	 * @param x location of board
	 * @param y location of board
	 * @return char at location
	 */
	public char getBoard(int x, int y) {
		return this.board[x][y];
	}
	
	/**
	 * Prints the board.
	 * @return board as a string
	 */
	public String toString() {
		String boardString = "   0   1   2   3   4   5   6   7   8\n";
		for (int i = 0; i < this.board.length; i++) {
			boardString += ((char)((int)'A' + i)) + " ";
			for (int x = 0; x < this.board[0].length; x++) {
				boardString += " " + this.board[i][x] + " |";
			}
			boardString += "\n" + "--------------------------------------" + "\n";
		}
		return boardString;
	}
	
	/**
	 * Checks to see if all the ships on a board were destroyed.
	 * @return if all ships have been destroyed
	 */
	public boolean won() {
		for (int i = 0; i < this.ships.length; i++) {
			if (!this.ships[i].getDestroyed()) {
				return false;
			}
		}
		return true;
	}
	
}