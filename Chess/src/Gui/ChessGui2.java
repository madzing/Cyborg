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
import Werkzeuge.AlphaBetaCyborg;
import Werkzeuge.RekursiverCyborg;

import java.awt.BorderLayout;
import javax.swing.JCheckBox;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;

public class ChessGui2 extends JFrame implements ActionListener{
	Position _position;
	Position _positionSpeicher;
	Map<Byte, Piece> _whiteFiguren;
	Map<Byte, Piece> _blackFiguren;
	List<JButton> _buttons;
	ArrayList<Position> _positions;
	List<JLabel> _geschlageneWhiteFiguren;
	List<JLabel> _geschlageneBlackFiguren;
	int _letzterGedrueckterButton;
	int _gedrueckterButton;
	int _aktuellerZug;
	JLabel _lblNewLabel;
	JLabel _lblNewLabel_1;
	JButton _btnNewButton_64;
	JButton _btnNewButton_65;
	PositionCalc _posCalc;
	private JPanel contentPane;
	private JButton _btnGetAktuelleFen;
	private JLabel _zugrechtLabel;
	private JToggleButton _tglbtnNewToggleButton;
	List <String> _spalte;
	List <String> _zeile;
	public static final Color LIGHT_BLUE = new Color(51,153,255);

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {

					Fen _startFen= Fen.select("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1");
//					Fen _startFen = Fen.select("rnbqkbn1/pppppppP/8/8/8/8/PPPPPPPp/RNBQKBN1 w KQkq - 0 1");

					Position _startPosition = new Position(_startFen);
					ChessGui2 window = new ChessGui2(_startPosition);
					window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ChessGui2(Position position) {
		_position = position;
		_positionSpeicher = new Position(_position);
		_whiteFiguren = position.getWhiteFiguren();
		_blackFiguren = position.getBlackFiguren();
		_buttons = new ArrayList<JButton>(64);
		_positions = new ArrayList<Position>(6000);
		_geschlageneWhiteFiguren = new ArrayList<JLabel>(16);
		_geschlageneBlackFiguren = new ArrayList<JLabel>(16);
		_letzterGedrueckterButton = 0;
		_gedrueckterButton = 0;
		_aktuellerZug = 0;
		_posCalc = new PositionCalc(position);
		_spalte = new ArrayList<String>(8);
		_zeile = new ArrayList<String>(8);
		befuelleZeileSpalte();
		_positions.add(position);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 800);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		_lblNewLabel = new JLabel("Alte Koordinate");
		menuBar.add(_lblNewLabel);

		Component verticalStrut = Box.createVerticalStrut(20);
		menuBar.add(verticalStrut);

		_lblNewLabel_1 = new JLabel("Neue Koordinate");
		menuBar.add(_lblNewLabel_1);

		Component verticalStrut_1 = Box.createVerticalStrut(20);
		menuBar.add(verticalStrut_1);

		JComboBox comboBox = new JComboBox();
		menuBar.add(comboBox);
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
		_btnGetAktuelleFen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		_btnGetAktuelleFen.setBounds(784, 60, 190, 45);
		getContentPane().add(_btnGetAktuelleFen);
		_btnGetAktuelleFen.addActionListener(this);

		_tglbtnNewToggleButton = new JToggleButton("Automatisch");
		_tglbtnNewToggleButton.setBounds(784, 10, 190, 45);
		getContentPane().add(_tglbtnNewToggleButton);

		_btnNewButton_64 = new JButton("Make Move");
		_btnNewButton_64.setBounds(784, 680, 190, 45);
		getContentPane().add(_btnNewButton_64);
		_btnNewButton_64.addActionListener(this);
		
		_btnNewButton_65 = new JButton("Reverse");
		_btnNewButton_65.setBounds(784, 630, 190, 45);
		getContentPane().add(_btnNewButton_65);
		_btnNewButton_65.addActionListener(this);
		
				
		createButtons();
		setFiguren();
		setZugrechtLabel();
		ButtonListenerErzeugen();
		createGeschlageneFigurLabels();

	}


	/* TODO
	 * W�re noch nice:
	 * -Anzeige geschlagener Figuren
	 * -Schwierigkeit einstellen
	 * -Zugrecht Feld displayed Gewinner
	 */

	public void makeCyborgMove()
	{
		AlphaBetaCyborg Ernd = new AlphaBetaCyborg(4);
		Position altePosition = new Position(_position);
		_position = Ernd.getBestFollowingPosition(_position);
		PositionsVergleicher posVergleicher = new PositionsVergleicher(altePosition, _position);
		posVergleicher.whatMoveWasMade(altePosition, _position);
		int alteKoordinate = posVergleicher.getAlteKoordinate();
		int neueKoordinate = posVergleicher.getNeueKoordinate();
		setFiguren();
		setZugrechtLabel();
		_buttons.get(alteKoordinate).setBackground(LIGHT_BLUE);
		_buttons.get(neueKoordinate).setBackground(Color.RED);
		//System.out.println(_position.getFen());
	}

	private void resetteFelder()
	{
		_buttons.get(0).setBackground(Color.WHITE);
		_buttons.get(1).setBackground(Color.BLACK);
		_buttons.get(2).setBackground(Color.WHITE);
		_buttons.get(3).setBackground(Color.BLACK);
		_buttons.get(4).setBackground(Color.WHITE);
		_buttons.get(5).setBackground(Color.BLACK);
		_buttons.get(6).setBackground(Color.WHITE);
		_buttons.get(7).setBackground(Color.BLACK);
		_buttons.get(8).setBackground(Color.BLACK);
		_buttons.get(9).setBackground(Color.WHITE);
		_buttons.get(10).setBackground(Color.BLACK);
		_buttons.get(11).setBackground(Color.WHITE);
		_buttons.get(12).setBackground(Color.BLACK);
		_buttons.get(13).setBackground(Color.WHITE);
		_buttons.get(14).setBackground(Color.BLACK);
		_buttons.get(15).setBackground(Color.WHITE);
		_buttons.get(16).setBackground(Color.WHITE);
		_buttons.get(17).setBackground(Color.BLACK);
		_buttons.get(18).setBackground(Color.WHITE);
		_buttons.get(19).setBackground(Color.BLACK);
		_buttons.get(20).setBackground(Color.WHITE);
		_buttons.get(21).setBackground(Color.BLACK);
		_buttons.get(22).setBackground(Color.WHITE);
		_buttons.get(23).setBackground(Color.BLACK);
		_buttons.get(24).setBackground(Color.BLACK);
		_buttons.get(25).setBackground(Color.WHITE);
		_buttons.get(26).setBackground(Color.BLACK);
		_buttons.get(27).setBackground(Color.WHITE);
		_buttons.get(28).setBackground(Color.BLACK);
		_buttons.get(29).setBackground(Color.WHITE);
		_buttons.get(30).setBackground(Color.BLACK);
		_buttons.get(31).setBackground(Color.WHITE);
		_buttons.get(32).setBackground(Color.WHITE);
		_buttons.get(33).setBackground(Color.BLACK);
		_buttons.get(34).setBackground(Color.WHITE);
		_buttons.get(35).setBackground(Color.BLACK);
		_buttons.get(36).setBackground(Color.WHITE);
		_buttons.get(37).setBackground(Color.BLACK);
		_buttons.get(38).setBackground(Color.WHITE);
		_buttons.get(39).setBackground(Color.BLACK);
		_buttons.get(40).setBackground(Color.BLACK);
		_buttons.get(41).setBackground(Color.WHITE);
		_buttons.get(42).setBackground(Color.BLACK);
		_buttons.get(43).setBackground(Color.WHITE);
		_buttons.get(44).setBackground(Color.BLACK);
		_buttons.get(45).setBackground(Color.WHITE);
		_buttons.get(46).setBackground(Color.BLACK);
		_buttons.get(47).setBackground(Color.WHITE);
		_buttons.get(48).setBackground(Color.WHITE);
		_buttons.get(49).setBackground(Color.BLACK);
		_buttons.get(50).setBackground(Color.WHITE);
		_buttons.get(51).setBackground(Color.BLACK);
		_buttons.get(52).setBackground(Color.WHITE);
		_buttons.get(53).setBackground(Color.BLACK);
		_buttons.get(54).setBackground(Color.WHITE);
		_buttons.get(55).setBackground(Color.BLACK);
		_buttons.get(56).setBackground(Color.BLACK);
		_buttons.get(57).setBackground(Color.WHITE);
		_buttons.get(58).setBackground(Color.BLACK);
		_buttons.get(59).setBackground(Color.WHITE);
		_buttons.get(60).setBackground(Color.BLACK);
		_buttons.get(61).setBackground(Color.WHITE);
		_buttons.get(62).setBackground(Color.BLACK);
		_buttons.get(63).setBackground(Color.WHITE);
	}

	private void promotion(int piece)
	{
		// piece = 0 wenn Queen. 1 wenn Rook. 2 wenn Bishop. 3 wenn Knight.
		if (! _position.getZugrecht())
		{
			if (piece == 0)
			{
				_positionSpeicher.promotion(new Queen((byte)_gedrueckterButton, false));
			}
			if (piece == 1)
			{
				_positionSpeicher.promotion(new Rook((byte)_gedrueckterButton, false));
			}
			if (piece == 2)
			{
				_positionSpeicher.promotion(new Bishop((byte)_gedrueckterButton, false));
			}
			if (piece == 3)
			{
				_positionSpeicher.promotion(new Knight((byte)_gedrueckterButton, false));
			}

		}
		else
		{
			if (piece == 0)
			{
				_positionSpeicher.promotion(new Queen((byte)_gedrueckterButton, true));
			}
			if (piece == 1)
			{
				_positionSpeicher.promotion(new Rook((byte)_gedrueckterButton, true));
			}
			if (piece == 2)
			{
				_positionSpeicher.promotion(new Bishop((byte)_gedrueckterButton, true));
			}
			if (piece == 3)
			{
				_positionSpeicher.promotion(new Knight((byte)_gedrueckterButton, true));
			}

		}
	}

	private void ButtonListenerErzeugen()
	{
		for (int i = 0; i <64; i++)
		{
			_buttons.get(i).addActionListener(this);
		}
	}

	private void setFiguren() {
		for (JButton b: _buttons)
		{
			b.setIcon(null);
		}
		_whiteFiguren = _position.getWhiteFiguren();
		_blackFiguren = _position.getBlackFiguren();
		for (Map.Entry<Byte, Piece> whiteFigur : _whiteFiguren.entrySet())
		{
			int whiteCoordinate= (int) whiteFigur.getValue().getCoordinate();
			if(whiteFigur.getValue() instanceof Pawn)
    		{
    			_buttons.get(whiteCoordinate).setIcon(new ImageIcon(ChessGui2.class.getResource("/Piece_Images/White_Pawn.png")));
    		}
    		if(whiteFigur.getValue() instanceof Knight)
    		{
    			_buttons.get(whiteCoordinate).setIcon(new ImageIcon(ChessGui2.class.getResource("/Piece_Images/White_Knight.png")));
    		}
    		if(whiteFigur.getValue() instanceof Bishop)
    		{
    			_buttons.get(whiteCoordinate).setIcon(new ImageIcon(ChessGui2.class.getResource("/Piece_Images/White_Bishop.png")));
    		}
    		if(whiteFigur.getValue() instanceof Rook)
    		{
    			_buttons.get(whiteCoordinate).setIcon(new ImageIcon(ChessGui2.class.getResource("/Piece_Images/White_Rook.png")));
    		}
    		if(whiteFigur.getValue() instanceof Queen)
    		{
    			_buttons.get(whiteCoordinate).setIcon(new ImageIcon(ChessGui2.class.getResource("/Piece_Images/White_Queen.png")));
    		}
    		if(whiteFigur.getValue() instanceof King)
    		{
    			_buttons.get(whiteCoordinate).setIcon(new ImageIcon(ChessGui2.class.getResource("/Piece_Images/White_King.png")));
    		}

    		for (Map.Entry<Byte, Piece> blackFigur : _blackFiguren.entrySet())
    		{
    			int blackCoordinate= (int) blackFigur.getValue().getCoordinate();
    			if(blackFigur.getValue() instanceof Pawn)
        		{
        			_buttons.get(blackCoordinate).setIcon(new ImageIcon(ChessGui2.class.getResource("/Piece_Images/Black_Pawn.png")));
        		}
        		if(blackFigur.getValue() instanceof Knight)
        		{
        			_buttons.get(blackCoordinate).setIcon(new ImageIcon(ChessGui2.class.getResource("/Piece_Images/Black_Knight.png")));
        		}
        		if(blackFigur.getValue() instanceof Bishop)
        		{
        			_buttons.get(blackCoordinate).setIcon(new ImageIcon(ChessGui2.class.getResource("/Piece_Images/Black_Bishop.png")));
        		}
        		if(blackFigur.getValue() instanceof Rook)
        		{
        			_buttons.get(blackCoordinate).setIcon(new ImageIcon(ChessGui2.class.getResource("/Piece_Images/Black_Rook.png")));
        		}
        		if(blackFigur.getValue() instanceof Queen)
        		{
        			_buttons.get(blackCoordinate).setIcon(new ImageIcon(ChessGui2.class.getResource("/Piece_Images/Black_Queen.png")));
        		}
        		if(blackFigur.getValue() instanceof King)
        		{
        			_buttons.get(blackCoordinate).setIcon(new ImageIcon(ChessGui2.class.getResource("/Piece_Images/Black_King.png")));
        		}
    		}
		}
	}

	private void createButtons() {
		JButton btnNewButton = new JButton("a8");
		btnNewButton.setBackground(Color.WHITE);
		contentPane.add(btnNewButton);
		_buttons.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("b8");
		btnNewButton_1.setBackground(Color.BLACK);
		contentPane.add(btnNewButton_1);
		_buttons.add(btnNewButton_1);
		btnNewButton_1.setForeground(Color.WHITE);

		JButton btnNewButton_2 = new JButton("c8");
		btnNewButton_2.setBackground(Color.WHITE);
		contentPane.add(btnNewButton_2);
		_buttons.add(btnNewButton_2);

		JButton btnNewButton_3 = new JButton("d8");
		btnNewButton_3.setBackground(Color.BLACK);
		contentPane.add(btnNewButton_3);
		_buttons.add(btnNewButton_3);
		btnNewButton_3.setForeground(Color.WHITE);

		JButton btnNewButton_4 = new JButton("e8");
		btnNewButton_4.setBackground(Color.WHITE);
		contentPane.add(btnNewButton_4);
		_buttons.add(btnNewButton_4);

		JButton btnNewButton_5 = new JButton("f8");
		btnNewButton_5.setBackground(Color.BLACK);
		contentPane.add(btnNewButton_5);
		_buttons.add(btnNewButton_5);
		btnNewButton_5.setForeground(Color.WHITE);

		JButton btnNewButton_6 = new JButton("g8");
		btnNewButton_6.setBackground(Color.WHITE);
		contentPane.add(btnNewButton_6);
		_buttons.add(btnNewButton_6);

		JButton btnNewButton_7 = new JButton("h8");
		btnNewButton_7.setBackground(Color.BLACK);
		contentPane.add(btnNewButton_7);
		_buttons.add(btnNewButton_7);
		btnNewButton_7.setForeground(Color.WHITE);

		JButton btnNewButton_8 = new JButton("a7");
		btnNewButton_8.setBackground(Color.BLACK);
		contentPane.add(btnNewButton_8);
		_buttons.add(btnNewButton_8);
		btnNewButton_8.setForeground(Color.WHITE);

		JButton btnNewButton_9 = new JButton("b7");
		btnNewButton_9.setBackground(Color.WHITE);
		contentPane.add(btnNewButton_9);
		_buttons.add(btnNewButton_9);

		JButton btnNewButton_10 = new JButton("c7");
		btnNewButton_10.setBackground(Color.BLACK);
		contentPane.add(btnNewButton_10);
		_buttons.add(btnNewButton_10);
		btnNewButton_10.setForeground(Color.WHITE);

		JButton btnNewButton_11 = new JButton("d7");
		btnNewButton_11.setBackground(Color.WHITE);
		contentPane.add(btnNewButton_11);
		_buttons.add(btnNewButton_11);

		JButton btnNewButton_12 = new JButton("e7");
		btnNewButton_12.setBackground(Color.BLACK);
		contentPane.add(btnNewButton_12);
		_buttons.add(btnNewButton_12);
		btnNewButton_12.setForeground(Color.WHITE);

		JButton btnNewButton_13 = new JButton("f7");
		btnNewButton_13.setBackground(Color.WHITE);
		contentPane.add(btnNewButton_13);
		_buttons.add(btnNewButton_13);

		JButton btnNewButton_14 = new JButton("g7");
		btnNewButton_14.setBackground(Color.BLACK);
		contentPane.add(btnNewButton_14);
		_buttons.add(btnNewButton_14);
		btnNewButton_14.setForeground(Color.WHITE);

		JButton btnNewButton_15 = new JButton("h7");
		btnNewButton_15.setBackground(Color.WHITE);
		contentPane.add(btnNewButton_15);
		_buttons.add(btnNewButton_15);

		JButton btnNewButton_16 = new JButton("a6");
		btnNewButton_16.setBackground(Color.WHITE);
		contentPane.add(btnNewButton_16);
		_buttons.add(btnNewButton_16);

		JButton btnNewButton_17 = new JButton("b6");
		btnNewButton_17.setBackground(Color.BLACK);
		contentPane.add(btnNewButton_17);
		_buttons.add(btnNewButton_17);
		btnNewButton_17.setForeground(Color.WHITE);

		JButton btnNewButton_18 = new JButton("c6");
		btnNewButton_18.setBackground(Color.WHITE);
		contentPane.add(btnNewButton_18);
		_buttons.add(btnNewButton_18);

		JButton btnNewButton_19 = new JButton("d6");
		btnNewButton_19.setBackground(Color.BLACK);
		contentPane.add(btnNewButton_19);
		_buttons.add(btnNewButton_19);
		btnNewButton_19.setForeground(Color.WHITE);

		JButton btnNewButton_20 = new JButton("e6");
		btnNewButton_20.setBackground(Color.WHITE);
		contentPane.add(btnNewButton_20);
		_buttons.add(btnNewButton_20);

		JButton btnNewButton_21 = new JButton("f6");
		btnNewButton_21.setBackground(Color.BLACK);
		contentPane.add(btnNewButton_21);
		_buttons.add(btnNewButton_21);
		btnNewButton_21.setForeground(Color.WHITE);

		JButton btnNewButton_22 = new JButton("g6");
		btnNewButton_22.setBackground(Color.WHITE);
		contentPane.add(btnNewButton_22);
		_buttons.add(btnNewButton_22);

		JButton btnNewButton_23 = new JButton("h6");
		btnNewButton_23.setBackground(Color.BLACK);
		contentPane.add(btnNewButton_23);
		_buttons.add(btnNewButton_23);
		btnNewButton_23.setForeground(Color.WHITE);

		JButton btnNewButton_24 = new JButton("a5");
		btnNewButton_24.setBackground(Color.BLACK);
		contentPane.add(btnNewButton_24);
		_buttons.add(btnNewButton_24);
		btnNewButton_24.setForeground(Color.WHITE);

		JButton btnNewButton_25 = new JButton("b5");
		btnNewButton_25.setBackground(Color.WHITE);
		contentPane.add(btnNewButton_25);
		_buttons.add(btnNewButton_25);

		JButton btnNewButton_26 = new JButton("c5");
		btnNewButton_26.setBackground(Color.BLACK);
		contentPane.add(btnNewButton_26);
		_buttons.add(btnNewButton_26);
		btnNewButton_26.setForeground(Color.WHITE);

		JButton btnNewButton_27 = new JButton("d5");
		btnNewButton_27.setBackground(Color.WHITE);
		contentPane.add(btnNewButton_27);
		_buttons.add(btnNewButton_27);

		JButton btnNewButton_28 = new JButton("e5");
		btnNewButton_28.setBackground(Color.BLACK);
		contentPane.add(btnNewButton_28);
		_buttons.add(btnNewButton_28);
		btnNewButton_28.setForeground(Color.WHITE);

		JButton btnNewButton_29 = new JButton("f5");
		btnNewButton_29.setBackground(Color.WHITE);
		contentPane.add(btnNewButton_29);
		_buttons.add(btnNewButton_29);

		JButton btnNewButton_30 = new JButton("g5");
		btnNewButton_30.setBackground(Color.BLACK);
		contentPane.add(btnNewButton_30);
		_buttons.add(btnNewButton_30);
		btnNewButton_30.setForeground(Color.WHITE);

		JButton btnNewButton_31 = new JButton("h5");
		btnNewButton_31.setBackground(Color.WHITE);
		contentPane.add(btnNewButton_31);
		_buttons.add(btnNewButton_31);

		JButton btnNewButton_32 = new JButton("a4");
		btnNewButton_32.setBackground(Color.WHITE);
		contentPane.add(btnNewButton_32);
		_buttons.add(btnNewButton_32);

		JButton btnNewButton_33 = new JButton("b4");
		btnNewButton_33.setBackground(Color.BLACK);
		contentPane.add(btnNewButton_33);
		_buttons.add(btnNewButton_33);
		btnNewButton_33.setForeground(Color.WHITE);

		JButton btnNewButton_34 = new JButton("c4");
		btnNewButton_34.setBackground(Color.WHITE);
		contentPane.add(btnNewButton_34);
		_buttons.add(btnNewButton_34);

		JButton btnNewButton_35 = new JButton("d4");
		btnNewButton_35.setBackground(Color.BLACK);
		contentPane.add(btnNewButton_35);
		_buttons.add(btnNewButton_35);
		btnNewButton_35.setForeground(Color.WHITE);

		JButton btnNewButton_36 = new JButton("e4");
		btnNewButton_36.setBackground(Color.WHITE);
		contentPane.add(btnNewButton_36);
		_buttons.add(btnNewButton_36);

		JButton btnNewButton_37 = new JButton("f4");
		btnNewButton_37.setBackground(Color.BLACK);
		contentPane.add(btnNewButton_37);
		_buttons.add(btnNewButton_37);
		btnNewButton_37.setForeground(Color.WHITE);

		JButton btnNewButton_38 = new JButton("g4");
		btnNewButton_38.setBackground(Color.WHITE);
		contentPane.add(btnNewButton_38);
		_buttons.add(btnNewButton_38);

		JButton btnNewButton_39 = new JButton("h4");
		btnNewButton_39.setBackground(Color.BLACK);
		contentPane.add(btnNewButton_39);
		_buttons.add(btnNewButton_39);
		btnNewButton_39.setForeground(Color.WHITE);

		JButton btnNewButton_40 = new JButton("a3");
		btnNewButton_40.setBackground(Color.BLACK);
		contentPane.add(btnNewButton_40);
		_buttons.add(btnNewButton_40);
		btnNewButton_40.setForeground(Color.WHITE);

		JButton btnNewButton_41 = new JButton("b3");
		btnNewButton_41.setBackground(Color.WHITE);
		contentPane.add(btnNewButton_41);
		_buttons.add(btnNewButton_41);

		JButton btnNewButton_42 = new JButton("c3");
		btnNewButton_42.setBackground(Color.BLACK);
		contentPane.add(btnNewButton_42);
		_buttons.add(btnNewButton_42);
		btnNewButton_42.setForeground(Color.WHITE);

		JButton btnNewButton_43 = new JButton("d3");
		btnNewButton_43.setBackground(Color.WHITE);
		contentPane.add(btnNewButton_43);
		_buttons.add(btnNewButton_43);

		JButton btnNewButton_44 = new JButton("e3");
		btnNewButton_44.setBackground(Color.BLACK);
		contentPane.add(btnNewButton_44);
		_buttons.add(btnNewButton_44);
		btnNewButton_44.setForeground(Color.WHITE);

		JButton btnNewButton_45 = new JButton("f3");
		btnNewButton_45.setBackground(Color.WHITE);
		contentPane.add(btnNewButton_45);
		_buttons.add(btnNewButton_45);

		JButton btnNewButton_46 = new JButton("g3");
		btnNewButton_46.setBackground(Color.BLACK);
		contentPane.add(btnNewButton_46);
		_buttons.add(btnNewButton_46);
		btnNewButton_46.setForeground(Color.WHITE);

		JButton btnNewButton_47 = new JButton("h3");
		btnNewButton_47.setBackground(Color.WHITE);
		contentPane.add(btnNewButton_47);
		_buttons.add(btnNewButton_47);

		JButton btnNewButton_48 = new JButton("a2");
		btnNewButton_48.setBackground(Color.WHITE);
		contentPane.add(btnNewButton_48);
		_buttons.add(btnNewButton_48);

		JButton btnNewButton_49 = new JButton("b2");
		btnNewButton_49.setBackground(Color.BLACK);
		contentPane.add(btnNewButton_49);
		_buttons.add(btnNewButton_49);
		btnNewButton_49.setForeground(Color.WHITE);

		JButton btnNewButton_50 = new JButton("c2");
		btnNewButton_50.setBackground(Color.WHITE);
		contentPane.add(btnNewButton_50);
		_buttons.add(btnNewButton_50);

		JButton btnNewButton_51 = new JButton("d2");
		btnNewButton_51.setBackground(Color.BLACK);
		contentPane.add(btnNewButton_51);
		_buttons.add(btnNewButton_51);
		btnNewButton_51.setForeground(Color.WHITE);

		JButton btnNewButton_52 = new JButton("e2");
		btnNewButton_52.setBackground(Color.WHITE);
		contentPane.add(btnNewButton_52);
		_buttons.add(btnNewButton_52);

		JButton btnNewButton_53 = new JButton("f2");
		btnNewButton_53.setBackground(Color.BLACK);
		contentPane.add(btnNewButton_53);
		_buttons.add(btnNewButton_53);
		btnNewButton_53.setForeground(Color.WHITE);

		JButton btnNewButton_54 = new JButton("g2");
		btnNewButton_54.setBackground(Color.WHITE);
		contentPane.add(btnNewButton_54);
		_buttons.add(btnNewButton_54);

		JButton btnNewButton_55 = new JButton("h2");
		btnNewButton_55.setBackground(Color.BLACK);
		contentPane.add(btnNewButton_55);
		_buttons.add(btnNewButton_55);
		btnNewButton_55.setForeground(Color.WHITE);

		JButton btnNewButton_56 = new JButton("a1");
		btnNewButton_56.setBackground(Color.BLACK);
		contentPane.add(btnNewButton_56);
		_buttons.add(btnNewButton_56);
		btnNewButton_56.setForeground(Color.WHITE);

		JButton btnNewButton_57 = new JButton("b1");
		btnNewButton_57.setBackground(Color.WHITE);
		contentPane.add(btnNewButton_57);
		_buttons.add(btnNewButton_57);

		JButton btnNewButton_58 = new JButton("c1");
		btnNewButton_58.setBackground(Color.BLACK);
		contentPane.add(btnNewButton_58);
		_buttons.add(btnNewButton_58);
		btnNewButton_58.setForeground(Color.WHITE);

		JButton btnNewButton_59 = new JButton("d1");
		btnNewButton_59.setBackground(Color.WHITE);
		contentPane.add(btnNewButton_59);
		_buttons.add(btnNewButton_59);

		JButton btnNewButton_60 = new JButton("e1");
		btnNewButton_60.setBackground(Color.BLACK);
		contentPane.add(btnNewButton_60);
		_buttons.add(btnNewButton_60);
		btnNewButton_60.setForeground(Color.WHITE);

		JButton btnNewButton_61 = new JButton("f1");
		btnNewButton_61.setBackground(Color.WHITE);
		contentPane.add(btnNewButton_61);
		_buttons.add(btnNewButton_61);

		JButton btnNewButton_62 = new JButton("g1");
		btnNewButton_62.setBackground(Color.BLACK);
		contentPane.add(btnNewButton_62);
		_buttons.add(btnNewButton_62);
		btnNewButton_62.setForeground(Color.WHITE);

		JButton btnNewButton_63 = new JButton("h1");
		btnNewButton_63.setBackground(Color.WHITE);
		contentPane.add(btnNewButton_63);
		_buttons.add(btnNewButton_63);
	}

	private void createGeschlageneFigurLabels()
	{
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
		}
	}
	
	private void befuelleZeileSpalte()
	{
		_spalte.add("a"); _spalte.add("b"); _spalte.add("c"); _spalte.add("d"); _spalte.add("e"); _spalte.add("f"); _spalte.add("g"); _spalte.add("h");
		_zeile.add("8"); _zeile.add("7"); _zeile.add("6"); _zeile.add("5"); _zeile.add("4"); _zeile.add("3"); _zeile.add("2"); _zeile.add("1");
	}

	private void setZugrechtLabel()
	{
		if(_position.getZugrecht())
		{
			_zugrechtLabel.setText("Weiss ist am Zug");
		}
		else
		{
			_zugrechtLabel.setText("Schwarz ist am Zug");
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (_buttons.contains(e.getSource()))
			{
				_letzterGedrueckterButton = _gedrueckterButton;
				_gedrueckterButton = _buttons.indexOf(e.getSource());
				int spalte = Math.floorMod(_gedrueckterButton, 8);
				int zeile = Math.abs(_gedrueckterButton / 8);
				int letzteSpalte = Math.floorMod(_letzterGedrueckterButton, 8);
				int letzteZeile = Math.abs(_letzterGedrueckterButton / 8);
				_lblNewLabel.setText("Alte Koordinate: " + _spalte.get(letzteSpalte) + _zeile.get(letzteZeile));
				_lblNewLabel_1.setText("Neue Koordinate: " + _spalte.get(spalte) + _zeile.get(zeile));
			}
		else if(e.getSource() == _btnGetAktuelleFen)
		{
			StringSelection stringSelection = new StringSelection (_position.getFen());
			Clipboard clpbrd = Toolkit.getDefaultToolkit ().getSystemClipboard ();
			clpbrd.setContents (stringSelection, null);
		}
		else if(e.getSource() == _btnNewButton_65)
		{
			_position = _positions.get(_aktuellerZug-1);
			_positions.remove(_aktuellerZug);
			_aktuellerZug--;
			setFiguren();
			setZugrechtLabel();
			resetteFelder();
		}
		else
			{
				
				_posCalc = new PositionCalc(_position);
				ArrayList<Position> legalePositionen = _posCalc.getLegalFollowingPositions();
				_positionSpeicher = new Position(_position);
				Object[] optionen = {"Queen", "Rook", "Bishop", "Knight"};

				_positionSpeicher.makeMove((byte)_letzterGedrueckterButton, (byte)_gedrueckterButton);
//				System.out.println("positionSpeicher1: " + _positionSpeicher.getFen());
				if ((_gedrueckterButton >=56 && _blackFiguren.get((byte)_letzterGedrueckterButton) instanceof Pawn)|| (_gedrueckterButton <=7 && _whiteFiguren.get((byte)_letzterGedrueckterButton) instanceof Pawn))
				{
					int piece = JOptionPane.showOptionDialog(null, "Waehle eine Figur:", null, JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, optionen, null);
					promotion(piece);
//					System.out.println("piece: "+piece);
//					System.out.println("zugrecht: " +_position.getZugrecht());
					// piece = 0 wenn Queen. 1 wenn Rook. 2 wenn Bishop. 3 wenn Knight.
				}

				Position altePosition = new Position(_position);
				
				for(Position p: legalePositionen)
				{
					if (p.getFen().equals(_positionSpeicher.getFen()))
					{
						_position = _positionSpeicher;
					}
				}
				_positions.add(_position);
				_aktuellerZug++;
//				PositionsVergleicher posVergleicher = new PositionsVergleicher(_positions, _aktuellerZug);
//				if(posVergleicher.wurdeFigurGeschlagen() == true)
//				{
//					System.out.println(posVergleicher.welcheFigurWurdeGeschlagen());
//				}
//				System.out.println(posVergleicher.wurdeFigurGeschlagen());
				setFiguren();
				setZugrechtLabel();
				resetteFelder();				
				for(int i = 0; i < _positions.size();i++)
				{
					System.out.println(_positions.get(i).getFen());
				}
//				System.out.println("positionSpeicher2: " + _positionSpeicher.getFen());
//				System.out.println("_position: " + _position.getFen());
			}
		if(_tglbtnNewToggleButton.isSelected() && !(_position._zugrecht))
		{
			makeCyborgMove();
		}
	}


}
