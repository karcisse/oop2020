package wsb.threads;

public class WinnerWrapper {

    private final Double score;
    private final Finisher finisher;

    public WinnerWrapper(Double score, Finisher finisher) {
        this.score = score;
        this.finisher = finisher;
    }

    public Double getScore() {
        return score;
    }

    public Finisher getFinisher() {
        return finisher;
    }
}
