package Services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Material.King;
import Material.Bishop;
import Material.Rook;
import Material.Queen;
import Material.Knight;
import Material.Pawn;
import Material.Piece;
import Material.Position;

public class PositionCalc3 {

	private Position _currentPosition;
	private Map<Byte, Piece> _figurenAmZug;

	public PositionCalc3(Position currentPosition) {
		_currentPosition = new Position(currentPosition);

		// wenn weiß am Zug ist
		if (currentPosition.getZugrecht()) {
			_figurenAmZug = convertListToMap(currentPosition.getWhiteFiguren());
		}
		// wenn schwarz am Zug ist
		else {
			_figurenAmZug = convertListToMap(currentPosition.getBlackFiguren());
		}
	}

	public List<Position> getLegalFollowingPositions() {
		{
			ArrayList<Position> folgePositionen = new ArrayList<Position>();
			//Map.Entry<String, String> entry : map.entrySet()
			for(Map.Entry<Byte, Piece> entry : _figurenAmZug.entrySet())
			{				
				if(false)//not King && not in check && notpinned && notpawn and capturing enPassant
				{
					folgePositionen.addAll(entry.getValue().getMoves(_currentPosition));
				}
				
				else
				{
					for(Position position : entry.getValue().getMoves(_currentPosition))
					{
						if(isPositionLegal(position))
						{
							folgePositionen.add(position);
						}
					}
				}
			}

			return folgePositionen;
		}
	}
	
	public boolean isPositionLegal(Position position)
	{
		
		if(position.getZugrecht())
		{
			for(Piece figur : position.getBlackFiguren())
			{
				if(figur instanceof King)
				{
					return figur.isInCheck(position);
				}
			}
		}
		else
		{
			for(Piece figur : position.getWhiteFiguren())
			{
				if(figur instanceof King)
				{
					return figur.isInCheck(position;
				}
			}
		}
	}

/////////////GETTER
	public Position getCurrentPosition() {
		return _currentPosition;
	}

	public Map<Byte, Piece> getFigurenAmZug() {
		return _figurenAmZug;
	}


/// Hilfsmethoden

//Eine Hilfsmethode, welche eine Liste von Pieces in eine Hashmap von Pieces umwandelt.
	public Map<Byte, Piece> convertListToMap(List<Piece> list) {
		Map<Byte, Piece> map = new HashMap<>(64);
		for (Piece piece : list) {
			map.put(piece.getCoordinate(), piece);
		}
		return map;
	}
}
