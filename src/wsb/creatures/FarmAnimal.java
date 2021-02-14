package wsb.creatures;

import wsb.creatures.enums.FoodType;

public class FarmAnimal extends Animal implements Edible{

    public FarmAnimal(String species) {
        super(species, Gender.MALE, FoodType.CROPS);
    }

    @Override
    public void beEaten() throws Exception {
        this.kill();
        System.out.println("that was yuammy");
    }
}
