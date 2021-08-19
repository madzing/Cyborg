package Werkzeuge;

import static org.junit.Assert.*;

import org.junit.Test;

import Fachwerte.Fen;
import Material.Position;

public class AlphaBetaCyborgSpeedTest {

	@Test
	// Fen für die Startposition als Ansicherung "r4rk1/1bpqbppp/p1np1n2/1p2p3/4P3/1BNP1N1P/PPPB1PP1/R2Q1RK1 w - - 3 11"
	public void testSpeed() {
		AlphaBetaCyborg anton = new AlphaBetaCyborg(5);

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
