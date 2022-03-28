package entities;

import java.util.SplittableRandom;

import tools.Stack;

public class Player {
	Stack backpack;
	int life = 5;
	int energy = 50;
	private int coordinateX = 0;
	private int coordinateY = 0;
	private final String name = "P";
	private final int score = 0;

	public Player() {
	}

	public Player(int life, int energy, Stack backpack) {
		this.life = life;
		this.energy = energy;
		this.backpack = backpack;
	}

	public void EnergyAdd(int energyValue) {
		this.setEnergy(energyValue + this.getEnergy());
	}

	public void LifeRemove() {
		this.setLife(this.getLife() - 1);
		;
	}

//	public void CoordinateChange(char direction) {
//		//U UP - R RIGHT - L LEFT- B DOWN
//		if(direction == 'U') {
//			this.setCoordinates(this.getCoordinateX(), this.getCoordinateY() + 1);
//		}else if(direction == 'R') {
//			this.setCoordinates(this.getCoordinateX() + 1, this.getCoordinateY());
//		}else if(direction == 'L') {
//			this.setCoordinates(this.getCoordinateX() - 1 , this.getCoordinateY());
//		}else if(direction == 'B') {
//			this.setCoordinates(this.getCoordinateX(), this.getCoordinateY() - 1);
//		}
//	}

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

	public int getX() {
		SplittableRandom splittableRandom = new SplittableRandom();
		int random = splittableRandom.nextInt(1, 55);
		coordinateX = random;
		return coordinateX;
	}

	public int getY() {
		SplittableRandom splittableRandom = new SplittableRandom();
		int random = splittableRandom.nextInt(1, 23);
		coordinateY = random;
		return coordinateY;

	}

	public String getName() {
		return name;
	}
	public int getScore() {
		return score;
	}
}
