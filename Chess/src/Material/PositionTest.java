package Material;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;


import Fachwerte.Fen;


public class PositionTest {
	
	
	Fen _startFen= Fen.select("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1");
	Position _startPosition = new Position(_startFen);
	
	
	
	@Test
	public void testPositionFen() {
		fail("Not yet implemented");
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
		List<Piece> white = new ArrayList<Piece>();
		white = _startPosition.getWhiteFiguren();
	}

	@Test
	public void testGetBlackFiguren() {
		List<Piece> black = new ArrayList<Piece>();
		black = _startPosition.getBlackFiguren();
	}

	@Test
	public void testGetZugrecht() {
		assertEquals(true,_startPosition.getZugrecht());
	}

	@Test
	public void testGetWhiteCastleRights() {
		final boolean[] white = {true, true};
		assertEquals(white[0],_startPosition.getWhiteCastleRights()[0]);
		assertEquals(white[1],_startPosition.getWhiteCastleRights()[1]);
	}

	@Test
	public void testGetBlackCastleRights() {
		final boolean[] black = {true, true};
		assertEquals(black[0],_startPosition.getBlackCastleRights()[0]);
		assertEquals(black[1],_startPosition.getBlackCastleRights()[1]);

	} 

	@Test
	public void testGetEnPassant() {
		assertEquals((byte)-1,_startPosition.getEnPassant());
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
