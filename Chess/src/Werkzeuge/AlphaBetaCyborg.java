package Werkzeuge;

import java.util.ArrayList;
import java.util.Map;

import Material.King;
import Material.Piece;
import Material.Position;
import Services.Eval;
import Services.PositionCalc;

public class AlphaBetaCyborg {

	Eval _eval;
	int _gewuenschtetiefe;
	Position _bestPosition;
	Double _lastEval;

	public AlphaBetaCyborg(int tiefe) {
		_eval = new Eval();
		_gewuenschtetiefe = tiefe;
	}

	public Position getBestFollowingPosition(Position position) {
		double alpha = -99999999;
		double beta = 99999999;
		_bestPosition = null;

		if (position.getZugrecht()) {
			max(position, _gewuenschtetiefe, alpha, beta);
		} else {
			min(position, _gewuenschtetiefe, alpha, beta);
		}
		return _bestPosition;
	}

	private double max(Position position, int tiefe, double alpha, double beta) {
		if(tiefe == 1)
		{
			_lastEval = _eval.getEval(position);
		}
		else if (tiefe <= 0) {
			double currentEval = _eval.getEval(position);
			if(Math.abs(currentEval-_lastEval)<1)
			{
				return currentEval;
			}
			_lastEval = currentEval;
		}
		double maxWert = alpha;
		double wert = 0;
		ArrayList<Position> legalPositions = new PositionCalc(position).getLegalFollowingPositions();

		if(legalPositions.size()==0)
		{	
				for(Map.Entry<Byte, Piece> whitePiece : position.getWhiteFiguren().entrySet())
				{
					if(whitePiece.getValue() instanceof King)
					{
						if(((King) whitePiece.getValue()).isInCheck(position))
						{
							return -9999999;
						}
						else {
							return 0;
						}
					}
				}
			}
		
		for (Position pos : legalPositions) {
			wert = min(pos, tiefe - 1, maxWert, beta);
			if (wert > maxWert) {
				maxWert = wert;
				if (tiefe == _gewuenschtetiefe) {
					_bestPosition = pos;
				}
				if (maxWert >= beta) {
					break;
				}
			}

		}
		if(maxWert > 9000000)
		{
			maxWert = maxWert-1;
		}
		return maxWert;
	}

	private double min(Position position, int tiefe, double alpha, double beta) {
		if(tiefe == 1)
		{
			_lastEval = _eval.getEval(position);
		}
		else if (tiefe <= 0) {
			double currentEval = _eval.getEval(position);
			if(Math.abs(currentEval-_lastEval)<1)
			{
				return currentEval;
			}
			_lastEval = currentEval;
		}
		double minWert = beta;
		double wert = 0;
		ArrayList<Position> legalPositions = new PositionCalc(position).getLegalFollowingPositions();

		if(legalPositions.size()==0)
		{
			for(Map.Entry<Byte, Piece> blackPiece : position.getBlackFiguren().entrySet())
			{
				if(blackPiece.getValue() instanceof King)
				{
					if(((King) blackPiece.getValue()).isInCheck(position))
					{
						return 9999999;
					}
					else
					{
						return 0;
					}
				}
			}	
		}
		
		for (Position pos : legalPositions) {
			wert = max(pos, tiefe - 1, alpha, minWert);
			if (wert < minWert) {
				minWert = wert;
				if (tiefe == _gewuenschtetiefe) {
					_bestPosition = pos;
				}
				if (minWert <= alpha) {
					break;
				}
			}
		}
		if(minWert < -9000000)
		{
			minWert = minWert+1;
		}
		return minWert;
	}

}
