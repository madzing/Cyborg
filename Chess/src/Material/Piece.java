package Material;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import Fachwerte.Coordinate;
import Fachwerte.PieceValue;

public abstract class Piece {
	private boolean _color;
	private PieceValue _value;
	private Coordinate _coordinate; 

	public Piece (byte coordinate) {
		setCoordinate(coordinate);
	}
	
	public byte getValue() {
		return _value.getValue();
	}

	public byte getCoordinate() {
		return _coordinate.getCoordinate();
	}

	public abstract byte[] getMovement();

	public void setCoordinate(byte coordinate) {
		_coordinate = Coordinate.select(coordinate);
	}
	
	public  ArrayList<Byte>  getMoves(Map<Byte, Piece> figurenAmZug, Map<Byte, Piece> figurenDesGegners, byte enPassant)
	{
    	ArrayList<Byte> list = new ArrayList<Byte>();
		for(int i = 0; i < getMovement().length;i++)
		{
			for(int j = 1 ; j <= 7; j++)
			{
				if(getCoordinate() + getMovement()[i]*j >=0 && getCoordinate() + getMovement()[i]*j <=63)
				{
					if(SprungUeberKante(getCoordinate()+getMovement()[i]*(j-1),getCoordinate()+getMovement()[i]*j))
					{
						break;
					}
					if (figurenDesGegners.containsKey((byte)(getCoordinate() + getMovement()[i]*j)))
					{
						list.add(((byte) (getCoordinate()+getMovement()[i]*j)));
						break;
					}
					
					else if( figurenAmZug.containsKey((byte)(getCoordinate()+ getMovement()[i]*j)))
					{
						break;
					}
					else
					{
						list.add((byte)(getCoordinate()+getMovement()[i]*j));
					}
				}
				else
				{
					break;
				}
			}
		}
		return list;
	}
	
    @Override
    public int hashCode() {
    	return this.getCoordinate();
    }
    
    @Override
    public boolean equals(Object obj) {
    	return this.hashCode() == obj.hashCode();
    }
    
    
    public  boolean SprungUeberKante(int alteFigurPos,int neueFigurPos)
	{
		return Math.abs((alteFigurPos % 8)-(neueFigurPos % 8))>2 ||  Math.abs((alteFigurPos / 8)-(neueFigurPos / 8))>2;
	}
}
