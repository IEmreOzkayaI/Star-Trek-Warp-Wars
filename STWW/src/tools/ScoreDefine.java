package tools;

import Treasures.Constants.One;
import Treasures.Constants.Three;
import Treasures.Constants.Two;
import Treasures.Moves.Five;
import Treasures.Moves.Four;

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
		return 0;
	}
}
