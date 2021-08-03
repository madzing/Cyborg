package Material;

import static org.junit.Assert.*;

import org.junit.Test;





import Fachwerte.Fen;

public class PositionTest {
	
	
	Fen _startFen= new Fen("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1");
	Position _startPosition = new Position(_startFen);
	
	
	
	@Test
	public void testPositionFen() {
		//assertTrue();
	}

	@Test
	public void testPositionPosition() {
		fail("Not yet implemented");
	}

	@Test
	public void testMakeMove() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetFen() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetWhiteFiguren() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetBlackFiguren() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetZugrecht() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetWhiteCastleRights() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetBlackCastleRights() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetEnPassant() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetZuegeKleiner50() {
		assertEquals((byte)0,_startPosition.getZuegeKleiner50());
	}

	@Test
	public void testGetZuegeGesamt() {
		assertEquals((short)1,_startPosition.getZuegeGesamt());
	}

}
