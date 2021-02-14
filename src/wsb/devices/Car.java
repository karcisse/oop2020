package wsb.devices;

import com.sun.deploy.util.StringUtils;
import wsb.Soldable;
import wsb.creatures.Human;

import java.security.SecureRandom;
import java.util.Random;

public abstract class Car extends Device implements Soldable, Comparable<Car> {
    public final Integer yearOfProduction;
    public final Double sizeOfAnEngine;
    public String plates;

    public Car(String producer, String model, Integer yearOfProduction, Double sizeOfAnEngine) {
        super(producer, model);
        this.yearOfProduction = yearOfProduction;
        this.sizeOfAnEngine = sizeOfAnEngine;

    }

    public String generateSerialNumber() {
        return producer.charAt(0)
                + model.charAt(0)
                + "-"
                + yearOfProduction
                + "-"
                + String.format("%05d", new SecureRandom().nextInt(100000));

    }

    abstract public void refuel();

    @Override
    public void turnOn() {
        System.out.println("car is ready to go");
    }

    public String toString() {
        return this.producer + " " + this.model + " " + this.plates;
    }

    @Override
    public int compareTo(Car otherCar) {
        return this.yearOfProduction - otherCar.yearOfProduction;
    }

    @Override
    public void sell(Human buyer, Human seller, Double price) throws Exception {
        if(!seller.hasACar(this)){
            throw new Exception("seller don't have a car");
        }
        if(!buyer.hasAFreePlace()){
            throw new Exception("bouer dont have a plase");
        }
        if(buyer.cash < price){
            throw new Exception("afwfwaawfawf");
        }
        buyer.removeCar(this);
        seller.addCar(this);
        buyer.cash -= price;
        seller.cash += price;
        System.out.println("great, transaction is done");
    }


}