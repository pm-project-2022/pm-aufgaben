package lambda;

import java.util.Comparator;

@FunctionalInterface
public interface CompareInterface<T extends Student> extends Comparator<T> {
    public int compare(T t1, T t2);
}
