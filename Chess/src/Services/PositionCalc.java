package Services;

import java.util.ArrayList;
import java.util.List;

import Material.Bishop;
import Material.Knight;
import Material.Pawn;
import Material.Piece;
import Material.Position;
import Material.Queen;
import Material.Rook;

public class PositionCalc {
	
	public PositionCalc()
    {

    }

	public List <Position> getLegalFollowingPositionsForBlack(Position )
    

//    public List<Position> getlegalFollowingPositions(Position currentPosition)
//    {
//        List<Position> legalFollowingPositions = new ArrayList<Position>();
//        for (int i = 0; i < 64; i++)
//        {
//            Piece piece = currentPosition.getPosition()[i];
//            if (piece != null)
//            {
//                if (piece instanceof Pawn)
//                {
//
//                }
//                else if (piece instanceof Knight)
//                {
//                    for (int a : piece.getMovement())
//                    {
//                        if (i + a >= 0 && i + a <= 63) // TODO hinzufügen, dass gleichfarbige pieceen nicht geschlagen werden können sollen
//                        {
//                            Position neuePosition = new Position(
//                                    currentPosition);
//                            neuePosition.makeMove(i, i + a);
//                            legalFollowingPositions.add(neuePosition);
//                        }
//                    }
//                }
//                else if (piece instanceof Bishop)
//                {
//
//                }
//                else if (piece instanceof Rook)
//                {
//
//                }
//                else if (piece instanceof Queen)
//                {
//
//                }
//                else
//                {
//
//                }
//            }
//        }
//        return legalFollowingPositions;
//    }
}
