public class HasLength implements Predicate<String> {
    private int length;

    HasLength(int length) {
        if (length < 0) {
            throw new IllegalArgumentException();
        }
        this.length = length;
    }
    
    @Override
    public boolean test(String value) {
        if (value == null) {
            return false;
        }
        return value.length() == length;
    }
}
