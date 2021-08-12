package Werkzeuge;

import java.util.ArrayList;
import java.util.List;

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
		short bestEval = 0;
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
		
		System.out.println(""+bestEval+"");
    	return bestPosition;
    }

    public Short getEvaluation(Position position, int tiefe)
    {
    	ArrayList<Short> list = new ArrayList<Short>();
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
    				for(Piece piece : position.getWhiteFiguren())
    				{
    					if(piece instanceof King)
    					{
    						if(((King) piece).isInCheck(position))
    						{
    							list.add((short)-127);
    						}
    					}
    				}
    				return 0;
    			}
    			else
    			{
    				for(Piece piece : position.getBlackFiguren())
    				{
    					if(piece instanceof King)
    					{
    						if(((King) piece).isInCheck(position))
    						{
    							list.add((short)127);
    						}
    					}
    				}
    				return 0;	
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
    
    public short bestValueforWhite(ArrayList<Short> list)
    {
		short maximum = -127;
		
		for(short current: list)
		{
			if(current>maximum)
			{
				maximum = current;
			}
		}
    	return maximum;
    }
    
    public short bestValueForBlack(ArrayList<Short> list)
    {
  		short minimum = 127;
  		
  		for(short current: list)
  		{
  			if(current<minimum)
  			{
  				minimum = current;
  			}
  		}
      	return minimum;
      }
}
