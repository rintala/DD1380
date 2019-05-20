/**
 * Created by jonathanrintala on 2019-05-21.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.*;
import java.io.File; 

import java.util.LinkedList; 
import java.util.Queue; 

public class Main {

    public String[] readFile(String filePath) throws IOException {
        System.out.println("readfile initialized..");
    
        File file = new File(filePath); 
        Scanner scan = new Scanner(file);   
        //Scanner scan = new Scanner(System.in);   
        
        String[] cryptoText = scan.nextLine().split("");

        return cryptoText;
    }

    public static void main(String[] args){
        System.out.println("Kvadranter script initialized..");
        Main theMain = new Main();

        try{
            //System.out.println("filepath: "+args[0]);
            String filePath = args[0];
            //String filePath = "";

            // field matrix from input file
            String[] cryptoAndClearText = theMain.readFile(filePath);
            for(int i = 0;i<cryptoAndClearText.length;i++){
                System.out.println("cry:" + cryptoAndClearText[i]);
            }
        }   
        catch(IOException e){
            System.out.println("Error occurred when reading file");
        }
    }


}
