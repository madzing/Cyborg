package Fachwerte;

import java.util.HashMap;
import java.util.Map;

public class Coordinate {
	private static Map<Byte, Coordinate> _coordinateMap = new HashMap<Byte, Coordinate>(64);
	private final byte _coordinate;
	
	private Coordinate(byte coordinate) {
		_coordinate = coordinate;
	}
	
	public static Coordinate select (byte coordinate) {
		if (!_coordinateMap.containsKey(coordinate)) {
			_coordinateMap.put(coordinate, new Coordinate(coordinate));
		}
		return _coordinateMap.get(coordinate);
	}
	
	public byte getCoordinate() {
		return _coordinate;
	}

}
