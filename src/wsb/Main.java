package wsb;

import wsb.devices.Car;
import wsb.devices.Device;
import wsb.devices.DieselCar;
import wsb.devices.ElectricCar;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {

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
    }
}
