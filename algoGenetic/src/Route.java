import java.util.ArrayList;
import java.util.Collections;

public class Route {

    public ArrayList<City> cities = new ArrayList<City>();
    public boolean isFitnessChanged = false;
    public double fitness;

    public Route(ArrayList<City> cities) {
        this.cities.addAll(cities);
        Collections.shuffle(this.cities);
    }
    public Route(AlgoGenitique algoGenitique){
        algoGenitique.getInitialRoute().forEach(x -> cities.add(null) );
    }

    public ArrayList<City> getCities() {
        isFitnessChanged = true;
        return cities;
    }

    public Route(Route currentRoute) {
        this.cities = currentRoute.cities;
    }

    public void setFitness(double fitness) {
        this.fitness = fitness;
    }

    public double getFitness() {
        if (isFitnessChanged){
            setFitness((1/totalDistance())*10000);
            isFitnessChanged = false;
        }
        return fitness;
    }

    @Override
    public String toString() {
        return "Route{" +
                "cities=" + cities.toString() +
                '}';
    }

    public  double totalDistance(){
        double total = 0;
        int i ;
        for (i = 0; i < this.cities.size()-1; i++){
            if (this.cities.get(i) == null){
                break;
            }else {
                total += measurDistance(this.cities.get(i),this.cities.get(i+1));
            }
        }
        total+= measurDistance(this.cities.get(i),this.cities.get(0));
        return total;
    }
    public double measurDistance(City city1, City city2) {

        return Math.sqrt(Math.pow(city1.getCordX() - city2.getCordX(), 2) + Math.pow(city1.getCordY() - city2.getCordY(), 2));

    }

    public String getTotalStringValue(){
        String returnValue = String.format("%.2f",totalDistance() );
        if (returnValue.length() ==7) returnValue = " " + returnValue;
        return returnValue;
    }
}















