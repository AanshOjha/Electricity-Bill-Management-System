import java.util.Scanner;

public class MeterInfo {
    private double lastReading;
    private double currentReading;

    public void inputReading() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter current reading");
        setCurrentReading(sc.nextDouble());
        System.out.println("Enter last reading");
        setLastReading(sc.nextDouble());
    }

    public double getLastReading() {
        return lastReading;
    }

    public void setLastReading(double lastReading) {
        this.lastReading = lastReading;
    }

    public double getCurrentReading() {
        return currentReading;
    }

    public void setCurrentReading(double currentReading) {
        this.currentReading = currentReading;
    }
}
