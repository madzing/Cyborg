package Werkzeuge;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Fachwerte.Fen;
import Gui.EinstellungMenueWerkzeugUI;
import Gui.MenueWerkzeugUI;
import Gui.SpielMenueWerkzeugUI;
import Material.Position;

public class MenueWerkzeug implements BeobachtendesWerkzeug{

	private MenueWerkzeugUI _ui;
	private int _cyborgSchwierigkeit;
	
	
	public MenueWerkzeug()
	{
		_ui = new MenueWerkzeugUI();
		_ui.setVisible(true);
		_cyborgSchwierigkeit = 5;
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
		_ui.EinstellungButton.addActionListener(new ActionListener()
			{
			@Override
			public void actionPerformed(ActionEvent e) {
				EinstellungButtonWurdeGedrueckt();
			}
			}
		);
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
					SpielWerkzeug spielui = new SpielWerkzeug(_startPosition, true, _cyborgSchwierigkeit);
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
					SpielWerkzeug spielui = new SpielWerkzeug(_startPosition, false, _cyborgSchwierigkeit);
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
					SpielWerkzeug spielui = new SpielWerkzeug(_startPosition, false, _cyborgSchwierigkeit);
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

	private void EinstellungButtonWurdeGedrueckt()
	{
		EinstellungMenueWerkzeugUI ui = new EinstellungMenueWerkzeugUI(_cyborgSchwierigkeit -1);
		ui.setVisible(true);
		_ui.setVisible(false);
		ui._zurueckButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) {
				ui.dispose();
				_ui.setVisible(true);
			}

		});
		ui._cyborgSchwierigkeitBox.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) {
				_cyborgSchwierigkeit = ui._cyborgSchwierigkeitBox.getSelectedIndex() + 1;
			}

		});
	}
	
	@Override
	public void reagiereAufAenderung() {
		// TODO Auto-generated method stub
		
	}

}
