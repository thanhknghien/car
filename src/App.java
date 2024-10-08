
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    static Scanner sc = new Scanner(System.in);

    public void logIn() {
        boolean c = true;
        while(c){
            System.out.println("----------LOG IN----------");
            System.out.println("1. Log in.");
            System.out.println("2. Register.");
            System.out.println("0.Out application.");
            int choice;
            try {
                choice = sc.nextInt();
                switch (choice) {
                    case 1:
                        System.out.print("User name: ");
                        sc.next();
                        String username = sc.nextLine();
                        System.out.print("Password: ");
                        String password = sc.nextLine();
                        if("admin".equals(username)||"admin123".equals(password)){
                            System.out.println("Hello Admin!");
                            //call admin method
                            c = false;    
                        }else{
                            //check if user is not have account
                            
                        }
                        break;
                    case 2:
                        break;
                    default:
                        System.out.println("Exit application!");
                        c = false;
                }
            } catch (Exception e) {
                System.out.println("Invalid input! Please enter number from 0 to 4 to use the application!");
                sc.next();
            }
        }
    }

    public static void main(String[] args) throws Exception {

        CarManagement listCar = new CarManagement();
        List<Car> list = new ArrayList<Car>();
        listCar.importTo(".\\Car.txt");

        list = listCar.getCars();
        /*for(Car car: list){
            System.out.println(car);
            System.err.println("-----------------");
        }*/
        Customer newCus = new Customer(123,"thanh" ,"thanh12345@","thanh2005dinh@gmail.com","098654657",null );
        newCus.menuForCustomer();

    }
}
