package Werkzeuge;

import java.util.ArrayList;
import java.util.List;

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
			if(bestPosition == null || bestEval < getEvaluation(pos,_tiefe))
			{
				bestPosition = pos;
				bestEval = getEvaluation(pos,_tiefe);
			}		
		}
		
		
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
    		for(Position p : legalPositions)
    		{
    			list.add(getEvaluation(p,tiefe-1));
    		}
    	}
    	if(position.getZugrecht())
    	{
<<<<<<< Updated upstream
        return bestValueforWhite(list);
=======
    		return bestValueforWhite(list);
>>>>>>> Stashed changes
    	}
    	else
    	{
    		return bestValueForBlack(list);
    	}
    }
    
    public short bestValueforWhite(ArrayList<Short> list)
    {
		short maximum = list.get(0);
		
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
  		short minimum = list.get(0);
  		
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
