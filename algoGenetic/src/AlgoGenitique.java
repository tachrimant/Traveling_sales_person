import java.util.ArrayList;
import java.util.stream.IntStream;

public class AlgoGenitique {

    public static final int POPULATION_SIZE = 8;
    public static final double MUTATION_RATE = 0.25;
    public static final int TOURNAMENT_SELECTION_SIZE = 8;
    public static final int NBR_OF_ELITE_ROUTES = 1;
    public static final int NBR_OF_GENERATIONS = 30;
    public ArrayList<City> initialRoute = null;


    public Population evolve(Population population){
        return mutate(crossOverPopulation(population));
    }

    public Population crossOverPopulation(Population population){
        Population crossOverPopulation = new Population(population.getRoutes().size(),this);
        IntStream.range(0,NBR_OF_ELITE_ROUTES).forEach(x -> crossOverPopulation.getRoutes().set(x,crossOverPopulation.getRoutes().get(x)) );
        IntStream.range(NBR_OF_ELITE_ROUTES,crossOverPopulation.getRoutes().size()).forEach(x ->{
            Route route1 = selectTourenamentPopulation(population).getRoutes().get(0);
            Route route2 = selectTourenamentPopulation(population).getRoutes().get(0);
            crossOverPopulation.getRoutes().set(x,crossOverRoute(route1,route2));
        } );
        return crossOverPopulation;
    }
    public Population mutate(Population population){
        population.getRoutes().stream().filter(x -> population.getRoutes().indexOf(x) >= NBR_OF_ELITE_ROUTES).forEach(
                this::mutateRoute);
        return population;
    }
    public Route crossOverRoute(Route route1, Route route2){
        Route crossOverRoute = new Route(this);
        Route tempRoute1 = route1;
        Route tempRoute2 = route2;
        if (Math.random() < 0.5){
            tempRoute1 = route2;
            tempRoute2 = route1;
        }
        for (int i =0 ; i< crossOverRoute.getCities().size()/2;i++){
            crossOverRoute.getCities().set(i,tempRoute1.getCities().get(i));
        }

        return fillNullsInCrossOverRoute(crossOverRoute,tempRoute2);
    }
    public Route fillNullsInCrossOverRoute(Route crossOverRoute, Route route){
        route.getCities().stream().filter(x-> !crossOverRoute.getCities().contains(x)).forEach(
                cityx -> {
                    for (int i =0; i < route.getCities().size(); i++){
                        if (crossOverRoute.getCities().get(i) == null){
                            crossOverRoute.getCities().set(i, cityx);
                            break;
                        }
                    }
                }
        );
        return crossOverRoute;
    }
    public Route mutateRoute(Route route){
        route.getCities().stream().filter(x -> Math.random() >= MUTATION_RATE).forEach(
                cityx -> {
                    int y = (int) (route.getCities().size()*Math.random());
                    City city = route.getCities().get(y);
                    route.getCities().set(route.getCities().indexOf(cityx), city);
                    route.getCities().set(y,cityx);
                }
        );
        return route;
    }
    public Population selectTourenamentPopulation(Population population){
        Population tourenamentPopulation = new Population(TOURNAMENT_SELECTION_SIZE, this);
        IntStream.range(0,TOURNAMENT_SELECTION_SIZE).forEach(x ->
                tourenamentPopulation.getRoutes().set(
                        x, tourenamentPopulation.getRoutes().get((int)(tourenamentPopulation.getRoutes().size()*Math.random())))
                );
        tourenamentPopulation.sortByFitness();
        return tourenamentPopulation;
    }

    public AlgoGenitique(ArrayList<City> initialRoute) {
        this.initialRoute = initialRoute;
    }

    public ArrayList<City> getInitialRoute() {
        return initialRoute;
    }
}
