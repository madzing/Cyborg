package Material;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;


import Fachwerte.Fen;


public class PositionTest {
	
	
	Fen _startFen= new Fen("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1");
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
		white.add(new Rook((byte)0));
		white.add(new Knight((byte)1));
		white.add(new Bishop((byte)2));
		white.add(new Queen((byte)3));
		white.add(new King((byte)4));
		white.add(new Bishop((byte)5));
		white.add(new Knight((byte)6));
		white.add(new Rook((byte)7));
		white.add(new Pawn((byte)8));
		assertEquals(white.get(0),_startPosition.getWhiteFiguren().get(0));
	}

	@Test
	public void testGetBlackFiguren() {
		List<Piece> black = new ArrayList<Piece>();
		black.add(new Rook((byte)0));
		black.add(new Knight((byte)1));
		black.add(new Bishop((byte)2));
		black.add(new Queen((byte)3));
		black.add(new King((byte)4));
		black.add(new Bishop((byte)5));
		black.add(new Knight((byte)6));
		black.add(new Rook((byte)7));
		black.add(new Pawn((byte)8));
		assertEquals(black.get(0),_startPosition.getWhiteFiguren().get(0));
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
