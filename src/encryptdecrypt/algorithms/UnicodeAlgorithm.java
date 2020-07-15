package encryptdecrypt.algorithms;

public class UnicodeAlgorithm extends BaseCipherAlgorithm {
    private String input;
    private int key;

    @Override
    public String encode(String input, int key) {
        this.input = input;
        this.key = key;
        return transform("enc");
    }

    @Override
    public String decode(String input, int key) {
        this.input = input;
        this.key = key;
        return transform("dec");
    }

    private String transform(String op) {
        StringBuilder result = new StringBuilder();
        for (char curChar : input.toCharArray()) {
            if ('\n' == curChar || '\r' == curChar) {
                result.append(curChar);
            } else {
                char transformed = (char) (op.equals("dec")
                        ? curChar - key   // decrypt
                        : curChar + key); // encrypt
                result.append(transformed);
            }
        }
        return result.toString();
    }
}
