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

    public double measurDistance(City city) {

        return Math.sqrt(Math.pow(this.getCordX() - city.getCordX(), 2) + Math.pow(this.getCordY() - city.getCordY(), 2));

    }

    @Override
    public String toString() {
        return this.index + "";
    }
}
