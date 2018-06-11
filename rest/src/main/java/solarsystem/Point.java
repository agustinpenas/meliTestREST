package solarsystem;

public class Point {
	private double radius;
	private double theta;
	
	public Point(double radius, double theta){
		this.radius = radius;
		this.theta = theta;
		
	}
	
	public double X(){
		return radius*Math.cos(Math.toRadians(theta));
	}
	
	public double Y(){
		return radius*Math.sin(Math.toRadians(theta));
	}
	
	public double Radius() {
		return radius;
	}
	
	public double Theta() {
		return theta;
	}
	
	public void setTheta(double theta) {
		this.theta = theta;
	}
	
	public void movePoint(double angle) {
		this.theta += angle;
	}
	
	public double distance(Point other) {
		//Points A and B : return sqrt( (B.x - A.x)^2  + (B.y - A.y)^2 )
		return Math.sqrt(  Math.pow(other.X()-this.X(), 2) + Math.pow(other.Y()-this.Y(), 2)  );
	}

}
