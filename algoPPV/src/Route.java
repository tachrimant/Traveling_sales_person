import java.util.ArrayList;

public class Route {

    public ArrayList<City> cities = new ArrayList<City>();

    public Route(ArrayList<City> cities) {
        this.cities.addAll(cities);
    }

    @Override
    public String toString() {
        return "Route{" +
                "cities=" + cities.toString() +
                '}';
    }
}
