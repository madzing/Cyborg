package Werkzeuge;

import org.junit.Test;

import Fachwerte.Fen;
import Material.Position;

public class AlphaBetaEndlosschleifenTest {

	// Diese Fen führte oft zu langen Zeiten, in denen eine "Ruheposition" gefunden wird !
	//"r2qk2r/1p3ppp/p1p2nn1/2bP3b/4PN2/1BN3B1/PP4PP/R2Q1R1K b - - 0 1"   
	

	
	// irgendwie scheint er sich zu "verheddern", wenn wertvolle Figuren geschlagen werden?

	
	@Test
	public void testSpeed3() {
		AlphaBetaCyborg anton = new AlphaBetaCyborg(5);

		Fen startFen = Fen.select("r3r1k1/pbpp2b1/1pn2RN1/4p2Q/4P3/3P2PB/PPP5/2K4R b - - 9 25");
		Position startPosition = new Position(startFen);

		Position besteFolgePos = anton.getBestFollowingPosition(startPosition);

		if (besteFolgePos == null) {
			System.out.println("Das Spiel ist vorbei!");
		} else {
			System.out.println(besteFolgePos.getFen());
		}
	}
}
