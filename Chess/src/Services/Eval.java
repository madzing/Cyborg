package Services;

import java.util.ArrayList;

import Material.Bishop;
import Material.King;
import Material.Knight;
import Material.Pawn;
import Material.Piece;
import Material.Position;
import Material.Queen;
import Material.Rook;

public class Eval {
	Double[] _pawnFelder;
	Double[] _knightFelder;
	Double[] _bishopFelder;
	Double[] _rookFelder;
	Double[] _queenFelder;
	Double[] _kingFelder;
	
	public Eval() {
		_pawnFelder = new Double[]{1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0,
								  1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0,
								  1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0,
								  1.0,1.0,1.0,1.7,1.7,1.0,1.0,1.0,
								  1.0,1.0,1.0,1.7,1.7,1.0,1.0,1.0,
								  1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0,
								  1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0,
								  1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0,
																};
		_knightFelder = new Double[] {0.7,0.8,0.8,0.8,0.8,0.8,0.8,0.7,
									 0.8,0.9,0.9,0.9,0.9,0.9,0.9,0.8,
				  					 0.8,0.9,1.0,1.0,1.0,1.0,0.9,0.8,
				  					 0.8,0.9,1.0,1.1,1.1,1.0,0.9,0.8,
				  					 0.8,0.9,1.0,1.1,1.1,1.0,0.9,0.8,
				  					 0.8,0.9,1.0,1.0,1.0,1.0,0.9,0.8,
				  					 0.8,0.9,0.9,0.9,0.9,0.9,0.9,0.8,
				  					 0.7,0.8,0.8,0.8,0.8,0.8,0.8,0.7,
																	};
		_bishopFelder = new Double[]{1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0,
				  					 1.0,1.2,1.0,1.0,1.0,1.0,1.2,1.0,
				  					 1.0,1.1,1.0,1.1,1.1,1.0,1.1,1.0,
				  					 1.0,1.0,1.1,1.1,1.1,1.1,1.0,1.0,
				  					 1.0,1.0,1.1,1.1,1.1,1.1,1.0,1.0,
				  					 1.0,1.1,1.0,1.1,1.1,1.0,1.1,1.0,
				  					 1.0,1.2,1.0,1.0,1.0,1.0,1.2,1.0,
				  					 1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0,
																	};
		_rookFelder = new Double[]{1.0,1.0,1.1,1.1,1.1,1.1,1.0,1.0,
								   1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0,
								   1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0,
								   1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0,
								   1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0,
								   1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0,
								   1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0,
								   1.0,1.0,1.1,1.1,1.1,1.1,1.0,1.0,
																  };
		_queenFelder = new Double[]{1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0,
				   					1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0,
				   					1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0,
				   					1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0,
				   					1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0,
				   					1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0,
				   					1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0,
				   					1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0,
												  				  };
		_kingFelder = new Double[]{1.0,1.2,1.1,1.0,1.0,1.0,1.2,1.2,
				   				   1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0,
				   				   1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0,
				   				   1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0,
				   				   1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0,
				   				   1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0,
				   				   1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0,
				   				   1.0,1.2,1.1,1.0,1.0,1.0,1.2,1.2,
												  				};

	}

	public double getEval(Position position) {

			double whitePoints = 0;
			double blackPoints = 0;
			for (Piece figur : position.getWhiteFiguren()) {
				if(figur instanceof Pawn)
				{
					whitePoints = (double) (whitePoints + (figur.getValue()* _pawnFelder[figur.getCoordinate()]));
				}
				else if(figur instanceof Knight)
				{
					whitePoints = (double) (whitePoints + (figur.getValue()*_knightFelder[figur.getCoordinate()]));
				}
				else if(figur instanceof Bishop)
				{
					whitePoints = (double) (whitePoints + (figur.getValue()*_bishopFelder[figur.getCoordinate()]));
				}
				else if(figur instanceof Rook)
				{
					whitePoints = (double) (whitePoints + (figur.getValue()*_rookFelder[figur.getCoordinate()]));
				}
				else if(figur instanceof Queen)
				{
					whitePoints = (double) (whitePoints + (figur.getValue()*_queenFelder[figur.getCoordinate()]));
				}
				else 
				{
					whitePoints = (double) (whitePoints + (figur.getValue()*_kingFelder[figur.getCoordinate()]));
				}
			}
			for (Piece figur : position.getBlackFiguren()) {
				if (figur instanceof Pawn)
				{
					blackPoints = (double) (blackPoints + (figur.getValue()*_pawnFelder[figur.getCoordinate()]));
				}
				else if (figur instanceof Knight)
				{
					blackPoints = (double) (blackPoints + (figur.getValue()*_knightFelder[figur.getCoordinate()]));
				}
				else if (figur instanceof Bishop)
				{
					blackPoints = (double) (blackPoints + (figur.getValue()*_bishopFelder[figur.getCoordinate()]));
				}
				else if (figur instanceof Rook)
				{
					blackPoints = (double) (blackPoints + (figur.getValue()*_rookFelder[figur.getCoordinate()]));
				}
				else if (figur instanceof Queen)
				{
					blackPoints = (double) (blackPoints + (figur.getValue()*_queenFelder[figur.getCoordinate()]));
				}
				else 
				{
					blackPoints = (double) (blackPoints + (figur.getValue()+_kingFelder[figur.getCoordinate()]));
				}
			}

			return (double) (whitePoints - blackPoints);
		}

}
