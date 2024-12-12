import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        TicketPool pool = new TicketPool();

        Thread vendorThread = new Thread(new Vendor(pool));
        Thread customerThread1 = new Thread(new Customer(pool));
        Thread customerThread2 = new Thread(new Customer(pool)); // Additional customer

        System.out.println("Enter 'start' to begin or 'stop' to terminate.");
        while (true) {
            String command = scanner.nextLine();
            if (command.equalsIgnoreCase("start")) {
                vendorThread.start();
                customerThread1.start();
                customerThread2.start();
            } else if (command.equalsIgnoreCase("stop")) {
                vendorThread.interrupt();
                customerThread1.interrupt();
                customerThread2.interrupt();
                break;
            }
        }

        scanner.close();
    }
}
