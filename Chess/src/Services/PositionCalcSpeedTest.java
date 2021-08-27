package Services;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import Fachwerte.Fen;
import Material.Position;

public class PositionCalcSpeedTest {

	@Test
	public void TestgetLegalPositionsStartposition() {
		Fen startFen = Fen.select("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1");
		Position startPosition = new Position(startFen);
		int positions = MoveGenerationTest(5,startPosition);
		System.out.println(""+positions);
	}
	public int MoveGenerationTest(int depth, Position position)
	{
		int numPositions = 0;
		PositionCalc posCalc = new PositionCalc(position);

		if(depth == 0)
		{
			return 1;
		}

		List<Position> nodes = posCalc.getLegalFollowingPositions();

		for(Position p : nodes)
		{
			numPositions += MoveGenerationTest(depth-1,p);
		}

		return numPositions;
	}
}

// Jan
// depth 5 35,4 sekunden! Viel zu lange! Es muss jedes mal eine neue Position und ein neuer Poscalc erzeugt werden das ist kacke!
// 20,5 / 20,2 / 20,2 sekunden nachdem Fachwerte gelöscht! gute arbeit
// Nach Paers Änderungen an KingInCheck 14,0/13,8/13,8
// Nach Änderungen an King.getMoves() 12,8/13,4/13,2

// Nikolai depth 5 17,34s / 17,2s / 17,5s


// depth 5 35,4 sekunden! Viel zu lange! Es muss jedes mal eine neue Position und ein neuer Poscalc erzeugt werden das ist kacke!
// Kai depth 5 beide Fachwert gel�scht: 26,8 / 26,657 / 26,787


//

// P�r depth 5 ~25,5s nach neuem KingIsInCheck ~17,7s


