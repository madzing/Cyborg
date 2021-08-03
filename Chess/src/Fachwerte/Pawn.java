package Fachwerte;

public class Pawn implements Figur
{
    private boolean _color;
    private byte _value = 1;
    private byte _coordinate;

    public Pawn(boolean farbe, byte coordinate)
    {
        _color = farbe;
        _coordinate = coordinate;

    }

    @Override
    public byte getValue()
    {
        return _value;
    }

    @Override
    public int[] getMovement()
    {
        if (_color)
        {
            return new int[] {-8};
        }
        else
        {
            return new int[] {8};
        }
    }

    @Override
    public boolean getColor()
    {
        return _color;
    }

    @Override
    public byte getCoordinate()
    {
        return _coordinate;
    }

    public void setCoordinate(byte coordinate)
    {
        _coordinate = coordinate;
    }
}
