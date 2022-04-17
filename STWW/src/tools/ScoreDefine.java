package tools;

import Treasures.Constants.One;
import Treasures.Constants.Three;
import Treasures.Constants.Two;
import Treasures.Devices.Trap;
import Treasures.Devices.Warp;
import Treasures.Moves.Five;
import Treasures.Moves.Four;
import entities.Computer;

public class ScoreDefine {
	
	public static int scoreDefinder(Object obje) {

		if(obje.getClass().getSimpleName().toString().equalsIgnoreCase("One")) {
			One one = (One) obje;
			return one.getScore();
		}
		if(obje.getClass().getSimpleName().toString().equalsIgnoreCase("Two")) {
			Two two = (Two) obje;
			return two.getScore();
		}
		if(obje.getClass().getSimpleName().toString().equalsIgnoreCase("Three")) {
			Three three = (Three) obje;
			return three.getScore();
		}
		if(obje.getClass().getSimpleName().toString().equalsIgnoreCase("Four")) {
			Four four = (Four) obje;
			return four.getScore();
		}
		if(obje.getClass().getSimpleName().toString().equalsIgnoreCase("Five")) {
			Five five = (Five) obje;
			return five.getScore();
		}
		if(obje.getClass().getSimpleName().toString().equalsIgnoreCase("Trap")) {
			Trap trap = (Trap) obje;
			return trap.getScore();
		}
		if(obje.getClass().getSimpleName().toString().equalsIgnoreCase("Warp")) {
			Warp warp = (Warp) obje;
			return warp.getScore();
		}
		if(obje.getClass().getSimpleName().toString().equalsIgnoreCase("Computer")) {
			Computer computer = (Computer) obje;
			computer.setCoordinateX(-1);
			computer.setCoordinateY(-1);
			return computer.getScore();
		}
		return 0;
	}
}
