package wsb.devices;

import wsb.Soldable;
import wsb.creatures.Human;

import java.security.SecureRandom;

public abstract class Car extends Device implements Soldable, Comparable<Car> {
    public final Integer yearOfProduction;
    public String plates;
    private Engine engine;

    public Car(String producer, String model, Integer yearOfProduction, Double sizeOfAnEngine) {
        super(producer, model);
        this.yearOfProduction = yearOfProduction;
        this.engine = new Engine(150d, sizeOfAnEngine, 0d);

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
        startACar();
    }

    public void stopACar() {
        engine.turnOff();
    }

    public void startACar() {
        engine.turnOn();
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

    private static class Engine {

        private Double horsePower;
        private Double volume;
        private Double millage;
        private boolean isRunning;

        public Engine(Double horsePower, Double volume, Double millage) {
            this.horsePower = horsePower;
            this.volume = volume;
            this.millage = millage;
        }

        public void turnOn() {
            isRunning = true;
            System.out.println("engine goes brrrrrr");
        }

        public void turnOff() {
            isRunning = false;
            System.out.println("engine stops brrrr");
        }
    }


}