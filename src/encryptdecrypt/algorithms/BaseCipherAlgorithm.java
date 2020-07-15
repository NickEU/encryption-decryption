package encryptdecrypt.algorithms;

abstract public class BaseCipherAlgorithm {
    abstract public String encode(String input, int key);
    abstract public String decode(String input, int key);
}
