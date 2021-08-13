package Werkzeuge;

import static org.junit.Assert.*;

import org.junit.Test;

import Fachwerte.Fen;
import Material.Position;

public class RekursiverCyborgTest {

	@Test
	public void testSpeed() {
		RekursiverCyborg anton = new RekursiverCyborg(3);

		Fen startFen = Fen.select("r4rk1/1bpqbppp/p1np1n2/1p2p3/4P3/1BNP1N1P/PPPB1PP1/R2Q1RK1 w - - 3 11");
		Position startPosition = new Position(startFen);

		Position besteFolgePos = anton.getBestFollowingPosition(startPosition);

		if (besteFolgePos == null) {
			System.out.println("Das Spiel ist vorbei!");
		} else {
			System.out.println(besteFolgePos.getFen());
		}
	}

}

/* DOKUMENTATION: (Zeit, die RekursiverCyborg braucht um seinen Zug zu finden in Sekunden)
*	
*	Jan PC:
*	Startversion (keine der unter ZWISCHENZIEL genannten "Verbesserungen implementiert):
*		10,346 /// 10.542 /// 10,365
*	
*	Kai PC:
*
*	Nikolai PC:
*
*
*
*
*/

/* ZWISCHENZIEL --->  Codebase anpassen, sodass RekursiverCyborg schneller wird, ihn selbst jedoch nicht verändern
 * 
 * - Position sollte white und black Figuren als map und nicht als Liste speichern 
 * 
 * - Nicht mehr jede Position sollte in PositionCalc auf legalität geprüft werden müssen
 * 
 * - König wir oft gesucht, gibt es eine bessere methode dies zu tun?
 * 
 * - king.isInCheck ist im Moment ineffizient
 * 
 * - alle maps, keys , hashcode sachen verstehen, um dann besser zu machen. 
 * 
 */