package Services;

import static org.junit.Assert.*;

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
		_startPosition.makeMove((byte)54,(byte) 46);
		firstPositions = PositionCalc.getLegalPositions(_startPosition);
		assertEquals(20,firstPositions.size());
		_startPosition.makeMove((byte)8,(byte) 16);
		firstPositions = PositionCalc.getLegalPositions(_startPosition);
		assertEquals(21,firstPositions.size());
		
		
		
	}

}
