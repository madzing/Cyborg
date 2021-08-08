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
	private static Position _currentPosition;
	
	private static Map<Byte,Piece> _figurenAmZug;  
	private static Map<Byte,Piece> _figurenDesGegners; 
	
	// Liste aller möglichen "folgePositionen"
	private static List<Position> _folgePositionen;
	
	// besondere Daten bezüglich King safety
	private static boolean _kingInCheck;
	private static Map<Byte,Piece> _attackingPieces;
	private static Map<Byte,Piece> _pinnedPieces;
	
	
	public static List<Position> getLegalPositions(Position currentPosition)
	{
		_currentPosition = currentPosition;
		_folgePositionen = new ArrayList<Position>();
		_attackingPieces = new HashMap<Byte,Piece>(); // wird im Moment nicht verwendet, nur Daten zugewiesen --> vllt in der Zukunft?

		_pinnedPieces = new HashMap<Byte,Piece>();
		_kingInCheck = false;
		
		
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
		
		//Map.Entry<String, String> entry : map.entrySet()
		for(Map.Entry<Byte, Piece> entry : _figurenAmZug.entrySet())
		{
			if(entry.getValue() instanceof Pawn)
			{
				getLegalPawnMoves(entry);
			}
			else if(entry.getValue() instanceof King)
			{
				getLegalKingMoves(entry);
			}
			else
			{
				getLegalPieceMoves(entry);
			}
		}

		return _folgePositionen;
	}
	
	 public static Map<Byte, Piece> convertListToMap(List<Piece> list) 
	 {
		   	Map<Byte,Piece> map = new HashMap<>(64);
		   	for (Piece piece:list) {
		   		map.put(piece.getCoordinate(), piece);
		   	}
		    return map;
	 }
	
	
	private static void getLegalPawnMoves(Entry<Byte, Piece> entry)
	{
		Piece piece = entry.getValue();
		List<Byte> pieceFelder = new ArrayList<Byte>();
		
		
		if(_currentPosition.getZugrecht()) 
		{
			if(!_figurenDesGegners.containsKey((byte)(piece.getCoordinate()-8))|| !_figurenAmZug.containsKey((byte)(piece.getCoordinate()-8)))
			{
				pieceFelder.add((byte)(piece.getCoordinate()-8));
				if((!_figurenDesGegners.containsKey((byte)(piece.getCoordinate()-16))|| !_figurenAmZug.containsKey((byte)(piece.getCoordinate()-16)))&& piece.getCoordinate()>=48&&piece.getCoordinate()<=55)
				{
					pieceFelder.add((byte)(piece.getCoordinate()-16));
				}
			}
			if(_figurenDesGegners.containsKey((byte)(piece.getCoordinate()-7))||_currentPosition.getEnPassant()==(byte)(piece.getCoordinate()-7))
			{
				pieceFelder.add((byte)(piece.getCoordinate()-7));
			}
			if(_figurenDesGegners.containsKey((byte)(piece.getCoordinate()-9))||_currentPosition.getEnPassant()==(byte)(piece.getCoordinate()-9))
			{
				pieceFelder.add((byte)(piece.getCoordinate()-9));
			}
		}
		else
		{
			if(!_figurenDesGegners.containsKey((byte)(piece.getCoordinate()+8))|| !_figurenAmZug.containsKey((byte)(piece.getCoordinate()+8)))
			{
				pieceFelder.add((byte)(piece.getCoordinate()+8));
				if(!_figurenDesGegners.containsKey((byte)(piece.getCoordinate()+16))|| !_figurenAmZug.containsKey((byte)(piece.getCoordinate()+16))&& piece.getCoordinate()>=8&&piece.getCoordinate()<=15)
				{
					pieceFelder.add((byte)(piece.getCoordinate()+16));
				}
			}
			if(_figurenDesGegners.containsKey((byte)(piece.getCoordinate()+7))||_currentPosition.getEnPassant()==(byte)(piece.getCoordinate()+7))
			{
				pieceFelder.add((byte)(piece.getCoordinate()+7));
			}
			if(_figurenDesGegners.containsKey((byte)(piece.getCoordinate()+9))||_currentPosition.getEnPassant()==(byte)(piece.getCoordinate()+9))
			{
				pieceFelder.add((byte)(piece.getCoordinate()+9));
			}
		}
		
		for(byte key: pieceFelder)
		{
			Position nextPosition = new Position(_currentPosition);
			
			System.out.println(" "+piece.getCoordinate()+ " "+ key +"//");
			nextPosition.makeMove(piece.getCoordinate(), key);
			
			if(_pinnedPieces.containsKey(entry.getValue().getCoordinate())||_kingInCheck||key==_currentPosition.getEnPassant())
			{
				if(!isPositionLegal(nextPosition))
				{
					break;
				}
			}
			
			_folgePositionen.add(nextPosition);
		}
	}
	
	private static void getLegalPieceMoves(Entry<Byte, Piece> entry)
	{
		Piece piece = entry.getValue();
		List<Byte> pieceFelder;
		if(piece instanceof Knight)
		{
			pieceFelder = hasViewOf(piece.getCoordinate(),piece.getMovement() ,false,true);
		}
		else
		{
			pieceFelder = hasViewOf(piece.getCoordinate(),piece.getMovement() ,true,true);
		}

		for(byte key: pieceFelder)
		{
			Position nextPosition = new Position(_currentPosition);
			System.out.println(" "+piece.getCoordinate()+ " "+ key +"//");
			nextPosition.makeMove(piece.getCoordinate(), key);
			
			
			if(_pinnedPieces.containsKey(entry.getValue().getCoordinate())||_kingInCheck)
			{
				if(!isPositionLegal(nextPosition))
				{
					break;
				}
			}
			
			_folgePositionen.add(nextPosition);
		}
	}
	
	//TODO noch nicht implementiert
	private static void getLegalKingMoves(Entry<Byte, Piece> entry)
	{
		
	}

	
	// Welche gegnerischen Figuren haben meinen König "in Sichtweite", welche meiner Figuren sind gepinnt, steht der König im Schach?
	private static void attackingPieces()
	{
		for(Map.Entry<Byte, Piece> entry : _figurenAmZug.entrySet())
		{
			if(entry.getValue() instanceof King)
				{
					byte kingPosition = entry.getValue().getCoordinate();
					List<Byte> bishopFelder = hasViewOf(kingPosition,new byte[]{ -7, 9, 7, -9 } ,true,false);
					List<Byte> rookFelder = hasViewOf(kingPosition,new byte[]{8, 1, 8, -1} ,true,false);
					List<Byte> knightFelder = hasViewOf(kingPosition,new byte[]{-15, -6, 10, 17, 15, 6, -10, -17 } ,false,false);
					
					if(_currentPosition.getZugrecht())
					{
						if(_figurenDesGegners.containsKey((byte)(kingPosition-9)))
						{
							Piece figur = _figurenDesGegners.get((byte)(kingPosition-9));
							if(figur instanceof Pawn)
							{
								_kingInCheck = true;
							}
						}
						if(_figurenDesGegners.containsKey((byte)(kingPosition-7)))
						{
							Piece figur = _figurenDesGegners.get((byte)(kingPosition-7));
							if(figur instanceof Pawn)
							{
								_kingInCheck = true;
							}
						}
					}
					else
					{
						if(_figurenDesGegners.containsKey((byte)(kingPosition+9)))
						{
							Piece figur = _figurenDesGegners.get((byte)(kingPosition+9));
							if(figur instanceof Pawn)
							{
								_kingInCheck = true;
							}
						}
						if(_figurenDesGegners.containsKey((byte)(kingPosition+7)))
						{
							Piece figur = _figurenDesGegners.get((byte)(kingPosition+7));
							if(figur instanceof Pawn)
							{
								_kingInCheck = true;
							}
						}
					}
					
					for(byte piecePosition: knightFelder)
					{
						if(_figurenDesGegners.containsKey(piecePosition))
						{
							Piece figur = _figurenDesGegners.get(piecePosition);
							if(figur instanceof Knight)
							{
								_kingInCheck = true;
							}
						}
					}
					
					for(byte piecePosition: bishopFelder)
					{
						Map<Byte,Piece> pinnedByBishopOrQueen =new HashMap<Byte,Piece>();
						if(_figurenAmZug.containsKey(piecePosition))
						{
							pinnedByBishopOrQueen.put(piecePosition, _figurenAmZug.get(piecePosition));
						}
						if(_figurenDesGegners.containsKey(piecePosition))
						{
								Piece figur = _figurenDesGegners.get(piecePosition);
								if(figur instanceof Bishop|| figur instanceof Queen)
								{
									_attackingPieces.put(piecePosition, figur);
									if(pinnedByBishopOrQueen.size()==1)
									{
										_pinnedPieces.putAll(pinnedByBishopOrQueen);
									}
									else if(pinnedByBishopOrQueen.size()==0)
									{
										_kingInCheck = true;
									}
									break;
								}
						}
					}
					
					for(byte piecePosition: rookFelder)
					{
						Map<Byte,Piece> pinnedByRookpOrQueen =new HashMap<Byte,Piece>();
						if(_figurenAmZug.containsKey(piecePosition))
						{
							pinnedByRookpOrQueen.put(piecePosition, _figurenAmZug.get(piecePosition));
						}
						if(_figurenDesGegners.containsKey(piecePosition))
						{
							Piece figur = _figurenDesGegners.get(piecePosition);
							if(figur instanceof Rook|| figur instanceof Queen)
							{
								_attackingPieces.put(piecePosition, figur);
								if(pinnedByRookpOrQueen.size()==1)
								{
									_pinnedPieces.putAll(pinnedByRookpOrQueen);
								}
								else if(pinnedByRookpOrQueen.size()==0)
								{
									_kingInCheck = true;
								}
								break;
							}
						}
					}
				}
		}
	}
	
	private static List<Byte>  hasViewOf(byte thisPieceCoordinate,byte[] vision, boolean repeatable, boolean blocked)
	{
		List<Byte> list = new ArrayList<Byte>();
		
		for(int i = 0; i < vision.length;i++)
		{
			for(int j = 1 ; j <= 7; j++)
			{
				if(thisPieceCoordinate + vision[i]*j >=0 && thisPieceCoordinate + vision[i]*j <=63)
				{
					if(SprungUeberKante(thisPieceCoordinate+vision[i]*(j-1),thisPieceCoordinate+vision[i]*j))
					{
						break;
					}
					if(!blocked)
					{
						list.add((byte) (thisPieceCoordinate+vision[i]*j));
					}
					else if (_figurenDesGegners.containsKey((byte)(thisPieceCoordinate + vision[i]*j)))
					{
						list.add((byte) (thisPieceCoordinate+vision[i]*j));
						break;
					}
					
					else if( _figurenAmZug.containsKey((byte)(thisPieceCoordinate+ vision[i]*j)))
					{
						break;
					}
					else
					{
						list.add((byte)(thisPieceCoordinate+vision[i]*j));
					}
				}
				else
				{
					break;
				}
				if(!repeatable)
				{
					break;
				}
			}
		}
		return list;
	}
	
	private static boolean SprungUeberKante(int alteFigurPos, int neueFigurPos)
	{
		return Math.abs((alteFigurPos % 8)-(neueFigurPos % 8))>2 ||  Math.abs((alteFigurPos / 8)-(neueFigurPos / 8))>2;
	}
	
	// TODO der letzte Spieler hat seinen Zug gemach, steht sein König jetzt im Schach? Wenn ja sollte FALSE zurückgegeben werden.
	private static boolean isPositionLegal(Position position)
	{
		
		return true;
	}
}
