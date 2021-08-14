package Fachwerte;

import java.util.HashMap;
import java.util.Map;

public class Zug {

	private static Map<String, Zug> _ZugMap = new HashMap<String, Zug>();
	private final Coordinate _alteFigurPosition;
	private final Coordinate _neueFigurPosition;
	private final char _geschlageneFigur;

	private Zug(byte alteFigurPosition, byte neueFigurPosition, char geschlageneFigur) {
		_alteFigurPosition = Coordinate.select(neueFigurPosition);
		_neueFigurPosition = Coordinate.select(neueFigurPosition);
		_geschlageneFigur = geschlageneFigur;

	}
	
	public static Zug select (byte alteFigurPosition, byte neueFigurPosition, char geschlageneFigur) {
		String Key = generateKey(alteFigurPosition,neueFigurPosition,geschlageneFigur);
		
		if (!_ZugMap.containsKey(Key)) {
			_ZugMap.put(Key, new Zug(alteFigurPosition,neueFigurPosition, geschlageneFigur));
		}
		return _ZugMap.get(Key);
	}
	private static String generateKey(byte alteFigurPosition, byte neueFigurPosition, char geschlageneFigur)
	{
		return alteFigurPosition+"/"+neueFigurPosition+"/"+geschlageneFigur;
	}


	public byte getAlteFigurPosition() 
	{
		return _alteFigurPosition.getCoordinate();
	}

	public byte getNeueFigurPosition() 
	{
		return _neueFigurPosition.getCoordinate();
	}

	public char getGeschlageneFigur() 
	{
		return _geschlageneFigur;
	}

}
