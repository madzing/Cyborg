package Werkzeuge;

import static org.junit.Assert.*;

import org.junit.Test;

import Fachwerte.Fen;
import Material.Position;

public class RekursiverCyborgSpeedTest {
	
	@Test
	// Fen für die Startposition als Ansicherung "r4rk1/1bpqbppp/p1np1n2/1p2p3/4P3/1BNP1N1P/PPPB1PP1/R2Q1RK1 w - - 3 11"
	public void testSpeed() {
		RekursiverCyborg anton = new RekursiverCyborg(2);

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
*		- Startversion (keine der unter ZWISCHENZIEL genannten "Verbesserungen" implementiert):
*				10,346 /// 10,542 /// 10,365
*		- Version-2 (Position sollte white und black Figuren als map und nicht als Liste speichern):
*				10.606 /// 10.733 /// 10.693	
*	
*	Kai PC:
*
*	Nikolai PC:
*		- Startversion (keine der unter ZWISCHENZIEL genannten "Verbesserungen" implementiert):
*				9,935 /// 9,194 /// 9,370
*
*
*
*
*/

/* ZWISCHENZIEL --->  Codebase anpassen, sodass RekursiverCyborg schneller wird, ihn selbst jedoch nicht verändern
 * 
 * DONE - Position sollte white und black Figuren als map und nicht als Liste speichern 
 * 
 * - Nicht mehr jede Position sollte in PositionCalc auf Legalität geprüft werden müssen
 * 
 * - König wir oft gesucht, gibt es eine bessere Methode dies zu tun?
 * 
 * - king.isInCheck ist im Moment ineffizient
 * 
 * - alle maps, keys , hashcode Sachen verstehen, um dann besser zu machen. 
 * 
 * - Position Make Move überarbeiten
 * 
 * 	- white und blackfiguren als Fachwerte speichern ? 
 * 
 */