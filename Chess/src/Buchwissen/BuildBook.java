package Buchwissen;

import java.awt.EventQueue;
import java.util.ArrayList;

import Fachwerte.Fen;
import Gui.ChessGui2;
import Material.Position;
import Services.PositionCalc;
import Werkzeuge.AlphaBetaCyborg;

public class BuildBook {

	AlphaBetaCyborg _cyborg;
	PositionCalc _positionCalc;
	Position _position;
	Fen _fen;
	
	public static void main(String[] args) {
		BuildBook bla = new BuildBook();
	}
	
	public BuildBook() {
		// "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1"				StartPosition
		// "rnbqkbnr/pppppppp/8/8/2PPP3/1PN2NP1/PB1Q1PBP/3RR1K1 w kq - 0 1"             sollte gut für weiß sein

		_fen = Fen.select("rnbqkbnr/pppppppp/8/8/4P3/8/PPPP1PPP/RNBQKBNR b KQkq e3 0 1");
		_position = new Position(_fen);
		_cyborg = new AlphaBetaCyborg(7);
		_positionCalc = new PositionCalc(_position);
		
		writeBook();
	}
	
	public void writeBook()
	{
		_cyborg.getBestFollowingPosition(_position);
		ArrayList<Position> positions = _positionCalc.getLegalFollowingPositions();
		
		for (Position position : positions)
		{
			if(_cyborg.getGuteZuege().containsKey(position.getPlacement()))
			{
				System.out.println(position.getFen()+" /// "+_cyborg.getGuteZuege().get(position.getPlacement()));
			}
		}
	}

}
