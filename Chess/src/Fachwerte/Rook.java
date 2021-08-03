package Fachwerte;

public class Rook extends Piece {

	public Rook(byte coordinate) {
		super(coordinate);
	}

	public int[] getMovement() {
		return new int[] { -8, 1, 8, -1 };
	}

}
