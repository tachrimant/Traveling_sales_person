import java.util.ArrayList;
import java.util.stream.IntStream;

public class Population {

    public ArrayList<Route> routes = new ArrayList<Route>(AlgoGenitique.POPULATION_SIZE);

    public ArrayList<Route> getRoutes() {
        return routes;
    }



    public Population(int populationSize, ArrayList<City> cities) {

        IntStream.range(0,populationSize).forEach(x-> routes.add(new Route(cities)));
    }

    public Population(int populationSize, AlgoGenitique algoGenitique) {

        IntStream.range(0,populationSize).forEach(x-> routes.add(new Route(algoGenitique.getInitialRoute())));
    }

    public void sortByFitness(){
        this.routes.sort(
                (route1,route2)->{
                    int flag = 0;
                    if (route1.getFitness()< route2.getFitness()) flag = 1;
                    else if (route1.getFitness() > route2.getFitness()) flag = -1;
                    return flag;
                }
        );
    }
}
