package wsb.creatures;

import wsb.creatures.enums.FoodType;

public class FarmAnimal extends Animal implements Edible{

    public FarmAnimal(String species, double weight) {
        super(species, Gender.MALE, FoodType.CROPS, weight);
    }

    @Override
    public void beEaten() throws Exception {
        this.kill();
        System.out.println("that was yuammy");
    }
}
