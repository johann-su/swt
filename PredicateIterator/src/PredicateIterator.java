import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PredicateIterator<T> implements Iterator<T> {
    private Iterator<T> iter;
    private Predicate<T> predicate;
    private List<T> elems;
    private Iterator<T> elemsIter;

    PredicateIterator(Iterator<T> iter, Predicate<T> predicate) {
        if (iter==null || predicate==null) {
            throw new NullPointerException();
        }
        this.iter = iter;
        this.predicate = predicate;

        this.elems = new ArrayList<T>();
        while (iter.hasNext()) {
            T next = iter.next();
            if (predicate.test(next)) {
                elems.add(next);
            }
        }
        this.elemsIter = elems.iterator();
    }

    @Override
    public boolean hasNext() {
        return elemsIter.hasNext();
    }

    @Override
    public T next() {
        return elemsIter.next();
    }
}