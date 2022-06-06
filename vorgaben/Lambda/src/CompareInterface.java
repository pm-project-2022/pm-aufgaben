import java.util.Comparator;

@FunctionalInterface
public interface CompareInterface<T> extends Comparator<T> {
    int compare(T o1, T o2);
}
