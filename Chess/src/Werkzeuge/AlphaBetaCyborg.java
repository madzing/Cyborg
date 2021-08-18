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

	public AlphaBetaCyborg(int tiefe) {
		_eval = new Eval();
		_gewuenschtetiefe = tiefe;
	}

	public Position getBestFollowingPosition(Position position) {
		double alpha = Double.MIN_VALUE;
		double beta = Double.MAX_VALUE;
		_bestPosition = null;

		if (position.getZugrecht()) {
			max(position, _gewuenschtetiefe, alpha, beta);
		} else {
			min(position, _gewuenschtetiefe, alpha, beta);
		}
		return _bestPosition;
	}

	private double max(Position position, int tiefe, double alpha, double beta) {
		if (tiefe == 0) {
			return _eval.getEval(position);
		}
		double maxWert = alpha;
		double wert = 0;
		ArrayList<Position> legalPositions = new PositionCalc(position).getLegalFollowingPositions();

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

		return maxWert;
	}

	private double min(Position position, int tiefe, double alpha, double beta) {
		if (tiefe == 0) {
			return _eval.getEval(position);
		}
		double minWert = beta;
		double wert = 0;
		ArrayList<Position> legalPositions = new PositionCalc(position).getLegalFollowingPositions();

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

		return minWert;
	}

}
