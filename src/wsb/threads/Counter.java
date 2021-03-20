package wsb.threads;

public class Counter implements Runnable {
    @Override
    public void run() {
        System.out.println("Counting ...");
        for (int i = 10; i >= 0; i--) {
            if (i == 0) {
                System.out.println("POOF");
            } else {
                System.out.println("" + i + "...");
            }
            sleepForASecond();
        }
    }

    private void sleepForASecond() {
        try {
            Thread.sleep(1000 );
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
