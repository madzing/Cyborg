package Startup;

import Fachwerte.Fen;
import Material.Position;
import Werkzeuge.RekursiverCyborg;

public class startupChess {

	public static void main(String[] args) {

		RekursiverCyborg anton = new RekursiverCyborg(3);

		Fen startFen = Fen.select("2r3k1/p4pp1/4p2p/3p1b2/n3N3/b3BP2/PqP1NQPP/K2R1R2 w - - 7 24");
		Position startPosition = new Position(startFen);

		Position besteFolgePos = anton.getBestFollowingPosition(startPosition);

		if (besteFolgePos == null) {
			System.out.println("Das Spiel ist vorbei!");
		} else {
			System.out.println(besteFolgePos.getFen());
		}
	}

}
