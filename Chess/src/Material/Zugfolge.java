package Material;

public class Zugfolge
{
    byte _zug;
    Zugfolge[] _folgezuege = null;

    public Zugfolge(byte zug)
    {
        _zug = zug;

    }
    

    public void addZugfolge(Zugfolge[] folgezuege)
    {
        _folgezuege = folgezuege;
    }
}

// A-->(a,null)
// A-->(a,{(b,null),(c,null),(d,null)})
// A-->(a,{(b,(e,null)),(c,(f,null)),(d,(g,null))})

//  Beispielcode:
//  Position pos = new Position("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1");
//
//  
//  byte ersterZug = PositionCalc.getErstenLegalZug(pos);
//  Zugfolge zf = new Zugfolge(ersterZug);
//  pos.makeMove(ersterZug);
//  Zugfolge[] alleZug = PositionCalc.getAlleLegalZug(pos);
//  zf.addZugfolge(alleZug);
// zf.get
