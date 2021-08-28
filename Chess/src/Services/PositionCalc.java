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
		_currentPosition = currentPosition;

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
	public ArrayList<Zug> getLegalFollowingMoves() {
		ArrayList<Zug> folgeZuege = new ArrayList<Zug>();
		Position position = new Position(_currentPosition);

		for(Map.Entry<Byte, Piece> entry : _figurenAmZug.entrySet()) {
			Piece piece = entry.getValue();

			for(Byte neuePosition : piece.getMoves(_figurenAmZug, _figurenDesGegners,
					_currentPosition))
			{
				position.makeMove(piece.getCoordinate(), neuePosition);

				if(piece instanceof Pawn && (neuePosition < 8 || neuePosition > 55) &&
						isPositionLegal(position)) {
					
					Zug zug = position.undoLastMove();
					zug.setPromotion(new Knight(zug.getNeueFigurPosition(), _currentPosition.getZugrecht()));
					folgeZuege.add(zug);
					
					zug.setPromotion(new Bishop(zug.getNeueFigurPosition(), _currentPosition.getZugrecht()));
					folgeZuege.add(zug);
					
					zug.setPromotion(new Rook(zug.getNeueFigurPosition(), _currentPosition.getZugrecht()));
					folgeZuege.add(zug);
					
					zug.setPromotion(new Queen(zug.getNeueFigurPosition(), _currentPosition.getZugrecht()));
					folgeZuege.add(zug);
					 
				} else if(isPositionLegal(position)) {
					folgeZuege.add(position.undoLastMove());
				} else {
					position.undoLastMove();
				}
			}
		}

		return folgeZuege;
	}

	// Ist die übergebene Position legal? oder steht der König nach einem Zug der
	// eigenen Seite im Schach?
	public boolean isPositionLegal(Position position) {

		if (position.getZugrecht()) {
			for (Map.Entry<Byte, Piece> blackPiece : position.getBlackFiguren().entrySet()) {
				if (blackPiece.getValue() instanceof King) {
					return !((King) blackPiece.getValue()).isInCheck(position);

				}
			}
		} else {
			for (Map.Entry<Byte, Piece> whitePiece : position.getWhiteFiguren().entrySet()) {
				if (whitePiece.getValue() instanceof King) {
					return !((King) whitePiece.getValue()).isInCheck(position);
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
