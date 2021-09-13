package Werkzeuge;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Gui.MenueWerkzeugUI;

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
		_ui.setSpielButton("Cyborg Schach");
		System.out.println("Test");
		
	}
	
	@Override
	public void reagiereAufAenderung() {
		// TODO Auto-generated method stub
		
	}

}
