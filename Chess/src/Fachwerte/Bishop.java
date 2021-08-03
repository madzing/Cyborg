package Fachwerte;

public class Bishop extends Figur {

	public Bishop(boolean farbe, byte coordinate) {
		super(farbe, coordinate);
	}

	public int[] getMovement() {
		return new int[] { -7, 9, 7, -9 };
	}
}
