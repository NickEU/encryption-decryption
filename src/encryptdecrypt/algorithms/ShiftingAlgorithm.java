package encryptdecrypt.algorithms;

public class ShiftingAlgorithm extends BaseCipherAlgorithm {
    private final int LETTERS_IN_ALPHABET = 26;
    @Override
    public String encode(String phraseToEncrypt, int key) {
        return transform(phraseToEncrypt, key % LETTERS_IN_ALPHABET);
    }

    @Override
    public String decode(String phraseToDecrypt, int key) {
        return transform(phraseToDecrypt, - (key % LETTERS_IN_ALPHABET));
    }

    private String transform(String phrase, int key) {
        final char nullChar = '\u0000';
        final int aChar = 97;
        final int zChar = 122;
        final int Achar = 65;
        final int Zchar = 90;

        StringBuilder result = new StringBuilder();

        for (char curChar : phrase.toCharArray()) {
            char baseChar = curChar >= aChar && curChar <= zChar ? aChar
                    : curChar >= Achar && curChar <= Zchar ? Achar : nullChar;
            if (baseChar == nullChar) {
                result.append(curChar);
            } else {
                char potentialChar = (char) (curChar + key);
                result.append(normalizeChar(potentialChar, baseChar, (char) (baseChar + 25)));
            }
        }
        return result.toString();
    }

    private char normalizeChar(char potential, char firstLetter, char lastLetter) {
        return potential < firstLetter ? (char) (potential + LETTERS_IN_ALPHABET)
                : potential > lastLetter ? (char) (potential - LETTERS_IN_ALPHABET) : potential;
    }
}
