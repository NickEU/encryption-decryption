package encryptdecrypt;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.Scanner;

public class IOHelper {
    private static final String FILE_NOT_FOUND_MSG = "Error! Cannot find file: ";
    private static final String FILE_WRITE_FAILED_MSG = "Error! Failed to write to file: ";

    static void print(String destination, String output) {
        final String cli = "console";
        if (destination.equals(cli)) {
            System.out.println(output);
        } else {
            writeDataToFile(destination, output);
        }
    }

    private static void writeDataToFile(String destination, String output) {
        try (PrintWriter writer = new PrintWriter(destination)) {
            writer.print(output);
        } catch (IOException e) {
            System.out.println(FILE_WRITE_FAILED_MSG + destination);
        }
    }

    public static String loadDataFromFile(String filePath) {
        StringBuilder data = new StringBuilder();
        try (Scanner sc = new Scanner(Paths.get(filePath))) {
            while (sc.hasNextLine()) {
                data.append(sc.nextLine()).append(System.lineSeparator());
            }
        } catch (IOException e) {
            System.out.println(FILE_NOT_FOUND_MSG + filePath);
        }
        return data.toString();
    }
}
