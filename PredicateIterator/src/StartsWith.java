public class StartsWith implements Predicate<String> {
    private char[] prefix;

    StartsWith(String prefix) {
        if (prefix == null) {
            throw new IllegalArgumentException();
        }
        this.prefix = prefix.toCharArray();
    }

    @Override
    public boolean test(String value) {
        if (value == null) {
            return false;
        }
        char[] valueChar = value.toCharArray();
        for (int i=0; i<prefix.length; i++) {
            if (valueChar.length < prefix.length || !(prefix[i] == valueChar[i])) {
                return false;
            }
        }
        return true;
    }
}
