package Fachwerte;

// "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1"

public class Fen
{
    String _stringFen;
    String _stringList[];

    public Fen(String stringFen)
    {
        _stringFen = stringFen;
        _stringList = _stringFen.split(" ");
    }

    public String getString()
    {
        return _stringFen;
    }

    public String getFigurPositionen()
    {
        return _stringList[0];
    }

    public String getZugrecht()
    {
        return _stringList[1];
    }

    public String getCastleRights()
    {
        return _stringList[2];
    }

    public String getEnPassant()
    {
        return _stringList[3];
    }

    public String getZuegeKleiner50()
    {
        return _stringList[4];
    }

    public String getZuegeGesamt()
    {
        return _stringList[5];
    }

}
