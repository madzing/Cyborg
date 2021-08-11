package Material;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

public class Pawn extends Piece {
	public Pawn(byte coordinate, boolean color) {
		super(coordinate, color);
	}

	@Override
	public byte[] getMovement() {
		return new byte[] {8,-8};
		}
	public  ArrayList<Byte> getMoves(Map<Byte, Piece> figurenAmZug, Map<Byte, Piece> figurenDesGegners, byte enPassant)
	{
		ArrayList<Byte> pieceFelder = new ArrayList<Byte>();
		
		//Wenn weiss
		if(_color) 
		{
			//Wenn vor ihm kein gegner und keine eigene Figur
			if(!figurenDesGegners.containsKey((byte)(getCoordinate()-8))&& !figurenAmZug.containsKey((byte)(getCoordinate()-8)))
			{
				pieceFelder.add((byte)(getCoordinate()-8));
				//Wenn auch vor diesem Feld nichs ist und der Bauer steht noch auf Startfeld
				if((!figurenDesGegners.containsKey((byte)(getCoordinate()-16))&& !figurenAmZug.containsKey((byte)(getCoordinate()-16)))&& getCoordinate()>=48&&getCoordinate()<=55)
				{
					pieceFelder.add((byte)(getCoordinate()-16));
				}
			}
			//Wenn oben rechts ein gegner oder ein enPassant Feld ist
			if(figurenDesGegners.containsKey((byte)(getCoordinate()-7))|| enPassant == (byte)(getCoordinate()-7))
			{
				//Wenn kein Sprung Ã¼ber Kante
				if (!SprungUeberKante(getCoordinate(), getCoordinate()-7)) {
				pieceFelder.add((byte)(getCoordinate()-7));
				}
			}
			//Wenn oben links ein gegner oder ein enPassant Feld ist
			if(figurenDesGegners.containsKey((byte)(getCoordinate()-9))|| enPassant == (byte)(getCoordinate()-9))
			{
				if (!SprungUeberKante(getCoordinate(), getCoordinate()-9)) {
				pieceFelder.add((byte)(getCoordinate()-9));
				}
			}
			
		}
		//Wenn schwarz
		else
		{
			if(!figurenDesGegners.containsKey((byte)(getCoordinate()+8))&& !figurenAmZug.containsKey((byte)(getCoordinate()+8)))
			{
				if (!SprungUeberKante(getCoordinate(), getCoordinate()+8)) {
				pieceFelder.add((byte)(getCoordinate()+8));
				}
				if(!figurenDesGegners.containsKey((byte)(getCoordinate()+16))&& !figurenAmZug.containsKey((byte)(getCoordinate()+16))&& getCoordinate()>=8&&getCoordinate()<=15)
				{
					if (!SprungUeberKante(getCoordinate(), getCoordinate()+16)) {
					pieceFelder.add((byte)(getCoordinate()+16));
					}
				}
			}
			if(figurenDesGegners.containsKey((byte)(getCoordinate()+7)) || enPassant == (byte)(getCoordinate()+7))
			{
				if (!SprungUeberKante(getCoordinate(), getCoordinate()+7)) {
				pieceFelder.add((byte)(getCoordinate()+7));
				}
			}
			if(figurenDesGegners.containsKey((byte)(getCoordinate()+9)) || enPassant == (byte)(getCoordinate()+9))
			{
				if (!SprungUeberKante(getCoordinate(), getCoordinate()+9)) {
				pieceFelder.add((byte)(getCoordinate()+9));
				}
			}
		}
	return pieceFelder;
	}
	}

