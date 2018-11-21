package briscoe.cleancode;

public class VehicleDaoFactory {

	private static VehicleDao vehicleDao;

	public static VehicleDao vehicleDao() {
		if (vehicleDao == null)
			vehicleDao = usingVehicleMapDao();
		return vehicleDao;
	}

	private static VehicleDao usingVehicleMapDao() {
		return new VehicleMapDao();
	}

	private VehicleDaoFactory() {

	}

}
