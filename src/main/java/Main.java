import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.CyclicBarrier;

public class Main {
    public static final int CARS_COUNT = 4;
    public static Thread[] carThreads = new Thread[CARS_COUNT];

    public static void main(String[] args) {

        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Подготовка!!!");
        Race race = new Race(new Road(60), new Tunnel(), new Road(40));
        Car[] cars = new Car[CARS_COUNT];
        Car.carsReady = new CyclicBarrier(CARS_COUNT, new allCarsReadyAndStart());

        for (int i = 0; i < cars.length; i++) {
            cars[i] = new Car(race, 20 + (int) (Math.random() * 10));
        }
        for (int i = 0; i < cars.length; i++) {
            carThreads[i] = new Thread(cars[i]);
            carThreads[i].start();
        }


        for (int i = 0; i < carThreads.length; i++) {       //ждем пока все потоки завершаться
            try {
                carThreads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка закончилась!!!");
    }
}

class allCarsReadyAndStart implements Runnable {        //сообщение по готовности всех потоков
    @Override
    public void run() {
        System.out.println("ВАЖНОЕ ОБЪЯВЕНИЕ >>> Гонка началась!!!");
    }
}


