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
	private Map<Byte, Piece> _figurenDesGegners;

	public PositionCalc3(Position currentPosition) {
		_currentPosition = new Position(currentPosition);

		// wenn weiß am Zug ist
		if (currentPosition.getZugrecht()) {
			_figurenAmZug = convertListToMap(currentPosition.getWhiteFiguren());
			_figurenDesGegners = convertListToMap(currentPosition.getBlackFiguren());
		}
		// wenn schwarz am Zug ist
		else {
			_figurenAmZug = convertListToMap(currentPosition.getBlackFiguren());
			_figurenDesGegners = convertListToMap(currentPosition.getWhiteFiguren());
		}
	}

	// Diese Methode gibt alle legalen Positionen, welche aus der derzeitigen
	// Position resultieren zurück.
	public ArrayList<Position> getLegalFollowingPositions() {
		ArrayList<Position> folgePositionen = new ArrayList<Position>();
		// Map.Entry<String, String> entry : map.entrySet()
		for (Map.Entry<Byte, Piece> entry : _figurenAmZug.entrySet()) {
			for (Byte bite : entry.getValue().getMoves(_figurenAmZug, _figurenDesGegners, _currentPosition.getEnPassant())) {

				Position neuePos = new Position(_currentPosition);
				neuePos.makeMove(entry.getValue().getCoordinate(), bite);
				if (isPositionLegal(neuePos)) {
					folgePositionen.add(neuePos);
				}
			}
		}
		return folgePositionen;
	}

	// Ist die übergebene Position legal? oder steht der König nach einem Zug der
	// eigenen Seite im Schach?
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

	public Map<Byte, Piece> getFigurenDesGegners() {
		return _figurenDesGegners;
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
