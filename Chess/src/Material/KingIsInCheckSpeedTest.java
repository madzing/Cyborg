package Material;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import Fachwerte.Fen;

public class KingIsInCheckSpeedTest {

	@Test
	public void test() {
		Fen _startFen = Fen.select("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1");
		Position _startPosition = new Position(_startFen);
		Map<Byte, Piece> whiteFiguren = new HashMap<>(16, (float) 1.0);
		byte kingPos;
		King king = new King((byte) 0, false);
		for (Map.Entry<Byte, Piece> piece : _startPosition.getWhiteFiguren().entrySet()) {
			if (piece.getValue() instanceof King) {
				king = (King) piece.getValue();
			}
		}

		for (int i = 0; i < 4865609; i++) {
			boolean a = king.isInCheck(_startPosition);
		}

	}
	
	/*Kai:  Version 1: 
	*		7.971 / 8,247 / 7,835
	*/
	
}