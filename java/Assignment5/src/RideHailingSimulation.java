package src;

import java.util.concurrent.*;
import java.util.*;
import java.util.concurrent.locks.*;

public class RideHailingSimulation {

    static class Driver {
        private final String name;

        public Driver(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

    static class RideService {
        private final Queue<Driver> availableDrivers = new LinkedList<>();
        private final Semaphore driverSemaphore;
        private final Lock lock = new ReentrantLock();

        public RideService(List<Driver> drivers) {
            availableDrivers.addAll(drivers);
            driverSemaphore = new Semaphore(drivers.size());
        }

        public void bookRide(int passengerId) {
            try {
                // Acquire a driver slot
                driverSemaphore.acquire();

                Driver assignedDriver;

                lock.lock();
                try {
                    assignedDriver = availableDrivers.poll();
                } finally {
                    lock.unlock();
                }

                if (assignedDriver != null) {
                    System.out.println("Passenger " + passengerId + " → Driver " + assignedDriver.getName());

                    // Simulate ride duration
                    Thread.sleep(1000);

                    // Driver becomes available again
                    lock.lock();
                    try {
                        availableDrivers.offer(assignedDriver);
                    } finally {
                        lock.unlock();
                    }

                    driverSemaphore.release();
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        int numDrivers = 10;
        int numPassengers = 20;

        // Create drivers
        List<Driver> drivers = new ArrayList<>();
        for (int i = 0; i < numDrivers; i++) {
            drivers.add(new Driver("Driver " + (char) ('A' + i)));
        }

        RideService rideService = new RideService(drivers);

        ExecutorService executor = Executors.newFixedThreadPool(numPassengers);
        CountDownLatch latch = new CountDownLatch(numPassengers);

        for (int i = 1; i <= numPassengers; i++) {
            final int passengerId = i;
            executor.execute(() -> {
                rideService.bookRide(passengerId);
                latch.countDown();
            });
        }

        latch.await();
        executor.shutdown();
        System.out.println("All passengers have been served.");
    }
}