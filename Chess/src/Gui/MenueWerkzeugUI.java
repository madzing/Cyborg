package Gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.FlowLayout;

public class MenueWerkzeugUI extends JFrame {

	private JPanel contentPane;
	
	public JButton SpielButton;
	public JButton EinstellungButton;
	public JButton BeendeteSpieleButton;

	/**
	 * Create the frame.
	 */
	public MenueWerkzeugUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 800);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		SpielButton = new JButton("Spielen");
		contentPane.add(SpielButton);
		
		EinstellungButton = new JButton("Einstellungen");
		contentPane.add(EinstellungButton);
		
		BeendeteSpieleButton = new JButton("Beendete Spiele");
		contentPane.add(BeendeteSpieleButton);
	}
	
	public void setSpielButton(String s)
	{
		SpielButton.setText(s);
	}
	

}
