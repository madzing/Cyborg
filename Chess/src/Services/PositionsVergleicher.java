package Services;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import Material.Piece;
import Material.Position;

public class PositionsVergleicher {
	private Position _positionNeu;
	private Position _positionAlt;
	private int _alteKoordinate;
	private int _neueKoordinate;
	Map<Byte, Piece> _alteBlackFiguren;
	Map<Byte, Piece> _neueBlackFiguren;
	Map<Byte, Piece> _alteWhiteFiguren;
	Map<Byte, Piece> _neueWhiteFiguren;
	ArrayList<Position> _positionsListe = new ArrayList<Position>(6000);
	int _aktuellerZug;
		
	public PositionsVergleicher(Position alt, Position neu)
	{
		_positionAlt = alt;
		_positionNeu = neu;
		_alteBlackFiguren = _positionAlt.getBlackFiguren();
		_neueBlackFiguren = _positionNeu.getBlackFiguren();
		_alteWhiteFiguren = _positionAlt.getWhiteFiguren();
		_neueWhiteFiguren = _positionNeu.getWhiteFiguren();
	}
	public PositionsVergleicher(ArrayList<Position> positionsListe, int aktuellerZug)
	{
		_positionsListe = positionsListe;
		_aktuellerZug = aktuellerZug;
		_positionAlt = _positionsListe.get(_aktuellerZug-1);
		_positionNeu = _positionsListe.get(_aktuellerZug);
		_alteBlackFiguren = _positionAlt.getBlackFiguren();
		_neueBlackFiguren = _positionNeu.getBlackFiguren();
		_alteWhiteFiguren = _positionAlt.getWhiteFiguren();
		_neueWhiteFiguren = _positionNeu.getWhiteFiguren();
	}
	
	public int getAlteKoordinate()
	{
		return _alteKoordinate;
	}
	public int getNeueKoordinate()
	{
		return _neueKoordinate;
	}
	public void whatMoveWasMade(Position altePosition, Position neuePosition)
	{
		Map<Byte, Piece> alteBlackFiguren = altePosition.getBlackFiguren();
		Map<Byte, Piece> neueBlackFiguren = neuePosition.getBlackFiguren();
		Map<Byte, Piece> alteWhiteFiguren = altePosition.getWhiteFiguren();
		Map<Byte, Piece> neueWhiteFiguren = neuePosition.getWhiteFiguren();
		if (!(altePosition.equals(neuePosition)))
		{
			if (!(altePosition.getBlackFiguren().equals(neuePosition.getBlackFiguren())))
			{
				for (int i = 0; i<63; i++)
				{
					if(alteBlackFiguren.containsKey((byte)i) && !(neueBlackFiguren.containsKey((byte)i)))
					{
						_alteKoordinate = i;
//						System.out.println(i);
					}
					else if(!(alteBlackFiguren.containsKey((byte)i)) && (neueBlackFiguren.containsKey((byte)i)))
					{
						_neueKoordinate = i;
//						System.out.println(i);
					}
					
				}
				
			}
			else
			{
				for (int i = 0; i<63; i++)
				{
					if(alteWhiteFiguren.containsKey((byte)i) && !(neueWhiteFiguren.containsKey((byte)i)))
					{
						_alteKoordinate = i;
//						System.out.println(i);
					}
					else if(!(alteWhiteFiguren.containsKey((byte)i)) && (neueWhiteFiguren.containsKey((byte)i)))
					{
						_neueKoordinate = i;
//						System.out.println(i);
					}
					
				}
			}
		}
	}
	public boolean wurdeFigurGeschlagen()
	{
				return !((_alteBlackFiguren.size() == _neueBlackFiguren.size()) && (_alteWhiteFiguren.size() == _neueWhiteFiguren.size()));
	}
	public int welcheFigurWurdeGeschlagen()
	{
		whatMoveWasMade(_positionsListe.get(_aktuellerZug-1), _positionsListe.get(_aktuellerZug));
		
		if(_positionAlt.getZugrecht())
		{
//			System.out.println("weiß schlaegt schwarz");
//			System.out.println(_alteBlackFiguren.get((byte)_neueKoordinate));
//			System.out.println("neue Koordinate: "+ _neueKoordinate);
//			System.out.println("alte Koordinate: "+_alteKoordinate);
			System.out.println("PositionsListe: "+_positionsListe.size());
			for (int i =0; i >_positionsListe.size();i++)
			{
				System.out.println(_positionsListe.get(i).getFen());
			}
			while(_positionsListe.size() > 1)
				{
					_positionsListe.remove(_aktuellerZug);
					System.out.println(_positionsListe.get(_aktuellerZug).getFen());
					System.out.println("aktueller Zug"+_aktuellerZug);
					PositionsVergleicher posVergleicher = new PositionsVergleicher(_positionsListe, _aktuellerZug-1);
					_aktuellerZug--;
					posVergleicher.welcheFigurWurdeGeschlagen();
				}
//			return _alteBlackFiguren.get((byte)_alteKoordinate).getCoordinate();
			return 0;
		}
		else
		{
//			System.out.println("schwarz schlaegt weiß");
//			System.out.println(_alteBlackFiguren.get((byte)_neueKoordinate));
//			System.out.println("neue Koordinate: "+ _neueKoordinate);
//			System.out.println("alte Koordinate: "+_alteKoordinate);
			while(_positionsListe.size() > 1)
			{
				_positionsListe.remove(_aktuellerZug);
				System.out.println(_positionsListe.get(_aktuellerZug).getFen());
				System.out.println("aktueller Zug"+_aktuellerZug);
				PositionsVergleicher posVergleicher = new PositionsVergleicher(_positionsListe, _aktuellerZug-1);
				_aktuellerZug--;
				posVergleicher.welcheFigurWurdeGeschlagen();
			}
			return 1;
//			return _alteWhiteFiguren.get((byte)_neueKoordinate).getCoordinate();
		}
	}
}

