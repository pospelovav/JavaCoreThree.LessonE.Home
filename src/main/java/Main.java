
import java.util.Arrays;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    public static final int CARS_COUNT = 5;
    public static Thread[] carThreads = new Thread[CARS_COUNT];
    public static int maximumCarsInTunnel = CARS_COUNT / 2;

    public static void main(String[] args) {

        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Подготовка!!!");
        Race race = new Race(new Road(60), new Tunnel(maximumCarsInTunnel), new Road(40));
        Car[] cars = new Car[CARS_COUNT];
        Car.carsReady = new CyclicBarrier(CARS_COUNT, new allCarsReadyAndStart());
        Car.ai = new AtomicInteger(0);

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
        Arrays.sort(cars);      //сортировка по местам
        for (int i = 0; i < cars.length; i++) {
            if (cars[i].place == 1){
                System.out.println(cars[i].getName() + ": Win (Fist place)");
            } else {
                System.out.println(cars[i].getName() + ": " + cars[i].place + " place");
            }
        }
    }
}

class allCarsReadyAndStart implements Runnable {        //сообщение по готовности всех потоков
    @Override
    public void run() {
        System.out.println("ВАЖНОЕ ОБЪЯВЕНИЕ >>> Гонка началась!!!");
    }
}

/*
ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Подготовка!!!
Участник #1 готовится
Участник #2 готовится
Участник #3 готовится
Участник #4 готовится
Участник #5 готовится
Участник #4 готов
Участник #3 готов
Участник #2 готов
Участник #1 готов
Участник #5 готов
ВАЖНОЕ ОБЪЯВЕНИЕ >>> Гонка началась!!!
Участник #4 начал этап: Дорога 60 метров
Участник #3 начал этап: Дорога 60 метров
Участник #2 начал этап: Дорога 60 метров
Участник #1 начал этап: Дорога 60 метров
Участник #5 начал этап: Дорога 60 метров
Участник #4 закончил этап: Дорога 60 метров
Участник #4 готовится к этапу(ждет): Тоннель 80 метров
Участник #4 начал этап: Тоннель 80 метров
Участник #5 закончил этап: Дорога 60 метров
Участник #3 закончил этап: Дорога 60 метров
Участник #3 готовится к этапу(ждет): Тоннель 80 метров
Участник #1 закончил этап: Дорога 60 метров
Участник #5 готовится к этапу(ждет): Тоннель 80 метров
Участник #1 готовится к этапу(ждет): Тоннель 80 метров
Участник #3 начал этап: Тоннель 80 метров
Участник #2 закончил этап: Дорога 60 метров
Участник #2 готовится к этапу(ждет): Тоннель 80 метров
Участник #4 закончил этап: Тоннель 80 метров
Участник #4 начал этап: Дорога 40 метров
Участник #5 начал этап: Тоннель 80 метров
Участник #3 закончил этап: Тоннель 80 метров
Участник #3 начал этап: Дорога 40 метров
Участник #1 начал этап: Тоннель 80 метров
Участник #4 закончил этап: Дорога 40 метров
Участник #3 закончил этап: Дорога 40 метров
Участник #5 закончил этап: Тоннель 80 метров
Участник #5 начал этап: Дорога 40 метров
Участник #2 начал этап: Тоннель 80 метров
Участник #5 закончил этап: Дорога 40 метров
Участник #1 закончил этап: Тоннель 80 метров
Участник #1 начал этап: Дорога 40 метров
Участник #1 закончил этап: Дорога 40 метров
Участник #2 закончил этап: Тоннель 80 метров
Участник #2 начал этап: Дорога 40 метров
Участник #2 закончил этап: Дорога 40 метров
ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка закончилась!!!
Участник #4: Win (Fist place)
Участник #3: 2 place
Участник #5: 3 place
Участник #1: 4 place
Участник #2: 5 place

Process finished with exit code 0

 */
