package Werkzeuge;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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

	public ArrayList<Position> sortPositionsByEvalQuick(ArrayList<Position> folgePositionen){
		ArrayList<Position> hilfsListe = new ArrayList<Position>();
		Double durchschnitt = 0.0;
		double lastEval = 0.0;
		for (int i = 0; i < folgePositionen.size(); i++) {
			if (_eval.getEval(folgePositionen.get(i)) >= durchschnitt) {
				hilfsListe.add(0, folgePositionen.get(i));
				lastEval = _eval.getEval(hilfsListe.get(i));
			}
			else {
				if (hilfsListe.isEmpty()) {
					hilfsListe.add(hilfsListe.size(), folgePositionen.get(i));
					lastEval = _eval.getEval(hilfsListe.get(i));
				}
				else {
				hilfsListe.add(hilfsListe.size()-1, folgePositionen.get(i));
				lastEval = _eval.getEval(hilfsListe.get(i));
				}
			}
			durchschnitt = ((durchschnitt + lastEval) / (i+1));
		}
		return hilfsListe;
	}
	
	public ArrayList<Position> sortPositionsByEval(ArrayList<Position> folgePositionen) {
		ArrayList<Position> hilfsListe = new ArrayList<Position>();

		for (int i = 0; i < folgePositionen.size(); i++) {
			if (hilfsListe.size() == 0) {
				hilfsListe.add(i, folgePositionen.get(i));

			} else if (hilfsListe.size() == 1) {
				if (_eval.getEval(folgePositionen.get(i)) >= _eval.getEval(hilfsListe.get(i - 1))) {
					hilfsListe.add(i - 1, folgePositionen.get(i));

				} else {
					hilfsListe.add(i, folgePositionen.get(i));

				}
			} else {

				for (int j = 0; j <= hilfsListe.size(); j++) {
					if (j == hilfsListe.size()) {
						hilfsListe.add(j, folgePositionen.get(i));
						break;
					}
					if (_eval.getEval(folgePositionen.get(i)) >= _eval.getEval(hilfsListe.get(j))) {
						hilfsListe.add(j, folgePositionen.get(i));

						break;
					}
					

				}

			}
		}
		return hilfsListe;
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
		if (tiefe == 0) {
			return _eval.getEval(position);
		}
		double maxWert = alpha;
		double wert = 0;
		ArrayList<Position> legalPositions = new PositionCalc(position).getLegalFollowingPositions();
//		if (tiefe == _gewuenschtetiefe|| tiefe == _gewuenschtetiefe-1) { 
//			legalPositions = sortPositionsByEvalQuick(legalPositions);
//		}
		if (legalPositions.size() == 0) {
			for (Map.Entry<Byte, Piece> whitePiece : position.getWhiteFiguren().entrySet()) {
				if (whitePiece.getValue() instanceof King) {
					if (((King) whitePiece.getValue()).isInCheck(position)) {
						return -9999999;
					} else {
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
		if (maxWert > 9000000) {
			maxWert = maxWert - 1;
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
	//if (tiefe == _gewuenschtetiefe|| tiefe == _gewuenschtetiefe-1) { 
		//legalPositions = sortPositionsByEvalQuick(legalPositions);
		
		//}

		if (legalPositions.size() == 0) {
			for (Map.Entry<Byte, Piece> blackPiece : position.getBlackFiguren().entrySet()) {
				if (blackPiece.getValue() instanceof King) {
					if (((King) blackPiece.getValue()).isInCheck(position)) {
						return 9999999;
					} else {
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
		if (minWert < -9000000) {
			minWert = minWert + 1;
		}
		return minWert;
	}

}
