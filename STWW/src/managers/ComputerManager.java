package managers;

import entities.Computer;

public class ComputerManager {
	
	Computer comp= new Computer();
	
	public void changeCoordinate(char direction) {
		if(direction=='U') {
			comp.setCy(comp.getCy()+1);
		}
		else if(direction=='B') {
			comp.setCy(comp.getCy()-1);
		}
		else if(direction=='L') {
			comp.setCx(comp.getCx()-1);
		}
		else if(direction=='R') {
			comp.setCx(comp.getCy()+1);
		}
	}
	
	public void increaseComouterScore(char treasure) {
		Boolean flag = Character.isDigit(treasure);
		if(flag) {
			Computer.setComputerTotalScore(Computer.getComputerTotalScore()+((int)treasure *2));
		}
		else {
			if(treasure=='='){
				Computer.setComputerTotalScore(Computer.getComputerTotalScore()+300);
			}
			else if(treasure=='*'){
				Computer.setComputerTotalScore(Computer.getComputerTotalScore()+300);
			}
		}
		
	}
}