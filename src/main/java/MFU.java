public class MFU {

    private Object printDock = new Object();
    private Object scanDock = new Object();

    public static void main(String[] args) {

        MFU mfu = new MFU();
        mfu.scan("Tutorial", 24);
        mfu.scan("Cookies", 50);
        mfu.print("Book", 55);
        mfu.print("Moto", 23);

    }

    public void scan(String data, int pages) {
        new Thread(() -> {
            synchronized (scanDock) {
                for (int i = 1; i <= pages; i++) {
                    System.out.println("Сканируется " + data + " страница " + i);
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("Отсканированно - " + pages + " страниц " + data);
            }
        }).start();
    }

    public void print(String data, int pages) {
        new Thread(() -> {
            synchronized (printDock) {
                for (int i = 1; i <= pages; i++) {
                    System.out.println("Печатается " + data + " страница " + i);
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("Отпечатано - " + pages + " страниц " + data);
            }
        }).start();
    }

}
