package Fachwerte;

public class Rook implements Figur
{
    private boolean _color;
    private byte _value = 5;
    private byte _coordinate;

    public Rook(boolean farbe, byte coordinate)
    {
        _color = farbe;
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
        return new int[] {-8, 1, 8, -1};
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
