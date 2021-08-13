package Startup;

import Fachwerte.Fen;
import Material.Position;
import Werkzeuge.RekursiverCyborg;

public class startupChess {

	public static void main(String[] args) {

		RekursiverCyborg anton = new RekursiverCyborg(3);

		Fen startFen = Fen.select("r4rk1/1bpqbppp/p1np1n2/1p2p3/4P3/1BNP1N1P/PPPB1PP1/R2Q1RK1 w - - 3 11");
		Position startPosition = new Position(startFen);

		Position besteFolgePos = anton.getBestFollowingPosition(startPosition);

		if (besteFolgePos == null) {
			System.out.println("Das Spiel ist vorbei!");
		} else {
			System.out.println(besteFolgePos.getFen());
		}
	}

}
