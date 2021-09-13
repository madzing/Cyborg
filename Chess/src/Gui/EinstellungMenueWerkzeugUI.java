package Gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class EinstellungMenueWerkzeugUI extends JFrame{

	private JPanel contentPane;
	public JButton _zurueckButton;
	private String[] _cyborgSchwierigkeit;
	public JComboBox _cyborgSchwierigkeitBox;
	
	/**
	 * Create the frame.
	 */
	public EinstellungMenueWerkzeugUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 800);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		contentPane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		JLabel spielLabel = new JLabel("Einstellungen", SwingConstants.CENTER);
		_zurueckButton = new JButton("Zurueck");
		Component verticalStrut = Box.createVerticalStrut(20);
		Component verticalStrut_1 = Box.createVerticalStrut(20);
		menuBar.add(verticalStrut);
		menuBar.add(spielLabel);
		menuBar.add(verticalStrut_1);
		menuBar.add(_zurueckButton);
		
		_cyborgSchwierigkeit = new String[]{"Schwierigkeit: 1 Computer Dumm", "Schwierigkeit: 2 EZ", "Schwierigkeit: 3 I'm 3 steps ahead of you", 
				"Schwierigkeit: 4 Für den angetrunkenen Abend", "Schwierigkeit: 5 Normal", "Schwierigkeit: 6 Koennte laenger dauern", "Schwierigkeit: 7 Lieber nicht",
				"Schwierigkeit: 8 Auf eigene Gefahr", "Schwierigkeit: 9 You'll die of old age", "Schwierigkeit: 10 NOPE"};	
		_cyborgSchwierigkeitBox = new JComboBox(_cyborgSchwierigkeit);
		contentPane.add(_cyborgSchwierigkeitBox);
	}
		
}
