import java.util.Scanner;

/**
 * Game class, puts together the game.
 * @author nozymko
 *
 */
public class Game {

	public static void main(String[] args) {
		int numPlayers = getNumPlayers();
		if (numPlayers == 0) {
			//implement two computer game using computer logic
		} else if (numPlayers == 1) {
			String name = getPlayerName(1);
			Board playerShips = new Board();
			Board playerHits = new Board();
			Board computerShips = new Board();
			Board computerHits = new Board();
			System.out.println(name + " place your ships.");
			placeShips(playerShips);
			System.out.println(playerShips);
			System.out.println("Computer will place their ships.");
			//computer place ships
			while (!playerShips.won() && !computerShips.won()) {
				System.out.println(playerHits);
				System.out.println(name + "'s turn");
				int[] hitCoord = getCoords();
				while (hit(hitCoord, computerShips, playerHits)) {
					if (computerShips.won()) {
						break;
					}
					System.out.println(playerHits);
					System.out.println(name + " gets to go again!");
					hitCoord = getCoords();
				}
				System.out.println(playerHits);
				System.out.println("Computer's turn");
				//get computer's coordinates as int array
				//copy the loop and keep the computer going while hit is true
			}
			if (playerShips.won()) {
				System.out.println("Computer has won!");
			} else {
				System.out.println(name + " has won!");
			}
		} else if (numPlayers == 2) {
			String name1 = getPlayerName(1);
			String name2 = getPlayerName(2);
			Board board1Ships = new Board();
			Board board2Ships = new Board();
			Board board1Hits = new Board();
			Board board2Hits = new Board();
			System.out.println(name1 + " place your ships.");
			placeShips(board1Ships);
			System.out.println(board1Ships);
			System.out.println(name2 + " place your ships.");
			placeShips(board2Ships);
			System.out.println(board2Ships);
			while (!board1Ships.won() && !board2Ships.won()) {
				System.out.println(board1Hits);
				System.out.println(name1 + "'s turn");
				int[] hitCoord = getCoords();
				while (hit(hitCoord, board2Ships, board1Hits)) {
					if (board2Ships.won()) {
						break;
					}
					System.out.println(board1Hits);
					System.out.println(name1 + " gets to go again!");
					hitCoord = getCoords();
				}
				System.out.println(board1Hits);
				System.out.println(board2Hits);
				System.out.println(name2 + "'s turn");
				hitCoord = getCoords();
				while (hit(hitCoord, board1Ships, board2Hits)) {
					if (board1Ships.won()) {
						break;
					}
					System.out.println(board2Hits);
					System.out.println(name2 + " gets to go again!");
					hitCoord = getCoords();
				}
				System.out.println(board2Hits);
			}
			if (board1Ships.won()) {
				System.out.println(name2 + " has won!");
			} else {
				System.out.println(name1 + " has won!");
			}
		}
	}
	
	/**
	 * Gets the number of players playing Battleship from the user.
	 * @return number of players between 0 and 2
	 */
	public static int getNumPlayers() {
		Scanner scan = new Scanner(System.in);
		System.out.println("How many players will be playing? (0-2)");
		int players = scan.nextInt();
		while (players < 0 || players > 2) {
			System.out.println("Invalid number of players, try again.");
			players = scan.nextInt();
		}
		return players;
	}
	
	/**
	 * Gets a player's name.
	 * @param num player name (first player or second player)
	 * @return player name
	 */
	public static String getPlayerName(int num) {
		Scanner scan = new Scanner(System.in);
		System.out.println("Player " + num + " enter your name.");
		return scan.nextLine();
	}
	
	/**
	 * User places ship on a given board.
	 * @param board to place ships on
	 */
	public static void placeShips(Board board) {
		for (int i = 0; i < board.ships.length; i++) {
			System.out.println(board);
			System.out.println("Where would you like to place a ship of length " + board.ships[i].getLength());
			int[] coords = getCoords();
			boolean vertical = getVertical();
			while (!board.setShip(coords[0], coords[1], board.ships[i], vertical)) {
				System.out.println("Try again.");
				coords = getCoords();
				vertical = getVertical();
			}
		}
	}
	
	/**
	 * Gets coordinates from the user.
	 * @return int array where index 0 is x, index 1 is y
	 */
	public static int[] getCoords() {
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter coordinate letter");
		String letter = scan.nextLine();
		letter = letter.toUpperCase();
		while (letter.length() > 1 || ((int)letter.charAt(0) > (int)'I') || ((int)letter.charAt(0) < (int)'A')) {
			System.out.println("Invalid letter, try again.");
			letter = scan.nextLine();
			letter = letter.toUpperCase();
		}
		System.out.println("Enter coordinate number");
		int number = scan.nextInt();
		while (number < 0 || number > 8) {
			System.out.println("Invalid number, try again.");
			number = scan.nextInt();
		}
		int[] coords = new int[2];
		coords[0] = (int)letter.charAt(0) - ((int)'A');
		coords[1] = number;
		return coords;
	}
	
	/**
	 * Gets if a ship is vertical or horizontal from the user.
	 * @return true if vertical, false if horizontal
	 */
	public static boolean getVertical() {
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter 0 to place the ship vertically, 1 to place the ship horizontally.");
		int verticalNum = scan.nextInt();
		while (verticalNum != 0 && verticalNum != 1) {
			System.out.println("You must enter 0 for vertical or 1 for horizontal.");
			verticalNum = scan.nextInt();
		}
		if (verticalNum == 0) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Sees if a ship has been hit given a coordinate and two boards.
	 * Updates the hit board where the user tried to hit. (X if destroyed, x if hit, * if miss)
	 * @param coord coordinate to try hitting
	 * @param boardShips board containing the ships being tested to be hit
	 * @param boardHit board updated to show the hits
	 * @return true if ship was hit, false otherwise
	 */
	public static boolean hit(int[] coord, Board boardShips, Board boardHit) {
		for (int i = 0; i < boardShips.ships.length; i++) { //goes through all the ships
			for (int x = 0; x < boardShips.ships[i].coords.length; x++) { //goes through ship's coordinates
				if (coord[0] == boardShips.ships[i].coords[x][0] && coord[1] == boardShips.ships[i].coords[x][1]) {
					boardShips.ships[i].setHits(true, x);
					if (boardShips.ships[i].getDestroyed()) {
						for (int w = 0; w < boardShips.ships[i].coords.length; w++) {
							boardHit.setBoard(boardShips.ships[i].coords[w][0], boardShips.ships[i].coords[w][1], 'X');
						}
						System.out.println("You destroyed a ship!");
						return true;
					} else {
						boardHit.setBoard(coord[0], coord[1], 'x');
						return true;
					}
				}
			}
		}
		boardHit.setBoard(coord[0], coord[1], '*');
		System.out.println("Missed!");
		return false;
	}
	
}
