package Werkzeuge;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Fachwerte.Fen;
import Gui.MenueWerkzeugUI;
import Gui.SpielMenueWerkzeugUI;
import Material.Position;

public class MenueWerkzeug implements BeobachtendesWerkzeug{

	private MenueWerkzeugUI _ui;
	
	
	public MenueWerkzeug()
	{
		_ui = new MenueWerkzeugUI();
		_ui.setVisible(true);
		
		registriereUIAktionen();
	}
	
	private void registriereUIAktionen()
	{
		_ui.SpielButton.addActionListener(new ActionListener()
			{
				@Override
				public void actionPerformed(ActionEvent e) {
					SpielButtonWurdeGedrueckt();
				}

			});
	}
	
	private void SpielButtonWurdeGedrueckt() 
	{
			SpielMenueWerkzeugUI ui = new SpielMenueWerkzeugUI();
			ui.setVisible(true);
			_ui.setVisible(false);
			ui._cyborgButton.addActionListener(new ActionListener()
			{
				@Override
				public void actionPerformed(ActionEvent e) {
					Fen _startFen= Fen.select("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1");
					Position _startPosition = new Position(_startFen);
					SpielWerkzeug spielui = new SpielWerkzeug(_startPosition, true);
					spielui.machSichtbar();
					ui.dispose();
				}

			});
			ui._versusButton.addActionListener(new ActionListener()
			{
				@Override
				public void actionPerformed(ActionEvent e) {
					Fen _startFen= Fen.select("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1");
					Position _startPosition = new Position(_startFen);
					SpielWerkzeug spielui = new SpielWerkzeug(_startPosition, false);
					spielui.machSichtbar();
					ui.dispose();
				}

			});
			//TODO Everything 
			ui._loadFenButton.addActionListener(new ActionListener()
			{
				@Override
				public void actionPerformed(ActionEvent e) {
					Fen _startFen= Fen.select("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1");
					Position _startPosition = new Position(_startFen);
					SpielWerkzeug spielui = new SpielWerkzeug(_startPosition, false);
					spielui.machSichtbar();
					ui.dispose();
				}

			});
			ui._zurueckButton.addActionListener(new ActionListener()
			{
				@Override
				public void actionPerformed(ActionEvent e) {
					ui.dispose();
					_ui.setVisible(true);
				}

			});
	}
	
	@Override
	public void reagiereAufAenderung() {
		// TODO Auto-generated method stub
		
	}

}
