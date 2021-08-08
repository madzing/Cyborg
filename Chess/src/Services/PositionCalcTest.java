package Services;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.junit.Test;

import Fachwerte.Fen;
import Material.Position;

public class PositionCalcTest {
	Fen _startFen = Fen.select("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1");
	Position _startPosition = new Position(_startFen);
	Position _startPosition2 = new Position(_startFen);

	@Test
	public void getLegalPositions() {
		List<Position> nodes = PositionCalc.getLegalPositions(_startPosition);
		// depth 1
		assertEquals(20, nodes.size());
		int depth = 4;
		for (int i = 1; i < depth; i++) {
			List<Position> latestNodes = new ArrayList<>();
			for (Position pos : nodes) {
				latestNodes.addAll(PositionCalc.getLegalPositions(pos));
			}
			nodes = latestNodes;
		}
		
		assertEquals(8902, nodes.size());
	}


	public static Map<String, Position> removeDuplicates(List<Position> list) 
	{
		Map<String, Position> bla = new HashMap<String, Position>();
		for (Position pos : list) {
			bla.put(pos.getFen(), pos);
		}
		return bla;
	}
	
	@Test
	public void testSinglePosition()
	{
		Fen startFen = Fen.select("rnbqkbnr/ppppppp1/8/7p/7P/8/PPPPPPP1/RNBQKBNR w KQkq h6 0 2");
		Position startPosition = new Position(startFen);
		List<Position> nodes = PositionCalc.getLegalPositions(startPosition);
		for(Position pos : nodes)
		{
			System.out.println(pos.getFen());
		}
		
	}

}
