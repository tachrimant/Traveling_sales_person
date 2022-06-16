import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RecuitSimule {

   public static final double RATE_OF_COOLING = 0.005;
   public static final double INITIAL_TEMPERATURE = 999;
   public static final double MIN_TEMPERATURE = 0.99;


   //effectuer des semi-tours entre les villes aleatoiremant
   public Route obtientAdgacentRoute(ArrayList<City> cities){

       int x1 =0;
       x1 = (int)(cities.size()*Math.random());
       int x2 = x1+1;
       if (x2 >= cities.size()) x2 = x1-1;
       Route adjacentRoute = new Route(cities);

/*       route.cities.set(x2, city1);
       route.cities.set(x1, city2);*/
       Collections.swap(adjacentRoute.cities,x1,x2);
       return adjacentRoute;
   }


    public Route findRoute(double temperature, Route currentRoute) {
       Route shortestRoute = new Route(currentRoute);
       Route adjacentRoute;
       while (temperature > MIN_TEMPERATURE){
           System.out.println("Current route : " + currentRoute.totalDistance() + " | current temperature | " + String.format("%.2f", temperature) );
           adjacentRoute = obtientAdgacentRoute(currentRoute.cities);
           System.out.println("possible route : " + adjacentRoute.totalDistance() + " | current temperature | " + String.format("%.2f", temperature) );
           if (currentRoute.totalDistance() < shortestRoute.totalDistance() ) shortestRoute = new Route(currentRoute);
           if (acceptedRoute(currentRoute.totalDistance(), adjacentRoute.totalDistance(), temperature)) currentRoute = new Route(adjacentRoute);
           temperature *= 1- RATE_OF_COOLING;
       }
       return shortestRoute;
    }

    public boolean acceptedRoute(double currentDistance, double adjacentDistance, double temperature){
       String decision = null;
       boolean shortestDistance = true;
       boolean acceptRouteFlag = false;
       double acceptanceProbability = 1.0;
        double random = Math.random();
       if (adjacentDistance >= currentDistance){
           double x = (currentDistance-adjacentDistance)/temperature;
           acceptanceProbability = Math.exp(x);
           shortestDistance = false;

       }
        if (random < acceptanceProbability){
            acceptRouteFlag = true;
        }

       if (shortestDistance) decision ="Proceed| adjacent route is shorter";
       else if (acceptRouteFlag) decision = "Proceed| proba > random ";
       else  decision = "Stay| random > proba ";
       System.out.println("probability :  " + acceptanceProbability + "| random -->" + random + "| decision --> " + decision);
       return  acceptRouteFlag;
    }
}
