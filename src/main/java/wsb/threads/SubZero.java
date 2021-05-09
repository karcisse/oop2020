package wsb.threads;

public class SubZero extends Counter {

    public SubZero() {
        super(() -> System.out.println("Frozen in Time"));
    }

    @Override
    public void run() {
        super.run();
    }
}
