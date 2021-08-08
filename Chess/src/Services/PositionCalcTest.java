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
		List<Position> firstPositions = PositionCalc.getLegalPositions(_startPosition);
		assertEquals(20,firstPositions.size());
		_startPosition.makeMove((byte)55,(byte) 39);
		firstPositions = PositionCalc.getLegalPositions(_startPosition);
		assertEquals(20,firstPositions.size());
		_startPosition.makeMove((byte)8,(byte) 16);
		firstPositions = PositionCalc.getLegalPositions(_startPosition);
		assertEquals(21,firstPositions.size());
		
		//////////
		
		_startPosition = new Position(_startFen);
	}
	@Test
	public void getLegalPositions2() {
		List<Position> nodes = PositionCalc.getLegalPositions(_startPosition);
		//depth 1
		assertEquals(20,nodes.size());	
		
		List<Position> latestNodes = new ArrayList();
		for(Position pos : nodes) {
			latestNodes.addAll(PositionCalc.getLegalPositions(pos));
		}
		assertEquals(400,latestNodes.size());
}
}
