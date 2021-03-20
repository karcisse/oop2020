package wsb;

import wsb.creatures.*;
import wsb.devices.*;
import wsb.threads.Counter;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {

        Car ford = new DieselCar("Ford", "Mondeo", 2012, 2.0d);
        ford.startACar();
        ford.stopACar();

        List<Animal> animals = Arrays.asList(
                new Human(0, 0, Gender.MALE, 80),
                new Pet("Dog", Gender.MALE, 40),
                new FarmAnimal("Sheep", 120),
                new Human(2, 1, Gender.FEMALE, 60),
                new Pet("Cat", Gender.FEMALE, 20),
                new FarmAnimal("Cow", 200)
        );
        System.out.println(animals);
        animals.sort(new Comparator<Animal>() { // for the sake of exercise we do this but it pains my eyes and should be Comparator.comparing(car -> car.producer)
            @Override
            public int compare(Animal o1, Animal o2) {
                return Double.compare(o1.getWeight(), o2.getWeight());
            }
        });
        System.out.println(animals);

        Map<String, Car> modelCarMap = new HashMap<>(Stream.of(
                new DieselCar("Ford", "Mondeo", 2012, 2.0d),
                new ElectricCar("Tesla", "S", 2019),
                new DieselCar("Opel", "Insignia", 2014, 1.8d)
        ).collect(Collectors.toMap(Car::getModel, car -> car)));

        modelCarMap.keySet().stream()
                .sorted(Comparator.comparing(key -> key))
                .forEach(key -> System.out.println(modelCarMap.get(key)));

        // This is for fun
        Map<String, Car> carsMap = new HashMap<>(modelCarMap.values().stream()
                .collect(Collectors.toMap(Car::generateSerialNumber, car -> car)));

        carsMap.values().stream().sorted(
                Comparator.comparing(Device::getModel))
                .forEach(System.out::println);

        Map<String, Double> countryAreaMap = Stream.of(
                new AbstractMap.SimpleEntry<>("Sweden", 75d),
                new AbstractMap.SimpleEntry<>("Germany", 100d),
                new AbstractMap.SimpleEntry<>("Poland", 10d),
                new AbstractMap.SimpleEntry<>("Austria", 1d),
                new AbstractMap.SimpleEntry<>("Norway", 40d))
                .collect(Collectors.toMap(
                        AbstractMap.SimpleEntry::getKey,
                        AbstractMap.SimpleEntry::getValue));

        System.out.println("Largest country is " + countryAreaMap.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(""));

        System.out.println("Smallest country is " + countryAreaMap.entrySet().stream()
                .min(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(""));


        /* <<<<<<<<<<< TASK 6 >>>>>>>>>>>>>> */

        List<Device> devices = Arrays.asList(
                new DieselCar("Ford", "Mondeo", 2012, 2d),
                new DieselCar("Ford", "Focus", 2010, 1.5d),
                new ElectricCar("Tesla", "S", 2020),
                Phone.createIPhone("7", 4.7d),
                Phone.createTrashPhone("Siemens", "XD", 2d),
                Phone.createTrashPhone("Siemens", "XDDDDDD", 2.5d)
        );

        Map<String, List<Device>> producerDevicesMap = devices.stream().collect(
                Collectors.groupingBy(Device::getProducer, HashMap::new, Collectors.toCollection(ArrayList::new)));

        System.out.println("Devices produced by Ford :" + producerDevicesMap.get("Ford"));
        System.out.println("Devices produced by Siemens :" + producerDevicesMap.get("Siemens"));

        new Thread(new Counter()).start();
        new Thread(new Counter()).start();
    }
}
