package Gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
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
import java.awt.BorderLayout;

public class ChessGui2 extends JFrame implements ActionListener{
	Position _position;
	List<Piece> _whiteFiguren;
	List<Piece> _blackFiguren;
	List<JButton> _buttons;
	int _letzterGedrueckterButton;
	int _gedrueckterButton;
	JLabel _lblNewLabel;
	JLabel _lblNewLabel_1;
	JButton _btnNewButton_64;
	PositionCalc _posCalc;
	private JPanel contentPane;
	private JButton btnNewButton;
	private JButton btnNewButton_1;
	private JButton btnNewButton_2;
	private JButton btnNewButton_3;
	private JButton btnNewButton_4;
	private JButton btnNewButton_5;
	private JButton btnNewButton_6;
	private JButton btnNewButton_7;
	private JButton btnNewButton_8;
	private JButton btnNewButton_9;
	private JButton btnNewButton_10;
	private JButton btnNewButton_11;
	private JButton btnNewButton_12;
	private JButton btnNewButton_13;
	private JButton btnNewButton_14;
	private JButton btnNewButton_15;
	private JButton btnNewButton_16;
	private JButton btnNewButton_17;
	private JButton btnNewButton_18;
	private JButton btnNewButton_19;
	private JButton btnNewButton_20;
	private JButton btnNewButton_21;
	private JButton btnNewButton_22;
	private JButton btnNewButton_23;
	private JButton btnNewButton_24;
	private JButton btnNewButton_25;
	private JButton btnNewButton_26;
	private JButton btnNewButton_27;
	private JButton btnNewButton_28;
	private JButton btnNewButton_29;
	private JButton btnNewButton_30;
	private JButton btnNewButton_31;
	private JButton btnNewButton_32;
	private JButton btnNewButton_33;
	private JButton btnNewButton_34;
	private JButton btnNewButton_35;
	private JButton btnNewButton_36;
	private JButton btnNewButton_37;
	private JButton btnNewButton_38;
	private JButton btnNewButton_39;
	private JButton btnNewButton_40;
	private JButton btnNewButton_41;
	private JButton btnNewButton_42;
	private JButton btnNewButton_43;
	private JButton btnNewButton_44;
	private JButton btnNewButton_45;
	private JButton btnNewButton_46;
	private JButton btnNewButton_47;
	private JButton btnNewButton_48;
	private JButton btnNewButton_49;
	private JButton btnNewButton_50;
	private JButton btnNewButton_51;
	private JButton btnNewButton_52;
	private JButton btnNewButton_53;
	private JButton btnNewButton_54;
	private JButton btnNewButton_55;
	private JButton btnNewButton_56;
	private JButton btnNewButton_57;
	private JButton btnNewButton_58;
	private JButton btnNewButton_59;
	private JButton btnNewButton_60;
	private JButton btnNewButton_61;
	private JButton btnNewButton_62;
	private JButton btnNewButton_63;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Fen _startFen= Fen.select("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1");
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
		_whiteFiguren = position.getWhiteFiguren();
		_blackFiguren = position.getBlackFiguren();
		_buttons = new ArrayList<JButton>(64);
		_letzterGedrueckterButton = 0;
		_gedrueckterButton = 0;
		_posCalc = new PositionCalc(position);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 800);
		
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
		
		_btnNewButton_64 = new JButton("Make Move");
		menuBar.add(_btnNewButton_64);
		_btnNewButton_64.addActionListener(this);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(Color.WHITE);
		getContentPane().add(contentPane, BorderLayout.CENTER);
		contentPane.setLayout(new GridLayout(8, 8));
		
		btnNewButton = new JButton("a8");
		btnNewButton.setBackground(Color.WHITE);
		contentPane.add(btnNewButton);
		
		btnNewButton_1 = new JButton("b8");
		btnNewButton_1.setBackground(Color.BLACK);
		contentPane.add(btnNewButton_1);
		
		btnNewButton_2 = new JButton("c8");
		btnNewButton_2.setBackground(Color.WHITE);
		contentPane.add(btnNewButton_2);
		
		btnNewButton_3 = new JButton("d8");
		btnNewButton_3.setBackground(Color.BLACK);
		contentPane.add(btnNewButton_3);
		
		btnNewButton_4 = new JButton("e8");
		btnNewButton_4.setBackground(Color.WHITE);
		contentPane.add(btnNewButton_4);
		
		btnNewButton_5 = new JButton("f8");
		btnNewButton_5.setBackground(Color.BLACK);
		contentPane.add(btnNewButton_5);
		
		btnNewButton_6 = new JButton("g8");
		btnNewButton_6.setBackground(Color.WHITE);
		contentPane.add(btnNewButton_6);
		
		btnNewButton_7 = new JButton("h8");
		btnNewButton_7.setBackground(Color.BLACK);
		contentPane.add(btnNewButton_7);
		
		btnNewButton_8 = new JButton("a7");
		btnNewButton_8.setBackground(Color.BLACK);
		contentPane.add(btnNewButton_8);
		
		btnNewButton_9 = new JButton("b7");
		btnNewButton_9.setBackground(Color.WHITE);
		contentPane.add(btnNewButton_9);
		
		btnNewButton_10 = new JButton("c7");
		btnNewButton_10.setBackground(Color.BLACK);
		contentPane.add(btnNewButton_10);
		
		btnNewButton_11 = new JButton("d7");
		btnNewButton_11.setBackground(Color.WHITE);
		contentPane.add(btnNewButton_11);
		
		btnNewButton_12 = new JButton("e7");
		btnNewButton_12.setBackground(Color.BLACK);
		contentPane.add(btnNewButton_12);
		
		btnNewButton_13 = new JButton("f7");
		btnNewButton_13.setBackground(Color.WHITE);
		contentPane.add(btnNewButton_13);
		
		btnNewButton_14 = new JButton("g7");
		btnNewButton_14.setBackground(Color.BLACK);
		contentPane.add(btnNewButton_14);
		
		btnNewButton_15 = new JButton("h7");
		btnNewButton_15.setBackground(Color.WHITE);
		contentPane.add(btnNewButton_15);
		
		btnNewButton_16 = new JButton("a6");
		btnNewButton_16.setBackground(Color.WHITE);
		contentPane.add(btnNewButton_16);
		
		btnNewButton_17 = new JButton("b6");
		btnNewButton_17.setBackground(Color.BLACK);
		contentPane.add(btnNewButton_17);
		
		btnNewButton_18 = new JButton("c6");
		btnNewButton_18.setBackground(Color.WHITE);
		contentPane.add(btnNewButton_18);
		
		btnNewButton_19 = new JButton("d6");
		btnNewButton_19.setBackground(Color.BLACK);
		contentPane.add(btnNewButton_19);
		
		btnNewButton_20 = new JButton("e6");
		btnNewButton_20.setBackground(Color.WHITE);
		contentPane.add(btnNewButton_20);
		
		btnNewButton_21 = new JButton("f6");
		btnNewButton_21.setBackground(Color.BLACK);
		contentPane.add(btnNewButton_21);
		
		btnNewButton_22 = new JButton("g6");
		btnNewButton_22.setBackground(Color.WHITE);
		contentPane.add(btnNewButton_22);
		
		btnNewButton_23 = new JButton("h6");
		btnNewButton_23.setBackground(Color.BLACK);
		contentPane.add(btnNewButton_23);
		
		btnNewButton_24 = new JButton("a5");
		btnNewButton_24.setBackground(Color.BLACK);
		contentPane.add(btnNewButton_24);
		
		btnNewButton_25 = new JButton("b5");
		btnNewButton_25.setBackground(Color.WHITE);
		contentPane.add(btnNewButton_25);
		
		btnNewButton_26 = new JButton("c5");
		btnNewButton_26.setBackground(Color.BLACK);
		contentPane.add(btnNewButton_26);
		
		btnNewButton_27 = new JButton("d5");
		btnNewButton_27.setBackground(Color.WHITE);
		contentPane.add(btnNewButton_27);
		
		btnNewButton_28 = new JButton("e5");
		btnNewButton_28.setBackground(Color.BLACK);
		contentPane.add(btnNewButton_28);
		
		btnNewButton_29 = new JButton("f5");
		btnNewButton_29.setBackground(Color.WHITE);
		contentPane.add(btnNewButton_29);
		
		btnNewButton_30 = new JButton("g5");
		btnNewButton_30.setBackground(Color.BLACK);
		contentPane.add(btnNewButton_30);
		
		btnNewButton_31 = new JButton("h5");
		btnNewButton_31.setBackground(Color.WHITE);
		contentPane.add(btnNewButton_31);
		
		btnNewButton_32 = new JButton("a4");
		btnNewButton_32.setBackground(Color.WHITE);
		contentPane.add(btnNewButton_32);
		
		btnNewButton_33 = new JButton("b4");
		btnNewButton_33.setBackground(Color.BLACK);
		contentPane.add(btnNewButton_33);
		
		btnNewButton_34 = new JButton("c4");
		btnNewButton_34.setBackground(Color.WHITE);
		contentPane.add(btnNewButton_34);
		
		btnNewButton_35 = new JButton("d4");
		btnNewButton_35.setBackground(Color.BLACK);
		contentPane.add(btnNewButton_35);
		
		btnNewButton_36 = new JButton("e4");
		btnNewButton_36.setBackground(Color.WHITE);
		contentPane.add(btnNewButton_36);
		
		btnNewButton_37 = new JButton("f4");
		btnNewButton_37.setBackground(Color.BLACK);
		contentPane.add(btnNewButton_37);
		
		btnNewButton_38 = new JButton("h4");
		btnNewButton_38.setBackground(Color.WHITE);
		contentPane.add(btnNewButton_38);
		
		btnNewButton_39 = new JButton("g4");
		btnNewButton_39.setBackground(Color.BLACK);
		contentPane.add(btnNewButton_39);
		
		btnNewButton_40 = new JButton("a3");
		btnNewButton_40.setBackground(Color.BLACK);
		contentPane.add(btnNewButton_40);
		
		btnNewButton_41 = new JButton("b3");
		btnNewButton_41.setBackground(Color.WHITE);
		contentPane.add(btnNewButton_41);
		
		btnNewButton_42 = new JButton("c3");
		btnNewButton_42.setBackground(Color.BLACK);
		contentPane.add(btnNewButton_42);
		
		btnNewButton_43 = new JButton("d3");
		btnNewButton_43.setBackground(Color.WHITE);
		contentPane.add(btnNewButton_43);
		
		btnNewButton_44 = new JButton("e3");
		btnNewButton_44.setBackground(Color.BLACK);
		contentPane.add(btnNewButton_44);
		
		btnNewButton_45 = new JButton("f3");
		btnNewButton_45.setBackground(Color.WHITE);
		contentPane.add(btnNewButton_45);
		
		btnNewButton_46 = new JButton("g3");
		btnNewButton_46.setBackground(Color.BLACK);
		contentPane.add(btnNewButton_46);
		
		btnNewButton_47 = new JButton("h3");
		btnNewButton_47.setBackground(Color.WHITE);
		contentPane.add(btnNewButton_47);
		
		btnNewButton_48 = new JButton("a2");
		btnNewButton_48.setBackground(Color.WHITE);
		contentPane.add(btnNewButton_48);
		
		btnNewButton_49 = new JButton("b2");
		btnNewButton_49.setBackground(Color.BLACK);
		contentPane.add(btnNewButton_49);
		
		btnNewButton_50 = new JButton("c2");
		btnNewButton_50.setBackground(Color.WHITE);
		contentPane.add(btnNewButton_50);
		
		btnNewButton_51 = new JButton("d2");
		btnNewButton_51.setBackground(Color.BLACK);
		contentPane.add(btnNewButton_51);
		
		btnNewButton_52 = new JButton("e2");
		btnNewButton_52.setBackground(Color.WHITE);
		contentPane.add(btnNewButton_52);
		
		btnNewButton_53 = new JButton("f2");
		btnNewButton_53.setBackground(Color.BLACK);
		contentPane.add(btnNewButton_53);
		
		btnNewButton_54 = new JButton("g2");
		btnNewButton_54.setBackground(Color.WHITE);
		contentPane.add(btnNewButton_54);
		
		btnNewButton_55 = new JButton("h2");
		btnNewButton_55.setBackground(Color.BLACK);
		contentPane.add(btnNewButton_55);
		
		btnNewButton_56 = new JButton("a1");
		btnNewButton_56.setBackground(Color.BLACK);
		contentPane.add(btnNewButton_56);
		
		btnNewButton_57 = new JButton("b1");
		btnNewButton_57.setBackground(Color.WHITE);
		contentPane.add(btnNewButton_57);
		
		btnNewButton_58 = new JButton("c1");
		btnNewButton_58.setBackground(Color.BLACK);
		contentPane.add(btnNewButton_58);
		
		btnNewButton_59 = new JButton("d1");
		btnNewButton_59.setBackground(Color.WHITE);
		contentPane.add(btnNewButton_59);
		
		btnNewButton_60 = new JButton("e1");
		btnNewButton_60.setBackground(Color.BLACK);
		contentPane.add(btnNewButton_60);
		
		btnNewButton_61 = new JButton("f1");
		btnNewButton_61.setBackground(Color.WHITE);
		contentPane.add(btnNewButton_61);
		
		btnNewButton_62 = new JButton("h1");
		btnNewButton_62.setBackground(Color.BLACK);
		contentPane.add(btnNewButton_62);
		
		btnNewButton_63 = new JButton("g1");
		btnNewButton_63.setBackground(Color.WHITE);
		contentPane.add(btnNewButton_63);
		
		createButtons();
		setFiguren();
		ButtonListenerErzeugen();
		
		
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
		for (Piece whiteFigur: _whiteFiguren)
		{
			int whiteCoordinate= (int) whiteFigur.getCoordinate();
			if(whiteFigur instanceof Pawn)
    		{
    			_buttons.get(whiteCoordinate).setIcon(new ImageIcon(ChessGui.class.getResource("/Piece_Images/White_Pawn.png")));
    		}
    		if(whiteFigur instanceof Knight)
    		{
    			_buttons.get(whiteCoordinate).setIcon(new ImageIcon(ChessGui.class.getResource("/Piece_Images/White_Knight.png")));
    		}
    		if(whiteFigur instanceof Bishop)
    		{
    			_buttons.get(whiteCoordinate).setIcon(new ImageIcon(ChessGui.class.getResource("/Piece_Images/White_Bishop.png")));
    		}
    		if(whiteFigur instanceof Rook)
    		{
    			_buttons.get(whiteCoordinate).setIcon(new ImageIcon(ChessGui.class.getResource("/Piece_Images/White_Rook.png")));
    		}
    		if(whiteFigur instanceof Queen)
    		{
    			_buttons.get(whiteCoordinate).setIcon(new ImageIcon(ChessGui.class.getResource("/Piece_Images/White_Queen.png")));
    		}
    		if(whiteFigur instanceof King)
    		{
    			_buttons.get(whiteCoordinate).setIcon(new ImageIcon(ChessGui.class.getResource("/Piece_Images/White_King.png")));
    		}
    		
    		for (Piece blackFigur: _blackFiguren)
    		{
    			int blackCoordinate= (int) blackFigur.getCoordinate();
    			if(blackFigur instanceof Pawn)
        		{
        			_buttons.get(blackCoordinate).setIcon(new ImageIcon(ChessGui.class.getResource("/Piece_Images/Black_Pawn.png")));
        		}
        		if(blackFigur instanceof Knight)
        		{
        			_buttons.get(blackCoordinate).setIcon(new ImageIcon(ChessGui.class.getResource("/Piece_Images/Black_Knight.png")));
        		}
        		if(blackFigur instanceof Bishop)
        		{
        			_buttons.get(blackCoordinate).setIcon(new ImageIcon(ChessGui.class.getResource("/Piece_Images/Black_Bishop.png")));
        		}
        		if(blackFigur instanceof Rook)
        		{
        			_buttons.get(blackCoordinate).setIcon(new ImageIcon(ChessGui.class.getResource("/Piece_Images/Black_Rook.png")));
        		}
        		if(blackFigur instanceof Queen)
        		{
        			_buttons.get(blackCoordinate).setIcon(new ImageIcon(ChessGui.class.getResource("/Piece_Images/Black_Queen.png")));
        		}
        		if(blackFigur instanceof King)
        		{
        			_buttons.get(blackCoordinate).setIcon(new ImageIcon(ChessGui.class.getResource("/Piece_Images/Black_King.png")));
        		}
    		}
		}
	}

	private void createButtons() {
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (_buttons.contains(e.getSource()))
			{
				_letzterGedrueckterButton = _gedrueckterButton;
				_gedrueckterButton = _buttons.indexOf(e.getSource());
				_lblNewLabel.setText("Alte Koordinate: " + Integer.toString(_letzterGedrueckterButton));
				_lblNewLabel_1.setText("Neue Koordinate: " + Integer.toString(_gedrueckterButton));
			}
		else
			{
				_posCalc = new PositionCalc(_position);
				ArrayList<Position> legalePositionen = _posCalc.getLegalFollowingPositions();
				Position positionSpeicher = new Position(_position);
				positionSpeicher.makeMove((byte)_letzterGedrueckterButton, (byte)_gedrueckterButton);
				for(Position p: legalePositionen)
				{
					if (p.getFen().equals(positionSpeicher.getFen()))
					{
						_position = positionSpeicher;
					}
				}
				setFiguren();
				System.out.println(_position.getFen());
			}
		
	}
}