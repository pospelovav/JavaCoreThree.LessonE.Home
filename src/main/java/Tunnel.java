import java.util.concurrent.Semaphore;

public class Tunnel extends Stage {

    Semaphore queueCar;

    public Tunnel(int maximumCars) {
        this.length = 80;
        this.description = "Тоннель " + length + " метров";
        queueCar = new Semaphore(maximumCars);
    }
    @Override
    public void go(Car c) {
        try {
            try {
                System.out.println(c.getName() + " готовится к этапу(ждет): " + description);
                queueCar.acquire();
                System.out.println(c.getName() + " начал этап: " + description);
                Thread.sleep(length / c.getSpeed() * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println(c.getName() + " закончил этап: " + description);
                queueCar.release();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}