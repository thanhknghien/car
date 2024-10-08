public class ElectricCar extends Car{
    private int batterycapacity;
    private int range;

    public ElectricCar(int batterycapacity, int range, int id, String name, String brand, int yearOfProduction, double price, String category, Warranty warranty) {
        super(id, name, brand, yearOfProduction, price, category, warranty);
        this.batterycapacity = batterycapacity;
        this.range = range;
    }

    public int getBatterycapacity() {
        return batterycapacity;
    }

    public int getRange() {
        return range;
    }

    public void setBatterycapacity(int batterycapacity) {
        this.batterycapacity = batterycapacity;
    }

    public void setRange(int range) {
        this.range = range;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append(", batterycapacity: ").append(batterycapacity);
        sb.append(", range: ").append(range);
        return sb.toString();
    }

}
