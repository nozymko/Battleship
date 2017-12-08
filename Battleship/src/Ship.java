
/**
 * Class for a ship object.
 * @author nozymko
 *
 */
public class Ship {
	
	private int length;
	private int startX, startY;
	private boolean destroyed;
	public boolean[] hits;
	public int[][] coords;
	
	/**
	 * Creates a new ship.
	 * @param length of the ship (5-2)
	 */
	public Ship(int length) {
		this.length = length;
		this.destroyed = false;
		this.coords = new int[this.length][2];
		this.hits = new boolean[this.length];
	}
	
	/**
	 * Sets the coordinates of a ship at a given index.
	 * @param coord coordinate to set coordinates at index
	 * @param index of coordinate
	 */
	public void setCoords(int[] coord, int index) {
		this.coords[index][0] = coord[0];
		this.coords[index][1] = coord[1];
	}
	
	/**
	 * Sees if a coordinate is part of the ship's coordinates.
	 * @param coord to test
	 * @return true if coordinate part of ship, false otherwise
	 */
	public boolean testCoords(int[] coord) {
		for (int i = 0; i < coords.length; i++) {
			if (coord[0] == coords[i][0] && coord[1] == coords[i][1]) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Gives the length of the ship.
	 * @return length of ship
	 */
	public int getLength() {
		return this.length;
	}
	
	/**
	 * Gets the start of the ship.
	 * @return x value of start of ship
	 */
	public int getStartX() {
		return this.startX;
	}
	
	/**
	 * Gets the start of the ship.
	 * @return y value of start of ship
	 */
	public int getStartY() {
		return this.startY;
	}
	
	/**
	 * Sees if ship has been destroyed.
	 * @return if ship has been destroyed
	 */
	public boolean getDestroyed() {
		return this.destroyed;
	}
	
	/**
	 * Sets the hit boolean array at given index.
	 * @param hit if ship has been hit (true or false)
	 * @param index of hit array to set
	 */
	public void setHits(boolean hit, int index) {
		this.hits[index] = hit;
		this.destroyed = true;
		for (int i = 0; i < this.hits.length; i++) {
			if (this.hits[i] == false) {
				this.destroyed = false;
			}
		}
	}
	
}
