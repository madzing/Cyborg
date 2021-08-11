package Material;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Bishop extends Piece {

	public Bishop(byte coordinate) {
		super(coordinate);
	}

	public byte[] getMovement() {
		return new byte[] { -7, 9, 7, -9 };
	}
}
