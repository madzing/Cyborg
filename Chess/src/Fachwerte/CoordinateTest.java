package Fachwerte;

import static org.junit.Assert.*;

import org.junit.Test;

public class CoordinateTest {

	@Test
	public void test() {
		for(int i =-128 ; i < 127; i++)
		{
			Coordinate.select((byte)i);
		}
		
		for(int j = 0 ; j < 500000000; j++)
		{
			Coordinate a = Coordinate.select((byte)90);
		}
	}

}
