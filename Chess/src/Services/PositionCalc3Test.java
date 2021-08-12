package Services;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import Fachwerte.Fen;
import Material.Position;

public class PositionCalc3Test {

	@Test
	public void TestKonstruktor() {
		Fen startFen = Fen.select("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1");
		Position startPosition = new Position(startFen);
		PositionCalc3 posCalc = new PositionCalc3(startPosition);
		
		assertEquals(posCalc.getFigurenAmZug().size(), 16);
		assertEquals(posCalc.getFigurenDesGegners().size(), 16);
		assertEquals(posCalc.getCurrentPosition(), startPosition);
	}
	
	@Test
	public void TestgetLegalPositions() {
		Fen startFen = Fen.select("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1");
		Position startPosition = new Position(startFen);
		PositionCalc3 posCalc = new PositionCalc3(startPosition);
		
		List<Position> nodes = posCalc.getLegalFollowingPositions();
		// depth 1
		assertEquals(20, nodes.size());
		int depth = 4;
		for (int i = 1; i < depth; i++) {
			List<Position> latestNodes = new ArrayList<>();
			for (Position pos : nodes) {
				posCalc = new PositionCalc3(pos);
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
		if (depth ==6) {
			assertEquals(119060324, nodes.size());
		}
		
	}
	
	@Test
	public void Testcastle()
	{
		Fen startFen = Fen.select("rnbqkbnr/1pp1p1p1/p2pPp1p/P2P1P1P/P2P1P1P/P2P1P1P/P2P1P1P/R3K2R w KQkq - 0 1");
		Position startPosition = new Position(startFen);
		PositionCalc3 posCalc = new PositionCalc3(startPosition);
		List<Position> nodes = posCalc.getLegalFollowingPositions();
		
		assertEquals(10, nodes.size());
	}
	

}
