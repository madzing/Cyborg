package Werkzeuge;

import java.util.ArrayList;



import Fachwerte.Fen;
import Material.Position;
import Services.Eval;
import Services.PositionCalc;

public class Cyborg
{
    Position _currentPosition;

    public Cyborg(Position currentPosition)
    {
        _currentPosition = currentPosition;
    }

    public Position calculate()
    {

        return _currentPosition;
    }
    public String getBestMove() {
    	Fen startFen = Fen.select("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1");
		Position startPosition = new Position(startFen);
		PositionCalc calc = new PositionCalc(startPosition);
		Eval eval = new Eval();
		Short[] evals = new Short[calc.getLegalFollowingPositions().size()];
		Position[] followingPositions = new Position[evals.length];
		for (int i = 0; i < followingPositions.length; i++)
		{
			followingPositions[i] = calc.getLegalFollowingPositions().get(i);
			evals[i] = eval.getEval(followingPositions[i]);
			calc = new PositionCalc(followingPositions[i]);
			for (int j = 0; j < 30000; j++)
			{
				if (j % 2 == 0) {
					if (evals[i] > eval.getEval(followingPositions[j]))
					{
						evals[i] = eval.getEval(calc.getLegalFollowingPositions().get(j));
					}
				}
				else {
					if (evals[i] < eval.getEval(followingPositions[j]))
					{
						evals[i] = eval.getEval(calc.getLegalFollowingPositions().get(j));
					}
				}
			}
		}		

    	return "";
    }

}
