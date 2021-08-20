package Buchwissen;

import Fachwerte.Fen;
import Material.Position;
import Services.PositionCalc;
import Werkzeuge.AlphaBetaCyborg;

public class BuildBook {

	AlphaBetaCyborg _cyborg;
	PositionCalc _positionCalc;
	Position _position;
	Fen _fen;
	
	public BuildBook() {
		_fen = Fen.select("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1");
		_position = new Position(_fen);
		_cyborg = new AlphaBetaCyborg(6);
		_positionCalc = new PositionCalc(_position);
		
		writeBook();
	}
	
	public void writeBook()
	{
		
	}

}
