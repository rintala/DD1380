
/**
 * Created by jonathanrintala on 2019-07-26.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.*;
import java.io.File;

public class Main_kattis {

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        scan.nextLine();
        float qaly = 0;

        while (scan.hasNextLine()) {
            String[] lineChars = scan.nextLine().split(" ");
            qaly += Float.valueOf(lineChars[0]) * Float.valueOf(lineChars[1]);
        }
        scan.close();
        System.out.println(String.format("%.3f", qaly));

    }

}
