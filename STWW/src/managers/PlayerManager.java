package managers;
import entities.Player;
import tools.Stack;

public class PlayerManager {
	
	Player player = new Player();
	
	public void EnergyAdd(int energyValue) {
		player.setEnergy(energyValue + player.getEnergy());
	}
	
	public void LifeRemove() {
		player.setLife(player.getLife() - 1);;
	}
	
	public void CoordinateChange(char direction) {
		//U UP - R RIGHT - L LEFT- B DOWN
		if(direction == 'U') {
			player.setCoordinates(player.getCoordinateX(), player.getCoordinateY() + 1);
		}else if(direction == 'R') {
			player.setCoordinates(player.getCoordinateX() + 1, player.getCoordinateY());
		}else if(direction == 'L') {
			player.setCoordinates(player.getCoordinateX() - 1 , player.getCoordinateY());
		}else if(direction == 'B') {
			player.setCoordinates(player.getCoordinateX(), player.getCoordinateY() - 1);
		}
	}
}