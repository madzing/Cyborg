package Fachwerte;

public class King implements Figur
{
    private boolean _color;
    private final byte _value = 127;
    private byte _coordinate;

    public King(boolean color, byte coordinate)
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
        return new int[] {-8, 1, 8, -1, -7, 9, 7, -9};
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
