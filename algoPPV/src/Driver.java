// TP de Plus Proche Voisin pour le probleme de voyageur de commerce
// les coordonners des villes de berlin en se basant sur le fichier berlin.txt
// Ce TP est Realiser par  SAAD TACHRIMANT 
// MASTER BIG DATA ET CLOUD COMPUTING 2022/2023



import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Driver {
    public static ArrayList<City> initialCities = new ArrayList<>();
    public static void lireVilles(String fichier) {

        try {
            InputStream ips = new FileInputStream(fichier);
            InputStreamReader ipsr = new InputStreamReader(ips);
            BufferedReader br = new BufferedReader(ipsr);
            String ligne;
            int i = 0;
            while ((ligne = br.readLine()) != null) {
                int distToCut = ligne.indexOf(" ");
                initialCities.add(new City(Double.parseDouble(ligne.substring(0, distToCut)), Double.parseDouble(ligne.substring(distToCut + 1)), i));
                i++;
            }
            br.close();
        } catch (Exception e) {
            System.out.println(e.toString() + " : Erreur lors de l'ouverture du fichier des villes");
        }
        System.out.println("initial cities: " + initialCities.toString());
    }
    public static void main(String[] args) {
       Driver.lireVilles("src/berlin52.txt");
       ArrayList<City> cities = new ArrayList<>();
       cities.addAll(initialCities);
       printShourtestRoute(new NearestNeighbor().findShortestRoute(cities));

    }
    public static void printShourtestRoute(Route route){
        route.cities.add(initialCities.get(0));
        System.out.println("best route found so far is :\n");
        System.out.println("----------> " + route.toString());
        System.out.println("---------------------");
        System.out.println("the total distance is -----> " + totalDistance(route));
    }
   public static double totalDistance(Route route){
        double total = 0;
       int i ;
        for (i = 0; i < route.cities.size()-1; i++){
            if (route.cities.get(i) == null){
                break;
            }else {
                total += route.cities.get(i).measurDistance(route.cities.get(i+1));
            }
        }
        total+= route.cities.get(i).measurDistance(route.cities.get(0));
        return total;
   }
}
