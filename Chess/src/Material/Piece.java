package Material;

import Fachwerte.Coordinate;
import Fachwerte.PieceValue;

public abstract class Piece {
	//private boolean _color;
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

	//public abstract int[] getMovement();

	public void setCoordinate(byte coordinate) {
		_coordinate = Coordinate.select(coordinate);
	}
	
    @Override
    public int hashCode() {
    	return this.getCoordinate();
    }
    
    @Override
    public boolean equals(Object obj) {
    	return this.hashCode() == obj.hashCode();
    }
}
