package Fachwerte;

import java.util.HashMap;
import java.util.Map;

public class Zug {

	private static Map<String, Zug> _zugMap = new HashMap<String, Zug>();
	private final Coordinate _alteFigurPosition;
	private final Coordinate _neueFigurPosition;
	private final char _geschlageneFigur;
	private final byte _enPassant;
	private final boolean[] _whiteCanCastle; 
	private final boolean[] _blackCanCastle;
	
	private Zug(byte alteFigurPosition, byte neueFigurPosition, char geschlageneFigur, byte enPassant,boolean[] whiteCanCastle,boolean[] blackCanCastle) 
	{
		_alteFigurPosition = Coordinate.select(neueFigurPosition);
		_neueFigurPosition = Coordinate.select(neueFigurPosition);
		_geschlageneFigur = geschlageneFigur;
		_enPassant = enPassant;
		_whiteCanCastle = whiteCanCastle;
		_blackCanCastle = blackCanCastle;
	}
	
	public static Zug select (byte alteFigurPosition, byte neueFigurPosition, char geschlageneFigur,byte enPassant,boolean[] whiteCanCastle,boolean[] blackCanCastle) {
		String Key = generateKey(alteFigurPosition,neueFigurPosition,geschlageneFigur,enPassant,whiteCanCastle,blackCanCastle);
		
		if (!_zugMap.containsKey(Key)) {
			_zugMap.put(Key, new Zug(alteFigurPosition,neueFigurPosition, geschlageneFigur, enPassant, whiteCanCastle, blackCanCastle));
		}
		return _zugMap.get(Key);
	}
	private static String generateKey(byte alteFigurPosition, byte neueFigurPosition, char geschlageneFigur,byte enPassant, boolean[] whiteCanCastle,boolean[] blackCanCastle)
	{
		return alteFigurPosition+"/"+neueFigurPosition+"/"+geschlageneFigur+"/"+enPassant+"/"+whiteCanCastle+"/"+blackCanCastle;
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
	public byte getenPassant()
	{
		return _enPassant;
	}
	public boolean[] getWhiteCanCastle()
	{
		return _whiteCanCastle;
	}
	public boolean[] getBlackCanCastle()
	{
		return _blackCanCastle;
	}

}
