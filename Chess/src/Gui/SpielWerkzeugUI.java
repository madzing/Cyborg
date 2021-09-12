package Gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Fachwerte.Fen;
import Material.Bishop;
import Material.King;
import Material.Knight;
import Material.Pawn;
import Material.Piece;
import Material.Position;
import Material.Queen;
import Material.Rook;
import Services.PositionCalc;
import Services.PositionsVergleicher;
import Werkzeuge.RekursiverCyborg;
import Werkzeuge.SpielWerkzeug;
import Werkzeuge.Cyborg;

import java.awt.BorderLayout;
import javax.swing.JCheckBox;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;

public class SpielWerkzeugUI extends JFrame{
	Position _position;
	Map<Byte, Piece> _whiteFiguren;
	Map<Byte, Piece> _blackFiguren;
	public List<JButton> _spielButtons;
	ArrayList<Position> _positions;
	List<JLabel> _geschlageneWhiteFiguren;
	List<JLabel> _geschlageneBlackFiguren;
	public int _letzterGedrueckterButton;
	public int _gedrueckterButton;
	int _aktuellerZug;
	JLabel _alteKoordinateLabel;
	JLabel _neueKoordinateLabel;
	public JButton _makeMoveButton;
	public JButton _reverseButton;
	private JPanel contentPane;
	public JButton _btnGetAktuelleFen;
	private JLabel _zugrechtLabel;
	private JToggleButton _CyborgButton;
	public JComboBox _schwierigkeitComboBox; 
	List <String> _spalte;
	List <String> _zeile;
	int b = 0;
	int n = 0;
	int r = 0;
	int p = 0;
	int B = 0;
	int N = 0;
	int R = 0;
	int P = 0;
	int _cyborgSchwierigkeit;
	public Stack<int[]> _cyborgHighlightStack = new Stack<int[]>();
	String _comboBoxListe[];
	

	/**
	 * Create the frame.
	 */
	public SpielWerkzeugUI(Position position) {
		_position = position;
		_whiteFiguren = position.getWhiteFiguren();
		_blackFiguren = position.getBlackFiguren();
		_spielButtons = new ArrayList<JButton>(64);
		_geschlageneWhiteFiguren = new ArrayList<JLabel>(16);
		_geschlageneBlackFiguren = new ArrayList<JLabel>(16);
		_letzterGedrueckterButton = 0;
		_gedrueckterButton = 0;
		_spalte = new ArrayList<String>(8);
		_zeile = new ArrayList<String>(8);
		_cyborgSchwierigkeit = 5;
		_comboBoxListe = new String[]{"Schwierigkeit: 1", "Schwierigkeit: 2", "Schwierigkeit: 3", 
		"Schwierigkeit: 4", "Schwierigkeit: 5", "Schwierigkeit: 6 Koennte laenger dauern", "Schwierigkeit: 7 Lieber nicht",
		"Schwierigkeit: 8 Auf eigene Gefahr", "Schwierigkeit: 9 You'll die of old age", "Schwierigkeit: 10 NOPE"};		
		befuelleZeileSpalte();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 800);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		_alteKoordinateLabel = new JLabel("Alte Koordinate");
		menuBar.add(_alteKoordinateLabel);

		Component verticalStrut = Box.createVerticalStrut(20);
		menuBar.add(verticalStrut);

		_neueKoordinateLabel = new JLabel("Neue Koordinate");
		menuBar.add(_neueKoordinateLabel);

		Component verticalStrut_1 = Box.createVerticalStrut(20);
		menuBar.add(verticalStrut_1);

		_schwierigkeitComboBox = new JComboBox(_comboBoxListe);
		menuBar.add(_schwierigkeitComboBox);
		_schwierigkeitComboBox.setSelectedIndex(4);
		
		getContentPane().setLayout(null);
		
		contentPane = new JPanel();
		contentPane.setBounds(10, 0, 764, 736);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(Color.WHITE);
		getContentPane().add(contentPane);
		contentPane.setLayout(new GridLayout(8, 8));

		_zugrechtLabel = new JLabel("Zugrecht Label", SwingConstants.CENTER);
		_zugrechtLabel.setBounds(784, 315, 190, 92);
		getContentPane().add(_zugrechtLabel);

		_btnGetAktuelleFen = new JButton("Get Aktuelle Fen");
		_btnGetAktuelleFen.setBounds(784, 60, 190, 45);
		getContentPane().add(_btnGetAktuelleFen);

		_CyborgButton = new JToggleButton("Automatisch");
		_CyborgButton.setBounds(784, 10, 190, 45);
		getContentPane().add(_CyborgButton);

		_makeMoveButton = new JButton("Make Move");
		_makeMoveButton.setBounds(784, 680, 190, 45);
		getContentPane().add(_makeMoveButton);
		
		_reverseButton = new JButton("Reverse");
		_reverseButton.setBounds(784, 630, 190, 45);
		getContentPane().add(_reverseButton);		
						
		createButtons();
		setFiguren(_whiteFiguren, _blackFiguren);
		setZugrechtLabel();
		createGeschlageneFigurLabels();
	}


	/* TODO
	 * Waere noch nice:
	 * -Zugrecht Feld displayed Gewinner
	 */

	public void setPosition(Position position)
	{
		_position = position;
	}
	
	public void setPositions(ArrayList<Position> positions)
	{
		_positions = positions;
	}
	
	public void setAktuellerZug(int aktuellerZug) 
	{
		_aktuellerZug = aktuellerZug;		
	}

	public void resetteFelder()
	{
		/*
		 * Methode um die Spiel Feld Knoepfe 0 bis 63 ihre original Farben zuzuweisen.
		 * Nuetzlich fuer das schnelle Resetten, nachdem ein Zug (z.B. vom Cyborg) highlighted wurde.
		 */
		_spielButtons.get(0).setBackground(Color.WHITE);
		_spielButtons.get(1).setBackground(Color.BLACK);
		_spielButtons.get(2).setBackground(Color.WHITE);
		_spielButtons.get(3).setBackground(Color.BLACK);
		_spielButtons.get(4).setBackground(Color.WHITE);
		_spielButtons.get(5).setBackground(Color.BLACK);
		_spielButtons.get(6).setBackground(Color.WHITE);
		_spielButtons.get(7).setBackground(Color.BLACK);
		_spielButtons.get(8).setBackground(Color.BLACK);
		_spielButtons.get(9).setBackground(Color.WHITE);
		_spielButtons.get(10).setBackground(Color.BLACK);
		_spielButtons.get(11).setBackground(Color.WHITE);
		_spielButtons.get(12).setBackground(Color.BLACK);
		_spielButtons.get(13).setBackground(Color.WHITE);
		_spielButtons.get(14).setBackground(Color.BLACK);
		_spielButtons.get(15).setBackground(Color.WHITE);
		_spielButtons.get(16).setBackground(Color.WHITE);
		_spielButtons.get(17).setBackground(Color.BLACK);
		_spielButtons.get(18).setBackground(Color.WHITE);
		_spielButtons.get(19).setBackground(Color.BLACK);
		_spielButtons.get(20).setBackground(Color.WHITE);
		_spielButtons.get(21).setBackground(Color.BLACK);
		_spielButtons.get(22).setBackground(Color.WHITE);
		_spielButtons.get(23).setBackground(Color.BLACK);
		_spielButtons.get(24).setBackground(Color.BLACK);
		_spielButtons.get(25).setBackground(Color.WHITE);
		_spielButtons.get(26).setBackground(Color.BLACK);
		_spielButtons.get(27).setBackground(Color.WHITE);
		_spielButtons.get(28).setBackground(Color.BLACK);
		_spielButtons.get(29).setBackground(Color.WHITE);
		_spielButtons.get(30).setBackground(Color.BLACK);
		_spielButtons.get(31).setBackground(Color.WHITE);
		_spielButtons.get(32).setBackground(Color.WHITE);
		_spielButtons.get(33).setBackground(Color.BLACK);
		_spielButtons.get(34).setBackground(Color.WHITE);
		_spielButtons.get(35).setBackground(Color.BLACK);
		_spielButtons.get(36).setBackground(Color.WHITE);
		_spielButtons.get(37).setBackground(Color.BLACK);
		_spielButtons.get(38).setBackground(Color.WHITE);
		_spielButtons.get(39).setBackground(Color.BLACK);
		_spielButtons.get(40).setBackground(Color.BLACK);
		_spielButtons.get(41).setBackground(Color.WHITE);
		_spielButtons.get(42).setBackground(Color.BLACK);
		_spielButtons.get(43).setBackground(Color.WHITE);
		_spielButtons.get(44).setBackground(Color.BLACK);
		_spielButtons.get(45).setBackground(Color.WHITE);
		_spielButtons.get(46).setBackground(Color.BLACK);
		_spielButtons.get(47).setBackground(Color.WHITE);
		_spielButtons.get(48).setBackground(Color.WHITE);
		_spielButtons.get(49).setBackground(Color.BLACK);
		_spielButtons.get(50).setBackground(Color.WHITE);
		_spielButtons.get(51).setBackground(Color.BLACK);
		_spielButtons.get(52).setBackground(Color.WHITE);
		_spielButtons.get(53).setBackground(Color.BLACK);
		_spielButtons.get(54).setBackground(Color.WHITE);
		_spielButtons.get(55).setBackground(Color.BLACK);
		_spielButtons.get(56).setBackground(Color.BLACK);
		_spielButtons.get(57).setBackground(Color.WHITE);
		_spielButtons.get(58).setBackground(Color.BLACK);
		_spielButtons.get(59).setBackground(Color.WHITE);
		_spielButtons.get(60).setBackground(Color.BLACK);
		_spielButtons.get(61).setBackground(Color.WHITE);
		_spielButtons.get(62).setBackground(Color.BLACK);
		_spielButtons.get(63).setBackground(Color.WHITE);
	}

	public void setFiguren(Map<Byte, Piece> whiteFiguren, Map<Byte, Piece> blackFiguren) 
	{
		/*
		 * Methode die im Konstruktor und nach jedem Spielzug aufgerufen wird.
		 * Die Spiel Feld Buttons werden zuerst alle von Icons gecleart um dann alle Icons neu zu setzen.
		 * Danach werden die weissen Spielfiguren aus der Position nacheinander durchgegangen und auf ihre Piece Zugehoerigkeit ueberprueft, danach
		 * an die entsprechende Koordinate gesetzt.
		 * Aequivalent fuer Schwarz.
		 */
		for (JButton b: _spielButtons)
		{
			b.setIcon(null);
		}
		for (Map.Entry<Byte, Piece> whiteFigur : whiteFiguren.entrySet())
		{
			int whiteCoordinate= (int) whiteFigur.getValue().getCoordinate();
			if(whiteFigur.getValue() instanceof Pawn)
    		{
    			_spielButtons.get(whiteCoordinate).setIcon(new ImageIcon(SpielWerkzeugUI.class.getResource("/Piece_Images/White_Pawn.png")));
    		}
    		if(whiteFigur.getValue() instanceof Knight)
    		{
    			_spielButtons.get(whiteCoordinate).setIcon(new ImageIcon(SpielWerkzeugUI.class.getResource("/Piece_Images/White_Knight.png")));
    		}
    		if(whiteFigur.getValue() instanceof Bishop)
    		{
    			_spielButtons.get(whiteCoordinate).setIcon(new ImageIcon(SpielWerkzeugUI.class.getResource("/Piece_Images/White_Bishop.png")));
    		}
    		if(whiteFigur.getValue() instanceof Rook)
    		{
    			_spielButtons.get(whiteCoordinate).setIcon(new ImageIcon(SpielWerkzeugUI.class.getResource("/Piece_Images/White_Rook.png")));
    		}
    		if(whiteFigur.getValue() instanceof Queen)
    		{
    			_spielButtons.get(whiteCoordinate).setIcon(new ImageIcon(SpielWerkzeugUI.class.getResource("/Piece_Images/White_Queen.png")));
    		}
    		if(whiteFigur.getValue() instanceof King)
    		{
    			_spielButtons.get(whiteCoordinate).setIcon(new ImageIcon(SpielWerkzeugUI.class.getResource("/Piece_Images/White_King.png")));
    		}

    	for (Map.Entry<Byte, Piece> blackFigur : blackFiguren.entrySet())
    	{
    		int blackCoordinate= (int) blackFigur.getValue().getCoordinate();
    		if(blackFigur.getValue() instanceof Pawn)
        	{
        		_spielButtons.get(blackCoordinate).setIcon(new ImageIcon(SpielWerkzeugUI.class.getResource("/Piece_Images/Black_Pawn.png")));
        	}
        	if(blackFigur.getValue() instanceof Knight)
        	{
       			_spielButtons.get(blackCoordinate).setIcon(new ImageIcon(SpielWerkzeugUI.class.getResource("/Piece_Images/Black_Knight.png")));
       		}
       		if(blackFigur.getValue() instanceof Bishop)
       		{
       			_spielButtons.get(blackCoordinate).setIcon(new ImageIcon(SpielWerkzeugUI.class.getResource("/Piece_Images/Black_Bishop.png")));
       		}
       		if(blackFigur.getValue() instanceof Rook)
       		{        			
       			_spielButtons.get(blackCoordinate).setIcon(new ImageIcon(SpielWerkzeugUI.class.getResource("/Piece_Images/Black_Rook.png")));
        	}
        	if(blackFigur.getValue() instanceof Queen)
        	{
        		_spielButtons.get(blackCoordinate).setIcon(new ImageIcon(SpielWerkzeugUI.class.getResource("/Piece_Images/Black_Queen.png")));
        	}
        	if(blackFigur.getValue() instanceof King)
        	{
        		_spielButtons.get(blackCoordinate).setIcon(new ImageIcon(SpielWerkzeugUI.class.getResource("/Piece_Images/Black_King.png")));
        	}
    	}
	}
}

	private void createButtons() {
		/*
		 * Methode um _spielButtons als Spiel Feld Buttons zu initialisieren.
		 * Bisher alles haendisch.
		 */
		JButton btnNewButton = new JButton("a8");
		btnNewButton.setBackground(Color.WHITE);
		contentPane.add(btnNewButton);
		_spielButtons.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("b8");
		btnNewButton_1.setBackground(Color.BLACK);
		contentPane.add(btnNewButton_1);
		_spielButtons.add(btnNewButton_1);
		btnNewButton_1.setForeground(Color.WHITE);

		JButton btnNewButton_2 = new JButton("c8");
		btnNewButton_2.setBackground(Color.WHITE);
		contentPane.add(btnNewButton_2);
		_spielButtons.add(btnNewButton_2);

		JButton btnNewButton_3 = new JButton("d8");
		btnNewButton_3.setBackground(Color.BLACK);
		contentPane.add(btnNewButton_3);
		_spielButtons.add(btnNewButton_3);
		btnNewButton_3.setForeground(Color.WHITE);

		JButton btnNewButton_4 = new JButton("e8");
		btnNewButton_4.setBackground(Color.WHITE);
		contentPane.add(btnNewButton_4);
		_spielButtons.add(btnNewButton_4);

		JButton btnNewButton_5 = new JButton("f8");
		btnNewButton_5.setBackground(Color.BLACK);
		contentPane.add(btnNewButton_5);
		_spielButtons.add(btnNewButton_5);
		btnNewButton_5.setForeground(Color.WHITE);

		JButton btnNewButton_6 = new JButton("g8");
		btnNewButton_6.setBackground(Color.WHITE);
		contentPane.add(btnNewButton_6);
		_spielButtons.add(btnNewButton_6);

		JButton btnNewButton_7 = new JButton("h8");
		btnNewButton_7.setBackground(Color.BLACK);
		contentPane.add(btnNewButton_7);
		_spielButtons.add(btnNewButton_7);
		btnNewButton_7.setForeground(Color.WHITE);

		JButton btnNewButton_8 = new JButton("a7");
		btnNewButton_8.setBackground(Color.BLACK);
		contentPane.add(btnNewButton_8);
		_spielButtons.add(btnNewButton_8);
		btnNewButton_8.setForeground(Color.WHITE);

		JButton btnNewButton_9 = new JButton("b7");
		btnNewButton_9.setBackground(Color.WHITE);
		contentPane.add(btnNewButton_9);
		_spielButtons.add(btnNewButton_9);

		JButton btnNewButton_10 = new JButton("c7");
		btnNewButton_10.setBackground(Color.BLACK);
		contentPane.add(btnNewButton_10);
		_spielButtons.add(btnNewButton_10);
		btnNewButton_10.setForeground(Color.WHITE);

		JButton btnNewButton_11 = new JButton("d7");
		btnNewButton_11.setBackground(Color.WHITE);
		contentPane.add(btnNewButton_11);
		_spielButtons.add(btnNewButton_11);

		JButton btnNewButton_12 = new JButton("e7");
		btnNewButton_12.setBackground(Color.BLACK);
		contentPane.add(btnNewButton_12);
		_spielButtons.add(btnNewButton_12);
		btnNewButton_12.setForeground(Color.WHITE);

		JButton btnNewButton_13 = new JButton("f7");
		btnNewButton_13.setBackground(Color.WHITE);
		contentPane.add(btnNewButton_13);
		_spielButtons.add(btnNewButton_13);

		JButton btnNewButton_14 = new JButton("g7");
		btnNewButton_14.setBackground(Color.BLACK);
		contentPane.add(btnNewButton_14);
		_spielButtons.add(btnNewButton_14);
		btnNewButton_14.setForeground(Color.WHITE);

		JButton btnNewButton_15 = new JButton("h7");
		btnNewButton_15.setBackground(Color.WHITE);
		contentPane.add(btnNewButton_15);
		_spielButtons.add(btnNewButton_15);

		JButton btnNewButton_16 = new JButton("a6");
		btnNewButton_16.setBackground(Color.WHITE);
		contentPane.add(btnNewButton_16);
		_spielButtons.add(btnNewButton_16);

		JButton btnNewButton_17 = new JButton("b6");
		btnNewButton_17.setBackground(Color.BLACK);
		contentPane.add(btnNewButton_17);
		_spielButtons.add(btnNewButton_17);
		btnNewButton_17.setForeground(Color.WHITE);

		JButton btnNewButton_18 = new JButton("c6");
		btnNewButton_18.setBackground(Color.WHITE);
		contentPane.add(btnNewButton_18);
		_spielButtons.add(btnNewButton_18);

		JButton btnNewButton_19 = new JButton("d6");
		btnNewButton_19.setBackground(Color.BLACK);
		contentPane.add(btnNewButton_19);
		_spielButtons.add(btnNewButton_19);
		btnNewButton_19.setForeground(Color.WHITE);

		JButton btnNewButton_20 = new JButton("e6");
		btnNewButton_20.setBackground(Color.WHITE);
		contentPane.add(btnNewButton_20);
		_spielButtons.add(btnNewButton_20);

		JButton btnNewButton_21 = new JButton("f6");
		btnNewButton_21.setBackground(Color.BLACK);
		contentPane.add(btnNewButton_21);
		_spielButtons.add(btnNewButton_21);
		btnNewButton_21.setForeground(Color.WHITE);

		JButton btnNewButton_22 = new JButton("g6");
		btnNewButton_22.setBackground(Color.WHITE);
		contentPane.add(btnNewButton_22);
		_spielButtons.add(btnNewButton_22);

		JButton btnNewButton_23 = new JButton("h6");
		btnNewButton_23.setBackground(Color.BLACK);
		contentPane.add(btnNewButton_23);
		_spielButtons.add(btnNewButton_23);
		btnNewButton_23.setForeground(Color.WHITE);

		JButton btnNewButton_24 = new JButton("a5");
		btnNewButton_24.setBackground(Color.BLACK);
		contentPane.add(btnNewButton_24);
		_spielButtons.add(btnNewButton_24);
		btnNewButton_24.setForeground(Color.WHITE);

		JButton btnNewButton_25 = new JButton("b5");
		btnNewButton_25.setBackground(Color.WHITE);
		contentPane.add(btnNewButton_25);
		_spielButtons.add(btnNewButton_25);

		JButton btnNewButton_26 = new JButton("c5");
		btnNewButton_26.setBackground(Color.BLACK);
		contentPane.add(btnNewButton_26);
		_spielButtons.add(btnNewButton_26);
		btnNewButton_26.setForeground(Color.WHITE);

		JButton btnNewButton_27 = new JButton("d5");
		btnNewButton_27.setBackground(Color.WHITE);
		contentPane.add(btnNewButton_27);
		_spielButtons.add(btnNewButton_27);

		JButton btnNewButton_28 = new JButton("e5");
		btnNewButton_28.setBackground(Color.BLACK);
		contentPane.add(btnNewButton_28);
		_spielButtons.add(btnNewButton_28);
		btnNewButton_28.setForeground(Color.WHITE);

		JButton btnNewButton_29 = new JButton("f5");
		btnNewButton_29.setBackground(Color.WHITE);
		contentPane.add(btnNewButton_29);
		_spielButtons.add(btnNewButton_29);

		JButton btnNewButton_30 = new JButton("g5");
		btnNewButton_30.setBackground(Color.BLACK);
		contentPane.add(btnNewButton_30);
		_spielButtons.add(btnNewButton_30);
		btnNewButton_30.setForeground(Color.WHITE);

		JButton btnNewButton_31 = new JButton("h5");
		btnNewButton_31.setBackground(Color.WHITE);
		contentPane.add(btnNewButton_31);
		_spielButtons.add(btnNewButton_31);

		JButton btnNewButton_32 = new JButton("a4");
		btnNewButton_32.setBackground(Color.WHITE);
		contentPane.add(btnNewButton_32);
		_spielButtons.add(btnNewButton_32);

		JButton btnNewButton_33 = new JButton("b4");
		btnNewButton_33.setBackground(Color.BLACK);
		contentPane.add(btnNewButton_33);
		_spielButtons.add(btnNewButton_33);
		btnNewButton_33.setForeground(Color.WHITE);

		JButton btnNewButton_34 = new JButton("c4");
		btnNewButton_34.setBackground(Color.WHITE);
		contentPane.add(btnNewButton_34);
		_spielButtons.add(btnNewButton_34);

		JButton btnNewButton_35 = new JButton("d4");
		btnNewButton_35.setBackground(Color.BLACK);
		contentPane.add(btnNewButton_35);
		_spielButtons.add(btnNewButton_35);
		btnNewButton_35.setForeground(Color.WHITE);

		JButton btnNewButton_36 = new JButton("e4");
		btnNewButton_36.setBackground(Color.WHITE);
		contentPane.add(btnNewButton_36);
		_spielButtons.add(btnNewButton_36);

		JButton btnNewButton_37 = new JButton("f4");
		btnNewButton_37.setBackground(Color.BLACK);
		contentPane.add(btnNewButton_37);
		_spielButtons.add(btnNewButton_37);
		btnNewButton_37.setForeground(Color.WHITE);

		JButton btnNewButton_38 = new JButton("g4");
		btnNewButton_38.setBackground(Color.WHITE);
		contentPane.add(btnNewButton_38);
		_spielButtons.add(btnNewButton_38);

		JButton btnNewButton_39 = new JButton("h4");
		btnNewButton_39.setBackground(Color.BLACK);
		contentPane.add(btnNewButton_39);
		_spielButtons.add(btnNewButton_39);
		btnNewButton_39.setForeground(Color.WHITE);

		JButton btnNewButton_40 = new JButton("a3");
		btnNewButton_40.setBackground(Color.BLACK);
		contentPane.add(btnNewButton_40);
		_spielButtons.add(btnNewButton_40);
		btnNewButton_40.setForeground(Color.WHITE);

		JButton btnNewButton_41 = new JButton("b3");
		btnNewButton_41.setBackground(Color.WHITE);
		contentPane.add(btnNewButton_41);
		_spielButtons.add(btnNewButton_41);

		JButton btnNewButton_42 = new JButton("c3");
		btnNewButton_42.setBackground(Color.BLACK);
		contentPane.add(btnNewButton_42);
		_spielButtons.add(btnNewButton_42);
		btnNewButton_42.setForeground(Color.WHITE);

		JButton btnNewButton_43 = new JButton("d3");
		btnNewButton_43.setBackground(Color.WHITE);
		contentPane.add(btnNewButton_43);
		_spielButtons.add(btnNewButton_43);

		JButton btnNewButton_44 = new JButton("e3");
		btnNewButton_44.setBackground(Color.BLACK);
		contentPane.add(btnNewButton_44);
		_spielButtons.add(btnNewButton_44);
		btnNewButton_44.setForeground(Color.WHITE);

		JButton btnNewButton_45 = new JButton("f3");
		btnNewButton_45.setBackground(Color.WHITE);
		contentPane.add(btnNewButton_45);
		_spielButtons.add(btnNewButton_45);

		JButton btnNewButton_46 = new JButton("g3");
		btnNewButton_46.setBackground(Color.BLACK);
		contentPane.add(btnNewButton_46);
		_spielButtons.add(btnNewButton_46);
		btnNewButton_46.setForeground(Color.WHITE);

		JButton btnNewButton_47 = new JButton("h3");
		btnNewButton_47.setBackground(Color.WHITE);
		contentPane.add(btnNewButton_47);
		_spielButtons.add(btnNewButton_47);

		JButton btnNewButton_48 = new JButton("a2");
		btnNewButton_48.setBackground(Color.WHITE);
		contentPane.add(btnNewButton_48);
		_spielButtons.add(btnNewButton_48);

		JButton btnNewButton_49 = new JButton("b2");
		btnNewButton_49.setBackground(Color.BLACK);
		contentPane.add(btnNewButton_49);
		_spielButtons.add(btnNewButton_49);
		btnNewButton_49.setForeground(Color.WHITE);

		JButton btnNewButton_50 = new JButton("c2");
		btnNewButton_50.setBackground(Color.WHITE);
		contentPane.add(btnNewButton_50);
		_spielButtons.add(btnNewButton_50);

		JButton btnNewButton_51 = new JButton("d2");
		btnNewButton_51.setBackground(Color.BLACK);
		contentPane.add(btnNewButton_51);
		_spielButtons.add(btnNewButton_51);
		btnNewButton_51.setForeground(Color.WHITE);

		JButton btnNewButton_52 = new JButton("e2");
		btnNewButton_52.setBackground(Color.WHITE);
		contentPane.add(btnNewButton_52);
		_spielButtons.add(btnNewButton_52);

		JButton btnNewButton_53 = new JButton("f2");
		btnNewButton_53.setBackground(Color.BLACK);
		contentPane.add(btnNewButton_53);
		_spielButtons.add(btnNewButton_53);
		btnNewButton_53.setForeground(Color.WHITE);

		JButton btnNewButton_54 = new JButton("g2");
		btnNewButton_54.setBackground(Color.WHITE);
		contentPane.add(btnNewButton_54);
		_spielButtons.add(btnNewButton_54);

		JButton btnNewButton_55 = new JButton("h2");
		btnNewButton_55.setBackground(Color.BLACK);
		contentPane.add(btnNewButton_55);
		_spielButtons.add(btnNewButton_55);
		btnNewButton_55.setForeground(Color.WHITE);

		JButton btnNewButton_56 = new JButton("a1");
		btnNewButton_56.setBackground(Color.BLACK);
		contentPane.add(btnNewButton_56);
		_spielButtons.add(btnNewButton_56);
		btnNewButton_56.setForeground(Color.WHITE);

		JButton btnNewButton_57 = new JButton("b1");
		btnNewButton_57.setBackground(Color.WHITE);
		contentPane.add(btnNewButton_57);
		_spielButtons.add(btnNewButton_57);

		JButton btnNewButton_58 = new JButton("c1");
		btnNewButton_58.setBackground(Color.BLACK);
		contentPane.add(btnNewButton_58);
		_spielButtons.add(btnNewButton_58);
		btnNewButton_58.setForeground(Color.WHITE);

		JButton btnNewButton_59 = new JButton("d1");
		btnNewButton_59.setBackground(Color.WHITE);
		contentPane.add(btnNewButton_59);
		_spielButtons.add(btnNewButton_59);

		JButton btnNewButton_60 = new JButton("e1");
		btnNewButton_60.setBackground(Color.BLACK);
		contentPane.add(btnNewButton_60);
		_spielButtons.add(btnNewButton_60);
		btnNewButton_60.setForeground(Color.WHITE);

		JButton btnNewButton_61 = new JButton("f1");
		btnNewButton_61.setBackground(Color.WHITE);
		contentPane.add(btnNewButton_61);
		_spielButtons.add(btnNewButton_61);

		JButton btnNewButton_62 = new JButton("g1");
		btnNewButton_62.setBackground(Color.BLACK);
		contentPane.add(btnNewButton_62);
		_spielButtons.add(btnNewButton_62);
		btnNewButton_62.setForeground(Color.WHITE);

		JButton btnNewButton_63 = new JButton("h1");
		btnNewButton_63.setBackground(Color.WHITE);
		contentPane.add(btnNewButton_63);
		_spielButtons.add(btnNewButton_63);
	}

	private void createGeschlageneFigurLabels()
	{
		/*
		 * Methode um die kleinen Labels an der Seite fï¿½r die geschlagenen Figuren zu initialisieren.
		 * Die Werte sind alle hï¿½ndisch eingetragen und hï¿½ndisch ermittelt, sowie auf addiert.
		 * Zusï¿½tzlich werden die Labels zu beginn alle Unsichtbar eingetragen, so dass sie nurnoch sichtbar
		 * geschaltet werden mï¿½ssen, wenn eine Figur geschlagen wurde.
		 * 
		 * Die Labels werden dabei in zwei Listen gespeichert:
		 * _geschlageneWhiteFiguren und
		 * _geschlageneBlackFiguren
		 */
		JLabel whitePawn1 = new JLabel("P");
		whitePawn1.setBounds(790, 550, 23, 25);
		_geschlageneWhiteFiguren.add(whitePawn1);
		JLabel whitePawn2 = new JLabel("P");
		whitePawn2.setBounds(813, 550, 23, 25);
		_geschlageneWhiteFiguren.add(whitePawn2);
		JLabel whitePawn3 = new JLabel("P");
		whitePawn3.setBounds(836, 550, 23, 25);
		_geschlageneWhiteFiguren.add(whitePawn3);
		JLabel whitePawn4 = new JLabel("P");
		whitePawn4.setBounds(859, 550, 23, 25);
		_geschlageneWhiteFiguren.add(whitePawn4);
		JLabel whitePawn5 = new JLabel("P");
		whitePawn5.setBounds(882, 550, 23, 25);
		_geschlageneWhiteFiguren.add(whitePawn5);
		JLabel whitePawn6 = new JLabel("P");
		whitePawn6.setBounds(905, 550, 23, 25);
		_geschlageneWhiteFiguren.add(whitePawn6);
		JLabel whitePawn7 = new JLabel("P");
		whitePawn7.setBounds(928, 550, 23, 25);
		_geschlageneWhiteFiguren.add(whitePawn7);
		JLabel whitePawn8 = new JLabel("P");
		whitePawn8.setBounds(951, 550, 23, 25);
		_geschlageneWhiteFiguren.add(whitePawn8);
		
		JLabel whiteRook1 = new JLabel("R");
		whiteRook1.setBounds(790, 575, 23, 25);
		_geschlageneWhiteFiguren.add(whiteRook1);
		JLabel whiteKnight1 = new JLabel("N");
		whiteKnight1.setBounds(813, 575, 23, 25);
		_geschlageneWhiteFiguren.add(whiteKnight1);
		JLabel whiteBishop1 = new JLabel("B");
		whiteBishop1.setBounds(836, 575, 23, 25);
		_geschlageneWhiteFiguren.add(whiteBishop1);
		JLabel whiteQueen = new JLabel("Q");
		whiteQueen.setBounds(859, 575, 23, 25);
		_geschlageneWhiteFiguren.add(whiteQueen);
		JLabel whiteKing = new JLabel("K");
		whiteKing.setBounds(882, 575, 23, 25);
		_geschlageneWhiteFiguren.add(whiteKing);
		JLabel whiteBishop2 = new JLabel("B");
		whiteBishop2.setBounds(905, 575, 23, 25);
		_geschlageneWhiteFiguren.add(whiteBishop2);
		JLabel whiteKnight2 = new JLabel("N");
		whiteKnight2.setBounds(928, 575, 23, 25);
		_geschlageneWhiteFiguren.add(whiteKnight2);
		JLabel whiteRook2 = new JLabel("R");
		whiteRook2.setBounds(951, 575, 23, 25);
		_geschlageneWhiteFiguren.add(whiteRook2);
		
		for(int i = 0; i<16;i++)
		{
			getContentPane().add(_geschlageneWhiteFiguren.get(i));
			_geschlageneWhiteFiguren.get(i).setVisible(false);
		}
		
		JLabel blackPawn1 = new JLabel("p");
		blackPawn1.setBounds(790, 160, 23, 25);
		_geschlageneBlackFiguren.add(blackPawn1);
		JLabel blackPawn2 = new JLabel("p");
		blackPawn2.setBounds(813, 160, 23, 25);
		_geschlageneBlackFiguren.add(blackPawn2);
		JLabel blackPawn3 = new JLabel("p");
		blackPawn3.setBounds(836, 160, 23, 25);
		_geschlageneBlackFiguren.add(blackPawn3);
		JLabel blackPawn4 = new JLabel("p");
		blackPawn4.setBounds(859, 160, 23, 25);
		_geschlageneBlackFiguren.add(blackPawn4);
		JLabel blackPawn5 = new JLabel("p");
		blackPawn5.setBounds(882, 160, 23, 25);
		_geschlageneBlackFiguren.add(blackPawn5);
		JLabel blackPawn6 = new JLabel("p");
		blackPawn6.setBounds(905, 160, 23, 25);
		_geschlageneBlackFiguren.add(blackPawn6);
		JLabel blackPawn7 = new JLabel("p");
		blackPawn7.setBounds(928, 160, 23, 25);
		_geschlageneBlackFiguren.add(blackPawn7);
		JLabel blackPawn8 = new JLabel("p");
		blackPawn8.setBounds(951, 160, 23, 25);
		_geschlageneBlackFiguren.add(blackPawn8);
		
		JLabel blackRook1 = new JLabel("r");
		blackRook1.setBounds(790, 135, 23, 25);
		_geschlageneBlackFiguren.add(blackRook1);
		JLabel blackKnight1 = new JLabel("n");
		blackKnight1.setBounds(813, 135, 23, 25);
		_geschlageneBlackFiguren.add(blackKnight1);
		JLabel blackBishop1 = new JLabel("b");
		blackBishop1.setBounds(836, 135, 23, 25);
		_geschlageneBlackFiguren.add(blackBishop1);
		JLabel blackQueen = new JLabel("q");
		blackQueen.setBounds(859, 135, 23, 25);
		_geschlageneBlackFiguren.add(blackQueen);
		JLabel blackKing = new JLabel("k");
		blackKing.setBounds(882, 135, 23, 25);
		_geschlageneBlackFiguren.add(blackKing);
		JLabel blackBishop2 = new JLabel("b");
		blackBishop2.setBounds(908, 135, 23, 25);
		_geschlageneBlackFiguren.add(blackBishop2);
		JLabel blackKnight2 = new JLabel("n");
		blackKnight2.setBounds(928, 135, 23, 25);
		_geschlageneBlackFiguren.add(blackKnight2);
		JLabel blackRook2 = new JLabel("r");
		blackRook2.setBounds(951, 135, 23, 25);
		_geschlageneBlackFiguren.add(blackRook2);		
		
		for(int i = 0; i<16;i++)
		{
			getContentPane().add(_geschlageneBlackFiguren.get(i));

			_geschlageneBlackFiguren.get(i).setVisible(false);
		}
	}
	
	public void setFigurWurdeGeschlagenLabelReverse(int geschlageneFigur)
	{
		/*
		 * Methode, die die Labels der geschlagenen Figuren einen Zug reversed.
		 * Funktioniert im Endeffekt genau umgekehrt wie setFigurWurdeGeschlagenLabel()
		 */
		if (geschlageneFigur <5)
		{
			if(geschlageneFigur ==4)
			{
				_geschlageneBlackFiguren.get(11).setVisible(false);
			}
			else if(geschlageneFigur ==3)
			{
				b = b - 3;
				_geschlageneBlackFiguren.get(10+b).setVisible(false);
			}
			else if(geschlageneFigur ==2)
			{
				n = n - 5;
				_geschlageneBlackFiguren.get(9+n).setVisible(false);
			}
			else if(geschlageneFigur ==1)
			{
				r = r - 7;
				_geschlageneBlackFiguren.get(8+r).setVisible(false);
			}
			else if(geschlageneFigur ==0)
			{
				p--;
				_geschlageneBlackFiguren.get(p).setVisible(false);
			}
		}
		else
		{
			if(geschlageneFigur ==9)
			{
				_geschlageneWhiteFiguren.get(11).setVisible(false);
			}
			else if(geschlageneFigur ==8)
			{
				B = B - 3;
				_geschlageneWhiteFiguren.get(10+B).setVisible(false);
			}
			else if(geschlageneFigur ==7)
			{
				N = N - 5;
				_geschlageneWhiteFiguren.get(9+N).setVisible(false);
			}
			else if(geschlageneFigur ==6)
			{
				R = R - 7;
				_geschlageneWhiteFiguren.get(8+R).setVisible(false);
			}
			else if(geschlageneFigur ==5)
			{
				P--;
				_geschlageneWhiteFiguren.get(P).setVisible(false);
			}
		}
	}
	
	public void setFigurWurdeGeschlagenLabel()
	{
		/*
		 * Methode um die Labels der geschlagenen Figuren sichtbar zu machen. 
		 * Die Methode wird nach jedem Make Move und in jedem Cyborg Move aufgerufen.
		 * Wenn eine Figur geschlagen wurde returnt die Funktion welcheFigurWurdeGeschlagen() ints wie folgt:
		 * SCHWARZ -> 0 = Bauer | 1 = Rook | 2 = Knight | 3 = Bishop | 4 = Queen
		 * WEISS   -> 5 = Bauer | 6 = Rook | 7 = Knight | 8 = Bishop | 9 = Queen
		 * Aktuell wird dabei nicht nachvollzogen ob es z.B. der linke oder rechte Turm ist, der geschlagen wurde. 
		 * Es wird dabei immer von links aufgefuellt.
		 * 
		 * Benoetigt werden 8 int Klassen Variablen b, n, r, p, B, N, R, P, da diese nicht in der Methode jedes mal neu initialisiert werden duerfen.
		 * Alle werden mit 0 im Konstruktor initialisiert.
		 * Beispiel:
		 * posVergleicher.welcheFigurWurdeGeschlagen(); returned 3 
		 * int geschlageneFigur = 3;
		 * Erste Fallunterscheidung guckt ob geschlageneFigur <5 ist
		 * Ist der Fall. Also naechste Fallunterscheidung zwischen 4 | 3 | 2 | 1 | 0
		 * 3 ist es, also wird _geschlageneBackFiguren an der Stelle 10 aufgerufen, da dies der RuhePlatz eines Bishop ist.
		 * Dieses Label wird sichtbar gemacht und die Klassenvariable b um 3 erhoeht, da dort der naechste Ruheplatz eines Bishops ist. 
		 * 
		 * TODO Keine Ahnung was passiert, wenn durch Promotion beispielsweise ein dritter Bishop geschlagen wird. 
		 */
		PositionsVergleicher posVergleicher = new PositionsVergleicher(_positions, _aktuellerZug);
		int geschlageneFigur = 999;
		if(posVergleicher.wurdeFigurGeschlagen() == true)
		{
			posVergleicher.whatMoveWasMade();
			geschlageneFigur = posVergleicher.welcheFigurWurdeGeschlagen();
		}
		if (geschlageneFigur <5)
		{
			if(geschlageneFigur ==4)
			{
				_geschlageneBlackFiguren.get(11).setVisible(true);
			}
			else if(geschlageneFigur ==3)
			{
				_geschlageneBlackFiguren.get(10+b).setVisible(true);
				b = b + 3;
			}
			else if(geschlageneFigur ==2)
			{
				_geschlageneBlackFiguren.get(9+n).setVisible(true);
				n = n + 5;
			}
			else if(geschlageneFigur ==1)
			{
				_geschlageneBlackFiguren.get(8+r).setVisible(true);
				r = r + 7;
			}
			else if(geschlageneFigur ==0)
			{
				_geschlageneBlackFiguren.get(p).setVisible(true);
				p++;
			}
		}
		else
		{
			if(geschlageneFigur ==9)
			{
				_geschlageneWhiteFiguren.get(11).setVisible(true);
			}
			else if(geschlageneFigur ==8)
			{
				_geschlageneWhiteFiguren.get(10+B).setVisible(true);
				B = B +3;
			}
			else if(geschlageneFigur ==7)
			{
				_geschlageneWhiteFiguren.get(9+N).setVisible(true);
				N = N + 5;
			}
			else if(geschlageneFigur ==6)
			{
				_geschlageneWhiteFiguren.get(8+R).setVisible(true);
				R = R + 7;
			}
			else if(geschlageneFigur ==5)
			{
				_geschlageneWhiteFiguren.get(P).setVisible(true);
				P++;
			}
		}
	}
	
	private void befuelleZeileSpalte()
	{
		/*
		 * Methode um die String Listen _spalte und _zeile zu befuellen.
		 * Die Listen sind fuer das befuellen der alten und neuen Koordinate Labels notwendig und werden in actionPerformed genutzt.
		 */
		_spalte.add("a"); _spalte.add("b"); _spalte.add("c"); _spalte.add("d"); _spalte.add("e"); _spalte.add("f"); _spalte.add("g"); _spalte.add("h");
		_zeile.add("8"); _zeile.add("7"); _zeile.add("6"); _zeile.add("5"); _zeile.add("4"); _zeile.add("3"); _zeile.add("2"); _zeile.add("1");
	}

	public void setZugrechtLabel()
	{
		if(_position.getZugrecht())
		{
			_zugrechtLabel.setText("Weiss");
			for (Map.Entry<Byte, Piece> entry : _whiteFiguren.entrySet())
			{
				Piece piece = entry.getValue();
				if (piece instanceof King)
				{
					if (((King) piece).isInCheck(_position))
					{
						_zugrechtLabel.setText("Weiss: König steht im Schach");
					}
				}
					
			}
		}
		
		else
		{
			_zugrechtLabel.setText("Schwarz");
			for (Map.Entry<Byte, Piece> entry : _blackFiguren.entrySet())
			{
				Piece piece = entry.getValue();
				if (piece instanceof King)
				{
					if (((King) piece).isInCheck(_position))
					{
						_zugrechtLabel.setText("Schwarz: König steht im Schach");
					}
				}
					
			}
		}
	}

	public void spielFeldButtonWurdeGedrueckt(int gedrueckterButton)
	{
		/*
		 * Methode, die jedes mal aufgerufen wird, wenn ein Spiel Feld Button gedrueckt wurde.
		 * Es werden die Klassen Variablen _letzterGedrueckterButton und _gedrueckterButton ueberschrieben.
		 * Durch die Listen _spalte und _zeile ist es moeglich durch den vom Spielfeld returnten int,  
		 * die Koordinate auf den entsprechenden Labels auszugeben. 
		 */
		_letzterGedrueckterButton = _gedrueckterButton;
		_gedrueckterButton = gedrueckterButton;
		int spalte = Math.floorMod(_gedrueckterButton, 8);
		int zeile = Math.abs(_gedrueckterButton / 8);
		int letzteSpalte = Math.floorMod(_letzterGedrueckterButton, 8);
		int letzteZeile = Math.abs(_letzterGedrueckterButton / 8);
		_alteKoordinateLabel.setText("Alte Koordinate: " + _spalte.get(letzteSpalte) + _zeile.get(letzteZeile));
		_neueKoordinateLabel.setText("Neue Koordinate: " + _spalte.get(spalte) + _zeile.get(zeile));
	}


}
