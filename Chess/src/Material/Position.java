package Material;

import java.util.HashMap;
import java.util.Map;
import Fachwerte.Fen;

public class Position implements Comparable<Object>{
	private Map<Byte, Piece> _whiteFiguren = new HashMap<>(16, (float) 1.0);
	private Map<Byte, Piece> _blackFiguren = new HashMap<>(16, (float) 1.0);
	public boolean _zugrecht;
	private boolean _whiteCanCastle[] = { false, false };
	private boolean _blackCanCastle[] = { false, false };
	private byte _enpassant;
	private byte _zuegeKleiner50;
	private short _zuegeGesamt;
	private int _comparator =0;

	public Position(Fen fenPosition) {
		createPosition(fenPosition);
	}

	public Position(Position copyable) {
		this._whiteFiguren = copyMap(copyable.getWhiteFiguren());
		this._blackFiguren = copyMap(copyable.getBlackFiguren());
		this._whiteCanCastle = copyArray(copyable.getWhiteCastleRights());
		this._blackCanCastle = copyArray(copyable.getBlackCastleRights());

		this._zugrecht = copyable.getZugrecht();
		this._enpassant = copyable.getEnPassant();
		this._zuegeKleiner50 = copyable.getZuegeKleiner50();
		this._zuegeGesamt = copyable.getZuegeGesamt();
		
	}

	private Map<Byte, Piece> copyMap(Map<Byte, Piece> copyable) {
		Map<Byte, Piece> neueMap = new HashMap<>(16, (float) 1.0);
		for (Map.Entry<Byte, Piece> piece : copyable.entrySet())

		{
			Piece figur = piece.getValue();
			if (figur instanceof Pawn) {
				neueMap.put(figur.getCoordinate(), (Pawn) new Pawn(figur.getCoordinate(), figur.getColor()));
			}
			if (figur instanceof Knight) {
				neueMap.put(figur.getCoordinate(), (Knight) new Knight(figur.getCoordinate(), figur.getColor()));
			}
			if (figur instanceof Bishop) {
				neueMap.put(figur.getCoordinate(), (Bishop) new Bishop(figur.getCoordinate(), figur.getColor()));
			}
			if (figur instanceof Rook) {
				neueMap.put(figur.getCoordinate(), (Rook) new Rook(figur.getCoordinate(), figur.getColor()));
			}
			if (figur instanceof Queen) {
				neueMap.put(figur.getCoordinate(), (Queen) new Queen(figur.getCoordinate(), figur.getColor()));
			}
			if (figur instanceof King) {
				neueMap.put(figur.getCoordinate(), (King) new King(figur.getCoordinate(), figur.getColor()));
			}
		}
		return neueMap;
	}

	private boolean[] copyArray(boolean[] copyable) {
		boolean[] array = { false, false };
		array[0] = copyable[0];
		array[1] = copyable[1];
		return array;
	}

	private void createPosition(Fen fenPosition) {
		fenToFiguren(fenPosition);
		fenToZugrecht(fenPosition);
		fenToZuegeKleiner50(fenPosition);
		fenToEnPassant(fenPosition);
		fenToCanCastle(fenPosition);
		fenToZuegeGesamt(fenPosition);
	}

	public void makeMove(byte alteFigurPosition, byte neueFigurPosition) {
		byte enPassant = _enpassant;
		_enpassant = -1;
		_zuegeKleiner50++;
		byte neuePos = neueFigurPosition;
		if (_zugrecht) {

			if (_whiteFiguren.get(alteFigurPosition) instanceof Pawn) {
				_zuegeKleiner50 = 0;
				if (Math.abs(alteFigurPosition - neueFigurPosition) == 16) {
					_enpassant = (byte) (alteFigurPosition + ((neueFigurPosition - alteFigurPosition) / 2));
				} else if (neuePos == enPassant) {
					neuePos = (byte) (neuePos + 8);
				}

			} else if (_whiteFiguren.get(alteFigurPosition) instanceof King) {
				_whiteCanCastle[0] = false;
				_whiteCanCastle[1] = false;
				if (alteFigurPosition == 60 && neueFigurPosition == 62) {
					_whiteFiguren.get((byte) 63).setCoordinate((byte) 61);
					_whiteFiguren.put((byte) 61, _whiteFiguren.remove((byte) 63));
				}

				else if (alteFigurPosition == 60 && neueFigurPosition == 58) {
					_whiteFiguren.get((byte) 56).setCoordinate((byte) 59);
					_whiteFiguren.put((byte) 59, _whiteFiguren.remove((byte) 56));
				}
			} else if (_whiteFiguren.get(alteFigurPosition) instanceof Rook) {
				if (alteFigurPosition == 56) {
					_whiteCanCastle[1] = false;
				} else if (alteFigurPosition == 63) {
					_whiteCanCastle[0] = false;
				}
			}

			_whiteFiguren.get(alteFigurPosition).setCoordinate(neueFigurPosition);
			_zugrecht = false;
			Piece capturedPiece = _blackFiguren.remove(neuePos);
			if (capturedPiece instanceof Rook) {
				if (capturedPiece.getCoordinate() == 7) {
					_blackCanCastle[0] = false;
				} else if (capturedPiece.getCoordinate() == 0) {
					_blackCanCastle[1] = false;
				}
			}
			_whiteFiguren.put(neueFigurPosition, _whiteFiguren.remove(alteFigurPosition));

		} else {
			_zuegeGesamt++;

			if (_blackFiguren.get(alteFigurPosition) instanceof Pawn) {
				_zuegeKleiner50 = 0;
				if (Math.abs(alteFigurPosition - neueFigurPosition) == 16) {
					_enpassant = (byte) (alteFigurPosition + ((neueFigurPosition - alteFigurPosition) / 2));
				} else if (neuePos == enPassant) {
					neuePos = (byte) (neuePos - 8);
				}

			} else if (_blackFiguren.get(alteFigurPosition) instanceof King) {
				_blackCanCastle[0] = false;
				_blackCanCastle[1] = false;
				if (alteFigurPosition == 4 && neueFigurPosition == 6) {
					_blackFiguren.get((byte) 7).setCoordinate((byte) 5);
					_blackFiguren.put((byte) 5, _blackFiguren.remove((byte) 7));
				} else if (alteFigurPosition == 4 && neueFigurPosition == 2) {
					_blackFiguren.get((byte) 0).setCoordinate((byte) 3);
					_blackFiguren.put((byte) 3, _blackFiguren.remove((byte) 0));
				}
			}
			if (_blackFiguren.get(alteFigurPosition) instanceof Rook) {
				if (alteFigurPosition == 0) {
					_blackCanCastle[1] = false;
				}
				if (alteFigurPosition == 7) {
					_blackCanCastle[0] = false;
				}
			}

			_blackFiguren.get(alteFigurPosition).setCoordinate(neueFigurPosition);

			_zugrecht = true;

			Piece capturedPiece = _whiteFiguren.remove(neuePos);
			if (capturedPiece instanceof Rook) {
				if (capturedPiece.getCoordinate() == 63) {
					_whiteCanCastle[0] = false;
				} else if (capturedPiece.getCoordinate() == 56) {
					_whiteCanCastle[1] = false;
				}
			}

			_blackFiguren.put(neueFigurPosition, _blackFiguren.remove(alteFigurPosition));
		}

	}

	public void promotion(Piece promotedFigur) {
		if (!_zugrecht) {
			_whiteFiguren.replace(promotedFigur.getCoordinate(), promotedFigur);
		} else {
			_blackFiguren.replace(promotedFigur.getCoordinate(), promotedFigur);
		}
	}

	// Alle fenTo Methoden
	private void fenToFiguren(Fen fenPosition) {

		String FigurPosition = fenPosition.getFigurPositionen();
		byte counter = 0;
		while (!FigurPosition.equals("")) {
			switch (FigurPosition.charAt(0)) {

			case 'p':
				_blackFiguren.put(counter, new Pawn(counter, false));
				counter++;
				FigurPosition = FigurPosition.substring(1);
				break;
			case 'P':
				_whiteFiguren.put(counter, new Pawn(counter, true));
				counter++;
				FigurPosition = FigurPosition.substring(1);
				break;
			case 'n':
				_blackFiguren.put(counter, new Knight(counter, false));
				counter++;
				FigurPosition = FigurPosition.substring(1);
				break;
			case 'N':
				_whiteFiguren.put(counter, new Knight(counter, true));
				counter++;
				FigurPosition = FigurPosition.substring(1);
				break;
			case 'b':
				_blackFiguren.put(counter, new Bishop(counter, false));
				counter++;
				FigurPosition = FigurPosition.substring(1);
				break;
			case 'B':
				_whiteFiguren.put(counter, new Bishop(counter, true));
				counter++;
				FigurPosition = FigurPosition.substring(1);
				break;
			case 'r':
				_blackFiguren.put(counter, new Rook(counter, false));
				counter++;
				FigurPosition = FigurPosition.substring(1);
				break;
			case 'R':
				_whiteFiguren.put(counter, new Rook(counter, true));
				counter++;
				FigurPosition = FigurPosition.substring(1);
				break;
			case 'q':
				_blackFiguren.put(counter, new Queen(counter, false));
				counter++;
				FigurPosition = FigurPosition.substring(1);
				break;
			case 'Q':
				_whiteFiguren.put(counter, new Queen(counter, true));
				counter++;
				FigurPosition = FigurPosition.substring(1);
				break;
			case 'k':
				_blackFiguren.put(counter, new King(counter, false));
				counter++;
				FigurPosition = FigurPosition.substring(1);
				break;
			case 'K':
				_whiteFiguren.put(counter, new King(counter, true));
				counter++;
				FigurPosition = FigurPosition.substring(1);
				break;
			case '/':
				FigurPosition = FigurPosition.substring(1);
				break;
			default:
				counter = (byte) (counter + (FigurPosition.charAt(0) - 48));
				FigurPosition = FigurPosition.substring(1);
				break;
			}
		}

	}

	private void fenToZugrecht(Fen fenPosition) {
		if (fenPosition.getZugrecht().equals("w")) {
			_zugrecht = true;
		} else {
			_zugrecht = false;
		}
	}

	private void fenToZuegeKleiner50(Fen fenPosition) {
		_zuegeKleiner50 = Byte.parseByte(fenPosition.getZuegeKleiner50());

	}

	private void fenToEnPassant(Fen fenPosition) {
		String enPassant = fenPosition.getEnPassant();
		switch (enPassant) {
		case "a3":
			_enpassant = 40;
			break;
		case "b3":
			_enpassant = 41;
			break;
		case "c3":
			_enpassant = 42;
			break;
		case "d3":
			_enpassant = 43;
			break;
		case "e3":
			_enpassant = 44;
			break;
		case "f3":
			_enpassant = 45;
			break;
		case "g3":
			_enpassant = 46;
			break;
		case "h3":
			_enpassant = 47;
			break;
		case "a6":
			_enpassant = 16;
			break;
		case "b6":
			_enpassant = 17;
			break;
		case "c6":
			_enpassant = 18;
			break;
		case "d6":
			_enpassant = 19;
			break;
		case "e6":
			_enpassant = 20;
			break;
		case "f6":
			_enpassant = 21;
			break;
		case "g6":
			_enpassant = 22;
			break;
		case "h6":
			_enpassant = 23;
			break;
		default:
			_enpassant = -1;
			break;
		}
	}

	private void fenToCanCastle(Fen fenPosition) {
		if (fenPosition.getCastleRights().contains("K")) {
			_whiteCanCastle[0] = true;
		}
		if (fenPosition.getCastleRights().contains("Q")) {
			_whiteCanCastle[1] = true;
		}
		if (fenPosition.getCastleRights().contains("k")) {
			_blackCanCastle[0] = true;
		}
		if (fenPosition.getCastleRights().contains("q")) {
			_blackCanCastle[1] = true;
		}
	}

	private void fenToZuegeGesamt(Fen fenPosition) {
		_zuegeGesamt = Byte.parseByte(fenPosition.getZuegeGesamt());

	}
	// Getter

	public String getFen() {
		String placement = "0000000000000000000000000000000000000000000000000000000000000000";
		char[] array = placement.toCharArray();

		for (Map.Entry<Byte, Piece> whitePiece : _whiteFiguren.entrySet()) {
			byte platz = whitePiece.getValue().getCoordinate();

			if (whitePiece.getValue() instanceof Pawn) {
				array[platz] = 'P';
			} else if (whitePiece.getValue() instanceof Knight) {
				array[platz] = 'N';
			} else if (whitePiece.getValue() instanceof Bishop) {
				array[platz] = 'B';
			} else if (whitePiece.getValue() instanceof Rook) {
				array[platz] = 'R';
			} else if (whitePiece.getValue() instanceof Queen) {
				array[platz] = 'Q';
			} else {
				array[platz] = 'K';
			}

		}
		for (Map.Entry<Byte, Piece> blackPiece : _blackFiguren.entrySet()) {
			byte platz = blackPiece.getValue().getCoordinate();

			if (blackPiece.getValue() instanceof Pawn) {
				array[platz] = 'p';
			} else if (blackPiece.getValue() instanceof Knight) {
				array[platz] = 'n';
			} else if (blackPiece.getValue() instanceof Bishop) {
				array[platz] = 'b';
			} else if (blackPiece.getValue() instanceof Rook) {
				array[platz] = 'r';
			} else if (blackPiece.getValue() instanceof Queen) {
				array[platz] = 'q';
			} else {
				array[platz] = 'k';
			}
		}

		placement = new String(array);

		String slashes = placement.substring(0, 8) + "/";
		slashes = slashes + placement.substring(8, 16) + "/";
		slashes = slashes + placement.substring(16, 24) + "/";
		slashes = slashes + placement.substring(24, 32) + "/";
		slashes = slashes + placement.substring(32, 40) + "/";
		slashes = slashes + placement.substring(40, 48) + "/";
		slashes = slashes + placement.substring(48, 56) + "/";
		slashes = slashes + placement.substring(56, 64);
		slashes = slashes.replace("00000000", "8");
		slashes = slashes.replace("0000000", "7");
		slashes = slashes.replace("000000", "6");
		slashes = slashes.replace("00000", "5");
		slashes = slashes.replace("0000", "4");
		slashes = slashes.replace("000", "3");
		slashes = slashes.replace("00", "2");
		slashes = slashes.replace("0", "1");
		placement = slashes;

		String zugrecht = "";
		if (getZugrecht()) {
			zugrecht = "w";
		} else {
			zugrecht = "b";
		}

		String castling = "";
		if (getWhiteCastleRights()[0]) {
			castling = castling + "K";
		}
		if (getWhiteCastleRights()[1]) {
			castling = castling + "Q";
		}
		if (getBlackCastleRights()[0]) {
			castling = castling + "k";
		}
		if (getBlackCastleRights()[1]) {
			castling = castling + "q";
		}
		if (castling.equals("")) {
			castling = "-";
		}

		String enPassant = "";
		switch (_enpassant) {
		case 40:
			enPassant = "a3";
			break;
		case 41:
			enPassant = "b3";
			break;
		case 42:
			enPassant = "c3";
			break;
		case 43:
			enPassant = "d3";
			break;
		case 44:
			enPassant = "e3";
			break;
		case 45:
			enPassant = "f3";
			break;
		case 46:
			enPassant = "g3";
			break;
		case 47:
			enPassant = "h3";
			break;
		case 16:
			enPassant = "a6";
			break;
		case 17:
			enPassant = "b6";
			break;
		case 18:
			enPassant = "c6";
			break;
		case 19:
			enPassant = "d6";
			break;
		case 20:
			enPassant = "e6";
			break;
		case 21:
			enPassant = "f6";
			break;
		case 22:
			enPassant = "g6";
			break;
		case 23:
			enPassant = "h6";
			break;
		default:
			enPassant = "-";
			break;
		}

		String halfmoves = "" + _zuegeKleiner50;

		String fullmoves = "" + _zuegeGesamt;

		return placement + " " + zugrecht + " " + castling + " " + enPassant + " " + halfmoves + " " + fullmoves;
	}

	public String getPlacement() {
		String placement = "0000000000000000000000000000000000000000000000000000000000000000";
		char[] array = placement.toCharArray();

		for (Map.Entry<Byte, Piece> whitePiece : _whiteFiguren.entrySet()) {
			byte platz = whitePiece.getValue().getCoordinate();

			if (whitePiece.getValue() instanceof Pawn) {
				array[platz] = 'P';
			} else if (whitePiece.getValue() instanceof Knight) {
				array[platz] = 'N';
			} else if (whitePiece.getValue() instanceof Bishop) {
				array[platz] = 'B';
			} else if (whitePiece.getValue() instanceof Rook) {
				array[platz] = 'R';
			} else if (whitePiece.getValue() instanceof Queen) {
				array[platz] = 'Q';
			} else {
				array[platz] = 'K';
			}

		}
		for (Map.Entry<Byte, Piece> blackPiece : _blackFiguren.entrySet()) {
			byte platz = blackPiece.getValue().getCoordinate();

			if (blackPiece.getValue() instanceof Pawn) {
				array[platz] = 'p';
			} else if (blackPiece.getValue() instanceof Knight) {
				array[platz] = 'n';
			} else if (blackPiece.getValue() instanceof Bishop) {
				array[platz] = 'b';
			} else if (blackPiece.getValue() instanceof Rook) {
				array[platz] = 'r';
			} else if (blackPiece.getValue() instanceof Queen) {
				array[platz] = 'q';
			} else {
				array[platz] = 'k';
			}
		}

		placement = new String(array);
		placement = placement+_zugrecht+_enpassant+_whiteCanCastle[0]+_whiteCanCastle[1]+_blackCanCastle[0]+_blackCanCastle[1];
		//System.out.println(placement);
		return placement;
	}

	public Map<Byte, Piece> getWhiteFiguren() {
		return _whiteFiguren;
	}

	public Map<Byte, Piece> getBlackFiguren() {
		return _blackFiguren;
	}

	public boolean getZugrecht() {
		return _zugrecht;
	}

	public boolean[] getWhiteCastleRights() {
		return _whiteCanCastle;
	}

	public boolean[] getBlackCastleRights() {
		return _blackCanCastle;
	}

	public byte getEnPassant() {
		return _enpassant;
	}

	public byte getZuegeKleiner50() {
		return _zuegeKleiner50;
	}

	public short getZuegeGesamt() {
		return _zuegeGesamt;
	}
	
	public int getComparator()
	{
		return _comparator;
	}
	
	public void setComparator(double d)
	{
		_comparator = (int) (d*10000);
	}

	@Override
	public boolean equals(Object pos) {
		return (getFen().equals((((Position) pos).getFen())));
	}

	@Override
	
	// sortiert f??r schwarz gut aber f??r wei?? nicht?
	public int compareTo(Object comparable) {
//		if(_zugrecht)
//		{
//			return getComparator() - ((Position) comparable).getComparator();
//			
//		}
//		else
//		{
			return ((Position) comparable).getComparator()-getComparator();
//		}
	
	}

}
