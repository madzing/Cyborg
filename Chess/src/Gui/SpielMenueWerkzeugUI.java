package Gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.FlowLayout;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class SpielMenueWerkzeugUI extends JFrame {

	private JPanel contentPane;
	public JButton _cyborgButton;
	public JButton _versusButton;
	public JButton _loadFenButton;
	public JButton _zurueckButton;
	
	/**
	 * Create the frame.
	 */
	public SpielMenueWerkzeugUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 800);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		contentPane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		JLabel spielLabel = new JLabel("Spielen", SwingConstants.CENTER);
		_zurueckButton = new JButton("Zurueck");
		Component verticalStrut = Box.createVerticalStrut(20);
		Component verticalStrut_1 = Box.createVerticalStrut(20);
		menuBar.add(verticalStrut);
		menuBar.add(spielLabel);
		menuBar.add(verticalStrut_1);
		menuBar.add(_zurueckButton);
		
		_cyborgButton = new JButton("Cyborg Schach");
		contentPane.add(_cyborgButton);
		
		_versusButton = new JButton("Versus");
		contentPane.add(_versusButton);
		
		_loadFenButton = new JButton("Load Fen");
		contentPane.add(_loadFenButton);
		
	}
	

}
