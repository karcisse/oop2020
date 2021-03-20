package wsb.threads;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

public class CallableSorter implements Callable<List<Integer>> {

    private final List<Integer> numbers;

    public CallableSorter(List<Integer> numbers) {
        this.numbers = numbers;
    }

    @Override
    public List<Integer> call() throws Exception {
        bubbleSort(numbers);
        return new ArrayList<>(numbers);
    }

    public void bubbleSort(List<Integer> list) {
        int temp = 0;
        for (int i = 0; i < list.size(); i++) {
            for (int j = 1; j < (list.size() - i); j++) {
                if (list.get(j - 1) > list.get(j)) {
                    //swap elements
                    temp = list.get(j - 1);
                    list.set(j - 1, list.get(j));
                    list.set(j, temp);
                }

            }
        }

    }
}
