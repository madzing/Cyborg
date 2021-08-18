package Material;

import java.util.Map;

public class Queen extends Piece implements Copy {

	public Queen(byte coordinate, boolean color) {
		super(coordinate, color);
		super.setValue((byte)9);
	}

	public byte[] getMovement() {
		return new byte[] { -8, 1, 8, -1, -7, 9, 7, -9 };
	}
	public Map<Byte, Piece> copy(Map<Byte, Piece> copyable) {
		copyable.put(this.getCoordinate(),
				new Queen(this.getCoordinate(), this.getColor()));
		return copyable;
	}
}
