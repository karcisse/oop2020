package wsb.creatures;

import wsb.creatures.enums.FoodType;

public interface Feedable {

    void feed(Double foodWeight, FoodType foodType);
}
