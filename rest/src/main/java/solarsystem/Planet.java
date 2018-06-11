package solarsystem;

public class Planet {
	private Point location;
	private String name;
	private int angularVelocity;
	
	public Planet(String name, double radius, int angularVelocity){
		this.name = name;
		this.location = new Point(radius, 0.0);
		this.angularVelocity = angularVelocity;
	}
	
	public void nextDay() {
		location.movePoint(angularVelocity);
	}
	
	public Point getLocation() {
		return location;
	}
	
	public int getAngularVelocity() {
		return angularVelocity;
	}
	
}
