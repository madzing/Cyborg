package Services;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import Fachwerte.Fen;
import Fachwerte.Zug;
import Material.Position;

public class PositionCalcTest {

	@Test
	public void TestKonstruktor() {
		Fen startFen = Fen.select("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1");
		Position startPosition = new Position(startFen);
		PositionCalc posCalc = new PositionCalc(startPosition);
		
		assertEquals(posCalc.getFigurenAmZug().size(), 16);
		assertEquals(posCalc.getFigurenDesGegners().size(), 16);
		assertEquals(posCalc.getCurrentPosition(), startPosition);
	}
	
	@Test
	public void TestgetLegalPositionsStartposition() {
		Fen startFen = Fen.select("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1");
		Position startPosition = new Position(startFen);
		PositionCalc posCalc = new PositionCalc(startPosition);
		
		List<Zug> nodes = posCalc.getLegalFollowingMoves();
		// depth 1
		assertEquals(20, nodes.size());
		int depth = 3;
		for (int i = 1; i < depth; i++) {
			List<Zug> latestNodes = new ArrayList<>();
			for (Zug zug : nodes) {
				startPosition.makeMove(zug);
				posCalc = new PositionCalc(startPosition);
				latestNodes.addAll(posCalc.getLegalFollowingMoves());
				startPosition.undoLastMove();
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
	
//	@Test
//	public void TestgetLegalPositionsPosition1() {
//		Fen startFen = Fen.select("r3k2r/p1ppqpb1/bn2pnp1/3PN3/1p2P3/2N2Q1p/PPPBBPPP/R3K2R w KQkq - 0 1");
//		Position startPosition = new Position(startFen);
//		PositionCalc posCalc = new PositionCalc(startPosition);
//		
//		List<Position> nodes = posCalc.getLegalFollowingMoves();
//		// depth 1
//		assertEquals(48, nodes.size());
//		int depth = 3;
//		for (int i = 1; i < depth; i++) {
//			List<Position> latestNodes = new ArrayList<>();
//			for (Position pos : nodes) {
//				posCalc = new PositionCalc(pos);
//				latestNodes.addAll(posCalc.getLegalFollowingMoves());
//			}
//			nodes = latestNodes;
//		}
//
//		if (depth ==2) {
//			assertEquals(2039, nodes.size());
//		}
//		if (depth ==3) {
//			assertEquals(97862, nodes.size());
//		}
//		if (depth ==4) {
//			assertEquals(4085603, nodes.size());
//		}
//		if (depth ==5) {
//			assertEquals(193690690 , nodes.size());
//		}
//
//		
//	}
//	@Test
//	public void TestgetLegalPositionsPosition2() {
//		Fen startFen = Fen.select("8/2p5/3p4/KP5r/1R3p1k/8/4P1P1/8 w - - 0 1");
//		Position startPosition = new Position(startFen);
//		PositionCalc posCalc = new PositionCalc(startPosition);
//		
//		List<Position> nodes = posCalc.getLegalFollowingMoves();
//		
//
//		// depth 1
//		assertEquals(14, nodes.size());
//		int depth = 3;
//		for (int i = 1; i < depth; i++) {
//			List<Position> latestNodes = new ArrayList<>();
//			for (Position pos : nodes) {
//				posCalc = new PositionCalc(pos);
//				latestNodes.addAll(posCalc.getLegalFollowingMoves());
//			}
//			nodes = latestNodes;
//		}
//
//		if (depth ==2) {
//			assertEquals(191, nodes.size());
//		}
//		if (depth ==3) {
//			assertEquals(2812, nodes.size());
//		}
//		if (depth ==4) {
//			assertEquals(43238, nodes.size());
//		}
//		if (depth ==5) {
//			assertEquals(674624, nodes.size());
//		}
//
//		
//	}
//	
//	@Test
//	public void Testcastle()
//	{
//		Fen startFen = Fen.select("rnbqkbnr/1pp1p1p1/p2pPp1p/P2P1P1P/P2P1P1P/P2P1P1P/P2P1P1P/R3K2R w KQkq - 0 1");
//		Position startPosition = new Position(startFen);
//		PositionCalc posCalc = new PositionCalc(startPosition);
//		List<Position> nodes = posCalc.getLegalFollowingMoves();
//		
//		assertEquals(10, nodes.size());
//	}
//	

}
