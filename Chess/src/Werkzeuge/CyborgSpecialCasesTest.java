package Werkzeuge;

import static org.junit.Assert.*;

import org.junit.Test;

import Fachwerte.Fen;
import Material.Position;

public class CyborgSpecialCasesTest {

	/*
	 * Erkennt der Cyborg Matt in 1?
	 */
	@Test
	public void testMattin1() {
		Cyborg anton = new Cyborg(5);

		Fen startFen = Fen.select("k6r/8/8/8/8/8/6r1/K7 b - - 0 1");
		Position startPosition = new Position(startFen);
		
		int zaehler = 0;
		Position besteFolgePos = anton.getBestFollowingPosition(startPosition);
		
		while(besteFolgePos != null)
		{
			zaehler++;
			besteFolgePos = anton.getBestFollowingPosition(besteFolgePos);
		}
		assertEquals(null,besteFolgePos);
		assertEquals(1,zaehler);
	}
	

}
