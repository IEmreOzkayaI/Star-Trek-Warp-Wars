package entities;

public class Computer {

	private int Cx;
	private int Cy;
	private static int computerTotalScore=0;
	
	public Computer() {}
	public Computer(int Cx, int Cy) {
		this.Cx=Cx;
		this.Cy=Cy;
		computerTotalScore=0;
	}
	public int getCx() {
		return Cx;
	}
	public void setCx(int cx) {
		Cx = cx;
	}
	public int getCy() {
		return Cy;
	}
	public void setCy(int cy) {
		Cy = cy;
	}
	public static int getComputerTotalScore() {
		return computerTotalScore;
	}
	public static void setComputerTotalScore(int computerTotalScore) {
		Computer.computerTotalScore = computerTotalScore;
	}

		
	
	
}

