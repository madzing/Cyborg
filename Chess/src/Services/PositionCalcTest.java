package Services;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import Fachwerte.Fen;
import Material.Position;

public class PositionCalcTest {
	Fen _startFen= Fen.select("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1");
	Position _startPosition = new Position(_startFen);
	
	
	@Test
	public void getLegalPositions() {
		List<Position> nodes = PositionCalc.getLegalPositions(_startPosition);
		//depth 1
		assertEquals(20,nodes.size());	
		int depth = 3;
		for (int i = 1; i < depth; i++) {
		List<Position> latestNodes = new ArrayList();
		for(Position pos : nodes) {
			latestNodes.addAll(PositionCalc.getLegalPositions(pos));
		}
		nodes = latestNodes;
		}
		assertEquals(8902,nodes.size());
}
}
