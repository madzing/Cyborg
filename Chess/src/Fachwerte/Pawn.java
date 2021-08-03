package Fachwerte;

public class Pawn extends Figur {
	public Pawn(boolean farbe, byte coordinate) {
		super(farbe, coordinate);
	}

	@Override
	public int[] getMovement() {
		if (getColor()) {
			return new int[] { -8 };
		} else {
			return new int[] { 8 };
		}
	}
}
