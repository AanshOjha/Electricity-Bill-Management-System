import java.util.Random;
import java.util.Scanner;

public class Main  {
    private CustomerInfo c1;
    private MeterInfo m1;
    private CalculateBill cb1;
    private StatementGenerate sg;
    private TariffInfo t1;

    public Main(CustomerInfo c1, MeterInfo m1, CalculateBill cb1, StatementGenerate sg, TariffInfo t1) {
        this.c1 = c1;
        this.m1 = m1;
        this.cb1 = cb1;
        this.sg = sg;
        this.t1 = t1;
    }
    public static void main(String[] args) {
        Random rn = new Random();
        Scanner sc = new Scanner(System.in);
        CustomerInfo c1 = new CustomerInfo();
        MeterInfo m1 = new MeterInfo();
        TariffInfo t1 = new TariffInfo();
        CalculateBill cb1 = new CalculateBill(m1, t1);
        StatementGenerate sg = new StatementGenerate(c1, m1, cb1, t1);

        System.out.println("Hello, What would you like to do?");
        System.out.println("1. Register user");
        System.out.println("Enter name:\t");
        c1.setName(sc.nextLine());

        System.out.println("Enter password:\t");
        c1.setPassword(sc.nextLine());

        System.out.println("Enter email:\t");
        c1.setEmail(sc.nextLine());

        System.out.println("Enter address:\t");
        c1.setAddress(sc.nextLine());

        c1.setMeterId(rn.nextInt(1000, 9999));
        System.out.printf("Your unique meter ID is: %d\n", c1.getMeterId());

        m1.inputReading();

        System.out.println(sg.statement());




    }
}