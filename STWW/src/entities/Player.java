package entities;
import tools.Stack;

public class Player {
	Stack backpack;
	int life = 5;
	int energy = 50;
	int[] coordinates = new int[2]; // 0 is X - 1 is Y
	public Player() {}
	
	public Player(int life, int energy, int[] coordinates, Stack backpack) {
		this.life = life;
		this.energy = energy;
		this.coordinates = coordinates;
		this.backpack = backpack;
	}
	
	public Stack getBackpack() {
		return backpack;
	}
	public void setBackpack(Stack backpack) {
		this.backpack = backpack;
	}
	public int getLife() {
		return life;
	}
	public void setLife(int life) {
		this.life = life;
	}
	public int getEnergy() {
		return energy;
	}
	public void setEnergy(int energy) {
		this.energy = energy;
	}
	public int getCoordinateX() {
		return coordinates[0];
	}
	public int getCoordinateY() {
		return coordinates[1];
	}
	public void setCoordinates(int xcoordinates, int ycoordinates) {
		this.coordinates[0] = xcoordinates;
		this.coordinates[1] = ycoordinates;
	}
<<<<<<< Updated upstream
}
=======
}
>>>>>>> Stashed changes
