import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CarManagement implements Modify<Car> {
    private List<Car> cars = new ArrayList<>();

    @Override
    public void add(Car car){
        cars.add(car);
    }

    @Override
    public void edit(Car car){
        try {
            List<Car> result = new ArrayList<>();
            for(Car car1 : cars){
                if( car1.getId() == car.getId()){
                    car1 = car;
                }
                result.add(car1);
            }
            this.cars = result;  
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public void delete(int id){
        cars.removeIf(car -> car.getId() == id);
    }

    @Override
    public void importTo(String filePath){
        try(BufferedReader reader = new BufferedReader(new FileReader(filePath))){
            String line;
            List<Car> result = new ArrayList<>();
            while((line = reader.readLine()) != null){
                String[] fields = line.split(",");
                if(fields.length == 10){
                    int id = Integer.parseInt(fields[0]);
                    String name = fields[1];
                    String brand = fields[2];
                    int year = Integer.parseInt(fields[3]);
                    double price = Double.parseDouble(fields[4]);
                    String cate = fields[5];
                    String[] word = fields[6].split("(?<= 5 )|(?= 5 )");
                    Warranty w = new Warranty(word[0], word[2]);
                    if("e".equalsIgnoreCase(fields[7])){
                        int battery = Integer.parseInt(fields[8]);
                        int range = Integer.parseInt(fields[9]);
                        ElectricCar elec = new ElectricCar(battery,range , id, name, brand, year, price, cate, w);
                        result.add(elec);
                    }else if("g".equalsIgnoreCase(fields[7])){
                        int fuel = Integer.parseInt(fields[8]);
                        int tank = Integer.parseInt(fields[9]);
                        GasolineCar gas = new GasolineCar(fuel, tank, id, name, brand, year, price, cate, w);
                        result.add(gas);
                    }
                }
            }
            this.cars = result;
        }catch(IOException e){
            System.err.println(e);
        }
    }

    @Override
    public void exportFrom(String filepath){
        
    }

    @Override
    public List<Car> search(List<Car> cars,String attribute, String value){
        List<Car> result = new ArrayList<>();
        attribute = attribute.toLowerCase();
        if("id".equals(attribute)){
            int id = Integer.parseInt(value);
            for(Car car: cars) if(car.getId() == id) result.add(car);
        }else if("name".equals(attribute)){
            for(Car car: cars) if(value.equals(car.getName()) ) result.add(car);
        }else if("brand".equals(attribute)){
            for(Car car: cars) if(value.equals(car.getBrand())) result.add(car);
        }else if ("year of production".equals(attribute)) {
            for(Car car: cars){
                int year = Integer.parseInt(value);
                if(car.getYearOfProduction() == year) result.add(car);
            } 
        }else if("category".equals(attribute)){
            for(Car car: cars) if(value.equals(car.getCategory())) result.add(car);
        }        
        return result;
    }

    public List<Car> getCars() {
        return cars;
    }

}
