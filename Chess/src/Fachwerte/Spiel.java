package Fachwerte;

import java.util.ArrayList;

import Material.Position;

public class Spiel {
	private final int _laengeDesSpiels;
	private final boolean _gewinner;
	private final ArrayList<Position> _positionen;
	private final ArrayList<String> _fenPositionen;
	
	public Spiel(int anzahlZuege, boolean gewinner, ArrayList<Position> positionen)
	{
		_laengeDesSpiels = anzahlZuege;
		_gewinner = gewinner; 
		_positionen = positionen;
		
		_fenPositionen = new ArrayList<String>();
			for (int i = 0; i < _positionen.size(); i++)
			{
				_fenPositionen.add(_positionen.get(i).getFen());
			}
	}

	public int getLaengeDesSpiels() {
		return _laengeDesSpiels;
	}

	public boolean getGewinner() {
		return _gewinner;
	}

	public ArrayList<Position> getPositionen() {
		return _positionen;
	}
	
	public ArrayList<String> getFens()
	{
		return _fenPositionen;
	}
	

}
