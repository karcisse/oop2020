package wsb.threads;

import java.util.Random;
import java.util.concurrent.Callable;

public class RandomBallThrowingNumbers implements Callable<WinnerWrapper> {

    private final Finisher finisher;

    public RandomBallThrowingNumbers(Finisher finisher) {
        this.finisher = finisher;
    }

    @Override
    public WinnerWrapper call() {
        for (int i = 3; i >= 0; i--) {
            if (i == 0) {
                System.out.println("POOF");
            } else {
                System.out.println("" + i + "...");
            }
            sleepForASecond();
        }
        return new WinnerWrapper(new Random().nextDouble(), finisher);
    }

    private void sleepForASecond() {
        try {
            Thread.sleep(1000 );
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
