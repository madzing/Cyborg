package Services;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import Fachwerte.Fen;
import Fachwerte.Zug;
import Material.Position;

public class PositionCalcSpeedTest {

	@Test
	public void TestgetLegalPositionsStartposition() {
		int depth = 1;
		Fen startFen = Fen.select("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1");
		Position startPosition = new Position(startFen);
		int positions = MoveGenerationTest(depth,startPosition);
		System.out.println(""+positions);

		if (depth ==2) {
			assertEquals(400, positions);
		}
		if (depth ==3) {
			assertEquals(8902, positions);
		}
		if (depth ==4) {
			assertEquals(197281, positions);
		}
		if (depth ==5) {
			assertEquals(4865609, positions);
		}
	}

	@Test
	public void TestgetLegalPositionsPosition2() {
		int depth = 1;
		Fen startFen = Fen.select("r3k2r/p1ppqpb1/bn2pnp1/3PN3/1p2P3/2N2Q1p/PPPBBPPP/R3K2R w KQkq - 0 1");
		Position startPosition = new Position(startFen);
		int positions = MoveGenerationTest(depth,startPosition);
		System.out.println(""+positions);

		if (depth ==2) {
			assertEquals(2039, positions);
		}
		if (depth ==3) {
			assertEquals(97862, positions);
		}
		if (depth ==4) {
			assertEquals(4085603, positions);
		}
		if (depth ==5) {
			assertEquals(193690690 , positions);
		}
	}

	@Test
	public void TestgetLegalPositionsPosition3() {
		int depth = 4;
		Fen startFen = Fen.select("8/2p5/3p4/KP5r/1R3p1k/8/4P1P1/8 w - - 0 1");
		Position startPosition = new Position(startFen);
		int positions = MoveGenerationTest(depth,startPosition);
		System.out.println(""+positions);

		if (depth ==2) {
		assertEquals(191, positions);
		}
		if (depth ==3) {
			assertEquals(2812, positions);
		}
		if (depth ==4) {
			assertEquals(43238, positions);
		}
		if (depth ==5) {
			assertEquals(674624, positions);
		}
	}

	public int MoveGenerationTest(int depth, Position position)
	{
		int numPositions = 0;
		PositionCalc posCalc = new PositionCalc(position);

		if(depth == 0)
		{
			return 1;
		}

		List<Zug> nodes = posCalc.getLegalFollowingMoves();

		for(Zug p : nodes)
		{
			position.makeMove(p.getAlteFigurPosition(), p.getNeueFigurPosition());
			numPositions += MoveGenerationTest(depth-1, position);
			position.undoLastMove();
		}

		return numPositions;
	}
}

// Jan
// depth 5 35,4 sekunden! Viel zu lange! Es muss jedes mal eine neue Position und ein neuer Poscalc erzeugt werden das ist kacke!
// 20,5 / 20,2 / 20,2 sekunden nachdem Fachwerte gelöscht! gute arbeit
// Nach Paers Änderungen an KingInCheck 14,0/13,8/13,8
// Nach Änderungen an King.getMoves() 12,8/13,4/13,2

// Nikolai depth 5 17,34s / 17,2s / 17,5s


// depth 5 35,4 sekunden! Viel zu lange! Es muss jedes mal eine neue Position und ein neuer Poscalc erzeugt werden das ist kacke!
// 20,5 / 20,2 / 20,2 sekunden nachdem Fachwerte gelöscht! gute arbeit
// 

// Nikolai depth 5 17,34s / 17,2s / 17,5s

// Kai depth 5 beide Fachwert gel�scht: 26,8 / 26,657 / 26,787


//

// P�r depth 5 ~25,5s nach neuem KingIsInCheck ~17,7s
