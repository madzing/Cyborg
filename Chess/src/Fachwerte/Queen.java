package Fachwerte;

public class Queen implements Figur
{
    private boolean _color;
    private byte _value = 9;
    private byte _coordinate;

    public Queen(boolean farbe, byte coordinate)
    {
        _color = farbe;
        _coordinate = coordinate;
    }

    @Override
    public byte getValue()
    {
        return _value;
    }

    public boolean getColor()
    {
        return _color;
    }

    public byte getCoordinate()
    {
        return _coordinate;
    }

    public int[] getMovement()
    {
        return new int[] {-8, 1, 8, -1, -7, 9, 7, -9};
    }

    public void setCoordinate(byte coordinate)
    {
        _coordinate = coordinate;
    }
}
