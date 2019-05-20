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

class CountryComparator implements Comparator<String>{
    Map<String, Integer> countriesPop;

    public CountryComparator(Map<String, Integer> countriesPop) {
        this.countriesPop = countriesPop;
    }

    public int compare(String a, String b) {
        if (countriesPop.get(a) > countriesPop.get(b)) {
            return -1;
        } else if (countriesPop.get(a) == countriesPop.get(b)){
            if(a.compareTo(b)>0){
                return 1;
            } else{
                return -1;
            }
        } else{
            return 1;
        } // returning 0 would merge keys
    }
}

public class Main {

    public Map<String, Integer> readFile() throws IOException {
        //System.out.println("readfile initialized..");
    
        //File file = new File(filePath); 
        Scanner scan = new Scanner(System.in);   
        
        String[] noOfCountries = scan.nextLine().split(" "); 
        
        int n = Integer.valueOf(noOfCountries[0]);
        
        //System.out.println("NO OF COUNTRIES n = "+n);

        Map<String, Integer> countriesPop = new HashMap<String, Integer>();
        for(int i = 0; i<n; i++){
            String[] countryData = scan.nextLine().split(" ");
            String countryName = countryData[0];
            int countryInitPop = Integer.valueOf(countryData[1]);
            countriesPop.put(countryName, countryInitPop);
        }

        String[] noOfUpdatesData = scan.nextLine().split(" ");
        int noOfUpdates = Integer.valueOf(noOfUpdatesData[0]);
        for(int i = 0; i <noOfUpdates; i++){
            String[] countryUpdateData = scan.nextLine().split(" ");
            String countryName = countryUpdateData[0];
            int countryUpdatePop = Integer.valueOf(countryUpdateData[1]);
            
            int countryPrevPop = countriesPop.containsKey(countryName) ? countriesPop.get(countryName) : 0;
            countriesPop.put(countryName, countryPrevPop + countryUpdatePop);
        }

        return countriesPop;
    }

    public static void main(String[] args){
        //System.out.println("Faltet script initialized...");

        Main theMain = new Main();

        try{
            //System.out.println("filepath: "+args[0]);
            //String filePath = args[0];

            // field matrix from input file
            Map<String, Integer> countriesPop = theMain.readFile();

            CountryComparator cComp = new CountryComparator(countriesPop);
            TreeMap<String, Integer> sortedCountries = new TreeMap<String, Integer>(cComp);
            sortedCountries.putAll(countriesPop);

            Set<Map.Entry<String,Integer>> set = sortedCountries.entrySet();

            for (Map.Entry<String, Integer> country : set) { 
                System.out.println(country.getKey() + " " + country.getValue()); 
            }
            //System.out.println(country.getKey() + " " + country.getValue());
            
            //System.out.println("sorted: " + sortedCountries);
        }   
        catch(IOException e){
            System.out.println("Error occurred when reading file");
        }
    }


}
