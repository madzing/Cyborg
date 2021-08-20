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
	
	public PositionsVergleicher(Position alt, Position neu)
	{
		_positionAlt = alt;
		_positionNeu = neu;
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
			if (!(_positionAlt.getBlackFiguren().equals(_positionNeu.getBlackFiguren())))
			{
				Map<Byte, Piece> alteBlackFiguren = _positionAlt.getBlackFiguren();
				Map<Byte, Piece> neueBlackFiguren = _positionNeu.getBlackFiguren();
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
				Map<Byte, Piece> alteWhiteFiguren = _positionAlt.getWhiteFiguren();
				Map<Byte, Piece> neueWhiteFiguren = _positionNeu.getWhiteFiguren();
				for (int i = 0; i<63; i++)
				{
					if(!(alteWhiteFiguren.get((byte)i).equals(neueWhiteFiguren.get((byte)i))))
					{
						System.out.println(alteWhiteFiguren.get((byte)i));
						System.out.println(neueWhiteFiguren.get((byte)i));
					}
				}
			}
		}
	}

}
