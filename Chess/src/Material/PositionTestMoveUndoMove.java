package Material;

import static org.junit.Assert.*;

import org.junit.Test;

import Fachwerte.Fen;

public class PositionTestMoveUndoMove {

	@Test
	public void test() {
		Fen _startFen= Fen.select("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1");
		Position _startPosition = new Position(_startFen);
		_startPosition.makeMove((byte)52,(byte) 36);
		_startPosition.makeMove((byte)6,(byte) 21);
		_startPosition.makeMove((byte)62,(byte) 45);
		_startPosition.makeMove((byte)1,(byte) 18);
		_startPosition.makeMove((byte)61,(byte) 52);
		_startPosition.makeMove((byte)11,(byte) 19);
		_startPosition.makeMove((byte)60,(byte) 62);
		_startPosition.makeMove((byte)21,(byte) 36);
		_startPosition.makeMove((byte)55,(byte) 39);
		_startPosition.makeMove((byte)36,(byte) 21);
		_startPosition.makeMove((byte)39,(byte) 31);
		_startPosition.makeMove((byte)14,(byte) 30);
		//_startPosition.makeMove((byte)31,(byte) 22);
		//_startPosition.undoLastMove();
		_startPosition.undoLastMove();
		_startPosition.undoLastMove();
		_startPosition.undoLastMove();
		_startPosition.undoLastMove();
		_startPosition.undoLastMove();
		_startPosition.undoLastMove();
		_startPosition.undoLastMove();
		_startPosition.undoLastMove();
		_startPosition.undoLastMove();
		_startPosition.undoLastMove();
		_startPosition.undoLastMove();
		_startPosition.undoLastMove();
		assertEquals(_startFen.getString(),_startPosition.getFen());
		
	}

}
