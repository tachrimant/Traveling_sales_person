import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

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
        Population population = new Population(AlgoGenitique.POPULATION_SIZE, driver.initialCities);
        population.sortByFitness();
        AlgoGenitique algoGenitique = new AlgoGenitique(driver.initialCities);
        int generationNumber = 0;
        while (generationNumber < AlgoGenitique.NBR_OF_GENERATIONS){
            population = algoGenitique.evolve(population);
            population.sortByFitness();
            driver.printPopulation(population, generationNumber);
            generationNumber++;
        }
        System.out.println("best Route found so far is : ");
        System.out.println(population.getRoutes().get(0));
        System.out.println(" | fitness --> " + String.format("%.4f", population.getRoutes().get(0).getFitness()) + " | totalDistance ---> "
                + String.format("%.4f", population.getRoutes().get(0).totalDistance()));
    }
    public void printPopulation(Population population, int generationNum){
        System.out.println("Generation number : " + generationNum);
        System.out.println("");
        population.getRoutes().forEach(
                x -> System.out.println(Arrays.toString(x.getCities().toArray()) + " | fitness --> " + String.format("%.4f", x.getFitness()) + " | totalDistance ---> "
                + String.format("%.4f", x.totalDistance()))
        );
        System.out.println("");
    }

}
