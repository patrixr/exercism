import java.util.*;
import java.util.stream.Collectors;

class CustomSet<T extends Object> implements Set<T> {

    private Map<Integer, T> store;

    public CustomSet() {
        this(Arrays.asList());
    }

    public CustomSet(Iterable<T> data) {
        store = new HashMap();
        data.forEach(this::add);
    }

    @Override
    public int size() {
        return store.size();
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public Iterator<T> iterator() {
        return store.values().iterator();
    }

    @Override
    public Object[] toArray() {
        return store.values().toArray();
    }

    @Override
    public <C> C[] toArray(C[] arr) {
        return store.values().toArray(arr);
    }

    @Override
    public boolean add(T item) {
        if (!contains(item)) {
            store.put(item.hashCode(), item);
            return true;
        }
        return false;
    }

    @Override
    public boolean remove(Object item) {
        if (store.containsKey(item.hashCode())) {
            store.remove(item.hashCode());
            return true;
        }
        return false;
    }

    @Override
    public boolean contains(Object o) {
        return store.containsKey(o.hashCode());
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return c.stream().allMatch(this::contains);
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        return c.stream().map(this::add).anyMatch(res -> res.equals(true));

    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return removeAll(
            Arrays
                .stream(toArray())
                .filter(v -> !c.contains(v))
                .collect(Collectors.toList())
        );
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return c.stream().map(this::remove).anyMatch(res -> res.equals(true));
    }

    @Override
    public void clear() {
        store.clear();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomSet<?> customSet = (CustomSet<?>) o;
        return size() == customSet.size() && customSet.containsAll(store.values());
    }

    public CustomSet<T> getIntersection(Set<T> otherSet) {
        CustomSet<T> out = new CustomSet<T>();
        otherSet.stream().filter(this::contains).forEach(out::add);
        return out;
    }

    public CustomSet<T> getDifference(Set<T> otherSet) {
        CustomSet<T> out = new CustomSet<T>();
        stream().filter(it -> !otherSet.contains(it)).forEach(out::add);
        return out;
    }

    public CustomSet<T> getUnion(Set<T> otherSet) {
        CustomSet<T> out = new CustomSet<T>();
        otherSet.stream().forEach(out::add);
        stream().forEach(out::add);
        return out;
    }

    public boolean isSubset(Set<T> otherSet) {
        return otherSet.stream().allMatch(this::contains);
    }

    public boolean isDisjoint(Set<T> otherSet) {
        return !otherSet.stream().anyMatch(this::contains);
    }

}