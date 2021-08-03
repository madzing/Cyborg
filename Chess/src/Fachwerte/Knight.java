package Fachwerte;

public class Knight extends Figur {
	public Knight(boolean farbe, byte coordinate) {
		super(farbe, coordinate);
	}

	public int[] getMovement() {
		return new int[] { -15, -6, 10, 17, 15, 6, -10, -17 };
	}
}
