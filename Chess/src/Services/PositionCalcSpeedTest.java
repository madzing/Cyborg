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