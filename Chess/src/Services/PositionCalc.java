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

import Fachwerte.Coordinate;
import Material.Bishop;
import Material.Rook;
import Material.Queen;
import Material.King;




public class PositionCalc 
{

	private static Map<Integer,Piece> _figurenAmZug;  // Wahrscheinlich besser in einer map zu speichern ---> "how to loop a map in java" anschauen auf google
	private static Map<Integer,Piece> _figurenDesGegners; // Wahrscheinlich besser in einer map zu speichern
	
	// Liste aller möglichen "folgePositionen"
	private static List<Position> _folgePositionen;
	
	// besondere Daten bezüglich King safety
	private static boolean _kinginCheck;
	private static Map<Integer,Piece> _attackingPieces;
	private static Map<Integer,Piece> _pinnedPieces;
	
	
	public static List<Position> getLegalPositions(Position currentPosition)
	{
		
		_folgePositionen = new ArrayList<Position>();
		_attackingPieces = new HashMap<Integer,Piece>();
		
		
		_pinnedPieces = new HashMap<Integer,Piece>();
		_kinginCheck = false;
		
		if(currentPosition.getZugrecht())
		{
			_figurenAmZug = currentPosition.getWhiteFiguren();
			_figurenDesGegners = currentPosition.getBlackFiguren();			
		}
		else
		{
			_figurenAmZug = currentPosition.getBlackFiguren();
			_figurenDesGegners = currentPosition.getWhiteFiguren();	
		}
		kingInCheck();
		attackingPieces();
		pinnedPieces();
		//Map.Entry<String, String> entry : map.entrySet()
		for(Map.Entry<Integer, Piece> entry : _figurenAmZug.entrySet())
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
	
	
	private static void insertLegalPawnMoves(Entry<Integer, Piece> entry)
	{
			
	}
	
	private static void insertLegalKnightMoves(Entry<Integer, Piece> entry)
	{
		
	}
	
	private static void insertLegalBishopMoves(Entry<Integer, Piece> entry)
	{
		
	}
	
	private static void insertLegalRookMoves(Entry<Integer, Piece> entry)
	{
		
	}
	
	private static void insertLegalQueenMoves(Entry<Integer, Piece> entry)
	{
		
	}
	
	private static void insertLegalKingMoves(Entry<Integer, Piece> entry)
	{
		
	}
	
	// Ist der König im Schach?
	private static void kingInCheck()
	{
		
	}
	
	// Welche gegnerischen Figuren haben meinen König "in Sichtweite"?
	private static void attackingPieces()
	{
		
	}
	
	// Finde heraus, ob der König im Schach steht, wenn die Figur gelöscht ist. Sonderregel für enPassant ist nötig.
	// Wenn dies für eine Figur zutrifft muss nach desses Movements geschaut werden, ob der König geschlagen werden kann.
	private static void pinnedPieces()
	{
		
	}
}
