public class EndsWith implements Predicate<String> {
    private char[] suffix;

    EndsWith(String suffix) {
        if (suffix == null) {
            throw new IllegalArgumentException();
        }
        this.suffix = suffix.toCharArray();
    }

    @Override
    public boolean test(String value) {
        if (value == null) {
            return false;
        }
        char[] valueChar = value.toCharArray();
        for (int i = suffix.length-1; i>=0; i--) {
            if (valueChar.length < suffix.length || !(suffix[i] == valueChar[i + (valueChar.length - suffix.length)])) {
                return false;
            }
        }
        return true;
    }
}