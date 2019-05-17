/**
 * Created by jonathanrintala on 2018-09-02.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.*;
import java.io.File; 

import java.util.LinkedList; 
import java.util.Queue; 


public class Main_kattis {

    public Integer[] readFile() throws IOException {
        //System.out.println("readfile initialized..");
    
        //File file = new File(filePath); 
        Scanner scan = new Scanner(System.in);   
        
        String[] inputData = scan.nextLine().split(" "); 
        
        int n = Integer.valueOf(inputData[0]);
        int a = Integer.valueOf(inputData[1]);
        int b = Integer.valueOf(inputData[2]);
        
        Integer[] inputParams = {n,a,b};

        return inputParams;
    }

    public static Set<Integer> findNumbersEndingWithN(int n, int a, int b, Set<Integer> boffNumbers){
        for(int i = a; i<=b;i++){
            //System.out.println("i: "+i);

            // check number of digits in n (in range 1-2, since n could be 1-99)
            int noOfDigitsInN = String.valueOf(n).length();

            if(noOfDigitsInN == 1){
                // if last digit = n => add to set
                if(i%10 == n){
                    //System.out.println("- i "+i);
                    boffNumbers.add(i);
                }
            } else if(noOfDigitsInN == 2){
                if(i%100 == n){
                    //System.out.println("- i "+i);
                    boffNumbers.add(i);
                }
            }
            
        }

        return boffNumbers;
    }

    public static Set<Integer> findNumbersDividableWithN(int n, int a, int b, Set<Integer> boffNumbers){
        //System.out.println("\n.....");
        for(int i = a; i<=b;i++){
            //System.out.println("i: "+i);
            if(i%n == 0){
                //System.out.println("- evenly dividable "+i);
                boffNumbers.add(i);
            }
        }
        return boffNumbers; 
    }

    public static void main(String[] args){
        //System.out.println("Faltet script initialized...");

        Main_kattis theMain = new Main_kattis();

        try{

            // field matrix from input file
            Integer[] inputParams = theMain.readFile();
            for(int i=0; i<inputParams.length;i++){
                //System.out.println("i"+i + ": " + inputParams[i]);
            }
            
            Set<Integer> boffNumbersUnique = new HashSet<>();

            // find numbers that end with n
            boffNumbersUnique = findNumbersEndingWithN(inputParams[0], inputParams[1], inputParams[2], boffNumbersUnique);

            // find numbers that are dividable with n
            boffNumbersUnique = findNumbersDividableWithN(inputParams[0], inputParams[1], inputParams[2], boffNumbersUnique);

            System.out.println(boffNumbersUnique.size());


        }
        catch(IOException e){
            System.out.println("Error occurred when reading file");
        }
    }


}
