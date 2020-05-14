package edu.miu.mwa.lab13;

public class SensorRecord {
	private String licencePlate;
	private int minute;
	private int second;
	private int cameraId;
	
	public String getLicencePlate() {
		return licencePlate;
	}
	public void setLicencePlate(String licencePlate) {
		this.licencePlate = licencePlate;
	}
	public int getMinute() {
		return minute;
	}
	public void setMinute(int minute) {
		this.minute = minute;
	}
	public int getSecond() {
		return second;
	}
	public void setSecond(int second) {
		this.second = second;
	}
	public int getCameraId() {
		return cameraId;
	}
	public void setCameraId(int cameraId) {
		this.cameraId = cameraId;
	} 	
	
	@Override
	public int hashCode() {
		return this.getLicencePlate().hashCode();
	}
}
