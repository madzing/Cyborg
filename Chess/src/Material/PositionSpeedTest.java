package Material;

import static org.junit.Assert.*;

import org.junit.Test;

import Fachwerte.Fen;

public class PositionSpeedTest {

	@Test
	public void test() {
		Fen _startFen= Fen.select("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1");
		Position _startPosition = new Position(_startFen);
		
		for(int i = 0; i < 4865609; i++)
		{
			Position pos = new Position(_startPosition);
			pos.makeMove((byte)52,(byte) 36);
			//pos.undoLastMove();
		}
	}
	
	// ohne make move 9,2
	// mit make move 9,0   <--- make move ist irrelevant! Positionen neu erzeugen dauert hingegen ewig!
	

}
