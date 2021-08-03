package Fachwerte;

import java.util.HashMap;
import java.util.Map;

public class PieceValue {
	private static Map<String, PieceValue> _valueMap = new HashMap<String, PieceValue>();

	private final byte _value;

	private PieceValue(byte value) {
		_value = value;
	}

	public static PieceValue select(byte value) {
		if (!_valueMap.containsKey("" + value)) {
			_valueMap.put("" + value, new PieceValue(value));
		}
		return _valueMap.get("" + value);
	}

	public byte getValue() {
		return _value;
	}

	@Override
	public String toString() {
		return "" + _value;
	}
}
