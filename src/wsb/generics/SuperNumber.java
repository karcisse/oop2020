package wsb.generics;

public class SuperNumber<N extends Number> {

    private final N number;

    public SuperNumber(N number) {
        this.number = number;
    }

    public int getAsInt() {
        return number.intValue();
    }

    public double getAsDouble() {
        return number.doubleValue();
    }

    public boolean isDoubleEqualInt() {
        return number.doubleValue() == number.intValue();
    }

    public boolean intEquals(SuperNumber<?> other) {
        return number.intValue() == other.getAsInt();
    }

    public boolean doubleEqual(SuperNumber<?> other) {
        return number.doubleValue() == other.getAsDouble();
    }

    public String printType() {
        return number.getClass().getSimpleName();
    }
}
