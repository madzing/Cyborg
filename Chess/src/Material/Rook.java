package Material;

import java.util.Map;

public class Rook extends Piece implements Copy{

	public Rook(byte coordinate, boolean color) {
		super(coordinate, color);
		super.setValue((byte)5);
	}

	public byte[] getMovement() {
		return new byte[] { -8, 1, 8, -1 };
	}
	public Map<Byte, Piece> copy(Map<Byte, Piece> copyable) {
		copyable.put(this.getCoordinate(),
				new Rook(this.getCoordinate(), this.getColor()));
		return copyable;
	}

}
