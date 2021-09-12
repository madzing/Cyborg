package Startup;

import java.awt.EventQueue;

import Fachwerte.Fen;
//import Gui.ChessGui2;
import Material.Position;
import Werkzeuge.RekursiverCyborg;
import Werkzeuge.SpielWerkzeug;

public class startupChess {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Fen _startFen= Fen.select("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1");
					Position _startPosition = new Position(_startFen);
					new SpielWerkzeug(_startPosition, false);
//					ChessGui2 frame = new ChessGui2(_startPosition);
//					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
