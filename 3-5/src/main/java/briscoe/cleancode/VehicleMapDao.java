package briscoe.cleancode;

import java.util.HashMap;
import java.util.Map;

public class VehicleMapDao implements VehicleDao {

	private Map<String, Boolean> vehicles;

	public VehicleMapDao() {
		vehicles = new HashMap<>();
		vehicles.put("1HGBH41JXMN109186", Boolean.TRUE);
		vehicles.put("2HGBH41JXMN109186", Boolean.FALSE);
		vehicles.put("3HGBH41JXMN109186", Boolean.TRUE);
		vehicles.put("4HGBH41JXMN109186", Boolean.FALSE);
	}

	@Override
	public boolean isBlacklisted(final String vin) {
		if(vehicles.containsKey(vin))
			return vehicles.get(vin);
		return false;
	}

}
