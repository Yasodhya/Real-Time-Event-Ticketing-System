public class Customer implements Runnable {
    private final TicketPool pool;

    public Customer(TicketPool pool) {
        this.pool = pool;
    }

    @Override
    public void run() {
        try {
            while (true) {
                pool.removeTicket(); // Attempt to buy a ticket
                Thread.sleep(500); // Simulate time between purchases
            }
        } catch (InterruptedException e) {
            System.out.println("Customer stopped.");
        }
    }
}
