package Material;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;


import Fachwerte.Fen;


public class PositionTest {
	
	
	Fen _startFen= Fen.select("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1");
	Position _startPosition = new Position(_startFen);
	Fen _startFenFuerMove= Fen.select("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1");
	Position _startPositionFuerMove = new Position(_startFen);
	
	
	@Test
	public void testMakeMove() {
		Fen schrittEinsFen= Fen.select("rnbqkbnr/pppppppp/8/8/8/P7/1PPPPPPP/RNBQKBNR b KQkq - 0 2");
		_startPositionFuerMove.makeMove((byte)48, (byte)40); //Weisser Bauer von a2 auf a3 !Kein En Passant!
		assertEquals(schrittEinsFen,_startPositionFuerMove.getFen());
		
		Fen schrittZweiFen= Fen.select("rnbqkbnr/p1pppppp/8/1p6/8/P7/1PPPPPPP/RNBQKBNR w KQkq b6 0 3");
		_startPositionFuerMove.makeMove((byte)9, (byte)25); //Schwarzer Bauer von b7 auf b5 !En Passant auf b6!
		assertEquals(schrittZweiFen, _startPositionFuerMove.getFen());
		
		Fen schrittDreiFen= Fen.select("rnbqkbnr/p1pppppp/8/1p6/P7/8/1PPPPPPP/RNBQKBNR b KQkq - 0 4");
		_startPositionFuerMove.makeMove((byte)40, (byte)32); //Weisser Bauern von a3 auf a4
		assertEquals(schrittDreiFen, _startPositionFuerMove.getFen());
		
		Fen schrittVierFen= Fen.select("rnbqkbnr/p1pppppp/8/8/p7/8/1PPPPPPP/RNBQKBNR w KQkq - 0 5");
		_startPositionFuerMove.makeMove((byte)25, (byte)32); //Schwarzer Bauer schlaegt von b5 auf a4
		assertEquals(schrittVierFen, _startPositionFuerMove.getFen());
	}

	@Test
	public void testGetFen() {
		Fen zweiKoenigeFen = Fen.select("8/8/8/2k5/8/4K3/8/8 w - -1 5 56");
		Position zweiKoenigeTest = new Position(zweiKoenigeFen);
		assertEquals("8/8/8/2k5/8/4K3/8/8 w - -1 5 56", zweiKoenigeTest.getFen());
		assertEquals("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1" , _startPosition.getFen());
	}

	@Test
	public void testGetWhiteFiguren() {
		List<Piece> white = new ArrayList<Piece>();
		white.add(new Pawn((byte)48));		//0
		white.add(new Pawn((byte)49));		//1
		white.add(new Pawn((byte)50)); 		//2
		white.add(new Pawn((byte)51)); 		//3
		white.add(new Pawn((byte)52)); 		//4
		white.add(new Pawn((byte)53)); 		//5
		white.add(new Pawn((byte)54)); 		//6
		white.add(new Pawn((byte)55)); 		//7
		white.add(new Rook((byte)56)); 		//8
		white.add(new Knight((byte)57));	//9
		white.add(new Bishop((byte)58));	//10
		white.add(new Queen((byte)59)); 	//11
		white.add(new King((byte)60)); 		//12
		white.add(new Bishop((byte)61));	//13
		white.add(new Knight((byte)62)); 	//14
		white.add(new Rook((byte)63)); 		//15

		assertEquals(white.get(0).getClass(),_startPosition.getWhiteFiguren().get(0).getClass());
		assertEquals(white.get(1).getClass(),_startPosition.getWhiteFiguren().get(1).getClass());
		assertEquals(white.get(2).getClass(),_startPosition.getWhiteFiguren().get(2).getClass());
		assertEquals(white.get(3).getClass(),_startPosition.getWhiteFiguren().get(3).getClass());
		assertEquals(white.get(4).getClass(),_startPosition.getWhiteFiguren().get(4).getClass());
		assertEquals(white.get(5).getClass(),_startPosition.getWhiteFiguren().get(5).getClass());
		assertEquals(white.get(6).getClass(),_startPosition.getWhiteFiguren().get(6).getClass());
		assertEquals(white.get(7).getClass(),_startPosition.getWhiteFiguren().get(7).getClass());
		assertEquals(white.get(8).getClass(),_startPosition.getWhiteFiguren().get(8).getClass());
		assertEquals(white.get(9).getClass(),_startPosition.getWhiteFiguren().get(9).getClass());
		assertEquals(white.get(10).getClass(),_startPosition.getWhiteFiguren().get(10).getClass());
		assertEquals(white.get(11).getClass(),_startPosition.getWhiteFiguren().get(11).getClass());
		assertEquals(white.get(12).getClass(),_startPosition.getWhiteFiguren().get(12).getClass());
		assertEquals(white.get(13).getClass(),_startPosition.getWhiteFiguren().get(13).getClass());
		assertEquals(white.get(14).getClass(),_startPosition.getWhiteFiguren().get(14).getClass());
		assertEquals(white.get(15).getClass(),_startPosition.getWhiteFiguren().get(15).getClass());
	}

	@Test
	public void testGetBlackFiguren() {
		List<Piece> black = new ArrayList<Piece>();
		black.add(new Rook((byte)0));		//0
		black.add(new Knight((byte)1));		//1
		black.add(new Bishop((byte)2));		//2
		black.add(new Queen((byte)3));		//3
		black.add(new King((byte)4));		//4
		black.add(new Bishop((byte)5));		//5		
		black.add(new Knight((byte)6));		//6
		black.add(new Rook((byte)7));		//7
		black.add(new Pawn((byte)8));		//8
		black.add(new Pawn((byte)9));		//9
		black.add(new Pawn((byte)10));		//10
		black.add(new Pawn((byte)11));		//11
		black.add(new Pawn((byte)12));		//12
		black.add(new Pawn((byte)13));		//13
		black.add(new Pawn((byte)14));		//14
		black.add(new Pawn((byte)15));		//15
		assertEquals(black.get(0).getClass(),_startPosition.getBlackFiguren().get(0).getClass());
		assertEquals(black.get(1).getClass(),_startPosition.getBlackFiguren().get(1).getClass());
		assertEquals(black.get(2).getClass(),_startPosition.getBlackFiguren().get(2).getClass());
		assertEquals(black.get(3).getClass(),_startPosition.getBlackFiguren().get(3).getClass());
		assertEquals(black.get(4).getClass(),_startPosition.getBlackFiguren().get(4).getClass());
		assertEquals(black.get(5).getClass(),_startPosition.getBlackFiguren().get(5).getClass());
		assertEquals(black.get(6).getClass(),_startPosition.getBlackFiguren().get(6).getClass());
		assertEquals(black.get(7).getClass(),_startPosition.getBlackFiguren().get(7).getClass());
		assertEquals(black.get(8).getClass(),_startPosition.getBlackFiguren().get(8).getClass());
		assertEquals(black.get(9).getClass(),_startPosition.getBlackFiguren().get(9).getClass());
		assertEquals(black.get(10).getClass(),_startPosition.getBlackFiguren().get(10).getClass());
		assertEquals(black.get(11).getClass(),_startPosition.getBlackFiguren().get(11).getClass());
		assertEquals(black.get(12).getClass(),_startPosition.getBlackFiguren().get(12).getClass());
		assertEquals(black.get(13).getClass(),_startPosition.getBlackFiguren().get(13).getClass());
		assertEquals(black.get(14).getClass(),_startPosition.getBlackFiguren().get(14).getClass());
		assertEquals(black.get(15).getClass(),_startPosition.getBlackFiguren().get(15).getClass());
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
