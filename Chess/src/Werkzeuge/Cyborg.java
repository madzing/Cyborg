package Werkzeuge;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import Buchwissen.BookReader;
import Material.King;
import Material.Piece;
import Material.Position;
import Services.Eval;
import Services.PositionCalc;

/*
 * Der Cyborg 
 */
public class Cyborg {

	HashMap<String, Double> _guteZuege;
	BookReader _buch;
	Eval _eval;
	Position _bestPosition;
	int _gewuenschtetiefe;
	boolean _finalDurchlauf;

	public Cyborg(int tiefe) {
		// Ist eine Position im Buch enhalten sollte diese zurückgegeben werden
		_buch = new BookReader();

		// Die Eval Klasse dient dazu eine Position zu "bewerten", ohne über
		// Folgepositionen nachzudenken
		_eval = new Eval();

		// Hashmap in der gute Folgepositionen (bzw. Keys dieser) gespeichert werden,
		// sowie die Evaluation.
		_guteZuege = new HashMap<String, Double>();

		// Dient dazu die beste Position abzuspeichern
		_bestPosition = null;

		// Gibt an, wie Tief mindestens gerechnet werden muss. Ist Evaluation der zuvor
		// betrachteten Position stark unterschiedlich,
		// so wird weitergerechnet, da wir nicht inmitten eines Schlagabtausches stoppen
		// wollen.
		_gewuenschtetiefe = tiefe;
		
		// Handelt es sich um den Durchlauf mit der gewünschen Tiefe oder vorherige?
		_finalDurchlauf = false;
	}

	/*
	 * Wikipedia: Diese Methode soll von "ausserhalb" aufgerufen werden um die beste
	 * Folgeposition einer gegebenen Position zu finden. Hierzu wird Der Minimax
	 * Algorithmus in Kombination mit dem sogenannten Alpha-Beta-Pruning verwendet.
	 * 
	 * Die Idee beim Pruning ist, dass zwei Werte (Alpha und Beta) weitergereicht
	 * werden, die das Worst-Case-Szenario der Spieler beschreiben. Der Alpha-Wert
	 * ist das Ergebnis, das Spieler A mindestens erreichen wird, der Beta-Wert ist
	 * das Ergebnis, das Spieler B höchstens erreichen wird. (Hier ist zu beachten,
	 * dass es für Spieler B darum geht, ein möglichst niedriges Ergebnis zu
	 * erhalten, da er ja „minimierend“ spielt!)
	 * 
	 * Besitzt ein maximierender Knoten (von Spieler A) einen Zug, dessen Rückgabe
	 * den Beta-Wert überschreitet, wird die Suche in diesem Knoten abgebrochen
	 * (Beta-Cutoff, denn Spieler B würde A diese Variante erst gar nicht anbieten,
	 * weil sie sein bisheriges Höchst-Zugeständnis überschreiten würde). Liefert
	 * der Zug stattdessen ein Ergebnis, das den momentanen Alpha-Wert übersteigt,
	 * wird dieser entsprechend nach oben angehoben.
	 */
	public Position getBestFollowingPosition(Position position) {

		// Resetten der Variablen, welche von einem vorherigen durchlauf noch belegt
		// sein könnten.
		_bestPosition = null;
		double lastEval = 0.0;
		_guteZuege = new HashMap<String, Double>();
		_finalDurchlauf = false;

		// Ist eine Position im Buch enhalten sollte diese zurückgegeben werden
		if (_buch.istEnthalten(position)) {
			return _buch.folgePos(position);
		}

		// schlechtester Wert, den weiß mindestens erreichen wird. (Weiß steht bei
		// positiven Zahlen besser)
		double alpha = -9999.0;

		// schlechtester Wert, den schwarz mindestens erreichen wird.
		double beta = 9999.0;

		/*
		 * Die Methode wird wiederholt und mit steigender Tiefe aufgerufen, sodass gute
		 * Zuege aus den vorigen Durchläufen gespeichert werden können und das Pruning
		 * effektiver ist. Zudem kann die Berechnung so theoretisch jederzeit
		 * abgebrochen werden und auf die Ergebnisse des letzten Durchlaufes
		 * zurückgegriffen werden.
		 */
		int spieler;
		if (position.getZugrecht()) {
			spieler = 1;
		} else {
			spieler = -1;
		}
		for (int i = 1; i <= _gewuenschtetiefe; i++) {
			if(i == _gewuenschtetiefe )
			{
				_finalDurchlauf = true;
			}
			miniMax(spieler,lastEval, position, i, alpha, beta);
		}

		return _bestPosition;
	}

	/*
	 * Die eigentliche rekursive Funktion. Das Herz von Cyborg! Gibt die Eval einer
	 * Position zurück. Belegt das Feld _bestePosition.
	 */
	private double miniMax(int spieler, double lastEval, Position position, int tiefe, double alpha, double beta) {

		// Wenn die Suchtiefe 1 ist, also die vorletzte tiefe erreicht ist sollte
		// _lastEval abgespeichert werden.
		if (tiefe == 1) {
			lastEval = _eval.getEval(position)* spieler;
		}

		// Wenn die Tiefe kleiner oder gleich null ist, also die kann die Suche
		// abgebrochen werden, solage eine "Ruheposition" erreicht ist.
		else if (tiefe <= 0) {
			double currentEval = _eval.getEval(position) * spieler;
			
			// TODO Möglicherweise zusätzlich auf finalDurchlauf überprüfen bei ersten Tests sind die Ergebnisse von Speedtest sehr ähnlich.
			// (!_finalDurchlauf ||Math.abs(Math.abs(currentEval) - Math.abs(lastEval)) < 2)
			if (Math.abs(Math.abs(currentEval) - Math.abs(lastEval)) < 2) {
				//System.out.println(tiefe +" "+ position.getFen());
				return currentEval;
			}
			lastEval = currentEval;
		}

		// Alle Legalen Folgepositionen berechnen
		ArrayList<Position> legalPositions = new PositionCalc(position).getLegalFollowingPositions();

		// die Positionen sortieren
		legalPositions = guteZuegeZuerst(legalPositions);

		// wenn keine legalen Zuege moglich sind
		if (legalPositions.size() == 0) {

			if (schachmatt(position)) {
				return -9999.0 *spieler ;
			} else {
				return 0.0;
			}

		}

		// Über alle legalen Zuege iterieren und minimax aufrufen
		for (Position pos : legalPositions) {
			double wert = -miniMax(-spieler,-lastEval, pos, tiefe - 1, -beta, -alpha);

			if (wert > alpha) {
				alpha = wert;

				_guteZuege.put(pos.getPlacement(), wert);

				if (tiefe == _gewuenschtetiefe) {
					_bestPosition = pos;
					_bestPosition.setComparator(wert);
				}
				if (alpha >= beta) {
					break;
				}
			} else {
				_guteZuege.replace(pos.getPlacement(), wert);
			}
		}
		
		// Dafür sorgen, dass der Cyborg für das schnellste Schachmatt spielt!
		if (alpha > 9000.0) {
			alpha = alpha - 1;
		}
		else if (alpha < -9000.0)
		{
			alpha = alpha + 1;
		}
		return alpha;
	}

	/*
	 * Hilfsmethode um eine Liste von legal Moves anhand der dazugehörigen Map von
	 * _gutenZuege zu sortieren. Jede Position kann, muss aber nicht in _guteZuege
	 * enthalten sein.
	 */
	private ArrayList<Position> guteZuegeZuerst(ArrayList<Position> legalPositions) {
		ArrayList<Position> gutesArray = new ArrayList<Position>();
		ArrayList<Position> schlechtesArray = new ArrayList<Position>();
		for (Position p : legalPositions) {
			String placement = p.getPlacement();
			if (_guteZuege.containsKey(placement)) {
				p.setComparator(_guteZuege.get(placement));
				gutesArray.add(p);
			} else {
				schlechtesArray.add(p);
			}
		}
		gutesArray.sort(null);
		gutesArray.addAll(schlechtesArray);
		return gutesArray;
	}

	/* 
	 * Hilfsmethode um herauszufinden, ob der sich am Zug befindliche im Schachmatt steht
	 */
	private boolean schachmatt(Position position) {
		if (position.getZugrecht()) {
			for (Map.Entry<Byte, Piece> whitePiece : position.getWhiteFiguren().entrySet()) {
				if (whitePiece.getValue() instanceof King) {
					if (((King) whitePiece.getValue()).isInCheck(position)) {
						return true;
					}
				}
			}
		} else {
			for (Map.Entry<Byte, Piece> blackPiece : position.getBlackFiguren().entrySet()) {
				if (blackPiece.getValue() instanceof King) {
					if (((King) blackPiece.getValue()).isInCheck(position)) {
						return true;
					}
				}
			}
		}
		return false;
	}
	
	/*
	 * Gibt _guteZuege zurueck!
	 */
	public HashMap<String, Double> getGuteZuege() {
		return _guteZuege;
	}

}