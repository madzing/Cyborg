package Material;

public class Queen extends Piece {

	public Queen(byte coordinate, boolean color) {
		super(coordinate, color);
		super.setValue((byte)9);
	}

	public byte[] getMovement() {
		return new byte[] { -8, 1, 8, -1, -7, 9, 7, -9 };
	}
}
