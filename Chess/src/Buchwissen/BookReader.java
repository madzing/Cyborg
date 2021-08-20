package Buchwissen;

import java.util.ArrayList;
import java.util.HashMap;

import Fachwerte.Fen;
import Material.Position;

public class BookReader {

	HashMap <String,Position> buch  = new HashMap<String,Position>();
	
	public BookReader()
	{
	}

	public boolean istEnthalten(Position position)
	{
		
		return buch.containsKey(position.getFen());
	}
	
	public Position folgePos(Position position)
	{
		return buch.get(position.getFen());
	}
	
	
	
	
	
}
