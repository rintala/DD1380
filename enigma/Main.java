/**
 * Created by jonathanrintala on 2019-05-20.
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

    public String[][] readFile(String filePath) throws IOException {
        //System.out.println("readfile initialized..");
    
        //File file = new File(filePath); 
        Scanner scan = new Scanner(System.in);   
        //Scanner scan = new Scanner(System.in);   
        
        String[] cryptoText = scan.nextLine().split("");
        String[] clearText = scan.nextLine().split(""); 
        
        String[][] outStrings = new String[2][];
        outStrings[0] = cryptoText;
        outStrings[1] = clearText;

        return outStrings;
    }

    public static ArrayList <Integer> findPositions(String[][] cryptoAndClearText){
        String[] cryptoText = cryptoAndClearText[0];
        String[] clearText = cryptoAndClearText[1];

        ArrayList <Integer> outputPositions = new ArrayList<Integer>();

        int noOfPossiblePositions = cryptoText.length - clearText.length + 1;
        //System.out.println("NO OF POSSbiLE POS: "+noOfPossiblePositions);
        if(noOfPossiblePositions>0){
            for(int i = 0; i<noOfPossiblePositions;i++){
            // start from cryptotext 0 and inc forward
           
            int clearI = 0;
            for(int c = i; c<cryptoText.length;c++){
                if(cryptoText[c].equals(clearText[clearI])){
                    //System.out.println("THIS LOOP IS DOOMED");
                    break;
                }

                clearI = clearI + 1;

                if(clearI == clearText.length){
                    //System.out.println("THIS LOOP IS GOLDEN - record");
                    outputPositions.add(i+1);
                    break;
                }
            }
            if(i == noOfPossiblePositions-1 && outputPositions.size() == 0){
                outputPositions.add(-1);
            }
            }
        } else{
            outputPositions.add(-1);
        }
        
        return outputPositions;
    }

    public static void main(String[] args){
        //System.out.println("Faltet script initialized...");

        Main theMain = new Main();

        try{
            //System.out.println("filepath: "+args[0]);
            //String filePath = args[0];
            String filePath = "";

            // field matrix from input file
            String[][] cryptoAndClearText = theMain.readFile(filePath);

            // find the possible positions
            ArrayList <Integer> outputPositions = findPositions(cryptoAndClearText);

            for(int oP : outputPositions){
                System.out.print(oP + " ");
            }
        }   
        catch(IOException e){
            System.out.println("Error occurred when reading file");
        }
    }


}
