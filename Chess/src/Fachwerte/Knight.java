package Fachwerte;

public class Knight implements Figur
{
    private boolean _color;
    private byte _value = 3;
    private byte _coordinate;

    public Knight(boolean color, byte coordinate)
    {
        _color = color;
        _coordinate = coordinate;
    }

    @Override
    public byte getValue()
    {
        // TODO Auto-generated method stub
        return _value;
    }

    public int[] getMovement()
    {
        return new int[] {-15, -6, 10, 17, 15, 6, -10, -17};
    }

    public boolean getColor()
    {
        return _color;
    }

    public byte getCoordinate()
    {
        return _coordinate;
    }

    public void setCoordinate(byte coordinate)
    {
        _coordinate = coordinate;
    }
}
