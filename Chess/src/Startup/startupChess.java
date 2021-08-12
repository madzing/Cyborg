package Startup;

import Fachwerte.Fen;
import Material.Position;
import Werkzeuge.RekursiverCyborg;

public class startupChess
{

    public static void main(String[] args)
    {

        RekursiverCyborg anton = new RekursiverCyborg(3);

        Fen startFen= Fen.select("r1bqkb1r/pppp1ppp/n6n/4p3/2B1P3/3P1Q2/PPP2PPP/RNB1K1NR b KQkq - 0 4");
        Position startPosition = new Position(startFen);

        Position besteFolgePos = anton.getBestFollowingPosition(startPosition);
        
        System.out.println(besteFolgePos.getFen());

    }

}
