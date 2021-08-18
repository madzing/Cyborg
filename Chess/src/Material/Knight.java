package Material;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Knight extends Piece {
	public Knight(byte coordinate, boolean color) {
		super(coordinate,color);
		super.setValue((byte)3);
	}

	public byte[] getMovement() {
		return new byte[] { -15, -6, 10, 17, 15, 6, -10, -17 };
	}

	public ArrayList<Byte> getMoves(Map<Byte, Piece> figurenAmZug, Map<Byte, Piece> figurenDesGegners, Position position) {
		ArrayList<Byte> list = new ArrayList<Byte>();
		for (int i = 0; i < getMovement().length; i++) {

			if (getCoordinate() + getMovement()[i] >= 0 && getCoordinate() + getMovement()[i] <= 63) {
				if (SprungUeberKante(getCoordinate(), getCoordinate() + getMovement()[i])) {
					// tue nichts
				}
			
				else if (figurenAmZug.containsKey((byte) (getCoordinate() + getMovement()[i]))) {
					// tue nichts
				} 
				else {
					list.add((byte) (getCoordinate() + getMovement()[i]));
				}
			} 
		}
		return list;
	}
	public Map<Byte, Piece> copy(Map<Byte, Piece> copyable) {
		copyable.put(super._coordinate,
				new Knight(super._coordinate, super._color));
		return copyable;
	}
}
