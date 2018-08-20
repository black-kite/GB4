
public class Main {

    static Object mon = new Object();
    static volatile char changeSymbol = 'A';

    public static void main(String[] args) {

        abc();

    }

    public static void abc() {

        new Thread(new Runnable() {
            public void run() {
                try {
                    for (int i = 0; i < 5; i++) {
                        synchronized (mon) {
                            while (changeSymbol != 'A') {
                                mon.wait();
                            }
                            System.out.print("A");
                            changeSymbol = 'B';
                            mon.notifyAll();
                        }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(new Runnable() {
            public void run() {
                try {
                    for (int i = 0; i < 5; i++) {
                        synchronized (mon) {
                            while (changeSymbol != 'B') {
                                mon.wait();
                            }
                            System.out.print("B");
                            changeSymbol = 'C';
                            mon.notifyAll();
                        }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(new Runnable() {
            public void run() {
                try {
                    for (int i = 0; i < 5; i++) {
                        synchronized (mon) {
                            while (changeSymbol != 'C') {
                                mon.wait();
                            }
                            System.out.print("C");
                            changeSymbol = 'A';
                            mon.notifyAll();
                        }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
