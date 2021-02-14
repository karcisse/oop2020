package wsb.creatures;

import wsb.creatures.enums.FoodType;
import wsb.database.Connector;

import java.io.File;
import java.sql.SQLException;

public class Animal implements Feedable, Comparable<Animal> {

    private static Double NEW_DOG_WEIGHT = 4.0;
    private static Double NEW_LION_WEIGHT = 39.2;
    private static Double NEW_OTHER_ANIMAL_WEIGHT = 5.3;
    private static Double DEFAULT_FEED_WEIGHT = 1.0;

    final String species;
    private Double weight;
    public String name;
    private final Gender gender;
    private final FoodType foodType;
    File pic;

    public Animal(String species, Gender gender, FoodType foodType) {
        System.out.println("we created new Animal");
        this.gender = gender;
        this.species = species;
        this.foodType = foodType;

        switch (species) {
            case "dog": {
                weight = NEW_DOG_WEIGHT;
                break;
            }
            case "lion": {
                weight = NEW_LION_WEIGHT;
                break;
            }
            default: {
                weight = NEW_OTHER_ANIMAL_WEIGHT;
                break;
            }
        }
        try {
            this.save();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Gender getGender() {
        return gender;
    }

    public void feed(Double foodWeight, FoodType foodType) {
        if (weight == 0) {
            System.out.println("too late, " + name + " is dead");
            return;
        }
        if (!this.foodType.equals(foodType)){
            System.out.println("Bad food, pfff");
        } else {
            digest(foodWeight);
            System.out.println(name + " says thx for food");
        }
    }

    public void takeForAWalk() {
        if (weight == 0.0) {
            System.out.println("you can't walk the street with dead animal you freak");
        } else if (weight > 3.0) {
            --weight;
            System.out.println(name + " says thx for a walk");
        } else if (weight > 1.0) {
            --weight;
            System.out.println(name + " says thx for a walk but I'm hungry");
        } else {
            weight = 0.0;
            System.out.println(name + " died");
        }
    }

    Double getWeight() {
        return weight;
    }

    protected void kill() {
        System.out.println("byeeeeeeeeeeeeee");
        this.weight = 0.0;
    }

    public String toString() {
        return "Hi I'm an Animal " + this.species + " " + this.name;
    }

    @Override
    public int compareTo(Animal o) {
        return this.species.compareTo(o.species);
    }

    public void save() throws SQLException {
        String sql = "insert into animal values ('" + this.species + "','" + this.name + "'," + this.weight + ");";
        System.out.println(sql);
        Connector.executeSQL(sql);
    }

    private void digest(Double foodWeight) {
        weight += foodWeight * foodType.getFoodToBodyRatio();
    }
}
