import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.AtomicInteger;

public class Car implements Runnable, Comparable<Car> {
    private static int CARS_COUNT;
    public static CyclicBarrier carsReady;
    public static AtomicInteger ai;

    static {
        CARS_COUNT = 0;
    }
    public int place;
    private Race race;
    private int speed;
    private String name;
    public String getName() {
        return name;
    }
    public int getSpeed() {
        return speed;
    }
    public Car(Race race, int speed) {
        this.race = race;
        this.speed = speed;
        CARS_COUNT++;
        this.name = "Участник #" + CARS_COUNT;
    }
    @Override
    public void run() {
        try {
            System.out.println(this.name + " готовится");
            Thread.sleep(500 + (int)(Math.random() * 800));
            System.out.println(this.name + " готов");
            carsReady.await();              //ожидаем, пока все потоки "скажут" готов

        } catch (Exception e) {
            e.printStackTrace();
        }
        for (int i = 0; i < race.getStages().size(); i++) {
            race.getStages().get(i).go(this);
        }
        this.place = ai.incrementAndGet();
    }

    @Override
    public int compareTo(Car o) {           //для сортировки по местам
        return this.place - o.place;
    }
}