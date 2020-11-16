package readme.generator;

public class Pair<T, U> {
    T key;
    U value;
    public Pair(T key, U value) {
        this.key = key;
        this.value = value;
    }

    public T getKey() {
        return key;
    }

    public U getValue() {
        return value;
    }
}
