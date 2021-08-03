package Fachwerte;

public abstract class Piece {
	//private boolean _color;
	private PieceValue _value;
	private byte _coordinate;

	public Piece (byte coordinate) {
		setCoordinate(coordinate);
	}
	
	public byte getValue() {
		return _value.getValue();
	}

	public byte getCoordinate() {
		return _coordinate;
	}

	//public abstract int[] getMovement();

	public void setCoordinate(byte coordinate) {
		_coordinate = coordinate;
	}
}
