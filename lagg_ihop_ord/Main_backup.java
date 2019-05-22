/**
 * Created by jonathanrintala on 2019-05-22.
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.FileReader;

class Main_backup {
    public static void main(String[] args) throws IOException{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in), 81920);
        int noOfSubstrings = Integer.parseInt(reader.readLine());
 
        StringBuilder sb = new StringBuilder();
        
        for(int i=0; i<noOfSubstrings;i++){
            sb.append(reader.readLine().trim()); 
        }

        System.out.println(sb);
    }
}
