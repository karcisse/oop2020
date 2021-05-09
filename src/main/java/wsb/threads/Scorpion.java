package wsb.threads;

public class Scorpion extends Counter {

    public Scorpion() {
        super(() ->  System.out.println("Chain Reaction"));
    }

    @Override
    public void run() {
        super.run();
    }
}
