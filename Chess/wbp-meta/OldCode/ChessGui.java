package Gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
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

import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import java.awt.Button;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JMenuBar;
import java.awt.Component;
import javax.swing.Box;

public class ChessGui extends JFrame implements ActionListener{

	private JPanel contentPane;
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
	
	/**
	 * Create the frame.
	 */
	public ChessGui(Position position) {
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
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(8, 8));
		
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
		JButton btnNewButton = new JButton("a8");
		btnNewButton.setBackground(Color.WHITE);
		contentPane.add(btnNewButton);
		_buttons.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("b8");
		btnNewButton_1.setBackground(Color.BLACK);
		contentPane.add(btnNewButton_1);
		_buttons.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("c8");
		btnNewButton_2.setBackground(Color.WHITE);
		contentPane.add(btnNewButton_2);
		_buttons.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("d8");
		btnNewButton_3.setBackground(Color.BLACK);
		contentPane.add(btnNewButton_3);
		_buttons.add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("e8");
		btnNewButton_4.setBackground(Color.WHITE);
		contentPane.add(btnNewButton_4);
		_buttons.add(btnNewButton_4);
		
		JButton btnNewButton_5 = new JButton("f8");
		btnNewButton_5.setBackground(Color.BLACK);
		contentPane.add(btnNewButton_5);
		_buttons.add(btnNewButton_5);
		
		JButton btnNewButton_6 = new JButton("g8");
		btnNewButton_6.setBackground(Color.WHITE);
		contentPane.add(btnNewButton_6);
		_buttons.add(btnNewButton_6);
		
		JButton btnNewButton_7 = new JButton("h8");
		btnNewButton_7.setBackground(Color.BLACK);
		contentPane.add(btnNewButton_7);
		_buttons.add(btnNewButton_7);
		
		JButton btnNewButton_8 = new JButton("a7");
		btnNewButton_8.setBackground(Color.BLACK);
		contentPane.add(btnNewButton_8);
		_buttons.add(btnNewButton_8);
		
		JButton btnNewButton_9 = new JButton("b7");
		btnNewButton_9.setBackground(Color.WHITE);
		contentPane.add(btnNewButton_9);
		_buttons.add(btnNewButton_9);
		
		JButton btnNewButton_10 = new JButton("c7");
		btnNewButton_10.setBackground(Color.BLACK);
		contentPane.add(btnNewButton_10);
		_buttons.add(btnNewButton_10);
		
		JButton btnNewButton_11 = new JButton("d7");
		btnNewButton_11.setBackground(Color.WHITE);
		contentPane.add(btnNewButton_11);
		_buttons.add(btnNewButton_11);
		
		JButton btnNewButton_12 = new JButton("e7");
		btnNewButton_12.setBackground(Color.BLACK);
		contentPane.add(btnNewButton_12);
		_buttons.add(btnNewButton_12);
		
		JButton btnNewButton_13 = new JButton("f7");
		btnNewButton_13.setBackground(Color.WHITE);
		contentPane.add(btnNewButton_13);
		_buttons.add(btnNewButton_13);
		
		JButton btnNewButton_14 = new JButton("g7");
		btnNewButton_14.setBackground(Color.BLACK);
		contentPane.add(btnNewButton_14);
		_buttons.add(btnNewButton_14);
		
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
		
		JButton btnNewButton_18 = new JButton("c6");
		btnNewButton_18.setBackground(Color.WHITE);
		contentPane.add(btnNewButton_18);
		_buttons.add(btnNewButton_18);
		
		JButton btnNewButton_19 = new JButton("d6");
		btnNewButton_19.setBackground(Color.BLACK);
		contentPane.add(btnNewButton_19);
		_buttons.add(btnNewButton_19);
		
		JButton btnNewButton_20 = new JButton("e6");
		btnNewButton_20.setBackground(Color.WHITE);
		contentPane.add(btnNewButton_20);
		_buttons.add(btnNewButton_20);
		
		JButton btnNewButton_21 = new JButton("f6");
		btnNewButton_21.setBackground(Color.BLACK);
		contentPane.add(btnNewButton_21);
		_buttons.add(btnNewButton_21);
		
		JButton btnNewButton_22 = new JButton("g6");
		btnNewButton_22.setBackground(Color.WHITE);
		contentPane.add(btnNewButton_22);
		_buttons.add(btnNewButton_22);
		
		JButton btnNewButton_23 = new JButton("h6");
		btnNewButton_23.setBackground(Color.BLACK);
		contentPane.add(btnNewButton_23);
		_buttons.add(btnNewButton_23);
		
		JButton btnNewButton_24 = new JButton("a5");
		btnNewButton_24.setBackground(Color.BLACK);
		contentPane.add(btnNewButton_24);
		_buttons.add(btnNewButton_24);
		
		JButton btnNewButton_25 = new JButton("b5");
		btnNewButton_25.setBackground(Color.WHITE);
		contentPane.add(btnNewButton_25);
		_buttons.add(btnNewButton_25);
		
		JButton btnNewButton_26 = new JButton("c5");
		btnNewButton_26.setBackground(Color.BLACK);
		contentPane.add(btnNewButton_26);
		_buttons.add(btnNewButton_26);
		
		JButton btnNewButton_27 = new JButton("d5");
		btnNewButton_27.setBackground(Color.WHITE);
		contentPane.add(btnNewButton_27);
		_buttons.add(btnNewButton_27);
		
		JButton btnNewButton_28 = new JButton("e5");
		btnNewButton_28.setBackground(Color.BLACK);
		contentPane.add(btnNewButton_28);
		_buttons.add(btnNewButton_28);
		
		JButton btnNewButton_29 = new JButton("f5");
		btnNewButton_29.setBackground(Color.WHITE);
		contentPane.add(btnNewButton_29);
		_buttons.add(btnNewButton_29);
		
		JButton btnNewButton_30 = new JButton("g5");
		btnNewButton_30.setBackground(Color.BLACK);
		contentPane.add(btnNewButton_30);
		_buttons.add(btnNewButton_30);
		
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
		
		JButton btnNewButton_34 = new JButton("c4");
		btnNewButton_34.setBackground(Color.WHITE);
		contentPane.add(btnNewButton_34);
		_buttons.add(btnNewButton_34);
		
		JButton btnNewButton_35 = new JButton("d4");
		btnNewButton_35.setBackground(Color.BLACK);
		contentPane.add(btnNewButton_35);
		_buttons.add(btnNewButton_35);
		
		JButton btnNewButton_36 = new JButton("e4");
		btnNewButton_36.setBackground(Color.WHITE);
		contentPane.add(btnNewButton_36);
		_buttons.add(btnNewButton_36);
		
		JButton btnNewButton_37 = new JButton("f4");
		btnNewButton_37.setBackground(Color.BLACK);
		contentPane.add(btnNewButton_37);
		_buttons.add(btnNewButton_37);
		
		JButton btnNewButton_38 = new JButton("h4");
		btnNewButton_38.setBackground(Color.WHITE);
		contentPane.add(btnNewButton_38);
		_buttons.add(btnNewButton_38);
		
		JButton btnNewButton_39 = new JButton("g4");
		btnNewButton_39.setBackground(Color.BLACK);
		contentPane.add(btnNewButton_39);
		_buttons.add(btnNewButton_39);
		
		JButton btnNewButton_40 = new JButton("a3");
		btnNewButton_40.setBackground(Color.BLACK);
		contentPane.add(btnNewButton_40);
		_buttons.add(btnNewButton_40);
		
		JButton btnNewButton_41 = new JButton("b3");
		btnNewButton_41.setBackground(Color.WHITE);
		contentPane.add(btnNewButton_41);
		_buttons.add(btnNewButton_41);
		
		JButton btnNewButton_42 = new JButton("c3");
		btnNewButton_42.setBackground(Color.BLACK);
		contentPane.add(btnNewButton_42);
		_buttons.add(btnNewButton_42);
		
		JButton btnNewButton_43 = new JButton("d3");
		btnNewButton_43.setBackground(Color.WHITE);
		contentPane.add(btnNewButton_43);
		_buttons.add(btnNewButton_43);
		
		JButton btnNewButton_44 = new JButton("e3");
		btnNewButton_44.setBackground(Color.BLACK);
		contentPane.add(btnNewButton_44);
		_buttons.add(btnNewButton_44);
		
		JButton btnNewButton_45 = new JButton("f3");
		btnNewButton_45.setBackground(Color.WHITE);
		contentPane.add(btnNewButton_45);
		_buttons.add(btnNewButton_45);
		
		JButton btnNewButton_46 = new JButton("g3");
		btnNewButton_46.setBackground(Color.BLACK);
		contentPane.add(btnNewButton_46);
		_buttons.add(btnNewButton_46);
		
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
		btnNewButton_49.addActionListener(this);
		_buttons.add(btnNewButton_49);
				
		JButton btnNewButton_50 = new JButton("c2");
		btnNewButton_50.setBackground(Color.WHITE);
		contentPane.add(btnNewButton_50);
		_buttons.add(btnNewButton_50);		
		
		JButton btnNewButton_51 = new JButton("d2");
		btnNewButton_51.setBackground(Color.BLACK);
		contentPane.add(btnNewButton_51);
		_buttons.add(btnNewButton_51);
		
		JButton btnNewButton_52 = new JButton("e2");
		btnNewButton_52.setBackground(Color.WHITE);
		contentPane.add(btnNewButton_52);
		_buttons.add(btnNewButton_52);
		
		JButton btnNewButton_53 = new JButton("f2");
		btnNewButton_53.setBackground(Color.BLACK);
		contentPane.add(btnNewButton_53);
		_buttons.add(btnNewButton_53);
		
		JButton btnNewButton_54 = new JButton("g2");
		btnNewButton_54.setBackground(Color.WHITE);
		contentPane.add(btnNewButton_54);
		_buttons.add(btnNewButton_54);
		
		JButton btnNewButton_55 = new JButton("h2");
		btnNewButton_55.setBackground(Color.BLACK);
		contentPane.add(btnNewButton_55);
		_buttons.add(btnNewButton_55);
		
		JButton btnNewButton_56 = new JButton("a1");
		btnNewButton_56.setBackground(Color.BLACK);
		contentPane.add(btnNewButton_56);
		_buttons.add(btnNewButton_56);
		
		JButton btnNewButton_57 = new JButton("b1");
		btnNewButton_57.setBackground(Color.WHITE);
		contentPane.add(btnNewButton_57);
		_buttons.add(btnNewButton_57);
		
		JButton btnNewButton_58 = new JButton("c1");
		btnNewButton_58.setBackground(Color.BLACK);
		contentPane.add(btnNewButton_58);
		_buttons.add(btnNewButton_58);
		
		JButton btnNewButton_59 = new JButton("d1");
		btnNewButton_59.setBackground(Color.WHITE);
		contentPane.add(btnNewButton_59);
		_buttons.add(btnNewButton_59);
		
		JButton btnNewButton_60 = new JButton("e1");
		btnNewButton_60.setBackground(Color.BLACK);
		contentPane.add(btnNewButton_60);
		_buttons.add(btnNewButton_60);
		
		JButton btnNewButton_61 = new JButton("f1");
		btnNewButton_61.setBackground(Color.WHITE);
		contentPane.add(btnNewButton_61);
		_buttons.add(btnNewButton_61);
		
		JButton btnNewButton_62 = new JButton("h1");
		btnNewButton_62.setBackground(Color.BLACK);
		contentPane.add(btnNewButton_62);
		_buttons.add(btnNewButton_62);
		
		JButton btnNewButton_63 = new JButton("g1");
		btnNewButton_63.setBackground(Color.WHITE);
		contentPane.add(btnNewButton_63);
		_buttons.add(btnNewButton_63);
		
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
	
	
	


