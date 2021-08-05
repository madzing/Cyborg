package Services;

import Material.Position;
import Material.Piece;
import Material.Pawn;
import Material.Knight;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Fachwerte.Coordinate;
import Material.Bishop;
import Material.Rook;
import Material.Queen;
import Material.King;




public class PositionCalc 
{

	private static List<Piece> _figurenAmZug;
	private static List<Piece> _figurenDesGegners;
	
	// Liste aller möglichen "folgePositionen"
	private static List<Position> _folgePositionen;
	
	// besondere Daten bezüglich King safety
	private static boolean _kinginCheck;
	private static Map<String,Piece> _attackingPieces;
	private static Map<String,Piece> _pinnedPieces;
	
	
	public static List<Position> getLegalPositions(Position currentPosition)
	{
		_folgePositionen = new ArrayList<Position>();
		_attackingPieces = new HashMap<String,Piece>();
		_pinnedPieces = new HashMap<String,Piece>();
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
		pinnedPieces();
		insertLegalPawnMoves();
		insertLegalKnightMoves();
		insertLegalBishopMoves();
		insertLegalRookMoves();
		insertLegalQueenMoves();
		insertLegalKingMoves();

		return _folgePositionen;
	}
	
	private static void insertLegalPawnMoves()
	{
			
	}
	
	private static void insertLegalKnightMoves()
	{
		
	}
	
	private static void insertLegalBishopMoves()
	{
		
	}
	
	private static void insertLegalRookMoves()
	{
		
	}
	
	private static void insertLegalQueenMoves()
	{
		
	}
	
	private static void insertLegalKingMoves()
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
