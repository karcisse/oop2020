package wsb.creatures;

import wsb.creatures.enums.FoodType;

public class Pet extends Animal {
    public Pet(String species, Gender gender, double weight) {
        super(species, gender, FoodType.MEAT, weight);
    }
}
