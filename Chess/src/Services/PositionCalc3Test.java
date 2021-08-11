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
		Fen _startFen = Fen.select("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1");
		Position _startPosition = new Position(_startFen);
		PositionCalc3 posCalc = new PositionCalc3(_startPosition);
		
		assertEquals(posCalc.getFigurenAmZug().size(), 16);
		assertEquals(posCalc.getFigurenDesGegners().size(), 16);
		assertEquals(posCalc.getCurrentPosition(), _startPosition);
	}
	
	
	

}
