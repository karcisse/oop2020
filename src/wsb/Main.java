package wsb;

import wsb.creatures.*;
import wsb.database.ObjectToSql;
import wsb.devices.*;
import wsb.generics.SuperNumber;
import wsb.threads.CallableSorter;

import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

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
        animals.sort(Comparator.comparingDouble(Animal::getWeight));
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

//        ExecutorService executor = Executors.newFixedThreadPool(4);
//        Collection<Future<WinnerWrapper>> futures = executor.invokeAll(Arrays.asList(
//                new RandomBallThrowingNumbers(() -> System.out.println("Frozen in Time")),
//                new RandomBallThrowingNumbers(() -> System.out.println("Kahn-Sequences")),
//                new RandomBallThrowingNumbers(() -> System.out.println("Split Decision")),
//                new RandomBallThrowingNumbers(() -> System.out.println("Hair Today Gone Tomorrow")),
//                new RandomBallThrowingNumbers(() -> System.out.println("Pop Goes the Mortal")),
//                new RandomBallThrowingNumbers(() -> System.out.println("Rest in Pieces"))
//        ));
//        System.out.println("DONE, let's see who won");
//        futures.stream().map(winnerWrapperFuture -> {
//            try {
//                return winnerWrapperFuture.get();
//            } catch (InterruptedException | ExecutionException e) {
//                throw new IllegalStateException(e);
//            }
//        }).max(Comparator.comparingDouble(WinnerWrapper::getScore))
//                .ifPresent(winner -> winner.getFinisher().finishHim());
//        executor.shutdown();

        List<Integer> list1 = generateListWithRandomUniqueNumbers(500);
        List<Integer> list2 = generateListWithRandomUniqueNumbers(1500);
        List<Integer> list3 = generateListWithRandomUniqueNumbers(5000);
        List<Integer> list4 = generateListWithRandomUniqueNumbers(10000);

        ExecutorService singleExecutor = Executors.newSingleThreadExecutor();

        Date start = new Date();
        Collection<Future<List<Integer>>> futureNumbers = singleExecutor.invokeAll(Arrays.asList(
                new CallableSorter(list1),
                new CallableSorter(list2),
                new CallableSorter(list3),
                new CallableSorter(list4)
        ));

        singleExecutor.shutdown();
        System.out.println("Single thread sorting done in " + (new Date().getTime() - start.getTime()) + " miliseconds");

        ExecutorService multiExecutor = Executors.newFixedThreadPool(4);

        Date start2 = new Date();
        Collection<Future<List<Integer>>> futureNumbersSortedSameTime = multiExecutor.invokeAll(Arrays.asList(
                new CallableSorter(list1),
                new CallableSorter(list2),
                new CallableSorter(list3),
                new CallableSorter(list4)
        ));

        multiExecutor.shutdown();
        System.out.println("Multi thread sorting done in " + (new Date().getTime() - start2.getTime()) + " miliseconds");

        // TASK 12 & 13 & 14
        ObjectToSql objectToSql = new ObjectToSql();
        System.out.println(objectToSql.insert(ford));
        ElectricCar electricCar = new ElectricCar("Tesla", "S", 2020);
        System.out.println(objectToSql.insert(electricCar));

        // TASK 15

        SuperNumber<Double> superNumber = new SuperNumber<>(7.5);
        SuperNumber<Double> superNumber2 = new SuperNumber<>(7.0);
        SuperNumber<Double> superNumber3 = new SuperNumber<>(7.5);

        System.out.println(superNumber.getAsDouble());
        System.out.println(superNumber.getAsInt());
        System.out.println(superNumber.isDoubleEqualInt());
        System.out.println(superNumber2.isDoubleEqualInt());
        System.out.println(superNumber.intEquals(superNumber2));
        System.out.println(superNumber.intEquals(superNumber3));
        System.out.println(superNumber.doubleEqual(superNumber2));
        System.out.println(superNumber.doubleEqual(superNumber3));
    }

    private static List<Integer> generateListWithRandomUniqueNumbers(int size) {
        Random random = new Random();
        Set<Integer> set = new HashSet<>();
        while (set.size() != size) {
            set.add(random.nextInt(Integer.MAX_VALUE));
        }
        return new ArrayList<>(set);
    }
}
