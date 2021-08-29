package Werkzeuge;

import static org.junit.Assert.*;

import java.util.Map;

import javax.lang.model.type.NullType;

import org.junit.Test;

import Fachwerte.Fen;
import Fachwerte.Zug;
import Material.Piece;
import Material.Position;

public class CyborgSpeedTest {

	@Test
	// Startposition "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1"
	// Fen für die SpeedTestPosition als Absicherung "r4rk1/1bpqbppp/p1np1n2/1p2p3/4P3/1BNP1N1P/PPPB1PP1/R2Q1RK1 w - - 3 11"
	// Diese Fen führte oft zu Endlosschleifen !           "r2qk2r/1p3ppp/p1p2nn1/2bP3b/4PN2/1BN3B1/PP4PP/R2Q1R1K b - - 0 1"

	public void testSpeed() {
		Cyborg anton = new Cyborg(5);

		Fen startFen = Fen.select("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1");
		Position startPosition = new Position(startFen);

		Zug besterFolgeZug = anton.getBestFollowingZug(startPosition);
		startPosition.makeMove(besterFolgeZug);
		
		if (besterFolgeZug == null) {
			System.out.println("Das Spiel ist vorbei!");
		} else {
			System.out.println(startPosition.getFen());
		}
	}

	@Test
	public void testSpeed2() {
		Cyborg anton = new Cyborg(5);

		Fen startFen = Fen.select("r4rk1/1bpqbppp/p1np1n2/1p2p3/4P3/1BNP1N1P/PPPB1PP1/R2Q1RK1 w - - 3 11");
		Position startPosition = new Position(startFen);

		Zug besterFolgeZug = anton.getBestFollowingZug(startPosition);
		startPosition.makeMove(besterFolgeZug);
		
		if (besterFolgeZug == null) {
			System.out.println("Das Spiel ist vorbei!");
		} else {
			System.out.println(startPosition.getFen());
		}
	}
	@Test
	public void testSpeed3() {
		Cyborg anton = new Cyborg(5);
		// Diese Fen führte oft zu Endlosschleifen !
		Fen startFen = Fen.select("r2qk2r/1p3ppp/p1p2nn1/2bP3b/4PN2/1BN3B1/PP4PP/R2Q1R1K b - - 0 1");
		Position startPosition = new Position(startFen);

		Zug besterFolgeZug = anton.getBestFollowingZug(startPosition);
		startPosition.makeMove(besterFolgeZug);
		
		if (besterFolgeZug == null) {
			System.out.println("Das Spiel ist vorbei!");
		} else {
			System.out.println(startPosition.getFen());
		}
	}
	@Test
	public void testSpeed4() {
		Cyborg anton = new Cyborg(5);

		Fen startFen = Fen.select("r1bq1rk1/ppppbppp/2n2n2/4p3/3PP3/2N2N2/PPP1BPPP/R1BQK2R w KQ - 0 1");
		Position startPosition = new Position(startFen);

		Zug besterFolgeZug = anton.getBestFollowingZug(startPosition);
		startPosition.makeMove(besterFolgeZug);
		
		if (besterFolgeZug == null) {
			System.out.println("Das Spiel ist vorbei!");
		} else {
			System.out.println(startPosition.getFen());
		}
	}
	@Test
	public void testSpeed5() {
		Cyborg anton = new Cyborg(5);

		Fen startFen = Fen.select("r1bqkb1r/pp2nppp/2n1p3/2ppP3/3P4/2P2N2/PP3PPP/RNBQKB1R w KQkq - 3 6");
		Position startPosition = new Position(startFen);

		Zug besterFolgeZug = anton.getBestFollowingZug(startPosition);
		startPosition.makeMove(besterFolgeZug);
		
		if (besterFolgeZug == null) {
			System.out.println("Das Spiel ist vorbei!");
		} else {
			System.out.println(startPosition.getFen());
		}
	}
	
	@Test
	public void testSpeed6() {
		Cyborg anton = new Cyborg(5);

		Fen startFen = Fen.select("1r4k1/pb2Qpb1/1p1p3p/n1pP1P2/2P3B1/1PN4P/P4qP1/B5RK b - - 1 26");
		Position startPosition = new Position(startFen);

		Zug besterFolgeZug = anton.getBestFollowingZug(startPosition);
		startPosition.makeMove(besterFolgeZug);
		
		if (besterFolgeZug == null) {
			System.out.println("Das Spiel ist vorbei!");
		} else {
			System.out.println(startPosition.getFen());
		}
	}
}