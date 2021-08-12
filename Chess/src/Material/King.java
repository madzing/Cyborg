package Material;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class King extends Piece {
	public King(byte coordinate, boolean color) {
		super(coordinate, color);
		super.setValue((byte)5);
	}

	public byte[] getMovement() {
		return new byte[] { -8, 1, 8, -1, -7, 9, 7, -9 };
	}
	
	public  ArrayList<Byte> getMoves(Map<Byte, Piece> figurenAmZug, Map<Byte, Piece> figurenDesGegners, Position position)
	{
		ArrayList<Byte> pieceFelder = new ArrayList<Byte>();;
		
		for(int i = 0; i < getMovement().length;i++)
		{
				if(getCoordinate() + getMovement()[i] >=0 && getCoordinate() + getMovement()[i] <=63)
				{
					if(SprungUeberKante(getCoordinate(),getCoordinate()+getMovement()[i]))
					{
						// tue nichts
					}
					else if( figurenAmZug.containsKey((byte)(getCoordinate()+ getMovement()[i])))
					{
						// tue nichts
					}
					else
					{
						pieceFelder.add((byte)(getCoordinate()+getMovement()[i]));
					}
				}
		}
		
		if(position.getZugrecht()&& getCoordinate()==60)
		{
			// Kann Weiss kingside castlen?
			if(position.getWhiteCastleRights()[0]==true&&!isInCheck(position)&&!new King((byte)(super.getCoordinate()+1),true).isInCheck(position)&&!new King((byte)(super.getCoordinate()+2),true).isInCheck(position) &&!figurenAmZug.containsKey((byte)61)&&!figurenAmZug.containsKey((byte)62)&&!figurenDesGegners.containsKey((byte)61)&&!figurenDesGegners.containsKey((byte)62))
			{
				pieceFelder.add((byte)62);
			}
			
			// Kann Weiss queenside castlen?
			if(position.getWhiteCastleRights()[1]==true&&!isInCheck(position)&&!new King((byte)(super.getCoordinate()-1),true).isInCheck(position)&&!new King((byte)(super.getCoordinate()-2),true).isInCheck(position)&&!figurenAmZug.containsKey((byte)59)&&!figurenAmZug.containsKey((byte)58)&&!figurenAmZug.containsKey((byte)57)&&!figurenDesGegners.containsKey((byte)59)&&!figurenDesGegners.containsKey((byte)58)&&!figurenDesGegners.containsKey((byte)57))
			{
				pieceFelder.add((byte)58);
			}
		}
		
		else
		{
			// Kann Schwarz kingside castlen?
			if(position.getBlackCastleRights()[0]==true&&!isInCheck(position)&&!new King((byte)(super.getCoordinate()+1),false).isInCheck(position)&&!new King((byte)(super.getCoordinate()+2),false).isInCheck(position)&&!figurenAmZug.containsKey((byte)5)&&!figurenAmZug.containsKey((byte)6)&&!figurenDesGegners.containsKey((byte)5)&&!figurenDesGegners.containsKey((byte)6))
			{
				pieceFelder.add((byte)6);
			}
			new King((byte)(super.getCoordinate()-1),false).isInCheck(position);
			// Kann Weiss queenside castlen?
			if(position.getBlackCastleRights()[1]==true&&!isInCheck(position)&&!new King((byte)(super.getCoordinate()-1),false).isInCheck(position)&&!new King((byte)(super.getCoordinate()-2),false).isInCheck(position)&&!figurenAmZug.containsKey((byte)3)&&!figurenAmZug.containsKey((byte)2)&&!figurenAmZug.containsKey((byte)1)&&!figurenDesGegners.containsKey((byte)3)&&!figurenDesGegners.containsKey((byte)2)&&!figurenDesGegners.containsKey((byte)1))
			{
				pieceFelder.add((byte)2);
			}
		}
		return pieceFelder;
	}
	
	// ineffiziente Methode
	public boolean isInCheck(Position position)
	{
		Map<Byte, Piece> figurenDesGegners;
		Map<Byte, Piece> figurenDesKoenigs;
		if(super.getColor())
		{
			figurenDesKoenigs = convertListToMap(position.getWhiteFiguren());
			figurenDesGegners = convertListToMap(position.getBlackFiguren());
		}
		else
		{
			figurenDesKoenigs = convertListToMap(position.getBlackFiguren());
			figurenDesGegners = convertListToMap(position.getWhiteFiguren());
		}
		
		for(Map.Entry<Byte, Piece> entry : figurenDesGegners.entrySet())
		{
			if(!(entry.getValue() instanceof King))
			{
			if(entry.getValue().getMoves(figurenDesGegners, figurenDesKoenigs, position).contains(super.getCoordinate()))
			{
				return true;
			}
		}
		}
		return false;
	}
	
	
	//Eine Hilfsmethode, welche eine Liste von Pieces in eine Hashmap von Pieces umwandelt.
		public Map<Byte, Piece> convertListToMap(List<Piece> list) {
			Map<Byte, Piece> map = new HashMap<>(64);
			for (Piece piece : list) {
				map.put(piece.getCoordinate(), piece);
			}
			return map;
		}
}
