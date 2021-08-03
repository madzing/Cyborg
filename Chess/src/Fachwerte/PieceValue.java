package Fachwerte;

public class PieceValue {
	private final byte _value;
	
	private PieceValue(byte value) {
		_value = value;
	}
	
	public static PieceValue select(byte value) {
		return new PieceValue(value);
	}
	
	public byte getValue() {
		return _value;
	}
}
