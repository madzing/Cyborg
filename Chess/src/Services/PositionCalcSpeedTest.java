package Services;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import Fachwerte.Fen;
import Material.Position;

public class PositionCalcSpeedTest {

	@Test
	public void TestgetLegalPositionsStartposition() {
		Fen startFen = Fen.select("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1");
		Position startPosition = new Position(startFen);
		PositionCalc posCalc = new PositionCalc(startPosition);
		
		List<Position> nodes = posCalc.getLegalFollowingPositions();
		// depth 1
		assertEquals(20, nodes.size());
		int depth = 4;
		for (int i = 1; i < depth; i++) {
			List<Position> latestNodes = new ArrayList<>();
			for (Position pos : nodes) {
				posCalc = new PositionCalc(pos);
				latestNodes.addAll(posCalc.getLegalFollowingPositions());
			}
			nodes = latestNodes;
		}

		if (depth ==2) {
			assertEquals(400, nodes.size());
		}
		if (depth ==3) {
			assertEquals(8902, nodes.size());
		}
		if (depth ==4) {
			assertEquals(197281, nodes.size());
		}
		if (depth ==5) {
			assertEquals(4865609, nodes.size());
		}

	}
}
