package Material;

public class Pawn extends Piece {
	public Pawn(byte coordinate) {
		super(coordinate);
	}

	@Override
	public byte[] getMovement() {
		return new byte[] {8,-8};
		}
	}

