package OldCode;

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

public class OldPositionCalc2
{
	private  Position _currentPosition;
	
	private  Map<Byte,Piece> _figurenAmZug;  
	private  Map<Byte,Piece> _figurenDesGegners; 
	
	// Liste aller möglichen "folgePositionen"
	private  List<Position> _folgePositionen;
	
	// besondere Daten bezüglich King safety
	private  boolean _kingInCheck;
	private  Map<Byte,Piece> _attackingPieces;
	private  Map<Byte,Piece> _pinnedPieces;
	
	public OldPositionCalc2(Position currentPosition)
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
	}
	
	public  List<Position> getLegalPositions()
	{

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
	
	 public  Map<Byte, Piece> convertListToMap(List<Piece> list) 
	 {
		   	Map<Byte,Piece> map = new HashMap<>(64);
		   	for (Piece piece:list) {
		   		map.put(piece.getCoordinate(), piece);
		   	}
		    return map;
	 }
	
	
	private  void getLegalPawnMoves(Entry<Byte, Piece> entry)
	{
		Piece piece = entry.getValue();
		List<Byte> pieceFelder = new ArrayList<Byte>();
		
		
		if(_currentPosition.getZugrecht()) 
		{
			if(!_figurenDesGegners.containsKey((byte)(piece.getCoordinate()-8))&& !_figurenAmZug.containsKey((byte)(piece.getCoordinate()-8)))
			{
				if (!SprungUeberKante(piece.getCoordinate(), piece.getCoordinate()-8)) {
				pieceFelder.add((byte)(piece.getCoordinate()-8));
				}
				if((!_figurenDesGegners.containsKey((byte)(piece.getCoordinate()-16))&& !_figurenAmZug.containsKey((byte)(piece.getCoordinate()-16)))&& piece.getCoordinate()>=48&&piece.getCoordinate()<=55)
				{
					if (!SprungUeberKante(piece.getCoordinate(), piece.getCoordinate()-16)) {
					pieceFelder.add((byte)(piece.getCoordinate()-16));
					}
				}
			}
			if(_figurenDesGegners.containsKey((byte)(piece.getCoordinate()-7))||_currentPosition.getEnPassant()==(byte)(piece.getCoordinate()-7))
			{
				if (!SprungUeberKante(piece.getCoordinate(), piece.getCoordinate()-7)) {
				pieceFelder.add((byte)(piece.getCoordinate()-7));
				}
			}
			if(_figurenDesGegners.containsKey((byte)(piece.getCoordinate()-9))||_currentPosition.getEnPassant()==(byte)(piece.getCoordinate()-9))
			{
				if (!SprungUeberKante(piece.getCoordinate(), piece.getCoordinate()-9)) {
				pieceFelder.add((byte)(piece.getCoordinate()-9));
				}
			}
			
		}
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
		
		for(byte key: pieceFelder)
		{
			Position nextPosition = new Position(_currentPosition);

			nextPosition.makeMove(piece.getCoordinate(), key);

			//Wenn der Bauer gepinnt ist, oder der k�nig im schach steht , oder der bauer auf einem enpassant feld schlägt,
			if(_pinnedPieces.containsKey(entry.getValue().getCoordinate())||_kingInCheck||key==_currentPosition.getEnPassant())
			{

				//dann wird getestet ob die Position legal ist.
				if(isPositionLegal(nextPosition))

				{
					_folgePositionen.add(nextPosition);
				}	
			}
			else
			{
				_folgePositionen.add(nextPosition);
			}
		}
	}
	
	private  void getLegalPieceMoves(Entry<Byte, Piece> entry)
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
			nextPosition.makeMove(piece.getCoordinate(), key);
			
			
			if(_pinnedPieces.containsKey(entry.getValue().getCoordinate())||_kingInCheck)
			{
				if(isPositionLegal(nextPosition))
				{
					_folgePositionen.add(nextPosition);
				}
			}
			else
			{
				_folgePositionen.add(nextPosition);
			}
		}
	}
	
	//TODO noch nicht implementiert
	private  void getLegalKingMoves(Entry<Byte, Piece> entry)
	{
		Piece piece = entry.getValue();
		List<Byte> pieceFelder;
		pieceFelder = hasViewOf(piece.getCoordinate(),piece.getMovement() ,false,true);
		
		if(_currentPosition.getZugrecht()&& piece.getCoordinate()==60)
		{
			// Kann Weiss kingside castlen?
			if(_currentPosition.getWhiteCastleRights()[0]==true&&!_figurenAmZug.containsKey((byte)61)&&!_figurenAmZug.containsKey((byte)62)&&!_figurenDesGegners.containsKey((byte)61)&&!_figurenDesGegners.containsKey((byte)62))
			{
				pieceFelder.add((byte)62);
			}
			
			// Kann Weiss queenside castlen?
			if(_currentPosition.getWhiteCastleRights()[1]==true&&!_figurenAmZug.containsKey((byte)59)&&!_figurenAmZug.containsKey((byte)58)&&!_figurenAmZug.containsKey((byte)57)&&!_figurenDesGegners.containsKey((byte)59)&&!_figurenDesGegners.containsKey((byte)58)&&!_figurenDesGegners.containsKey((byte)57))
			{
				pieceFelder.add((byte)58);
			}
		}
		
		else
		{
			// Kann Schwarz kingside castlen?
			if(_currentPosition.getBlackCastleRights()[0]==true&&!_figurenAmZug.containsKey((byte)5)&&!_figurenAmZug.containsKey((byte)6)&&!_figurenDesGegners.containsKey((byte)5)&&!_figurenDesGegners.containsKey((byte)6))
			{
				pieceFelder.add((byte)6);
			}
			
			// Kann Weiss queenside castlen?
			if(_currentPosition.getBlackCastleRights()[1]==true&&!_figurenAmZug.containsKey((byte)3)&&!_figurenAmZug.containsKey((byte)2)&&!_figurenAmZug.containsKey((byte)1)&&!_figurenDesGegners.containsKey((byte)3)&&!_figurenDesGegners.containsKey((byte)2)&&!_figurenDesGegners.containsKey((byte)1))
			{
				pieceFelder.add((byte)2);
			}
		}
		
		
		for(byte key: pieceFelder)
		{
			Position nextPosition = new Position(_currentPosition);
			nextPosition.makeMove(piece.getCoordinate(), key);
			if(isPositionLegal(nextPosition))
			{
				_folgePositionen.add(nextPosition);
			}
		}
	}

	
	// Welche gegnerischen Figuren haben meinen König "in Sichtweite", welche meiner Figuren sind gepinnt, steht der König im Schach?
	protected  void attackingPieces()
	{
		//Iteriert �ber alle Figuren am Zug
		for(Map.Entry<Byte, Piece> entry : _figurenAmZug.entrySet())
		{
			//Wenn King gefunden 
			if(entry.getValue() instanceof King)
				{
					byte kingPosition = entry.getValue().getCoordinate();
					List<Byte> bishopFelder = hasViewOf(kingPosition,new byte[]{ -7, 9, 7, -9 } ,true,false);
					List<Byte> rookFelder = hasViewOf(kingPosition,new byte[]{8, 1, 8, -1} ,true,false);
					List<Byte> knightFelder = hasViewOf(kingPosition,new byte[]{-15, -6, 10, 17, 15, 6, -10, -17 } ,false,false);
					
					//Wenn wei� dran ist
					if(_currentPosition.getZugrecht())
					{
						//Wenn der Gegner eine Figur auf Kingfeld -9 hat
						if(_figurenDesGegners.containsKey((byte)(kingPosition-9)))
						{
							//Wenn die Figur ein Bauer ist
							Piece figur = _figurenDesGegners.get((byte)(kingPosition-9));
							if(figur instanceof Pawn)
							{
								_kingInCheck = true;
							}
						}
						//Wenn der Gegner eine Figur auf Kingfeld -7 hat
						if(_figurenDesGegners.containsKey((byte)(kingPosition-7)))
						{
							//Wenn die Figur ein Bauer ist
							Piece figur = _figurenDesGegners.get((byte)(kingPosition-7));
							if(figur instanceof Pawn)
							{
								_kingInCheck = true;
							}
						}
					}
					//Wenn schwarz dran ist
					else
					{
						//Wenn der Gegner eine Figur auf Kingfeld +9 hat
						if(_figurenDesGegners.containsKey((byte)(kingPosition+9)))
						{
							//Wenn die Figur ein Bauer ist
							Piece figur = _figurenDesGegners.get((byte)(kingPosition+9));
							if(figur instanceof Pawn)
							{
								_kingInCheck = true;
							}
						}
						//Wenn der Gegner eine Figur auf Kingfeld +7 hat
						if(_figurenDesGegners.containsKey((byte)(kingPosition+7)))
						{
							//Wenn die Figur ein Bauer ist
							Piece figur = _figurenDesGegners.get((byte)(kingPosition+7));
							if(figur instanceof Pawn)
							{
								_kingInCheck = true;
							}
						}
					}
					//
					//Iteriert �ber alle Felder, von denen aus ein Knight den K�nig schlagen k�nnte
					for(byte piecePosition: knightFelder)
					{
						//Wenn eine gegnerische Figur auf einem dieser Felder steht
						if(_figurenDesGegners.containsKey(piecePosition))
						{
							//Wenn die Figur ein Knight ist
							Piece figur = _figurenDesGegners.get(piecePosition);
							if(figur instanceof Knight)
							{
								_kingInCheck = true;
							}
						}
					}
					//Iteriert �ber alle Felder, von denen aus ein Bishop den K�nig schlagen k�nnte
					for(byte piecePosition: bishopFelder)
					{
						
						Map<Byte,Piece> pinnedByBishopOrQueen =new HashMap<Byte,Piece>();
						//Wenn auf einem dieser Felder eine eigene Figur steht
						if(_figurenAmZug.containsKey(piecePosition))
						{
							//Dann kommt sie in die Map pinnedByBishopOrQueen
							pinnedByBishopOrQueen.put(piecePosition, _figurenAmZug.get(piecePosition));
						}
						//Wenn auf einem dieser Felder eine gegnerische Figur steht
						if(_figurenDesGegners.containsKey(piecePosition))
						{
								//Wenn die Figur ein Bishop oder eine Queen ist
								Piece figur = _figurenDesGegners.get(piecePosition);
								if(figur instanceof Bishop|| figur instanceof Queen)
								{
									//kommt sie in _attackingpieces
									_attackingPieces.put(piecePosition, figur);
									//Wenn die Map pinnedByBishopOrQueen genau ein Element enth�lt
									if(pinnedByBishopOrQueen.size()==1)
									{
										//kommt sie in pinnedPieces
										_pinnedPieces.putAll(pinnedByBishopOrQueen);
									}
									//Wenn die Map pinnedByBishopOrQueen nicht genau ein und genau 0 Elemente enh�lt
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
	
	private  List<Byte>  hasViewOf(byte thisPieceCoordinate,byte[] vision, boolean repeatable, boolean blocked)
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
	
	private  boolean SprungUeberKante(int alteFigurPos, int neueFigurPos)
	{
		return Math.abs((alteFigurPos % 8)-(neueFigurPos % 8))>2 ||  Math.abs((alteFigurPos / 8)-(neueFigurPos / 8))>2;
	}
	
	// TODO der letzte Spieler hat seinen Zug gemach, steht sein König jetzt im Schach? Wenn ja sollte FALSE zurückgegeben werden.
	public  boolean isPositionLegal(Position position)
	{
		OldPositionCalc2 neuPos = new OldPositionCalc2(position);
		Map<Byte, Piece> figurenDesGegners;
		Map<Byte,Piece> figurenAmZug;
		List<Byte> pawnFelder;
		Map<Byte, Piece> zwischenspeicher;
		
		
		byte kingPosition;
		boolean zugrecht = position.getZugrecht();

		
		if(zugrecht)
		{
			figurenAmZug = convertListToMap(position.getWhiteFiguren());
			figurenDesGegners = convertListToMap(position.getBlackFiguren());			
		}
		else
		{
			figurenAmZug = convertListToMap(position.getBlackFiguren());
			figurenDesGegners = convertListToMap(position.getWhiteFiguren());	
		}
		

		for(Map.Entry<Byte, Piece> entry : figurenDesGegners.entrySet())
		{
			if(entry.getValue() instanceof King)
			{
				kingPosition = entry.getValue().getCoordinate();
				
				
				
				if(zugrecht)
				{
					pawnFelder = neuPos.hasViewOf(kingPosition,new byte[]{-7,-9} ,false,true);
				}
				else
				{
					pawnFelder = neuPos.hasViewOf(kingPosition,new byte[]{7,9} ,false,true);
				}
				List<Byte> kingFelder = neuPos.hasViewOf(kingPosition,new byte[]{ -7, 9, 7, -9,8, 1, 8, -1 } ,false,true);
				List<Byte> bishopFelder = neuPos.hasViewOf(kingPosition,new byte[]{ -7, 9, 7, -9 } ,true,true);
				List<Byte> rookFelder = neuPos.hasViewOf(kingPosition,new byte[]{8, 1, 8, -1} ,true,true);
				List<Byte> knightFelder = neuPos.hasViewOf(kingPosition,new byte[]{-15, -6, 10, 17, 15, 6, -10, -17 } ,false,true);
				
				
				for(byte pawn : pawnFelder)
				{
					if(figurenAmZug.get(pawn) instanceof Pawn)
					{

						return false;
					}
				}
				for(byte king : kingFelder)
				{
					if(figurenAmZug.get(king) instanceof King)
					{

						return false;
					}
				}
				for(byte knight : knightFelder)
				{
					if(figurenAmZug.get(knight) instanceof Knight)
					{

						return false;
					}
				}
				for(byte bishop : bishopFelder)
				{
					if(figurenAmZug.get(bishop) instanceof Bishop|| figurenAmZug.get(bishop) instanceof Queen)
					{

						return false;
					}
				}
				for(byte rook : rookFelder)
				{
					if(figurenAmZug.get(rook) instanceof Rook|| figurenAmZug.get(rook) instanceof Queen)
					{

						return false;
					}
				}
			}
		}


		return true;

	}

	public Map<Byte, Piece> getAttackingPieces(){
		return _attackingPieces;
	}
	public Map<Byte, Piece> getPinnedPieces(){
		return _attackingPieces;
	}
	public boolean getKingInCheck()
	{
		return _kingInCheck;
	}
}
