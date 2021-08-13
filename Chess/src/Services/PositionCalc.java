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

public class PositionCalc {

	private Position _currentPosition;
	private Map<Byte, Piece> _figurenAmZug;
	private Map<Byte, Piece> _figurenDesGegners;

	public PositionCalc(Position currentPosition) {
		_currentPosition = new Position(currentPosition);

		// wenn weiß am Zug ist
		if (_currentPosition.getZugrecht()) {
			_figurenAmZug = _currentPosition.getWhiteFiguren();
			_figurenDesGegners = _currentPosition.getBlackFiguren();
		}
		// wenn schwarz am Zug ist
		else {
			_figurenAmZug = _currentPosition.getBlackFiguren();
			_figurenDesGegners = _currentPosition.getWhiteFiguren();
		}
	}

	// Diese Methode gibt alle legalen Positionen, welche aus der derzeitigen
	// Position resultieren zurück.
	public ArrayList<Position> getLegalFollowingPositions() {
		ArrayList<Position> folgePositionen = new ArrayList<Position>();
		for (Map.Entry<Byte, Piece> entry : _figurenAmZug.entrySet()) {
			for (Byte bite : entry.getValue().getMoves(_figurenAmZug, _figurenDesGegners, _currentPosition)) {

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
			for(Map.Entry<Byte, Piece> blackPiece : position.getBlackFiguren().entrySet())
			{
				if(blackPiece.getValue() instanceof King)
				{
					return  !((King) blackPiece.getValue()).isInCheck(position);
				}
			}
		}
		else
		{
			for(Map.Entry<Byte, Piece> whitePiece : position.getWhiteFiguren().entrySet())
			{
				if( whitePiece.getValue() instanceof King)
				{
					return  !((King) whitePiece.getValue()).isInCheck(position);
				}
			}
		}
		return true;
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

}
