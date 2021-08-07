package Material;

public class Bishop extends Piece {

	public Bishop(byte coordinate) {
		super(coordinate);
	}

	public byte[] getMovement() {
		return new byte[] { -7, 9, 7, -9 };
	}
}
