package Services;

import Material.Position;
import Material.Piece;
import Material.Pawn;
import Material.Knight;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import Fachwerte.Coordinate;
import Material.Bishop;
import Material.Rook;
import Material.Queen;
import Material.King;




public class PositionCalc 
{

	private static Map<Byte,Piece> _figurenAmZug;  
	private static Map<Byte,Piece> _figurenDesGegners; 
	
	// Liste aller möglichen "folgePositionen"
	private static List<Position> _folgePositionen;
	
	// besondere Daten bezüglich King safety
	private static boolean _kinginCheck;
	private static Map<Byte,Piece> _attackingPieces;
	private static Map<Byte,Piece> _pinnedPieces;
	
	
	public static List<Position> getLegalPositions(Position currentPosition)
	{
		
		_folgePositionen = new ArrayList<Position>();
		_attackingPieces = new HashMap<Byte,Piece>();
		
		
		_pinnedPieces = new HashMap<Byte,Piece>();
		_kinginCheck = false;
		
		if(currentPosition.getZugrecht())
		{
			_figurenAmZug = convertListToMap(currentPosition.getWhiteFiguren());
			_figurenDesGegners = convertListToMap(currentPosition.getBlackFiguren());			
		}
		else
		{
			_figurenAmZug = convertListToMap(currentPosition.getBlackFiguren());
			_figurenDesGegners = convertListToMap(currentPosition.getWhiteFiguren());	
		}
		attackingPieces();
		kingInCheck();
		pinnedPieces();
		//Map.Entry<String, String> entry : map.entrySet()
		for(Map.Entry<Byte, Piece> entry : _figurenAmZug.entrySet())
		{
			if(entry.getValue() instanceof Pawn)
			{
				insertLegalPawnMoves(entry);
			}
			else if(entry.getValue() instanceof Knight)
			{
				insertLegalKnightMoves(entry);
			}
			else if(entry.getValue() instanceof Bishop)
			{
				insertLegalBishopMoves(entry);
			}
			else if(entry.getValue() instanceof Rook)
			{
				insertLegalRookMoves(entry);
			}
			else if(entry.getValue() instanceof Queen)
			{
				insertLegalQueenMoves(entry);
			}
			else
			{
				insertLegalKingMoves(entry);
			}
		}

		return _folgePositionen;
	}
	
	 public static Map<Byte, Piece> convertListToMap(List<Piece> list) {
		   	Map<Byte,Piece> map = new HashMap<>(64);
		   	for (Piece piece:list) {
		   		map.put(piece.getCoordinate(), piece);
		   	}
		    return map;
		}
	
	
	private static void insertLegalPawnMoves(Entry<Byte, Piece> entry)
	{
			
	}
	
	private static void insertLegalKnightMoves(Entry<Byte, Piece> entry)
	{
		
	}
	
	private static void insertLegalBishopMoves(Entry<Byte, Piece> entry)
	{
		
	}
	
	private static void insertLegalRookMoves(Entry<Byte, Piece> entry)
	{
		
	}
	
	private static void insertLegalQueenMoves(Entry<Byte, Piece> entry)
	{
		
	}
	
	private static void insertLegalKingMoves(Entry<Byte, Piece> entry)
	{
		
	}
	
	// Ist der König im Schach?
	private static void kingInCheck()
	{
		
	}
	
	// Welche gegnerischen Figuren haben meinen König "in Sichtweite"?
	private static void attackingPieces()
	{
		for(Map.Entry<Byte, Piece> entry : _figurenAmZug.entrySet())
		{
			if(entry.getValue() instanceof King)
				{
					byte kingPosition = entry.getValue().getCoordinate();
					int bishopFelder[]=new int[14];
					int rookFelder[] = null;

					
				}
			_attackingPieces
		}
	}
	
	private static List<Integer>  hasViewOf(Coordinate thisPieceCoordinate,byte[] vision, boolean repeatable, boolean blocked)
	{
		List<Integer> list = new ArrayList<Integer>();
		
		for(int i = 0; i < vision.length;i++)
		{
			for(int j = 1 ; j <= 7; j++)
			{
				if(thisPieceCoordinate.getCoordinate() + vision[i]*j >=0 && thisPieceCoordinate.getCoordinate() + vision[i]*j <=63)
				{
					if (_figurenDesGegners.containsKey((byte)(thisPieceCoordinate.getCoordinate() + vision[i]*j))&& blocked)
					{
						list.add(thisPieceCoordinate.getCoordinate()+vision[i]*j);
						break;
					}
					
					else if( _figurenAmZug.containsKey((byte)(thisPieceCoordinate.getCoordinate()+ vision[i]*j)) && blocked)
					{
						break;
					}
				}
				if(!repeatable)
				{
					break;
				}
			}
		}
		return list;
	}
	
	// Finde heraus, ob der König im Schach steht, wenn die Figur gelöscht ist. Sonderregel für enPassant ist nötig.
	// Wenn dies für eine Figur zutrifft muss nach desses Movements geschaut werden, ob der König geschlagen werden kann.
	private static void pinnedPieces()
	{
		
	}
}
