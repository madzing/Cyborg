package Fachwerte;

import Material.Piece;
import Material.Position;

;

public class Zug implements Comparable<Object> {


	private final byte _alteFigurPosition;
	private final byte _neueFigurPosition;
	private final char _geschlageneFigur;
	private Piece _promoteteFigur;
	private final byte _enPassant;
	private final boolean[] _whiteCanCastle;
	private final boolean[] _blackCanCastle;
	private final byte _zuegeKleiner50;
	private int _comparator =0;

	public Zug(byte alteFigurPosition, byte neueFigurPosition, char geschlageneFigur,
			byte enPassant,boolean[] whiteCanCastle,boolean[] blackCanCastle,byte zuegeKleiner50,
			Piece _promoteteFigur)
	{
		_alteFigurPosition = alteFigurPosition;
		_neueFigurPosition = neueFigurPosition;
		_geschlageneFigur = geschlageneFigur;
		this._promoteteFigur = _promoteteFigur;
		_enPassant = enPassant;
		_whiteCanCastle = whiteCanCastle;
		_blackCanCastle = blackCanCastle;
		_zuegeKleiner50 = zuegeKleiner50;
	}
	
	public Zug(Zug zug)
	{
		_alteFigurPosition = zug._alteFigurPosition;
		_neueFigurPosition = zug._neueFigurPosition;
		_geschlageneFigur = zug._geschlageneFigur;
		this._promoteteFigur = zug._promoteteFigur;
		_enPassant = zug._enPassant;
		_whiteCanCastle = copyArray(zug._whiteCanCastle);
		_blackCanCastle = copyArray(zug._blackCanCastle);
		_zuegeKleiner50 = zug._zuegeKleiner50;
		
	}
	private boolean[] copyArray(boolean[] copyable) {
		boolean[] array = { false, false };
		array[0] = copyable[0];
		array[1] = copyable[1];
		return array;
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
	
	public Piece getPromoteteFigur()
	{
		return _promoteteFigur;
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
	
	public void setPromotion(Piece piece)
	{
		_promoteteFigur = piece;
	}
	public int getComparator()
	{
		return _comparator;
	}

	public void setComparator(double d)
	{
		_comparator = (int) (d*10000);
	}

	// sortiert für schwarz gut aber für weiß nicht?
	@Override
	public int compareTo(Object comparable) {
//		if(_zugrecht)
//		{
//			return getComparator() - ((Position) comparable).getComparator();
//
//		}
//		else
//		{
			return ((Zug) comparable).getComparator()-getComparator();
//		}

	}
}
