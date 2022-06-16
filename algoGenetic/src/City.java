public class City {
    public double cordX;
    public double cordY;
    public int index;

    public City(double cordX, double cordY, int index) {
        this.cordX = cordX;
        this.cordY = cordY;
        this.index = index;
    }

    public double getCordX() {
        return cordX;
    }

    public void setCordX(double cordX) {
        this.cordX = cordX;
    }

    public double getCordY() {
        return cordY;
    }

    public void setCordY(double cordY) {
        this.cordY = cordY;
    }

    public double measurDistance(City city1, City city2) {

        return Math.sqrt(Math.pow(city1.getCordX() - city2.getCordX(), 2) + Math.pow(city1.getCordY() - city2.getCordY(), 2));

    }

    @Override
    public String toString() {
        return this.index + "";
    }
}
