package Fachwerte;

;

public class Zug {


	private final byte _alteFigurPosition;
	private final byte _neueFigurPosition;
	private final char _geschlageneFigur;
	private final byte _enPassant;
	private final boolean[] _whiteCanCastle;
	private final boolean[] _blackCanCastle;
	private final byte _zuegeKleiner50;

	public Zug(byte alteFigurPosition, byte neueFigurPosition, char geschlageneFigur, byte enPassant,boolean[] whiteCanCastle,boolean[] blackCanCastle,byte zuegeKleiner50)
	{
		_alteFigurPosition = alteFigurPosition;
		_neueFigurPosition = neueFigurPosition;
		_geschlageneFigur = geschlageneFigur;
		_enPassant = enPassant;
		_whiteCanCastle = whiteCanCastle;
		_blackCanCastle = blackCanCastle;
		_zuegeKleiner50 = zuegeKleiner50;
	}

	public byte getAlteFigurPosition()
	{
		return _alteFigurPosition;
	}

	public byte getNeueFigurPosition()
	{
		return _neueFigurPosition;
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
	public byte getZuegeKleiner50()
	{
		return _zuegeKleiner50;
	}

}
