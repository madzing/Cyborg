package Fachwerte;

public class Bishop implements Figur
{
    private boolean _color;
    private byte _value = 3;
    private byte _coordinate;

    public Bishop(boolean farbe, byte coordinate)
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
        return new int[] {-7, 9, 7, -9};
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
