package Material;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Bishop extends Piece {

	public Bishop(byte coordinate, boolean color) {
		super(coordinate, color);
		
	}

	public byte[] getMovement() {
		return new byte[] { -7, 9, 7, -9 };
	}
}
