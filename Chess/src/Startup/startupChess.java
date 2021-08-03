package Startup;

import Fachwerte.Fen;
import Material.Position;

public class startupChess
{

    public static void main(String[] args)
    {

        Fen startposition = new Fen(
                "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1");
        
        Position currentPosition = new Position(startposition);

    }

}
