package Startup;

import Fachwerte.Fen;
import Material.Position;
import Werkzeuge.RekursiverCyborg;

public class startupChess
{

    public static void main(String[] args)
    {

        RekursiverCyborg anton = new RekursiverCyborg(3);

        Fen startFen= Fen.select("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1");
        Position startPosition = new Position(startFen);

        Position besteFolgePos = anton.getBestFollowingPosition(startPosition);
        
        System.out.println(besteFolgePos.getFen());

    }

}
