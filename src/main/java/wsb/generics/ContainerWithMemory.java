package wsb.generics;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ContainerWithMemory<E> extends Container<E> {

    private final List<List<E>> history = new ArrayList<>();

    public void backToVersion(int version) {
        getChanges().add(Change.RESTORE);
        setObjects(history.get(version));
    }

    public void printVersion(int version) {
        System.out.println(history.get(version));
    }

    public void printAll() {
        int i = 0;
        for (List<E> historyRecord : history) {
            System.out.println("" + i + " - " + historyRecord);
            i++;
        }
    }

    @Override
    public boolean add(E e) {
        history.add(getObjectsCopy());
        return super.add(e);
    }

    @Override
    public boolean remove(Object o) {
        history.add(getObjectsCopy());
        return super.remove(o);
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        history.add(getObjectsCopy());
        return super.addAll(c);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        history.add(getObjectsCopy());
        return super.removeAll(c);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        history.add(getObjectsCopy());
        return super.retainAll(c);
    }

    @Override
    public void clear() {
        history.add(getObjectsCopy());
        super.clear();
    }
}
