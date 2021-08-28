package Material;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class King extends Piece {
	public King(byte coordinate, boolean color) {
		super(coordinate, color);
		super.setValue((byte) 5);
	}

	public byte[] getMovement() {
		return new byte[] { -8, 1, 8, -1, -7, 9, 7, -9 };
	}

	public ArrayList<Byte> getMoves(Map<Byte, Piece> figurenAmZug, Map<Byte, Piece> figurenDesGegners,
			Position position) {
		ArrayList<Byte> pieceFelder = new ArrayList<Byte>();
		;

		for (int i = 0; i < getMovement().length; i++) {
			if (getCoordinate() + getMovement()[i] >= 0 && getCoordinate() + getMovement()[i] <= 63) {
				if (SprungUeberKante(getCoordinate(), getCoordinate() + getMovement()[i])) {
					// tue nichts
				} else if (figurenAmZug.containsKey((byte) (getCoordinate() + getMovement()[i]))) {
					// tue nichts
				} else {
					pieceFelder.add((byte) (getCoordinate() + getMovement()[i]));
				}
			}
		}

		if (position.getZugrecht() && getCoordinate() == 60) {
			// Kann Weiss kingside castlen?
			if (position.getWhiteCastleRights()[0] == true && !isInCheck(position)
					&& !new King((byte) (super.getCoordinate() + 1), true).isInCheck(position)
					&& !new King((byte) (super.getCoordinate() + 2), true).isInCheck(position)
					&& !figurenAmZug.containsKey((byte) 61) && !figurenAmZug.containsKey((byte) 62)
					&& !figurenDesGegners.containsKey((byte) 61) && !figurenDesGegners.containsKey((byte) 62)) {
				pieceFelder.add((byte) 62);
			}

			// Kann Weiss queenside castlen?
			if (position.getWhiteCastleRights()[1] == true && !isInCheck(position)
					&& !new King((byte) (super.getCoordinate() - 1), true).isInCheck(position)
					&& !new King((byte) (super.getCoordinate() - 2), true).isInCheck(position)
					&& !figurenAmZug.containsKey((byte) 59) && !figurenAmZug.containsKey((byte) 58)
					&& !figurenAmZug.containsKey((byte) 57) && !figurenDesGegners.containsKey((byte) 59)
					&& !figurenDesGegners.containsKey((byte) 58) && !figurenDesGegners.containsKey((byte) 57)) {
				pieceFelder.add((byte) 58);
			}
		}

		else {
			// Kann Schwarz kingside castlen?
			if (position.getBlackCastleRights()[0] == true && !isInCheck(position)
					&& !new King((byte) (super.getCoordinate() + 1), false).isInCheck(position)
					&& !new King((byte) (super.getCoordinate() + 2), false).isInCheck(position)
					&& !figurenAmZug.containsKey((byte) 5) && !figurenAmZug.containsKey((byte) 6)
					&& !figurenDesGegners.containsKey((byte) 5) && !figurenDesGegners.containsKey((byte) 6)) {
				pieceFelder.add((byte) 6);
			}
			new King((byte) (super.getCoordinate() - 1), false).isInCheck(position);
			// Kann Weiss queenside castlen?
			if (position.getBlackCastleRights()[1] == true && !isInCheck(position)
					&& !new King((byte) (super.getCoordinate() - 1), false).isInCheck(position)
					&& !new King((byte) (super.getCoordinate() - 2), false).isInCheck(position)
					&& !figurenAmZug.containsKey((byte) 3) && !figurenAmZug.containsKey((byte) 2)
					&& !figurenAmZug.containsKey((byte) 1) && !figurenDesGegners.containsKey((byte) 3)
					&& !figurenDesGegners.containsKey((byte) 2) && !figurenDesGegners.containsKey((byte) 1)) {
				pieceFelder.add((byte) 2);
			}
		}
		return pieceFelder;
	}

	/*
	 * Gibt eine ArrayList mit allen vom K�nig begehbaren Feldern aus
	 */
	public ArrayList<Byte> kingHitsKing(Map<Byte, Piece> figurenAmZug, Map<Byte, Piece> figurenDesGegners,
			Position position) {
		ArrayList<Byte> pieceFelder = new ArrayList<Byte>();
		;

		for (int i = 0; i < getMovement().length; i++) {
			if (getCoordinate() + getMovement()[i] >= 0 && getCoordinate() + getMovement()[i] <= 63) {
				if (SprungUeberKante(getCoordinate(), getCoordinate() + getMovement()[i])) {
					// tue nichts
				} else if (figurenAmZug.containsKey((byte) (getCoordinate() + getMovement()[i]))) {
					// tue nichts
				} else {
					pieceFelder.add((byte) (getCoordinate() + getMovement()[i]));
				}
			}
		}
		return pieceFelder;
	}

	/*
	 *
	 */
	public boolean isInCheck(Position position) {
		Map<Byte, Piece> figurenDesGegners;
		Map<Byte, Piece> figurenDesKoenigs;

		if (super.getColor()) {
			figurenDesKoenigs = position.getWhiteFiguren();
			figurenDesGegners = position.getBlackFiguren();
		} else {
			figurenDesKoenigs = position.getBlackFiguren();
			figurenDesGegners = position.getWhiteFiguren();
		}
		byte kingPosition = super.getCoordinate();

		//Test auf gegnerischen Koenig:
		ArrayList<Byte> felder = this.kingHitsKing(figurenDesKoenigs, figurenDesGegners, position);
		for(int i = 0;i < felder.size();i++)
		{
			if(figurenDesGegners.containsKey(felder.get(i)) &&
				figurenDesGegners.get(felder.get(i)) instanceof King)
			{
				return true;
			}
		}

		//Test auf gegnerisches Pferd:
		Knight knight = new Knight(kingPosition, this.getColor());
		felder = knight.getMoves(figurenDesKoenigs, figurenDesGegners, position);
		for(int i = 0;i < felder.size();i++)
		{
			if(figurenDesGegners.containsKey(felder.get(i)) &&
				figurenDesGegners.get(felder.get(i)) instanceof Knight)
			{
				return true;
			}
		}

		//Test auf gegnerisches Bishop und Queen:
		Bishop bishop = new Bishop(kingPosition, this.getColor());
		felder = bishop.getMoves(figurenDesKoenigs, figurenDesGegners, position);
		for(int i = 0;i < felder.size();i++)
		{
			if(figurenDesGegners.containsKey(felder.get(i)) &&
				(figurenDesGegners.get(felder.get(i)) instanceof Bishop ||
				figurenDesGegners.get(felder.get(i)) instanceof Queen))
			{
				return true;
			}
		}

		//Test auf gegnerischen Rook und Queen:
		Rook rook = new Rook(kingPosition, this.getColor());
		felder = rook.getMoves(figurenDesKoenigs, figurenDesGegners, position);
		for(int i = 0;i < felder.size();i++)
		{
//			Piece piece = figurenDesGegners.get(felder.get(i));
			if(figurenDesGegners.containsKey(felder.get(i)) &&
				(figurenDesGegners.get(felder.get(i)) instanceof Rook ||
				figurenDesGegners.get(felder.get(i)) instanceof Queen))
			{
				return true;
			}
		}

		//Test auf gegnerischen Pawn:
		Pawn pawn = new Pawn(kingPosition, this.getColor());
		felder = pawn.getMoves(figurenDesKoenigs, figurenDesGegners, position);
		for(int i = 0;i < felder.size();i++)
		{
			if(figurenDesGegners.containsKey(felder.get(i)) &&
				figurenDesGegners.get(felder.get(i)) instanceof Pawn)
			{
				return true;
			}
		}

		return false;
	}
}
