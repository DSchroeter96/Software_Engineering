package example;

import java.util.HashMap;

public class StudentCache implements Cache<Integer, Student> {
    private final HashMap<Integer, Student> cache;

    public StudentCache() {
        this.cache = new HashMap<>();
    }

    @Override
    public boolean contains(Integer key) {
        return this.cache.containsKey(key);
    }

    @Override
    public void put(Integer key, Student value) {
        this.cache.put(key, value);
    }

    @Override
    public Student get(Integer key) {
        return this.cache.get(key);
    }

    @Override
    public void clear() {
        this.cache.clear();
    }
}
