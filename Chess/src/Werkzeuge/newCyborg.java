package Werkzeuge;

import java.util.ArrayList;
import java.util.HashMap;

import Buchwissen.BookReader;
import Material.Position;
import Services.Eval;

/*
 * Der Cyborg 
 */
public class newCyborg {

	HashMap<String, Double> _guteZuege;
	BookReader _buch;
	Eval _eval;
	Position _bestPosition;
	int _gewuenschtetiefe;
	Double _lastEval;

	public newCyborg(int tiefe) {
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

		// Speichert die Evaluation der letzten Position.
		_lastEval = 0.0;
	}

	/*
	 * Diese Methode soll von "ausserhalb" aufgerufen werden um die beste
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
		_lastEval = 0.0;
		_guteZuege = new HashMap<String, Double>();

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
		 * Wenn weiß dran ist sollte die Methode Max aufgerufen werden.
		 * 
		 * Die Methode wird wiederholt und mit steigender Tiefe aufgerufen, sodass gute
		 * Zuege aus den vorigen Durchläufen gespeichert werden können und das Pruning
		 * effektiver ist. Zudem kann die Berechnung so theoretisch jederzeit
		 * abgebrochen werden und auf die Ergebnisse des letzten Durchlaufes
		 * zurückgegriffen werden.
		 */
		if (position.getZugrecht()) {
			for (int i = 1; i <= _gewuenschtetiefe; i++) {
				max(position, i, alpha, beta);
			}
			// Wenn schwarz dran ist sollte die Methode Min aufgerufen werden.
		} else {
			for (int i = 1; i <= _gewuenschtetiefe; i++) {
				min(position, i, alpha, beta);
			}
		}
		return _bestPosition;
	}

	private double max(Position position, int tiefe, double alpha, double beta) {

		return 0.0;
	}

	private double min(Position position, int tiefe, double alpha, double beta) {

		return 0.0;
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
}
