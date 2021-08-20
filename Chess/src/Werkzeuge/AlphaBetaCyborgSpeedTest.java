package Werkzeuge;

import static org.junit.Assert.*;

import java.util.Map;

import javax.lang.model.type.NullType;

import org.junit.Test;

import Fachwerte.Fen;
import Material.Piece;
import Material.Position;

public class AlphaBetaCyborgSpeedTest {

	@Test
	// Startposition "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1"
	// Fen für die SpeedTestPosition als Ansicherung "r4rk1/1bpqbppp/p1np1n2/1p2p3/4P3/1BNP1N1P/PPPB1PP1/R2Q1RK1 w - - 3 11"
	// Diese Fen führte oft zu Endlosschleifen !           "r2qk2r/1p3ppp/p1p2nn1/2bP3b/4PN2/1BN3B1/PP4PP/R2Q1R1K b - - 0 1"
	public void testSpeed() {
		AlphaBetaCyborg anton = new AlphaBetaCyborg(5);

		Fen startFen = Fen.select("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1");
		Position startPosition = new Position(startFen);

		Position besteFolgePos = anton.getBestFollowingPosition(startPosition);

		if (besteFolgePos == null) {
			System.out.println("Das Spiel ist vorbei!");
		} else {
			System.out.println(besteFolgePos.getFen());
		}
	}
}
