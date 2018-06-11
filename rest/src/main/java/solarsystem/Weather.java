package solarsystem;

public enum Weather {

	DROUGHT("Sequia"), COPT("Condiciones optimas de presion y temperatura"), RAIN("Lluvia"), NONE("Nada");

	private Weather(String name) {
		this.name=name;
	}
	
	private String name;
	
	public String getName() {
		return name;
	}
}
