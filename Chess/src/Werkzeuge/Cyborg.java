package Werkzeuge;

import Material.Position;

public class Cyborg
{
    Position _currentPosition;

    public Cyborg(Position currentPosition)
    {
        _currentPosition = currentPosition;
    }

    public Position getBestMove()
    {

        return _currentPosition;
    }

}
