package Fachwerte;

public class Rook extends Figur {

	public Rook(boolean farbe, byte coordinate) {
		super(farbe, coordinate);
	}

	public int[] getMovement() {
		return new int[] { -8, 1, 8, -1 };
	}

}
