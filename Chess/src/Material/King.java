package Material;

public class King extends Piece {
	public King(byte coordinate) {
		super(coordinate);
	}

	public byte[] getMovement() {
		return new byte[] { -8, 1, 8, -1, -7, 9, 7, -9 };
	}

}
