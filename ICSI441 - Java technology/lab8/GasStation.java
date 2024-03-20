import java.util.concurrent.atomic.AtomicInteger;

class GasStation {
    private static final int NUM_DEVICES = 4;
    private static final int REFUEL_TIME = 6;
    private static final int MAINTENANCE_INTERVAL = 45;
    private static final int[] MAINTENANCE_TIMES = {10, 15, 5, 13};
    private static final int SIMULATION_TIME = 240;

    private AtomicInteger totalCarsServiced = new AtomicInteger(0);

    class Car extends Thread {
        @Override
        public void run() {
            while (totalCarsServiced.get() < (SIMULATION_TIME / REFUEL_TIME)) {
                for (int i = 0; i < NUM_DEVICES; i++) {
                    if (totalCarsServiced.get() >= (SIMULATION_TIME / REFUEL_TIME)) {
                        break;
                    }

                    if (i % 4 == 0) {
                        if (totalCarsServiced.get() % (MAINTENANCE_INTERVAL / REFUEL_TIME) == 0) {
                            int deviceIndex = totalCarsServiced.get() % NUM_DEVICES;
                            int maintenanceTime = MAINTENANCE_TIMES[deviceIndex];
                            try {
                                sleep(maintenanceTime);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                    try {
                        sleep(REFUEL_TIME);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    totalCarsServiced.incrementAndGet();
                }
            }
        }
    }

    public void startSimulation() {
        Car[] cars = new Car[NUM_DEVICES];
        for (int i = 0; i < NUM_DEVICES; i++) {
            cars[i] = new Car();
            cars[i].start();
        }

        for (Car car : cars) {
            try {
                car.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Дөрвөн цагт бүх төхөөрөмжөөр үйлчлэх автомашины тоо: " + totalCarsServiced.get());
        System.out.println("Дөрвөн цагт төхөөрөмж тус бүрт үйлчлэх автомашины тоо: " + (totalCarsServiced.get() / NUM_DEVICES));
    }

    public static void main(String[] args) {
        GasStation gasStation = new GasStation();
        gasStation.startSimulation();
    }
}
