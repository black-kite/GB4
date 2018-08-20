import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class ThreeThreads {

    public static DataOutputStream out;

    public static void main(String[] args) throws InterruptedException {

        ThreeThreads monitor = new ThreeThreads();

        try {
            out = new DataOutputStream(new FileOutputStream("outFiles/threeThread.out"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                monitor.writeToFile(out, "Первый пошел №1\n");
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                monitor.writeToFile(out, "Второй пошел №2\n");
            }
        });
        Thread t3 = new Thread(new Runnable() {
            @Override
            public void run() {
                monitor.writeToFile(out, "Третий пошел №3\n");
            }
        });
        t1.start();
        t2.start();
        t3.start();
        t1.join();
        t2.join();
        t3.join();
        try {
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Запись произведена, посмотрите еще раз файл (threeThread.out)");
    }

    private void writeToFile(DataOutputStream out, String text) {

        try {
            for (int i = 0; i < 10; i++) {
                out.writeUTF(text);
                Thread.sleep(20);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
