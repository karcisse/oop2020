package wsb.creatures.enums;

public enum FoodType {
    MEAT(.7), CROPS(.3), ALL(.5);

    private final Double foodToBodyRatio;

    FoodType(Double foodToBodyRatio) {
        this.foodToBodyRatio = foodToBodyRatio;
    }

    public Double getFoodToBodyRatio() {
        return foodToBodyRatio;
    }
}
