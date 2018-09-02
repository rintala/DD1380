/**
 * Created by jonathanrintala on 2018-09-02.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class Main {

    public ArrayList<Integer> readFile() throws IOException {
        BufferedReader br;
        String sCurrentLine;
        br = new BufferedReader(new InputStreamReader(System.in));
        ArrayList<Integer> dim = new ArrayList<>();

        String[] input = new String[2];

        while ((sCurrentLine = br.readLine()) != null) {
            input = sCurrentLine.split(" ");
        }
        for(int i=0;i<input.length;i++){
            dim.add(Integer.valueOf(input[i]));
        }

        return dim;
    }

    public static void print2D(String matrix[][]){
        int counter = 0;

        try{
            for (String[] row : matrix) {
                if(counter==0){}
                else{
                    System.out.print("\n");
                }
                for(int i =0;i<row.length;i++){
                    System.out.print(row[i]);
                }
                counter++;
            }
        }

        catch(Exception e){
            System.out.println("ERROR w map print");
        }
    }


    public static int determineNumber(String[][] matrix, int i, int j){
        //four distances - left, right, top, bottom
        int leftDistance = j+1;
        int rightDistance = matrix[i].length-j;
        int topDistance = i+1;
        int bottomDistance = matrix.length-i;

        int[] distances = new int[] {leftDistance,rightDistance,topDistance,bottomDistance};

        int minDistance = 1001;

        for(int k = 0; k<distances.length;k++){
            if(distances[k] == 0){
                minDistance = 1;
                break;
            }
            else{

                if(distances[k]<minDistance){
                    minDistance = distances[k];
                }
            }
        }

        return minDistance;

    }


    public static void main(String[] args){
        Main theMain = new Main();
        try{
            ArrayList<Integer> dimensions = theMain.readFile();

            int row = dimensions.get(0);
            int col = dimensions.get(1);

            String[][] theMatrix = new String[row][col];

            for(int i = 0;i<row;i++){
                for(int j=0;j<col;j++){
                    //theMatrix[i][j] = i;
                    //System.out.println(i);

                    int out = determineNumber(theMatrix, i,j);
                    if(out>9){
                        theMatrix[i][j] = ".";
                    }
                    else{
                        theMatrix[i][j] = Integer.toString(out);
                    }

                }
            }

            print2D(theMatrix);

        }
        catch(IOException e){
            System.out.println("Error occurred when reading file");
        }
    }


}
