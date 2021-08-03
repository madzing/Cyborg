package Services;

import java.util.ArrayList;
import java.util.List;

import Fachwerte.Bishop;
import Fachwerte.Figur;
import Fachwerte.Knight;
import Fachwerte.Pawn;
import Fachwerte.Queen;
import Fachwerte.Rook;
import Material.Position;

public class PositionCalc
{
    public PositionCalc()
    {

    }

    public List<Position> getlegalFollowingPositions(Position currentPosition)
    {
        List<Position> legalFollowingPositions = new ArrayList<Position>();
        for (int i = 0; i < 64; i++)
        {
            Figur figur = currentPosition.getPosition()[i];
            if (figur != null)
            {
                if (figur instanceof Pawn)
                {

                }
                else if (figur instanceof Knight)
                {
                    for (int a : figur.getMovement())
                    {
                        if (i + a >= 0 && i + a <= 63) // TODO hinzufügen, dass gleichfarbige Figuren nicht geschlagen werden können sollen
                        {
                            Position neuePosition = new Position(
                                    currentPosition);
                            neuePosition.makeMove(i, i + a);
                            legalFollowingPositions.add(neuePosition);
                        }
                    }
                }
                else if (figur instanceof Bishop)
                {

                }
                else if (figur instanceof Rook)
                {

                }
                else if (figur instanceof Queen)
                {

                }
                else
                {

                }
            }
        }
        return legalFollowingPositions;
    }
}
