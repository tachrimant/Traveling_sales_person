import java.util.ArrayList;

public class NearestNeighbor {

   public Route findShortestRoute(ArrayList<City> cities){
       ArrayList<City> shortestRoute = new ArrayList<>(cities.size());
       City city = cities.get(0);
       updateRoute(shortestRoute, city, cities);
       while (cities.size()>=1){
           city = getNextCity(cities,city);
           updateRoute(shortestRoute,city,cities);
       }


     return new Route(shortestRoute);
   }

    private City getNextCity(ArrayList<City> cities, City city) {
       return cities.stream().min((city1,city2)->{
           int flag = 0;
           if (city1.measurDistance(city)> city2.measurDistance(city)) flag = 1;
           else if (city1.measurDistance(city) < city2.measurDistance(city)) flag = -1;
       return flag;
       }
       ).get();
    }

    public void updateRoute(ArrayList<City> shortestRoute, City city,ArrayList<City> cities ){
       shortestRoute.add(city);
       cities.remove(city);
        System.out.println("vivited cities: " + shortestRoute.toString());
        System.out.println("-----------------------");
        System.out.println("remaining cities: " + cities.toString());

   }

}
