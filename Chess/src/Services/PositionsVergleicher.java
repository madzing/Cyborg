package Services;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import Material.Bishop;
import Material.Knight;
import Material.Pawn;
import Material.Piece;
import Material.Position;
import Material.Queen;
import Material.Rook;

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
		_alteKoordinate = 0;
		_neueKoordinate = 0;
	}
	
	public int getAlteKoordinate()
	{
		return _alteKoordinate;
	}
	public int getNeueKoordinate()
	{
		return _neueKoordinate;
	}
	
	public void whatMoveWasMade()
	{
		if (!(_positionAlt.equals(_positionNeu)))
		{
			if (!(_positionAlt.getBlackFiguren().equals(_positionNeu.getBlackFiguren())) && !(_positionAlt._zugrecht))
			{
				for (int i = 0; i<64; i++)
				{
					if(_alteBlackFiguren.containsKey((byte)i) && !(_neueBlackFiguren.containsKey((byte)i)))
					{
						_alteKoordinate = i;
					}
					else if(!(_alteBlackFiguren.containsKey((byte)i)) && (_neueBlackFiguren.containsKey((byte)i)))
					{
						_neueKoordinate = i;
					}
					
				}
				
			}
			else
			{
				for (int i = 0; i<64; i++)
				{
					if(_alteWhiteFiguren.containsKey((byte)i) && !(_neueWhiteFiguren.containsKey((byte)i)))
					{
						_alteKoordinate = i;
					}
					else if(!(_alteWhiteFiguren.containsKey((byte)i)) && (_neueWhiteFiguren.containsKey((byte)i)))
					{
						_neueKoordinate = i;
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
		whatMoveWasMade();		
		if(_positionAlt.getZugrecht())
		{
			if(_alteBlackFiguren.get((byte)_neueKoordinate) instanceof Pawn)
			{
				return 0;
			}
			else if(_alteBlackFiguren.get((byte)_neueKoordinate) instanceof Rook)
			{
				return 1;
			}
			else if(_alteBlackFiguren.get((byte)_neueKoordinate) instanceof Knight)
			{
				return 2;
			}
			else if(_alteBlackFiguren.get((byte)_neueKoordinate) instanceof Bishop)
			{
				return 3;
			}
			else if(_alteBlackFiguren.get((byte)_neueKoordinate) instanceof Queen)
			{
				return 4;
			}
		}
		else
		{
			if(_alteWhiteFiguren.get((byte)_neueKoordinate) instanceof Pawn)
			{
				return 5;
			}
			else if(_alteWhiteFiguren.get((byte)_neueKoordinate) instanceof Rook)
			{
				return 6;
			}
			else if(_alteWhiteFiguren.get((byte)_neueKoordinate) instanceof Knight)
			{
				return 7;
			}
			else if(_alteWhiteFiguren.get((byte)_neueKoordinate) instanceof Bishop)
			{
				return 8;
			}
			else if(_alteWhiteFiguren.get((byte)_neueKoordinate) instanceof Queen)
			{
				return 9;
			}
		}
		return 10;
	}
}
	


