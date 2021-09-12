package Werkzeuge;

import java.awt.Color;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import Gui.IllegalMoveException;
import Gui.SchachmattException;
import Gui.SpielWerkzeugUI;
import Gui.UnentschiedenException;
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

public class SpielWerkzeug extends BeobachtbaresSubwerkzeug{

	private SpielWerkzeugUI _ui;
	private int _cyborgSchwierigkeit;
	private boolean _isCyborgGame;
	
	private Position _position;
	private Position _positionZwischenSpeicher;
	private ArrayList<Position> _positions;
	private int _aktuellerZug;
	public Map<Byte, Piece> _whiteFiguren;
	public Map<Byte, Piece> _blackFiguren;
	PositionCalc _posCalc;
	
	public static final Color LIGHT_BLUE = new Color(51,153,255);
	
	public SpielWerkzeug(Position position, boolean isCyborgGame)
	{
		_position = position;
		_positionZwischenSpeicher = new Position(_position);
		_positions = new ArrayList<Position>(6000);
		_positions.add(position);
		_aktuellerZug = 0;
		
		_ui = new SpielWerkzeugUI(_position);
		_ui.setVisible(true);
		
		_whiteFiguren = position.getWhiteFiguren();
		_blackFiguren = position.getBlackFiguren();
		
		_posCalc = new PositionCalc(position);
		
		_cyborgSchwierigkeit = 5;
		_isCyborgGame = isCyborgGame;
		
		registriereUIAktionen();
	}
	
	private void registriereUIAktionen() 
	{
		for (int i = 0; i <64; i++) //Den Spiel Feld Knoepfen werden die Listener hinzugefuegt
		{
			_ui._spielButtons.get(i).addActionListener(new ActionListener()
					{
						@Override
						public void actionPerformed(ActionEvent e)
						{
							_ui.spielFeldButtonWurdeGedrueckt(_ui._spielButtons.indexOf(e.getSource()));
						}
					});
		}
		
		_ui._btnGetAktuelleFen.addActionListener(new ActionListener() //Dem GetAktuelleFen Button wird ein Listener hinzugefuegt
				{
					@Override
					public void actionPerformed(ActionEvent e)
					{
						GetAktuelleFen();
					}
				});
		
		_ui._reverseButton.addActionListener(new ActionListener() //Dem Reverse Button wird ein Listener hinzugefuegt
				{
					@Override
					public void actionPerformed(ActionEvent e)
					{
						try
						{
							reverseZug();
						}
						catch(IndexOutOfBoundsException i)
						{
							JOptionPane.showMessageDialog(null, "Du bist schon am Start des Spiels");
						}
					}
				});
		
		_ui._makeMoveButton.addActionListener(new ActionListener() //Dem MakeMove Button wird ein Listener hinzugefuegt
				{
					@Override
					public void actionPerformed(ActionEvent e)
					{
						try
						{	
							makeMove();
						}
						catch(NullPointerException n)
						{
							JOptionPane.showMessageDialog(null, "An der alten Koordinate steht keine Figur deiner Farbe");
						}
						catch(IndexOutOfBoundsException i)
						{
							JOptionPane.showMessageDialog(null, "Das ist ein illegaler Zug");
						}
						catch(SchachmattException sx)
						{
							Object[] options = {"Spiel beenden", "Alle Fens"};
							if(_position._zugrecht)
							{
								int i = JOptionPane.showOptionDialog(null, "Schwarz hat gewonnen", "Gewinner", JOptionPane.DEFAULT_OPTION,
								JOptionPane.PLAIN_MESSAGE, null, options, null);
								if (i == 0)
								{
									_ui.dispose();
								}
								else if (i == 1)
								{
									String leererString = "";
									for(int j = 0; j < _aktuellerZug; j++)
									{
										String fen = _positions.get(j).getFen();
										leererString = leererString + "\r\n" +fen;
										StringSelection stringSelection = new StringSelection (leererString);
										Clipboard clpbrd = Toolkit.getDefaultToolkit ().getSystemClipboard ();
										clpbrd.setContents (stringSelection, null);
									}
									_ui.dispose();
								}
							}	
							else if (_position._zugrecht && _isCyborgGame)
							{
								int i = JOptionPane.showOptionDialog(null, "Der Cyborg hat gewonnen", "Gewinner", JOptionPane.DEFAULT_OPTION,
								JOptionPane.PLAIN_MESSAGE, null, options, null);
								if (i == 0)
								{
									_ui.dispose();
								}
								else if (i == 1)
								{
									String leererString = "";
									for(int j = 0; j < _aktuellerZug; j++)
									{
										String fen = _positions.get(j).getFen();
										leererString = leererString + "\r\n" +fen;
										StringSelection stringSelection = new StringSelection (leererString);
										Clipboard clpbrd = Toolkit.getDefaultToolkit ().getSystemClipboard ();
										clpbrd.setContents (stringSelection, null);
									}
									_ui.dispose();
								}
							}
							else
							{
								int i = JOptionPane.showOptionDialog(null, "Du hast gewonnen", "Gewinner", JOptionPane.DEFAULT_OPTION,
								JOptionPane.PLAIN_MESSAGE, null, options, null);
								if (i == 0)
								{
									_ui.dispose();
								}
								else if (i == 1)
								{
									String leererString = "";
									for(int j = 0; j < _aktuellerZug; j++)
									{
										String fen = _positions.get(j).getFen();
										leererString = leererString + "\r\n" +fen;
										StringSelection stringSelection = new StringSelection (leererString);
										Clipboard clpbrd = Toolkit.getDefaultToolkit ().getSystemClipboard ();
										clpbrd.setContents (stringSelection, null);
									}
									_ui.dispose();
								}
							}
					
						}
						catch(UnentschiedenException ux)
						{
							Object[] options = {"Spiel beenden", "Alle Fens"};
							int i = JOptionPane.showOptionDialog(null, "Unentschieden", "Kein Gewinner", JOptionPane.DEFAULT_OPTION,
							JOptionPane.PLAIN_MESSAGE, null, options, null);
							if (i == 0)
							{
								_ui.dispose();
							}
							else if (i == 1)
							{
								String leererString = "";
								for(int j = 0; j < _aktuellerZug; j++)
								{
									String fen = _positions.get(j).getFen();
									leererString = leererString + "\r\n" +fen;
									StringSelection stringSelection = new StringSelection (leererString);
									Clipboard clpbrd = Toolkit.getDefaultToolkit ().getSystemClipboard ();
									clpbrd.setContents (stringSelection, null);
									_ui.dispose();
								}
							}
						}
						catch(IllegalMoveException ix)
						{
							JOptionPane.showMessageDialog(null, "Das ist ein illegaler Zug");
						}
					}
				});
	}

	public void makeCyborgMove() throws SchachmattException, UnentschiedenException, NullPointerException
	{
		/*
		 * Methode mit der ein Cyborg einen Zug auf dem GUI darstellen kann. 
		 * Es wird einfach eine neue Position aus dem Cyborg erzeugt und diese wird der Positions Liste hinzugefuegt, sowie der aktuelle Zug um einen erhoeht.
		 * Zusaetzlich wird ein Positions Vergleicher erzeugt, dessen Klassen Variablen AlteKoordinate und NeueKoordinate
		 * nun gehighlighted werden koennen.
		 */
		Cyborg Ernd = new Cyborg(_cyborgSchwierigkeit);
		Position altePosition = new Position(_position);
		_position = Ernd.getBestFollowingPosition(_position);
		PositionsVergleicher posVergleicher = new PositionsVergleicher(altePosition, _position);
		posVergleicher.whatMoveWasMade();
		_positions.add(_position);
		_aktuellerZug++;
		PositionCalc neuePosCalc = new PositionCalc(_position);
		ArrayList<Position> neuelegalePositionen = neuePosCalc.getLegalFollowingPositions();
		
		for (Map.Entry<Byte, Piece> entry : _whiteFiguren.entrySet())
		{
			Piece piece = entry.getValue();
			if (piece instanceof King)
			{
				if (((King) piece).isInCheck(_position) && neuelegalePositionen.isEmpty())
				{
					throw new SchachmattException();
				}
			}
		}
		if(neuelegalePositionen.isEmpty())
		{
			throw new UnentschiedenException();
		}
		
		_ui.setPosition(_position);
		_ui.setPositions(_positions);
		_ui.setAktuellerZug(_aktuellerZug);
		_whiteFiguren = _position.getWhiteFiguren();
		_blackFiguren = _position.getBlackFiguren();
		
		_ui.setFigurWurdeGeschlagenLabel(); 
		int alteKoordinate = posVergleicher.getAlteKoordinate();
		int neueKoordinate = posVergleicher.getNeueKoordinate();
		_ui.setFiguren(_whiteFiguren, _blackFiguren);
		_ui.setZugrechtLabel();
		_ui._cyborgHighlightStack.push(new int[]{alteKoordinate, neueKoordinate});
		_ui._spielButtons.get(alteKoordinate).setBackground(LIGHT_BLUE);
		_ui._spielButtons.get(neueKoordinate).setBackground(Color.RED);
		//System.out.println(_position.getFen());
	}
	
	private void promotion()
	{
		/*
		 * Methode um einen Bauern auf einem Endfeld zu bef�rdern.
		 * Wird bei jedem MakeMove im GUI aufgerufen, aber nur funktionstuechtig, wenn ein Scharzer Bauer ein Feld >= 56
		 * oder ein weisser Bauer ein Feld <=7 betritt, oder dorthin schlaegt.
		 * Im Gui gibt es eine Auswahl von 4 Knoepfen, die bei Betaetigen die untenstehenden ints returnen.
		 * piece = 0 wenn Queen. 1 wenn Rook. 2 wenn Bishop. 3 wenn Knight.
		 */
		Object[] optionen = {"Queen", "Rook", "Bishop", "Knight"};
		int piece = 999;
		if ((_ui._gedrueckterButton >=56 && _blackFiguren.get((byte)_ui._letzterGedrueckterButton) instanceof Pawn)|| (_ui._gedrueckterButton <=7 && _whiteFiguren.get((byte)_ui._letzterGedrueckterButton) instanceof Pawn))
		{
			piece = JOptionPane.showOptionDialog(null, "Waehle eine Figur:", null, JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, optionen, null);
			// piece = 0 wenn Queen. 1 wenn Rook. 2 wenn Bishop. 3 wenn Knight.
		}
		if (! _position.getZugrecht())
		{
			if (piece == 0)
			{
				_positionZwischenSpeicher.promotion(new Queen((byte)_ui._gedrueckterButton, false));
			}
			if (piece == 1)
			{
				_positionZwischenSpeicher.promotion(new Rook((byte)_ui._gedrueckterButton, false));
			}
			if (piece == 2)
			{
				_positionZwischenSpeicher.promotion(new Bishop((byte)_ui._gedrueckterButton, false));
			}
			if (piece == 3)
			{
				_positionZwischenSpeicher.promotion(new Knight((byte)_ui._gedrueckterButton, false));
			}

		}
		else
		{
			if (piece == 0)
			{
				_positionZwischenSpeicher.promotion(new Queen((byte)_ui._gedrueckterButton, true));
			}
			if (piece == 1)
			{
				_positionZwischenSpeicher.promotion(new Rook((byte)_ui._gedrueckterButton, true));
			}
			if (piece == 2)
			{
				_positionZwischenSpeicher.promotion(new Bishop((byte)_ui._gedrueckterButton, true));
			}
			if (piece == 3)
			{
				_positionZwischenSpeicher.promotion(new Knight((byte)_ui._gedrueckterButton, true));
			}

		}
	}

	private void makeMove() throws IllegalMoveException, SchachmattException, UnentschiedenException
	{
		_posCalc = new PositionCalc(_position);
		ArrayList<Position> legalePositionen = _posCalc.getLegalFollowingPositions();
		_positionZwischenSpeicher = new Position(_position);
		_positionZwischenSpeicher.makeMove((byte)_ui._letzterGedrueckterButton, (byte)_ui._gedrueckterButton);
		promotion();
		for(Position p: legalePositionen)
		{
			if (p.getFen().equals(_positionZwischenSpeicher.getFen()))
			{
				_position = _positionZwischenSpeicher;
				_aktuellerZug++;
				_positions.add(_position);
			}
		}
		PositionCalc neuePosCalc = new PositionCalc(_position);
		ArrayList<Position> neuelegalePositionen = neuePosCalc.getLegalFollowingPositions();
		if (_position._zugrecht)
		{
			for (Map.Entry<Byte, Piece> entry : _whiteFiguren.entrySet())
			{
				Piece piece = entry.getValue();
				if (piece instanceof King)
				{
					if (((King) piece).isInCheck(_position) && neuelegalePositionen.isEmpty())
					{
						throw new SchachmattException();
					}
				}
			}
		}
		else
		{
			for (Map.Entry<Byte, Piece> entry : _blackFiguren.entrySet())
			{
				Piece piece = entry.getValue();
				if (piece instanceof King)
				{
					if (((King) piece).isInCheck(_position) && neuelegalePositionen.isEmpty())
					{
						throw new SchachmattException();
					}
				}
			}
		}
		if(neuelegalePositionen.isEmpty())
		{
			throw new UnentschiedenException();
		}
		if(!(_positionZwischenSpeicher.equals(_position)))
		{
			throw new IllegalMoveException();
		}
		
		_ui.setPosition(_position);
		_ui.setPositions(_positions);
		_ui.setAktuellerZug(_aktuellerZug);
		_whiteFiguren = _position.getWhiteFiguren();
		_blackFiguren = _position.getBlackFiguren();
		
		_ui.setFigurWurdeGeschlagenLabel(); 
		_ui.setFiguren(_whiteFiguren, _blackFiguren);
		_ui.setZugrechtLabel();
		_ui.resetteFelder();	
	}
	
	private void reverseZug()
	{
		/*
		 * Methode in der entweder die letzte oder beim Spielen gegen den Cyborg die vorletzte Position aufgerufen wird
		 * Es wird unterschieden zwischen:
		 * 1. Cyborg spielt NICHT und es wurde KEINE Figur geschlagen
		 * 2. Cyborg spielt und es wurde KEINE Figur geschlagen
		 * 3. Cyborg spielt und es wurde eine Figur geschlagen
		 * 4. Cyborg spielt NICHT und es wurde eine Figur geschlagen
		 * 
		 * Wenn der Cyborg nicht spielt muss nur die alte Position geladen, die aktuelle geloescht und der aktuelle Zug um ein subtrahiert werden
		 * Sowie das Geschlagene Figuren Label, insofern eine Figur geschlagen wurde.
		 * 
		 * Wenn der Cyborg spielt laueft es genauso ab, aber zusaetzlich wird eine Position geladen.
		 */
		PositionsVergleicher posVergleicher = new PositionsVergleicher(_positions, _aktuellerZug);
		if(_isCyborgGame && posVergleicher.wurdeFigurGeschlagen() == false) // Es wurde keine Figur vom Spieler geschlagen
		{
			_position = _positions.get(_aktuellerZug-1);
			_positions.remove(_aktuellerZug);
			_aktuellerZug--;
			_ui.resetteFelder();
			
		}
		else if (_isCyborgGame && posVergleicher.wurdeFigurGeschlagen() == false) // Es wurde keine Figur vom Cyborg geschlagen
		{
			System.out.println("Keine Figur vom Cyborg geschlagen");
			_position = _positions.get(_aktuellerZug-1);
			_positions.remove(_aktuellerZug);
			_aktuellerZug--;
			_position = _positions.get(_aktuellerZug-1);
			_positions.remove(_aktuellerZug);
			_aktuellerZug--;
			_ui.resetteFelder();
			_ui._cyborgHighlightStack.pop();
			if (!_ui._cyborgHighlightStack.isEmpty())
			{
				int neueKoordinate = _ui._cyborgHighlightStack.peek()[1];
				int alteKoordinate = _ui._cyborgHighlightStack.peek()[0];
				_ui._spielButtons.get(alteKoordinate).setBackground(LIGHT_BLUE);
				_ui._spielButtons.get(neueKoordinate).setBackground(Color.RED);
			}
		}
		else if (_isCyborgGame && posVergleicher.wurdeFigurGeschlagen() == true) // Es wurde eine Figur vom Cyborg geschlagen
		{
			System.out.println("Figur vom Cyborg geschlagen");
			int geschlageneFigur = posVergleicher.welcheFigurWurdeGeschlagen();
			_ui.setFigurWurdeGeschlagenLabelReverse(geschlageneFigur);
			_position = _positions.get(_aktuellerZug-1);
			_positions.remove(_aktuellerZug);
			_aktuellerZug--;
			_position = _positions.get(_aktuellerZug-1);
			_positions.remove(_aktuellerZug);
			_aktuellerZug--;
			_ui.resetteFelder();
			_ui._cyborgHighlightStack.pop();
			if (!_ui._cyborgHighlightStack.isEmpty())
			{
				int neueKoordinate = _ui._cyborgHighlightStack.peek()[1];
				int alteKoordinate = _ui._cyborgHighlightStack.peek()[0];
				_ui._spielButtons.get(alteKoordinate).setBackground(LIGHT_BLUE);
				_ui._spielButtons.get(neueKoordinate).setBackground(Color.RED);
			}
		}
		else // Es wurde eine Figur vom Spieler geschlagen
		{
			int geschlageneFigur = posVergleicher.welcheFigurWurdeGeschlagen();
			_ui.setFigurWurdeGeschlagenLabelReverse(geschlageneFigur);
			_position = _positions.get(_aktuellerZug-1);
			_positions.remove(_aktuellerZug);
			_aktuellerZug--;

			_ui.resetteFelder();
		}
		
		_ui.setPosition(_position);
		_ui.setPositions(_positions);
		_ui.setAktuellerZug(_aktuellerZug);
		_whiteFiguren = _position.getWhiteFiguren();
		_blackFiguren = _position.getBlackFiguren();
		
		_ui.setFiguren(_whiteFiguren, _blackFiguren);
		_ui.setZugrechtLabel();
	}
	
	private void GetAktuelleFen()
	{
		/*
		 * Methode die aufgerufen werden kann wenn der Button Get Aktuelle Fen gedrueckt wird.
		 * Es wird die Aktuelle Fen auf das Windows ClipBoard kopiert und loescht damit natuerlich alles was vorhin im ClipBoard stand.
		 */
		StringSelection stringSelection = new StringSelection (_position.getFen());
		Clipboard clpbrd = Toolkit.getDefaultToolkit ().getSystemClipboard ();
		clpbrd.setContents (stringSelection, null);
	}

	

	





}
