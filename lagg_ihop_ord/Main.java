/**
 * Created by jonathanrintala on 2019-05-22.
 */

import java.util.Scanner;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException{

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int noOfSubstrings = Integer.valueOf(reader.readLine());
        
        String totString = "";

        for(int i=0; i<noOfSubstrings;i++){
            totString += reader.readLine().trim(); 
        }
        
        System.out.println(totString);

    }

}
