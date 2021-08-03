package Services;

import Fachwerte.Figur;
import Material.Position;

public class Eval
{
    public Eval()
    {

    }

    public short getEval(Position position)
    {
        short whitePoints = 0;
        short blackPoints = 0;
        for (Figur figur : position.getWhiteFiguren())
        {
            whitePoints = (short) (whitePoints + figur.getValue());
        }
        for (Figur figur : position.getBlackFiguren())
        {
            blackPoints = (short) (blackPoints + figur.getValue());
        }

        return (short) (whitePoints - blackPoints);
    }
}
