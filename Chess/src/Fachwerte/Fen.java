package Fachwerte;

import java.util.HashMap;
import java.util.Map;

// "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1"

public class Fen
{
    private static Map<String, Fen> _fenMap = new HashMap<String, Fen>();
    private String _stringFen;
    private String _stringList[];

    private Fen(String stringFen)
    {
        _stringFen = stringFen;
        _stringList = _stringFen.split(" ");
    }
    
    public static Fen select(String stringFen) {
    	if (!_fenMap.containsKey(stringFen)) {
    		_fenMap.put(stringFen, new Fen(stringFen));
    	}
        	return _fenMap.get(stringFen);
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
