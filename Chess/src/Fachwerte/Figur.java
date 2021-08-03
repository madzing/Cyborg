package Fachwerte;

public abstract class Figur {
	private boolean _color;
	private byte _value;
	private byte _coordinate;

	public Figur (boolean farbe, byte coordinate) {
		setColor(farbe);
		setCoordinate(coordinate);
	}
	
	public byte getValue() {
		return _value;
	}

	public boolean getColor() {
		return _color;
	}

	public byte getCoordinate() {
		return _coordinate;
	}

	public abstract int[] getMovement();

	public void setColor(boolean c) {
		_color = c;
	}

	public void setCoordinate(byte coordinate) {
		_coordinate = coordinate;
	}
}
