package Werkzeuge;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.lang.model.type.NullType;

import Buchwissen.BookReader;
import Material.King;
import Material.Piece;
import Material.Position;
import Services.Eval;
import Services.PositionCalc;


// Probleme mit endlosschleife nicht behoben
public class AlphaBetaCyborg {

	Eval _eval;
	int _gewuenschtetiefe;
	Position _bestPosition;
	Double _lastEval;
	BookReader _buch;
	HashMap <String,Double> _guteZuege;

	public AlphaBetaCyborg(int tiefe) {
		_eval = new Eval();
		_gewuenschtetiefe = tiefe;
		_buch = new BookReader();
		_guteZuege = new HashMap<String,Double>();
	}

	public Position getBestFollowingPosition(Position position) {
		if(_buch.istEnthalten(position))
		{
			return _buch.folgePos(position);
		}
		
		
		double alpha = -99999999;
		double beta = 99999999;
		_bestPosition = null;

		if (position.getZugrecht()) {
			for(int i = 1; i <=_gewuenschtetiefe;i++)
			{
				max(position, i, alpha, beta);
			}
		} else {
			for(int i = 1; i <=_gewuenschtetiefe;i++)
			{
			min(position, i, alpha, beta);
			}
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
			if(Math.abs(Math.abs(currentEval)-Math.abs(_lastEval))<2||_lastEval<-9000||_lastEval>9000||_lastEval==0.0)
			{
				//System.out.println(tiefe);
				return currentEval;
			}

			_lastEval = currentEval;
		}
		double maxWert = alpha;
		double wert = 0;
		ArrayList<Position> legalPositions = new PositionCalc(position).getLegalFollowingPositions();
		
		legalPositions = guteZuegeZuerst(legalPositions);

		if(legalPositions.size()==0)
		{	
				for(Map.Entry<Byte, Piece> whitePiece : position.getWhiteFiguren().entrySet())
				{
					if(whitePiece.getValue() instanceof King)
					{
						if(((King) whitePiece.getValue()).isInCheck(position))
						{
							_lastEval = -9999999.0;
							return -9999999;
						}
						else {
							_lastEval = 0.0;
							return 0;
						}
					}
				}
			}
		
		for (Position pos : legalPositions) {
			wert = min(pos, tiefe - 1, maxWert, beta);

			
			if (wert > maxWert) {
				maxWert = wert;
				_guteZuege.put(pos.getPlacement(), wert);
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
			if(Math.abs(Math.abs(currentEval)-Math.abs(_lastEval))<2||_lastEval<-9000||_lastEval>9000||_lastEval==0.0)
			{
				//System.out.println(tiefe);
				return currentEval;
			}

			_lastEval = currentEval;
		}
		double minWert = beta;
		double wert = 0;
		ArrayList<Position> legalPositions = new PositionCalc(position).getLegalFollowingPositions();

		legalPositions = guteZuegeZuerst(legalPositions);
		
		if(legalPositions.size()==0)
		{
			for(Map.Entry<Byte, Piece> blackPiece : position.getBlackFiguren().entrySet())
			{
				if(blackPiece.getValue() instanceof King)
				{
					if(((King) blackPiece.getValue()).isInCheck(position))
					{
						_lastEval = 9999999.0;
						return 9999999;
					}
					else
					{
						_lastEval = 0.0;
						return 0;
					}
				}
			}	
		}
		
		for (Position pos : legalPositions) {
			wert = max(pos, tiefe - 1, alpha, minWert);

			if (wert < minWert) {
				minWert = wert;

				_guteZuege.put(pos.getPlacement(), wert);
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
	
	private ArrayList<Position> guteZuegeZuerst(ArrayList<Position> legalPositions)
	{
		ArrayList<Position> gutesArray = new ArrayList<Position>();
		ArrayList<Position> schlechtesArray = new ArrayList<Position>();
		for (Position p : legalPositions)
		{
			String placement = p.getPlacement();
			if(_guteZuege.containsKey(placement))
			{
				p.setComparator(_guteZuege.get(placement));
				gutesArray.add(p);
				//_guteZuege.remove(placement); // TODO warum funktioniert dies nicht, sondern macht das Programm langsamer
			}
			else
			{
				schlechtesArray.add(p);
			}
		}
		gutesArray.sort(null);
		gutesArray.addAll(schlechtesArray);
		return gutesArray;
	}
	
	public 	HashMap <String,Double> getGuteZuege()
	{
		return _guteZuege;
	}
	

}
