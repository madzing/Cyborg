package Services;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import Fachwerte.Fen;
import Material.Position;

public class PositionCalc2Test {

	Fen _startFen = Fen.select("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1");
	Position _startPosition = new Position(_startFen);
	Position _startPosition2 = new Position(_startFen);
	PositionCalc2 posCalc = new PositionCalc2(_startPosition);
	@Test
	public void getLegalPositions() {
		List<Position> nodes = posCalc.getLegalPositions();
		// depth 1
		assertEquals(20, nodes.size());
		int depth = 4;
		for (int i = 1; i < depth; i++) {
			List<Position> latestNodes = new ArrayList<>();
			for (Position pos : nodes) {
				posCalc = new PositionCalc2(pos);
				latestNodes.addAll(posCalc.getLegalPositions());
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
		if (depth ==6) {
			assertEquals(119060324, nodes.size());
		}
		
	}


	public static Map<String, Position> removeDuplicates(List<Position> list) 
	{
		Map<String, Position> bla = new HashMap<String, Position>();
		for (Position pos : list) {
			bla.put(pos.getFen(), pos);
		}
		return bla;
	}
}
