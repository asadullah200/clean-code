package briscoe.cleancode;

public class Vehicle {

	private final String vin;

	public static Vehicle byVin(final String vin) {
		return new Vehicle(vin);
	}

	public boolean isBlacklisted() {
		return VehicleDaoFactory.vehicleDao().isBlacklisted(vin);
	}

	private Vehicle(final String vin) {
		this.vin = vin;
	}
}
