package Material;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

public class Pawn extends Piece {
	public Pawn(byte coordinate) {
		super(coordinate);
	}

	@Override
	public byte[] getMovement() {
		return new byte[] {8,-8};
		}
	public  ArrayList<Byte> getMoves(Entry<Byte, Piece> entry, byte enPassant)
	{
		Piece piece = entry.getValue();
		ArrayList<Byte> pieceFelder = new ArrayList<Byte>();
		
		//Wenn weiss
		if(_currentPosition.getZugrecht()) 
		{
			//Wenn vor ihm kein gegner und keine eigene Figur
			if(!_figurenDesGegners.containsKey((byte)(piece.getCoordinate()-8))&& !_figurenAmZug.containsKey((byte)(piece.getCoordinate()-8)))
			{
				pieceFelder.add((byte)(piece.getCoordinate()-8));
				//Wenn auch vor diesem Feld nichs ist und der Bauer steht noch auf Startfeld
				if((!_figurenDesGegners.containsKey((byte)(piece.getCoordinate()-16))&& !_figurenAmZug.containsKey((byte)(piece.getCoordinate()-16)))&& piece.getCoordinate()>=48&&piece.getCoordinate()<=55)
				{
					pieceFelder.add((byte)(piece.getCoordinate()-16));
				}
			}
			//Wenn oben rechts ein gegner oder ein enPassant Feld ist
			if(_figurenDesGegners.containsKey((byte)(piece.getCoordinate()-7))||_currentPosition.getEnPassant()==(byte)(piece.getCoordinate()-7))
			{
				//Wenn kein Sprung über Kante
				if (!SprungUeberKante(piece.getCoordinate(), piece.getCoordinate()-7)) {
				pieceFelder.add((byte)(piece.getCoordinate()-7));
				}
			}
			//Wenn oben links ein gegner oder ein enPassant Feld ist
			if(_figurenDesGegners.containsKey((byte)(piece.getCoordinate()-9))||_currentPosition.getEnPassant()==(byte)(piece.getCoordinate()-9))
			{
				if (!SprungUeberKante(piece.getCoordinate(), piece.getCoordinate()-9)) {
				pieceFelder.add((byte)(piece.getCoordinate()-9));
				}
			}
			
		}
		//Wenn schwarz
		else
		{
			if(!_figurenDesGegners.containsKey((byte)(piece.getCoordinate()+8))&& !_figurenAmZug.containsKey((byte)(piece.getCoordinate()+8)))
			{
				if (!SprungUeberKante(piece.getCoordinate(), piece.getCoordinate()+8)) {
				pieceFelder.add((byte)(piece.getCoordinate()+8));
				}
				if(!_figurenDesGegners.containsKey((byte)(piece.getCoordinate()+16))&& !_figurenAmZug.containsKey((byte)(piece.getCoordinate()+16))&& piece.getCoordinate()>=8&&piece.getCoordinate()<=15)
				{
					if (!SprungUeberKante(piece.getCoordinate(), piece.getCoordinate()+16)) {
					pieceFelder.add((byte)(piece.getCoordinate()+16));
					}
				}
			}
			if(_figurenDesGegners.containsKey((byte)(piece.getCoordinate()+7))||_currentPosition.getEnPassant()==(byte)(piece.getCoordinate()+7))
			{
				if (!SprungUeberKante(piece.getCoordinate(), piece.getCoordinate()+7)) {
				pieceFelder.add((byte)(piece.getCoordinate()+7));
				}
			}
			if(_figurenDesGegners.containsKey((byte)(piece.getCoordinate()+9))||_currentPosition.getEnPassant()==(byte)(piece.getCoordinate()+9))
			{
				if (!SprungUeberKante(piece.getCoordinate(), piece.getCoordinate()+9)) {
				pieceFelder.add((byte)(piece.getCoordinate()+9));
				}
			}
		}
	return pieceFelder;
	}
	}

