package briscoe.cleancode;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class VehicleTest {

	@Test
	public void isBlacklisted() {
		assertTrue(Vehicle.byVin("1HGBH41JXMN109186").isBlacklisted());
		assertFalse(Vehicle.byVin("2HGBH41JXMN109186").isBlacklisted());
		assertTrue(Vehicle.byVin("3HGBH41JXMN109186").isBlacklisted());
		assertFalse(Vehicle.byVin("4HGBH41JXMN109186").isBlacklisted());
	}

}
