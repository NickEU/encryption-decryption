package encryptdecrypt;

class ArgumentParser {
    private static final String ERROR_INVALID_ARGS_MSG = "Error! Invalid arguments!";
    private static final String ERROR_UNSUPPORTED_OPERATION_MSG = "Error! Unsupported operation!";

    final static String UNI_ALG = "unicode";
    final static String SHIFT_ALG = "shift";

    final static String DEC_MODE = "dec";
    final static String ENC_MODE = "enc";

    private String outputDest = "console";
    private final Operation op = new Operation();


    void start(String[] args){
        parseArguments(args);
        String result = op.transform();
        IOHelper.print(outputDest, result);
    }

    private void parseArguments(String[] args) {
        final String modeArg = "mode";
        final String keyArg = "key";
        final String dataArg = "data";
        final String inArg = "in";
        final String outArg = "out";
        final String algArg = "alg";

        for (int i = 0; i < args.length; i += 2) {
            String type = args[i].substring(1);
            String val;
            try {
                val = args[i + 1];
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println(ERROR_INVALID_ARGS_MSG);
                return;
            }
            switch(type) {
                case algArg:
                    parseAlgoType(val);
                    break;
                case modeArg:
                    parseModeSetting(val);
                    break;
                case keyArg:
                    op.setKey(Integer.parseInt(val));
                    break;
                case dataArg:
                    op.setData(val);
                    break;
                case inArg:
                    if (op.dataIsEmpty()) {
                        op.setData(IOHelper.loadDataFromFile(val));
                    }
                    break;
                case outArg:
                    outputDest = val;
                    break;
                default:
                    System.out.println(ERROR_INVALID_ARGS_MSG);
            }
        }
    }

    private void parseModeSetting(String userInput) {
        switch (userInput) {
            case DEC_MODE:
                op.setMode(Mode.DECRYPT);
                break;
            case ENC_MODE:
                op.setMode(Mode.ENCRYPT);
                break;
            default:
                System.out.println(ERROR_UNSUPPORTED_OPERATION_MSG);
                break;
        }
    }

    private void parseAlgoType(String userInput) {
        switch (userInput) {
            case UNI_ALG:
                op.setAlgo(AlgoType.UNICODE);
                break;
            case SHIFT_ALG:
                op.setAlgo(AlgoType.SHIFTING_AKA_CAESAR);
                break;
            default:
                System.out.println(ERROR_UNSUPPORTED_OPERATION_MSG);
        }
    }
}
