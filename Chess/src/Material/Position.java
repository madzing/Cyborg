package Material;

import java.util.ArrayList;
import java.util.List;

import Fachwerte.Fen;

public class Position
{
    private List<Piece> _whiteFiguren = new ArrayList<Piece>();
    private List<Piece> _blackFiguren= new ArrayList<Piece>();

    private boolean _zugrecht;
    private boolean _whiteCanCastle[] = {true, true};
    private boolean _blackCanCastle[] = {true, true};
    private byte _enpassant;
    private byte _zuegeKleiner50;
    private short _zuegeGesamt;

    public Position(Fen fenPosition)
    {
        createPosition(fenPosition);
    }

    public Position(Position copyable)
    {
        this._whiteFiguren = copyable.getWhiteFiguren();
        this._blackFiguren = copyable.getBlackFiguren();
        this._zugrecht = copyable.getZugrecht();
        this._whiteCanCastle = copyable.getWhiteCastleRights();
        this._blackCanCastle = copyable.getBlackCastleRights();
        this._enpassant = copyable.getEnPassant();
        this._zuegeKleiner50 = copyable.getZuegeKleiner50();
        this._zuegeGesamt = copyable.getZuegeGesamt();
    }

    private void createPosition(Fen fenPosition)
    {
        fenToFiguren(fenPosition);
        fenToZugrecht(fenPosition);
        fenToZuegeKleiner50(fenPosition);
        fenToEnPassant(fenPosition);
        fenToCanCastle(fenPosition);
        fenToZuegeGesamt(fenPosition);
    }

    //TODO alles
    public void makeMove(byte alteFigurPosition, byte neueFigurPosition)
    {
        if (_zugrecht)
        {
            for (Piece figur : _whiteFiguren)
            {
                if (figur.getCoordinate() == alteFigurPosition)
                {
                    figur.setCoordinate(neueFigurPosition);
                    if(figur instanceof Pawn) {
                    	_zuegeKleiner50 = 0;
                    	if(Math.abs(alteFigurPosition-neueFigurPosition)==16) {
                    	_enpassant = (byte) (alteFigurPosition +(neueFigurPosition-alteFigurPosition /2));
                    	}
                    if(figur instanceof King)
                    {
                    	_whiteCanCastle[0] = false;
                    	_whiteCanCastle[1] = false;
                    }
                    if(figur instanceof Rook)
                    {
                    	if (alteFigurPosition == 56)
                    	{
                    		_whiteCanCastle[1] = false;
                    	}
                    	if (alteFigurPosition == 63)
                    	{
                    		_whiteCanCastle[0] = false;
                    	}
                    }
                    }
                }
            }
            for (Piece figur : _blackFiguren)
            {
                if (figur.getCoordinate() == neueFigurPosition)
                {
                    _blackFiguren.remove(figur);
                }
                if(figur instanceof Rook)
                {
                	if (alteFigurPosition == 0)
                	{
                		_blackCanCastle[1] = false;
                	}
                	if (alteFigurPosition == 7)
                	{
                		_blackCanCastle[0] = false;
                	}
                }
            }
           
            
        }
        else
        {
            for (Piece figur : _blackFiguren)
            {
                if (figur.getCoordinate() == alteFigurPosition)
                {
                    figur.setCoordinate(neueFigurPosition);
                }
            }
            for (Piece figur : _whiteFiguren)
            {
                if (figur.getCoordinate() == neueFigurPosition)
                {
                    _whiteFiguren.remove(figur);
                }
            }
        }

    }

    // Alle fenTo Methoden
    private void fenToFiguren(Fen fenPosition)
    {

        String FigurPosition = fenPosition.getFigurPositionen();
        byte counter = 0;
        while (!FigurPosition.equals(""))
        {
            switch (FigurPosition.charAt(0))
            {

            case 'p':
                _blackFiguren.add(new Pawn(counter));
                counter++;
                FigurPosition = FigurPosition.substring(1);
                break;
            case 'P':
                _whiteFiguren.add(new Pawn(counter));
                counter++;
                FigurPosition = FigurPosition.substring(1);
                break;
            case 'n':
                _blackFiguren.add(new Knight(counter));
                counter++;
                FigurPosition = FigurPosition.substring(1);
                break;
            case 'N':
                _whiteFiguren.add(new Knight(counter));
                counter++;
                FigurPosition = FigurPosition.substring(1);
                break;
            case 'b':
                _blackFiguren.add(new Bishop(counter));
                counter++;
                FigurPosition = FigurPosition.substring(1);
                break;
            case 'B':
                _whiteFiguren.add(new Bishop(counter));
                counter++;
                FigurPosition = FigurPosition.substring(1);
                break;
            case 'r':
                _blackFiguren.add(new Rook(counter));
                counter++;
                FigurPosition = FigurPosition.substring(1);
                break;
            case 'R':
                _whiteFiguren.add(new Rook(counter));
                counter++;
                FigurPosition = FigurPosition.substring(1);
                break;
            case 'q':
                _blackFiguren.add(new Queen(counter));
                counter++;
                FigurPosition = FigurPosition.substring(1);
                break;
            case 'Q':
                _whiteFiguren.add(new Queen(counter));
                counter++;
                FigurPosition = FigurPosition.substring(1);
                break;
            case 'k':
                _blackFiguren.add(new King(counter));
                counter++;
                FigurPosition = FigurPosition.substring(1);
                break;
            case 'K':
                _whiteFiguren.add(new King(counter));
                counter++;
                FigurPosition = FigurPosition.substring(1);
                break;
            case '/':
                FigurPosition = FigurPosition.substring(1);
                break;
            default:
                counter = (byte) (counter + (FigurPosition.charAt(0) - 48));
                FigurPosition = FigurPosition.substring(1);
                break;
            }
        }

    }

    private void fenToZugrecht(Fen fenPosition)
    {
        if (fenPosition.getZugrecht().equals("w"))
        {
            _zugrecht = true;
        }
        else
        {
            _zugrecht = false;
        }
    }

    private void fenToZuegeKleiner50(Fen fenPosition)
    {
        _zuegeKleiner50 = Byte.parseByte(fenPosition.getZuegeKleiner50());

    }

    private void fenToEnPassant(Fen fenPosition)
    {
        String enPassant = fenPosition.getEnPassant();
        switch (enPassant)
        {
        case "a3":
            _enpassant = 40;
            break;
        case "b3":
            _enpassant = 41;
            break;
        case "c3":
            _enpassant = 42;
            break;
        case "d3":
            _enpassant = 43;
            break;
        case "e3":
            _enpassant = 44;
            break;
        case "f3":
            _enpassant = 45;
            break;
        case "g3":
            _enpassant = 46;
            break;
        case "h3":
            _enpassant = 47;
            break;
        case "a6":
            _enpassant = 16;
            break;
        case "b6":
            _enpassant = 17;
            break;
        case "c6":
            _enpassant = 18;
            break;
        case "d6":
            _enpassant = 19;
            break;
        case "e6":
            _enpassant = 20;
            break;
        case "f6":
            _enpassant = 21;
            break;
        case "g6":
            _enpassant = 22;
            break;
        case "h6":
            _enpassant = 23;
            break;
        default:
            _enpassant = -1;
            break;
        }
    }

    private void fenToCanCastle(Fen fenPosition)
    {
        if (fenPosition.getCastleRights()
            .contains("K"))
        {
            _whiteCanCastle[0] = true;
        }
        if (fenPosition.getCastleRights()
            .contains("Q"))
        {
            _whiteCanCastle[1] = true;
        }
        if (fenPosition.getCastleRights()
            .contains("k"))
        {
            _blackCanCastle[0] = true;
        }
        if (fenPosition.getCastleRights()
            .contains("q"))
        {
            _blackCanCastle[1] = true;
        }
    }
    
    private void fenToZuegeGesamt(Fen fenPosition)
    {
        _zuegeGesamt = Byte.parseByte(fenPosition.getZuegeGesamt());

    }
    // Getter

    // TODO alles
    public String getFen()
    {
    	String zugrecht = "";
    	if (getZugrecht())
    	{
    		zugrecht = "w";
    	}
    	else 
    	{
    		zugrecht = "b";
    	}
    	
    	String castling = "";
    	if (getWhiteCastleRights()[0])
    	{
    		castling = castling +  "K";
    	}
    	if (getWhiteCastleRights()[1])
    	{
    		castling = castling +  "Q";
    	}
    	if (getBlackCastleRights()[0])
    	{
    		castling = castling +  "k";
    	}
    	if (getBlackCastleRights()[1])
    	{
    		castling = castling +  "q";
    	}
    	else 
    	{
    		castling = "-";
    	}
    	
    	String enPassant = "";
    	switch (_enpassant)
        {
        case 40:
            enPassant = "a3";
            break;
        case 41:
        	enPassant = "b3";
            break;
        case 42:
        	enPassant = "c3";
            break;
        case 43:
        	enPassant = "d3";
            break;
        case 44:
        	enPassant = "e3";
            break;
        case 45:
        	enPassant = "f3";
            break;
        case 46:
        	enPassant = "g3";
            break;
        case 47:
        	enPassant = "h3";
            break;
        case 16:
        	enPassant = "a6";
            break;
        case 17:
        	enPassant = "b6";
            break;
        case 18:
        	enPassant = "c6";
            break;
        case 19:
        	enPassant = "d6";
            break;
        case 20:
        	enPassant = "e6";
            break;
        case 21:
        	enPassant = "f6";
            break;
        case 22:
        	enPassant = "g6";
            break;
        case 23:
        	enPassant = "h6";
            break;
        default:
        	enPassant = "-";
            break;
        }
    	
    	String halfmoves = "" + _zuegeKleiner50;
    	
    	String fullmoves = "" + _zuegeGesamt;
    		
    	
        return /*placement + */ " " + zugrecht + " " + castling + " " + enPassant + " " + halfmoves + " " + fullmoves;
    }

    public List<Piece> getWhiteFiguren()
    {
        return _whiteFiguren;
    }

    public List<Piece> getBlackFiguren()
    {
        return _blackFiguren;
    }

    public boolean getZugrecht()
    {
        return _zugrecht;
    }

    public boolean[] getWhiteCastleRights()
    {
        return _whiteCanCastle;
    }

    public boolean[] getBlackCastleRights()
    {
        return _blackCanCastle;
    }

    public byte getEnPassant()
    {
        return _enpassant;
    }

    public byte getZuegeKleiner50()
    {
        return _zuegeKleiner50;
    }

    public short getZuegeGesamt()
    {
        return _zuegeGesamt;
    }
}
