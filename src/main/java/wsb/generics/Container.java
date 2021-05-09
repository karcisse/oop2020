package wsb.generics;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class Container<E> implements Collection<E> {

    private List<E> objects = new ArrayList<>();
    private List<Change> changes = new ArrayList<>();

    public int numberOfChanges() {
        return changes.size();
    }

    public List<Change> getChanges() {
        return changes;
    }

    @Override
    public boolean add(E e) {
        changes.add(Change.ADD);
        return objects.add(e);
    }

    @Override
    public boolean remove(Object o) {
        changes.add(Change.REMOVE);
        return objects.remove(o);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return objects.containsAll(c);
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        c.forEach(o -> changes.add(Change.ADD));
        return objects.addAll(c);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        c.forEach(o -> changes.add(Change.REMOVE));
        return objects.removeAll(c);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        c.stream()
                .filter(o -> !objects.contains(o))
                .forEach(o -> changes.add(Change.REMOVE));
        return objects.retainAll(c);
    }

    @Override
    public void clear() {
        changes.add(Change.CLEAR);
        objects.clear();
    }

    @Override
    public int size() {
        return objects.size();
    }

    @Override
    public boolean isEmpty() {
        return objects.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return objects.contains(o);
    }

    @Override
    public Iterator<E> iterator() {
        return objects.iterator();
    }

    @Override
    public Object[] toArray() {
        return objects.toArray();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return objects.toArray(a);
    }

    @Override
    public String toString() {
        return objects.toString();
    }

    protected List<E> getObjectsCopy() {
        return new ArrayList<>(objects);
    }

    protected void setObjects(List<E> objects) {
        this.objects = objects;
    }
}
