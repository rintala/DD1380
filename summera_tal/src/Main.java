import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
/**
 * Created by jonathanrintala on 2018-09-01.
 */
public class Main {
    static ArrayList<String> ar = new ArrayList<String>();
    public static int noOfNumbersToSum = 0;
    public static int theSum = 0;

    public ArrayList<String> readFile() throws IOException {
        BufferedReader br;
            String sCurrentLine;
            br = new BufferedReader(new InputStreamReader(System.in));
            while ((sCurrentLine = br.readLine()) != null) {
                ar.add(sCurrentLine);
            }
        return ar;
    }

    public static void main(String[] args){
        Main theMain = new Main();
            try{
                ArrayList<String> file = theMain.readFile();
                int numberOfNumbers = Integer.valueOf(file.get(0));
                if(numberOfNumbers%2 ==1){
                    noOfNumbersToSum = (numberOfNumbers+1)/2;
                } else{
                        noOfNumbersToSum = numberOfNumbers/2;
                }
                String[] theNumbers = file.get(1).split(" ");
                int[] theNumbersInt = new int[theNumbers.length];

                for(int i = 0;i<theNumbers.length;i++){
                    theNumbersInt[i] = Integer.valueOf(theNumbers[i]);
                }
                Arrays.sort(theNumbersInt);
                for(int i=theNumbersInt.length-1; i>(theNumbersInt.length-noOfNumbersToSum-1);i--){
                    theSum+=theNumbersInt[i];
                }
                System.out.println(theSum);
            }
            catch(IOException e){
                System.out.println("Error occurred when reading file");
            }
    }
}
