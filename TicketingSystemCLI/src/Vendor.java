public class Vendor implements Runnable {
    private final TicketPool pool;

    public Vendor(TicketPool pool) {
        this.pool = pool;
    }

    @Override
    public void run() {
        try {
            while (true) {
                pool.addTickets(5); // Add 5 tickets at a time
                Thread.sleep(1000); // Simulate some delay
            }
        } catch (InterruptedException e) {
            System.out.println("Vendor stopped.");
        }
    }
}