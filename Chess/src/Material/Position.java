package Material;

import java.util.ArrayList;
import java.util.List;

import Fachwerte.Bishop;
import Fachwerte.Fen;
import Fachwerte.Figur;
import Fachwerte.King;
import Fachwerte.Knight;
import Fachwerte.Pawn;
import Fachwerte.Queen;
import Fachwerte.Rook;

public class Position
{
    private List<Figur> _whiteFiguren = new ArrayList<Figur>();
    private List<Figur> _blackFiguren = new ArrayList<Figur>();
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
            for (Figur figur : _whiteFiguren)
            {
                if (figur.getCoordinate() == alteFigurPosition)
                {
                    figur.setCoordinate(neueFigurPosition);
                }
            }
            for (Figur figur : _blackFiguren)
            {
                if (figur.getCoordinate() == neueFigurPosition)
                {
                    _blackFiguren.remove(figur);
                }
            }
        }
        else
        {
            for (Figur figur : _blackFiguren)
            {
                if (figur.getCoordinate() == alteFigurPosition)
                {
                    figur.setCoordinate(neueFigurPosition);
                }
            }
            for (Figur figur : _whiteFiguren)
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
                _blackFiguren.add(new Pawn(false, counter));
                counter++;
                FigurPosition = FigurPosition.substring(1);
                break;
            case 'P':
                _whiteFiguren.add(new Pawn(true, counter));
                counter++;
                FigurPosition = FigurPosition.substring(1);
                break;
            case 'n':
                _blackFiguren.add(new Knight(false, counter));
                counter++;
                FigurPosition = FigurPosition.substring(1);
                break;
            case 'N':
                _whiteFiguren.add(new Knight(true, counter));
                counter++;
                FigurPosition = FigurPosition.substring(1);
                break;
            case 'b':
                _blackFiguren.add(new Bishop(false, counter));
                counter++;
                FigurPosition = FigurPosition.substring(1);
                break;
            case 'B':
                _whiteFiguren.add(new Bishop(true, counter));
                counter++;
                FigurPosition = FigurPosition.substring(1);
                break;
            case 'r':
                _blackFiguren.add(new Rook(false, counter));
                counter++;
                FigurPosition = FigurPosition.substring(1);
                break;
            case 'R':
                _whiteFiguren.add(new Rook(true, counter));
                counter++;
                FigurPosition = FigurPosition.substring(1);
                break;
            case 'q':
                _blackFiguren.add(new Queen(false, counter));
                counter++;
                FigurPosition = FigurPosition.substring(1);
                break;
            case 'Q':
                _whiteFiguren.add(new Queen(true, counter));
                counter++;
                FigurPosition = FigurPosition.substring(1);
                break;
            case 'k':
                _blackFiguren.add(new King(false, counter));
                counter++;
                FigurPosition = FigurPosition.substring(1);
                break;
            case 'K':
                _whiteFiguren.add(new King(true, counter));
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
        if (fenPosition.getZugrecht() == "w")
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
        return "bla";
    }

    public List<Figur> getWhiteFiguren()
    {
        return _whiteFiguren;
    }

    public List<Figur> getBlackFiguren()
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
