package tools;

public  class ObjeComparator {
	
	public static String objComparator(Object obje) {
		if(obje.equals("#")) {
			return "#";
		}
		if(obje.equals(" ")) {
			return" ";
		}
		if(obje.getClass().getSimpleName().toString().equalsIgnoreCase("One")) {
			return"1";
		}
		if(obje.getClass().getSimpleName().toString().equalsIgnoreCase("Two")) {
			return"2";
		}
		if(obje.getClass().getSimpleName().toString().equalsIgnoreCase("Three")) {
			return"3";
		}
		if(obje.getClass().getSimpleName().toString().equalsIgnoreCase("Four")) {
			return"4";
		}
		if(obje.getClass().getSimpleName().toString().equalsIgnoreCase("Five")) {
			return"5";
		}
		if(obje.getClass().getSimpleName().toString().equalsIgnoreCase("Warp")) {
			return"*";
		}
		if(obje.getClass().getSimpleName().toString().equalsIgnoreCase("Trap")) {
			return"=";
		}
		if(obje.getClass().getSimpleName().toString().equalsIgnoreCase("Computer")) {
			return"C";
		}
		if(obje.getClass().getSimpleName().toString().equalsIgnoreCase("Player")) {
			return"P";
		}
		return null;
	}
}
