package Material;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Bishop extends Piece implements Copy {

	public Bishop(byte coordinate, boolean color) {
		super(coordinate, color);
		super.setValue((byte)3);
	}

	public byte[] getMovement() {
		return new byte[] { -7, 9, 7, -9 };
	}
	public Map<Byte, Piece> copy(Map<Byte, Piece> copyable) {
		copyable.put(this.getCoordinate(),
				new Bishop(this.getCoordinate(), this.getColor()));
		return copyable;
	}
}
