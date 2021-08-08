package Material;

public class Knight extends Piece {
	public Knight(byte coordinate) {
		super(coordinate);
	}

	public byte[] getMovement() {
		return new byte[] { -15, -6, 10, 17, 15, 6, -10, -17 };
	}
}
