import java.util.Random;
import java.util.Scanner;

public class CustomerInfo {
    private String name;
    private String password;
    private int meterId;
    private String address;
    private String email;

    public void registerUser() {
        Scanner sc = new Scanner(System.in);
        Random rn = new Random();

        System.out.println("Enter name:\t");
        setName(sc.nextLine());

        System.out.println("Enter password:\t");
        setPassword(sc.nextLine());

        System.out.println("Enter email:\t");
        setEmail(sc.nextLine());

        System.out.println("Enter address:\t");
        setAddress(sc.nextLine());

        setMeterId(rn.nextInt(1000, 9999));
        System.out.printf("Your unique meter ID is: %d\n", getMeterId());
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getMeterId() {
        return meterId;
    }

    public void setMeterId(int meterId) {
        this.meterId = meterId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
