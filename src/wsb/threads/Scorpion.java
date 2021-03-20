package wsb.threads;

public class Scorpion extends Counter {

    @Override
    public void run() {
        super.run();
        finishHim();
    }

    public void finishHim() {
        System.out.println("Chain Reaction");
    }
}
