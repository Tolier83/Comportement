package tests;

import system.CBase;
import static org.junit.jupiter.api.Assertions.*;

import java.awt.Color;

import org.junit.jupiter.api.Test;

class CBaseTest {

	@Test 
	void testIfColorReturned() {
		CBase base = new CBase(1, 1, 12, Color.BLACK, 10);
		Color black = Color.BLACK;
		assertTrue(black == base.getColor());

	}

}
