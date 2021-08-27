package Services;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

import Fachwerte.Zug;
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
	public ArrayList<Zug> getLegalFollowingPositions() {
		ArrayList<Zug> folgeZuege = new ArrayList<Zug>();
		Iterator entryIterator = _figurenAmZug.entrySet().iterator();
		while(entryIterator.hasNext()) {
			Map.Entry piece = (Map.Entry)(entryIterator.next());
			ArrayList<Byte> moves = ((Piece) piece.getValue()).getMoves(_figurenAmZug, _figurenDesGegners,
					_currentPosition);
			for (Byte neueFigurPos : moves) {
				_currentPosition.makeMove(((Piece) (piece.getValue())).getCoordinate(), neueFigurPos);
				
				if (isPositionLegal(_currentPosition)) {
					folgeZuege.add(_currentPosition.undoLastMove());
				} else {
					_currentPosition.undoLastMove();
				}
			}
		}
		
		return folgeZuege;
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
