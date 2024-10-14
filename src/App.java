
import java.util.Scanner;

public class App {
    static Scanner sc = new Scanner(System.in);

    public static void logIn() {
        boolean c = true;
        while(c){
            System.out.println("----------LOG IN----------");
            System.out.println("1. Log in.");
            System.out.println("2. Register.");
            System.out.println("0. Out application.");
            System.err.println("--------------------------");
            int choice;
            try {
                choice = sc.nextInt();App.sc.nextLine();
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
                        boolean z = true;
                        Customer newCus = new Customer();
                        ManagementCustomer manaCus = new ManagementCustomer();
                        while(z){
                            System.err.print("Enter username: "); String name = App.sc.nextLine();
                            System.err.println(name);
                            System.err.print("Enter password: "); String pass = App.sc.nextLine();
                            System.err.print("Enter your email: "); String email = App.sc.nextLine();
                            System.out.print("Enter your phone: "); String phone = App.sc.nextLine();
                            newCus = newCus.register(name, pass, email, phone);
                            if(newCus== null){
                                System.err.println("Registration failed! Please try again!");
                                continue;
                            }
                            System.out.println("Successfully! Hello "+name+"!Please log in!");
                            manaCus.importTo(".\\Customer.txt");
                            manaCus.add(newCus);
                            manaCus.exportFrom(".\\Customer.txt");
                            break;
                        }
                        break;
                    default:
                        System.out.println("Exit application!");
                        c = false;
                }
            } catch (Exception e) {
                //System.out.println("Invalid input! Please enter number from 0 to 4 to use the application!");
                System.err.println(e);
            }
        }
    }

    public static void main(String[] args) throws Exception {

        /*CarManagement listCar = new CarManagement();
        List<Car> list = new ArrayList<Car>();
        listCar.importTo(".\\Car.txt");
        ManagementCustomer manaCus = new ManagementCustomer();
        manaCus.importTo(".\\Customer.txt");
        List<Customer> listCus = new ArrayList<>();
        listCus = manaCus.getListCus();
        for(Customer cus : listCus){
            System.out.println(cus);
        }*/
        App.logIn();

    }
}
