import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class TicketPool {
    private final Queue<Integer> tickets = new LinkedList<>();
    private final int maxCapacity = 5; // Vendors can add only up to 5 tickets at a time
    private final ReentrantLock lock = new ReentrantLock();
    private final Condition notEmpty = lock.newCondition();
    private final Condition notFull = lock.newCondition();

    public void addTickets(int count) throws InterruptedException {
        lock.lock();
        try {
            // Wait until all tickets are consumed
            while (!tickets.isEmpty()) {
                notFull.await();
            }
            // Add tickets up to the max capacity
            for (int i = 0; i < count; i++) {
                tickets.add(1); // 1 represents a single ticket
            }
            System.out.println("Vendor added " + count + " tickets.");
            notEmpty.signalAll(); // Notify customers that tickets are available
        } finally {
            lock.unlock();
        }
    }

    public void removeTicket() throws InterruptedException {
        lock.lock();
        try {
            // Wait until tickets are available
            while (tickets.isEmpty()) {
                notEmpty.await();
            }
            tickets.poll(); // Consume one ticket
            System.out.println("Customer bought a ticket.");
            if (tickets.isEmpty()) {
                notFull.signal(); // Notify vendors that tickets are all consumed
            }
        } finally {
            lock.unlock();
        }
    }

    public int getCurrentSize() {
        lock.lock();
        try {
            return tickets.size();
        } finally {
            lock.unlock();
        }
    }
}
