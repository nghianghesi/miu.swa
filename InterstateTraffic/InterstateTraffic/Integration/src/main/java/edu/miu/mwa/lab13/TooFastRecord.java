package edu.miu.mwa.lab13;

public class TooFastRecord {
	private String licencePlate;
	private double speed;
	
	
	public String getLicencePlate() {
		return licencePlate;
	}
	public void setLicencePlate(String licencePlate) {
		this.licencePlate = licencePlate;
	}
	public double getSpeed() {
		return speed;
	}
	public void setSpeed(double speed) {
		this.speed = speed;
	}
	
	public TooFastRecord(String licencePlate, double speed) {
		super();
		this.licencePlate = licencePlate;
		this.speed = speed;
	}
	
	public TooFastRecord() {
		
	}
}
