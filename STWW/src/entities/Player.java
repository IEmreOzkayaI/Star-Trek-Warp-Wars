package entities;

import java.util.SplittableRandom;

import tools.Stack;

public class Player {
	Stack backpack;
	int life = 5;
	int energy = 50;
	SplittableRandom splittableRandom = new SplittableRandom();
	int randomX = splittableRandom.nextInt(1, 55);
	int randomY = splittableRandom.nextInt(1, 23);
	private int coordinateX = randomX;
	private int coordinateY = randomY;
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
	}

	public void CoordinateChange(char direction) {
		//U UP - R RIGHT - L LEFT - B DOWN
		if(direction == 'U') {
			this.setX(this.getX());
			this.setY(this.getY() + 1);
		}else if(direction == 'R') {
			this.setX(this.getX() + 1);
			this.setY(this.getY());
		}else if(direction == 'L') {
			this.setX(this.getX() - 1);
			this.setY(this.getY());
		}else if(direction == 'B') {
			this.setX(this.getX());
			this.setY(this.getY() - 1);
		}
	}

	public void BackpackAdd(int item) {
		backpack.push(item);
	}
	
	public void BackpackRemove() {
		backpack.pop();
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

	public int getX() {
		return coordinateX;
	}

	public int getY() {
		return coordinateY;

	}

	public void setX(int coordinateX) {
		this.coordinateX = coordinateX;
	}
	
	public void setY(int coordinateY) {
		this.coordinateY = coordinateY;
	}
	
	public String getName() {
		return name;
	}
	public int getScore() {
		return score;
	}


}
