
/**
 * Created by jonathanrintala on 2019-07-26.
 */

import java.io.IOException;
import java.util.*;
import java.io.File;

public class Main {

    public float readFile(String filePath) throws IOException {
        File file = new File(filePath);
        Scanner scan = new Scanner(file);

        String noOfEntries = scan.nextLine();
        System.out.println("No of entries: " + noOfEntries);

        float accumulatedQaly = 0;

        while (scan.hasNextLine()) {
            String[] lineChars = scan.nextLine().split(" ");
            accumulatedQaly += Float.valueOf(lineChars[0]) * Float.valueOf(lineChars[1]);
        }
        scan.close();

        return accumulatedQaly;
    }

    public static void main(String[] args) {

        Main theMain = new Main();

        try {
            String filePath = args[0];

            // field matrix from input file
            float qaly = theMain.readFile(filePath);
            System.out.println(String.format("%.3f", qaly));

        } catch (IOException e) {
            System.out.println("Error occurred when reading file");
        }
    }

}
