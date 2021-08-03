package Fachwerte;

import java.util.HashMap;
import java.util.Map;

public class Coordinate {
	private static Map<String, Coordinate> _coordinateMap = new HashMap<String, Coordinate>();
	private final byte _coordinate;
	
	private Coordinate(byte coordinate) {
		_coordinate = coordinate;
	}
	
	public static Coordinate select (byte coordinate) {
		if (!_coordinateMap.containsKey(""+ coordinate)) {
			_coordinateMap.put(""+ coordinate, new Coordinate(coordinate));
		}
		return _coordinateMap.get(""+ coordinate);
	}
	
	public byte getCoordinate() {
		return _coordinate;
	}
	@Override
	public String toString() {
		return ""+_coordinate;
	}
}