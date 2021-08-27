package Material;

import static org.junit.Assert.*;

import java.util.Map;

import org.junit.Test;

import Fachwerte.Fen;

public class KingIsInCheckSpeedTest {

	@Test
	public void test() {
		Fen startFen = Fen.select("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1");
		Position startPosition = new Position(startFen);
		King Koenig = null;
		
		for (Map.Entry<Byte, Piece> piece : startPosition.getBlackFiguren().entrySet())
		{
			if(piece.getValue() instanceof King)
			{
				Koenig = (King) piece.getValue();
			}
		}
		
		for(int i = 0;i < 4865609;i++)
		{
			Koenig.isInCheck(startPosition);
		}
	}

	//Paer: 7,8 / 7,1 / 7,7 nach Aenderungen 2,2 / 2,7 / 2,2
}
