package Material;

import Fachwerte.PieceValue;

public class Rook extends Piece {

	public Rook(byte coordinate, boolean color) {
		super(coordinate, color);
		super.setValue((byte)5);
	}

	public byte[] getMovement() {
		return new byte[] { -8, 1, 8, -1 };
	}

}
