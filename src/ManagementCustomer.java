import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ManagementCustomer implements Modify<Customer> {
    private List<Customer> listCus;

    @Override
    public void add(Customer cus) {
        this.listCus.add(cus);
    }

    @Override
    public void edit(Customer cus) {
        //Not useful(Access personaline)
    }

    @Override
    public void delete(int id) {
        listCus.removeIf(cus -> cus.getId()==id);
    }

    @Override
    public void importTo(String filePath) {
        try(BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            List<Customer> resul =new ArrayList<>();
            while((line = reader.readLine()) != null){
                String[] fields = line.split(",");
                if(fields.length==5){
                    int id = Integer.parseInt(fields[0]);
                    String username = fields[1];
                    String password = fields[2];
                    String email = fields[3];
                    String phone = fields[4];
                    Customer newCus = new Customer();
                    newCus.setId(id);
                    newCus.setUsername(username);
                    newCus.setPassword(password);
                    newCus.setEmail(email);
                    newCus.setPhone(phone);
                    resul.add(newCus);
                }
                this.listCus = resul;
            }
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    @Override
    public void exportFrom(String filePath) {
        try(FileWriter myWriter = new FileWriter(filePath);) {
            for(Customer cus : listCus){
                myWriter.write(cus.getId()+","+cus.getUsername()+","+cus.getPassword()+","+cus.getEmail()+","+cus.getPhone()+"\n");
            }
            myWriter.close();
        } catch (IOException ex) {
            System.out.println("Cannot export!");
        }
    }

    @Override
    public List<Customer> search(List<Customer> t, String attribute, String value) {
        return null;
    }

    public List<Customer> getListCus() {
        return listCus;
    }
}
