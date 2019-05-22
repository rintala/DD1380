/**
 * Created by jonathanrintala on 2019-05-22.
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.InputStream;

class Main {
    public static void main(String[] args) throws IOException{
        int i = 0;
        char c;
        byte[] bs = new byte[1000];
      
      try {
        String filePath = args[0];
        // create new file input stream
        FileInputStream fis = new FileInputStream(filePath);
        //InputStream fis = System.in;

        // read bytes to the buffer        
        i = fis.read(bs);

        // info
        //System.out.println("Number of bytes read: "+i);

        // for each byte in buffer
        int k = 0;
        String noOfStrings = "";
        int newLineCount = 0;
        int comp = 0;
        
        String line;

        StringBuilder sb = new StringBuilder();
        
        outer: for(byte b : bs) {
            // converts byte to character
            c = (char)b;
            
            //System.out.println("k"+k+": "+c);
            if(c == ' '){
                // space found - do nothing
                // keep space if it is part of a word!
                //System.out.println("space:" + (char)bs[k-1] + ". "+ (char)bs[k] +". "+(char)bs[k+1]);
                if(Character.isLetter((char)bs[k-1]) && Character.isLetter((char)bs[k+1])){
                    sb.append(c);
                }
            } else{
                if(k == comp){
                    int numberToAdd = Character.getNumericValue(c);
                    if(numberToAdd != -1){
                        comp++;
                        noOfStrings += Integer.toString(numberToAdd);
                    } 
                } else if(c == '\n'){
                    //System.out.println("NEW LINE!!");
                    newLineCount++;
                    if(newLineCount == Integer.valueOf(noOfStrings)){
                        break outer;
                        //System.out.println("END!");
                    }
                } else{
                    sb.append(c);
                }
            }
            k++;
            
        }
        System.out.println(sb); 
        } catch(Exception ex) {
            // if any error occurs
            ex.printStackTrace();
        }
    }

}

