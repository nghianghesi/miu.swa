package edu.miu.mwa.lab13;

public class FineRecord {

	private String licencePlate;
	private double speed;
	private double fine;
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
	public double getFine() {
		return fine;
	}
	public void setFine(double fine) {
		this.fine = fine;
	}
	public FineRecord(String licencePlate, double speed, double fine) {
		super();
		this.licencePlate = licencePlate;
		this.speed = speed;
		this.fine = fine;
	}
	
	public FineRecord() {
		
	}
}
