package encryptdecrypt;

import encryptdecrypt.algorithms.BaseCipherAlgorithm;
import encryptdecrypt.algorithms.ShiftingAlgorithm;
import encryptdecrypt.algorithms.UnicodeAlgorithm;

class Operation {
    private BaseCipherAlgorithm algo = new ShiftingAlgorithm();
    private Mode mode = Mode.ENCRYPT;
    private int key = 0;
    private String data = "";

    void setAlgo(AlgoType type) {
        switch(type) {
            case UNICODE:
                this.algo = new UnicodeAlgorithm();
                break;
            case SHIFTING_AKA_CAESAR:
                this.algo = new ShiftingAlgorithm();
                break;
        }
    }

    void setMode(Mode mode) {
        this.mode = mode;
    }

    void setKey(int key) {
        this.key = key;
    }

    void setData(String data) {
        this.data = data;
    }

    boolean dataIsEmpty() {
        return data.isEmpty();
    }

    String transform() {
        switch (mode) {
            case ENCRYPT:
                return algo.encode(data, key);
            case DECRYPT:
                return algo.decode(data, key);
            default:
                return null;
        }
    }

}
