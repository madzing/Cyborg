package Fachwerte;

public class King extends Figur {
	public King(boolean farbe, byte coordinate) {
		super(farbe, coordinate);
	}

	public int[] getMovement() {
		return new int[] { -8, 1, 8, -1, -7, 9, 7, -9 };
	}

}
