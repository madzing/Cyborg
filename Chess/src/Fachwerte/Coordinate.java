package Fachwerte;

public class Coordinate {
	private final byte _coordinate;
	
	private Coordinate(byte coordinate) {
		_coordinate = coordinate;
	}
	
	public static Coordinate select (byte coordinate) {
		return new Coordinate(coordinate);
	}
	
	public byte getCoordinate() {
		return _coordinate;
	}
}
