// TP de recuit simmulé pour le probleme de voyageur de commerce
// les coordonners des villes de berlin en se basant sur le fichier berlin.txt
// Ce TP est Realiser par  SAAD TACHRIMANT 
// MASTER BIG DATA ET CLOUD COMPUTING 2022/2023



import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Driver {

    public  ArrayList<City> initialCities = new ArrayList<>();
    public  void lireVilles(String fichier) {

        try {
            InputStream ips = new FileInputStream(fichier);
            InputStreamReader ipsr = new InputStreamReader(ips);
            BufferedReader br = new BufferedReader(ipsr);
            String ligne;
            int i = 0;
            while ((ligne = br.readLine()) != null) {
                int distToCut = ligne.indexOf(" ");
                this.initialCities.add(new City(Double.parseDouble(ligne.substring(0, distToCut)), Double.parseDouble(ligne.substring(distToCut + 1)), i));
                i++;
            }
            br.close();
        } catch (Exception e) {
            System.out.println(e.toString() + " : Erreur lors de l'ouverture du fichier des villes");
        }
        System.out.println("initial cities: " + initialCities.toString());
    }

    public static void main(String[] args) {
        Driver driver = new Driver();
        driver.lireVilles("src/berlin52.txt");
        ArrayList<City> cities = new ArrayList<>();
       // cities.addAll(initialCities);
        Route route = new Route(driver.initialCities);
        Route shortestRoute = new RecuitSimule().findRoute(RecuitSimule.INITIAL_TEMPERATURE, route);
        System.out.println(shortestRoute.toString());
        System.out.println("total distance is : " + shortestRoute.totalDistance());
    }

}
