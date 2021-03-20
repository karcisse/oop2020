package wsb.threads;

public class SubZero extends Counter {

    @Override
    public void run() {
        super.run();
        finishHim();
    }

    public void finishHim() {
        System.out.println("Frozen in Time");
    }
}
