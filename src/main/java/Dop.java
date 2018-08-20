import java.io.*;
import java.util.concurrent.Semaphore;

public class Dop {

    public static void main(String[] args) throws IOException {
        Semaphore sem = new Semaphore(4);
        for (int i = 0; i < 10; i++) {
            new ReadingThreads(sem, i).start();
        }
    }
//    public void justRun() throws IOException {
//        int c;
//        while ((c = reader.read()) != -1) {
//            System.out.print((char) c);
//        }
//    }
}

class ReadingThreads extends Thread {

    BufferedReader reader;

    {
        try {
            reader = new BufferedReader(new FileReader("outFiles/random.out"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    Semaphore sem;
    int numDones = 0;
    int idThread;

    ReadingThreads(Semaphore sem, int idThread) {
        this.sem = sem;
        this.idThread = idThread;
    }

    @Override
    public void run() {
        try {
            while (numDones < 1) {
                sem.acquire();
                int c;
                while ((c = reader.read()) != -1) {
                    System.out.print((char) c);
                }
                sleep(100);
                numDones++;

                sem.release();
                sleep(500);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.out.println();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
