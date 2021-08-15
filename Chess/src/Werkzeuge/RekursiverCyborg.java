package Werkzeuge;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import Material.King;
import Material.Piece;
import Material.Position;
import Services.Eval;
import Services.PositionCalc;

public class RekursiverCyborg
{
	Eval _eval;
	int _tiefe;
    
 

    public RekursiverCyborg(int tiefe)
    {
    	_eval= new Eval();
        _tiefe = tiefe; 
    }
    public Position getBestFollowingPosition(Position position)
    {
		Position bestPosition = null;
		double bestEval = 0;
		ArrayList<Position> legalPositions = new PositionCalc(position).getLegalFollowingPositions();
		for(Position pos : legalPositions)
		{
			if(bestPosition == null)
			{
				bestPosition = pos;
				bestEval = getEvaluation(pos,_tiefe);
			}		
			else if (position.getZugrecht() && bestEval < getEvaluation(pos,_tiefe))
			{
				bestPosition = pos;
				bestEval = getEvaluation(pos,_tiefe);
			}
			else if (!position.getZugrecht() && bestEval > getEvaluation(pos,_tiefe))
			{
				bestPosition = pos;
				bestEval = getEvaluation(pos,_tiefe);
			}
		}
		
		//System.out.println(""+bestEval+"");
    	return bestPosition;
    }

    public Double getEvaluation(Position position, int tiefe)
    {
    	ArrayList<Double> list = new ArrayList<Double>();
    	if(tiefe == 0)
    	{
    		list.add(_eval.getEval(position));
    	}
    	else
    	{
    		
    		PositionCalc posCalc = new PositionCalc(position);
    		ArrayList<Position> legalPositions = posCalc.getLegalFollowingPositions();
    		if(legalPositions.size()==0)
    		{
    			if(position.getZugrecht())
    			{	
    				for(Map.Entry<Byte, Piece> whitePiece : position.getWhiteFiguren().entrySet())
    				{
    					if(whitePiece.getValue() instanceof King)
    					{
    						if(((King) whitePiece.getValue()).isInCheck(position))
    						{
    							list.add((double) -127);
    						}
    						else {
    							list.add((double)0);
    						}
    					}
    				}
    			}
    			else
    			{
    				for(Map.Entry<Byte, Piece> blackPiece : position.getBlackFiguren().entrySet())
    				{
    					if(blackPiece.getValue() instanceof King)
    					{
    						if(((King) blackPiece.getValue()).isInCheck(position))
    						{
    							list.add((double)127);
    						}
    						else
    						{
    							list.add((double)0);
    						}
    					}
    				}	
    			}
    		}
    		for(Position p : legalPositions)
    		{
    			list.add(getEvaluation(p,tiefe-1));
    		}
    	}
    	if(position.getZugrecht())
    	{


        return bestValueforWhite(list);


    	}
    	else
    	{
    		return bestValueForBlack(list);
    	}
    }
    
    public double bestValueforWhite(ArrayList<Double> list)
    {
    	double maximum = -127.0;
		
		for(double current: list)
		{
			if(current>maximum)
			{
				maximum = current;
			}
		}
    	return maximum;
    }
    
    public double bestValueForBlack(ArrayList<Double> list)
    {
    	double minimum = 127.0;
  		
  		for(double current: list)
  		{
  			if(current<minimum)
  			{
  				minimum = current;
  			}
  		}
      	return minimum;
      }
}
