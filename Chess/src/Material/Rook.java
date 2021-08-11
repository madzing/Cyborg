package Material;

public class Rook extends Piece {

	public Rook(byte coordinate, boolean color) {
		super(coordinate, color);
	}

	public byte[] getMovement() {
		return new byte[] { -8, 1, 8, -1 };
	}

}
