package solarsystem;

public class SolarSystem {

	private Planet vulcan;
	private Planet ferengis;
	private Planet betasoides;
	
	public SolarSystem(){
		//We asume that the planets all start at angle 0
		vulcan = new Planet("Vulcan", 1000, 5);
		ferengis = new Planet("Ferengis", 500, -1);
		betasoides = new Planet("Betasoid", 2000, -3);
	}
	
	public void setDate(int date) {
		vulcan.getLocation().setTheta(vulcan.getAngularVelocity()*date);
		ferengis.getLocation().setTheta(ferengis.getAngularVelocity()*date);
		betasoides.getLocation().setTheta(betasoides.getAngularVelocity()*date);
	}
	
	public void newDay() {
		vulcan.nextDay();
		ferengis.nextDay();
		betasoides.nextDay();
	}
	
	public boolean arePlanetsAligned() {
		double epsilon = 0.0001d;
		//we calculate the distance from betasoid to the line that intersects vulcan and ferengis
		double distance = distanceFromLine(vulcan.getLocation(), ferengis.getLocation(), betasoides.getLocation());
		
		//we say they are in line if the distance close enough
		return distance<epsilon;
	}
	
	public boolean arePlanetsAlignedToTheSun() {
		if(!arePlanetsAligned()) return false;
		
		double epsilon = 0.0001d;
		//we calculate the distance from the sun to the line that intersects vulcan and ferengis
		//the planets are aligned so if the sun is aligned to vulcan and ferengis then it is aligned to all of them
		double distance = distanceFromLine(vulcan.getLocation(), ferengis.getLocation(), new Point(0, 0));
		
		//we say they are in line if the distance close enough
		return distance<epsilon;
	}
	
	//returns the distance of point 'p' from the line that intersects endA and endB
	//this is not exactlky the distance but will be close to 0 when distance is close to 0
	public double distanceFromLine(Point endA, Point endB, Point p) {
		double dividend = Math.abs( (endB.Y()-endA.Y())*p.X() - (endB.X()-endA.X())*p.Y() + endB.X()*endA.Y() - endB.Y()*endA.X() );
		//double divisor = Math.sqrt( Math.pow(endB.X()-endA.X(), 2) + Math.pow(endB.Y()-endA.Y(), 2));
		//maybe we dont need divisor, distance will be 0 when dividend is 0 and will be close to 0 when dividend is close to 0
		return dividend;
	}
	
	public double perimeter() {
		double per=0;
		per += vulcan.getLocation().distance(ferengis.getLocation());
		per += ferengis.getLocation().distance(betasoides.getLocation());
		per += betasoides.getLocation().distance(vulcan.getLocation()); 
		return per;
	}
	
	double sign (Point p1, Point p2, Point p3)
	{
	    return (p1.X() - p3.X()) * (p2.Y() - p3.Y()) - (p2.X() - p3.X()) * (p1.Y() - p3.Y());
	}
	
	public boolean isSunInsideTriangle() {
		
		boolean b1, b2, b3;
		
		Point sun = new Point(0, 0);

	    b1 = sign(sun, vulcan.getLocation(), ferengis.getLocation()) < 0.0d;
	    b2 = sign(sun, ferengis.getLocation(), betasoides.getLocation()) < 0.0d;
	    b3 = sign(sun, betasoides.getLocation() , vulcan.getLocation()) < 0.0d;

	    return ((b1 == b2) && (b2 == b3));
		
	}
	
	public Weather weatherForToday() {
		if(arePlanetsAlignedToTheSun()) {
			return Weather.DROUGHT;
		}
		if(arePlanetsAligned()) {
			return Weather.COPT;
		}
		if(isSunInsideTriangle()) {
			return Weather.RAIN;
		}
		return Weather.NONE;
	}

}
