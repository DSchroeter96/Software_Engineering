package example;

public class UselessCache implements Cache<Integer, Student> {

    @Override
    public boolean contains(Integer key) {
        return false;
    }

    @Override
    public void put(Integer key, Student value) {

    }

    @Override
    public Student get(Integer key) {
        return null;
    }

    @Override
    public void clear() {

    }
}
