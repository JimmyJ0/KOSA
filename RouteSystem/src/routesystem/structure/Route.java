package routesystem.structure;

public class Route {

	private Routes route;
	private double distance;

	public Route(Routes route) {
		this.route = route;
		setDistance(route);
	}

	public double getDistance() {
		return distance;
	}

	public void setDistance(Routes route) {
		distance = route.getDistance() * 1.45;
	}

	public Routes getRoute() {
		return route;
	}

}
