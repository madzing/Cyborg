package Services;

import java.util.Map;

import Material.Bishop;
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
								   1.0,1.0,1.2,1.3,1.3,1.0,1.0,1.0,
								   1.0,1.0,1.2,1.3,1.3,1.0,1.0,1.0,
								   1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0,
								   1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0,
								   1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0,
																};
		_knightFelder = new Double[] {0.7,0.8,0.8,0.8,0.8,0.8,0.8,0.7,
									  0.8,0.9,0.9,0.9,0.9,0.9,0.9,0.8,
				  					  0.8,0.9,1.0,1.0,1.0,1.0,0.9,0.8,
				  					  0.8,0.9,1.0,1.0,1.0,1.0,0.9,0.8,
				  					  0.8,0.9,1.0,1.0,1.0,1.0,0.9,0.8,
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
		_rookFelder = new Double[]{1.0,1.0,1.0,1.1,1.1,1.0,1.0,1.0,
								   1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0,
								   1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0,
								   1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0,
								   1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0,
								   1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0,
								   1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0,
								   1.0,1.0,1.0,1.1,1.1,1.0,1.0,1.0,
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
		_kingFelder = new Double[]{1.0,1.2,1.1,1.0,1.0,1.0,1.2,1.1,
				   				   1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0,
				   				   1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0,
				   				   1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0,
				   				   1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0,
				   				   1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0,
				   				   1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0,
				   				   1.0,1.2,1.1,1.0,1.0,1.0,1.2,1.1,
												  				};

	}

	public double getEval(Position position) {

			double whitePoints = 0;
			double blackPoints = 0;

			if(position.getZuegeKleiner50()>=50)
			{
				return 0.0;
			}
			for (Map.Entry<Byte, Piece> whitePiece : position.getWhiteFiguren().entrySet()) {
				
				if(whitePiece.getValue() instanceof Pawn)
				{
					whitePoints = (double) (whitePoints + (whitePiece.getValue().getValue()* _pawnFelder[whitePiece.getValue().getCoordinate()]));
				}
				else if(whitePiece.getValue() instanceof Knight)
				{
					whitePoints = (double) (whitePoints + (whitePiece.getValue().getValue()*_knightFelder[whitePiece.getValue().getCoordinate()]));
				}
				else if(whitePiece.getValue() instanceof Bishop)
				{
					whitePoints = (double) (whitePoints + (whitePiece.getValue().getValue()*_bishopFelder[whitePiece.getValue().getCoordinate()]));
				}
				else if(whitePiece.getValue() instanceof Rook)
				{
					whitePoints = (double) (whitePoints + (whitePiece.getValue().getValue()*_rookFelder[whitePiece.getValue().getCoordinate()]));
				}
				else if(whitePiece.getValue() instanceof Queen)
				{
					whitePoints = (double) (whitePoints + (whitePiece.getValue().getValue()*_queenFelder[whitePiece.getValue().getCoordinate()]));
				}
				else 
				{
					whitePoints = (double) (whitePoints + (whitePiece.getValue().getValue()*_kingFelder[whitePiece.getValue().getCoordinate()]));
				}
			}
			for (Map.Entry<Byte, Piece> blackPiece : position.getBlackFiguren().entrySet()) {
				if (blackPiece.getValue() instanceof Pawn)
				{
					blackPoints = (double) (blackPoints + (blackPiece.getValue().getValue()*_pawnFelder[blackPiece.getValue().getCoordinate()]));
				}
				else if (blackPiece.getValue() instanceof Knight)
				{
					blackPoints = (double) (blackPoints + (blackPiece.getValue().getValue()*_knightFelder[blackPiece.getValue().getCoordinate()]));
				}
				else if (blackPiece.getValue() instanceof Bishop)
				{
					blackPoints = (double) (blackPoints + (blackPiece.getValue().getValue()*_bishopFelder[blackPiece.getValue().getCoordinate()]));
				}
				else if (blackPiece.getValue() instanceof Rook)
				{
					blackPoints = (double) (blackPoints + (blackPiece.getValue().getValue()*_rookFelder[blackPiece.getValue().getCoordinate()]));
				}
				else if (blackPiece.getValue() instanceof Queen)
				{
					blackPoints = (double) (blackPoints + (blackPiece.getValue().getValue()*_queenFelder[blackPiece.getValue().getCoordinate()]));
				}
				else 
				{
					blackPoints = (double) (blackPoints + (blackPiece.getValue().getValue()+_kingFelder[blackPiece.getValue().getCoordinate()]));
				}
			}
			
			
			double rueckgabe = (double) (whitePoints - blackPoints);
			if(position.getZugrecht())
			{
				rueckgabe = rueckgabe+0.251234;
			}
			else
			{
				rueckgabe = rueckgabe-0.251234;
			}
			
			return rueckgabe;
		}

}
