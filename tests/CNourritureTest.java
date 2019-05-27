package tests;
import system.CNourriture;
import static org.junit.jupiter.api.Assertions.*;

import java.awt.Color;

import org.junit.jupiter.api.Test;

class CNourritureTest {

	@Test
	void testDecreaseSize() {
		CNourriture nourriture = new CNourriture(1, 1, Color.BLACK, 10);
		// Decrease quantité
		nourriture.decreaseSize();
		// Résultat attendu
		int sizeDecreased = 19;
		assertEquals(sizeDecreased, nourriture.quantite);
	}

}
