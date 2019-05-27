package tests;

import system.CObject;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CObjectTest {

	public int x1 = 12;
	public int x = 54;
	public int y = 25;
	public int y1 = 245;
	double expectedDistance = 223.9732126840172;
	double expectedDistanceCarre = 50164.0;
	
	@Test
	void testDistance() {
		
		CObject object = new CObject((double)x, (double)y);
		CObject object1 = new CObject((double)x1, (double)y1);
		assertEquals(object.distance(object1), expectedDistance);
		System.out.println(expectedDistance);
	}
	
	
	@Test
	void testDistanceCarree() {
		CObject object = new CObject((double)x, (double)y);
		CObject object1 = new CObject((double)x1, (double)y1);
		assertEquals(object.DistanceCarre(object1), expectedDistanceCarre);
		System.out.println(expectedDistanceCarre);

	}
	
	
	
	

    
    

}
