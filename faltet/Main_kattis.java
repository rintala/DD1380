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

// data structure for storing coordinates in the field
class FieldCoord implements Comparable<FieldCoord> {
    int row;
    int col;
    String cameFrom;

    //camefrom
    public int compareTo(FieldCoord coord) {
        int rowDiff = row-coord.row;
        if(rowDiff != 0){
            return rowDiff;
        }

        int colDiff = col-coord.col;
        
        return colDiff;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + row;
        result = prime * result + col;
        return result;
    }

    @Override
    public boolean equals(Object obj) { 
  
        // If the object is compared with itself then return true   
        if (obj == this) { 
            return true; 
        } 
          
        // typecast obj to FieldCoord so we can compare  
        FieldCoord fCoord = (FieldCoord) obj; 

        // Compare the data members and return accordingly  
        if(row != fCoord.row){
            return false;
        }

        if(col != fCoord.col){
            return false;
        }

        return true;

        //return this.compareTo(fCoord) == 0;
    }
}

class NoDuplicates<E> extends PriorityQueue<E> {
  public boolean add(E e) {
    boolean isAdded = false;
    if (!super.contains(e)) {
        isAdded = super.add(e);
    }
    return isAdded;
  }
}

public class Main_kattis {

    public String[][] readFile() throws IOException {
       
        Scanner scan = new Scanner(System.in);   
        ArrayList<Integer> dim = new ArrayList<>();
          
        String[] dimensions = scan.nextLine().split(" "); 
        int rows = Integer.valueOf(dimensions[0]);
        int cols = Integer.valueOf(dimensions[1]);

        String[][] field = new String[rows][cols];

        int row = 0;

        // processing loop
        while( scan.hasNextLine() )    
        {
          String[] lineChars = scan.nextLine().split("");
          for(int col = 0; col<cols; col++){
            field[row][col] = lineChars[col];
          }
          row = row+1;
        }

        return field;
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

    static ArrayList<FieldCoord> findSpace(String[][] field, String player){
        //return list of all ostens spaces (coordinates) - ostenList
        ArrayList<FieldCoord> ostenList = new ArrayList();
        //Set<FieldCoord> ostenList = new ArrayList();

        //Queue<FieldCoord> ostenQueue = new LinkedList<>();
        PriorityQueue<FieldCoord> ostenQueue = new NoDuplicates<FieldCoord>();
        //PriorityQueue<Integer> p = new NoDuplicates<Integer>();

        if(player.equals("osten")){
            // add all possible starting positions to the queue and list
            for(int rw=0; rw < field.length; rw++){
                
                // check for empty lot
                if(field[rw][0].equals(".")){
                    FieldCoord startCoord = new FieldCoord();
                    startCoord.row = rw;
                    startCoord.col = 0;
                    startCoord.cameFrom = "L";
                    ostenList.add(startCoord);
                    ostenQueue.add(startCoord);
                    field[rw][0] = "o";
                }
            }
        } else if(player.equals("vasten")) {
            // add all possible starting positions to the queue and list
            for(int rw=0; rw < field.length; rw++){
                
                // check for empty lot
                if(field[rw][field[0].length-1].equals(".")){
                    FieldCoord startCoord = new FieldCoord();
                    startCoord.row = rw;
                    startCoord.col = field[0].length-1;
                    startCoord.cameFrom = "R";
                    ostenList.add(startCoord);
                    ostenQueue.add(startCoord);
                    field[rw][field[0].length-1] = "o";
                }
            }
        }
        
        // iterate over queue of empty lots to ensure finding connecting ones
        while(!ostenQueue.isEmpty()){
            
            // pop from queue
            FieldCoord emptyLot = (FieldCoord) ostenQueue.poll();

            //----------- Check U ----------------------------------
            int lotUpRow = emptyLot.row-1;
            int lotUpCol = emptyLot.col;
            FieldCoord lotUp = new FieldCoord();
            lotUp.row = lotUpRow;
            lotUp.col = lotUpCol;
            lotUp.cameFrom = "D";   // from down
            
            //make sure coords are within field
            if(!emptyLot.cameFrom.equals("U") && lotUpRow < field.length && 0 <= lotUpRow && lotUpCol < field[0].length && 0 <= lotUpCol){
                String lotUpChar = field[lotUpRow][lotUpCol];
                if(lotUpChar.equals(".")){
                    //add to queue and add to list
                    //System.out.println("UP add");
                    ostenQueue.add(lotUp);
                    ostenList.add(lotUp);
                    field[lotUpRow][lotUpCol] = "o";
                }
            }
            
            //----------- Check D ----------------------------------
            int lotDownRow = emptyLot.row+1;
            int lotDownCol = emptyLot.col;
            FieldCoord lotDown = new FieldCoord();
            lotDown.row = lotDownRow;
            lotDown.col = lotDownCol;
            lotDown.cameFrom = "U";   // from up
            
            if(!emptyLot.cameFrom.equals("D") && lotDownRow < field.length && 0 <= lotDownRow && lotDownCol < field[0].length && 0 <= lotDownCol){
                String lotDownChar = field[lotDownRow][lotDownCol];
                if(lotDownChar.equals(".")){
                    //add to queue and add to list
                    //System.out.println("DOWN add");
                    ostenQueue.add(lotDown);
                    ostenList.add(lotDown);
                    field[lotDownRow][lotDownCol] = "o";
                }
            }

            //----------- Check R ----------------------------------
            int lotRightRow = emptyLot.row;
            int lotRightCol = emptyLot.col+1;
            FieldCoord lotRight = new FieldCoord();
            lotRight.row = lotRightRow;
            lotRight.col = lotRightCol;
            lotRight.cameFrom = "L";   // from left
            //System.out.println("RIGHT :"+lotRightRow + " " + lotRightCol);
            if(!emptyLot.cameFrom.equals("R") && lotRightRow < field.length && 0 <= lotRightRow && lotRightCol < field[0].length && 0 <= lotRightCol){
                String lotRightChar = field[lotRightRow][lotRightCol];
                //System.out.println("Rght: "+lotRightChar);
                if(lotRightChar.equals(".")){
                    //add to queue and add to list
                    //System.out.println("RIGHT add");
                    ostenQueue.add(lotRight);
                    ostenList.add(lotRight);
                    field[lotRightRow][lotRightCol] = "o"; 
                }
            }

            //----------- Check L ----------------------------------
            int lotLeftRow = emptyLot.row;
            int lotLeftCol = emptyLot.col-1;
            FieldCoord lotLeft = new FieldCoord();
            lotLeft.row = lotLeftRow;
            lotLeft.col = lotLeftCol;
            lotLeft.cameFrom = "R";   // from right
            
            if(!emptyLot.cameFrom.equals("L") && lotLeftRow < field.length && 0 <= lotLeftRow && lotLeftCol < field[0].length && 0 <= lotLeftCol){
                String lotLeftChar = field[lotLeftRow][lotLeftCol];
                //System.out.println("Lft: "+lotLeftChar);
                if(lotLeftChar.equals(".")){
                    //add to queue and add to list
                    //System.out.println("LEFT add");
                    ostenQueue.add(lotLeft);
                    ostenList.add(lotLeft);
                    field[lotLeftRow][lotLeftCol] = "o";
                }
            }
        }
    
        return ostenList;
    }

    public static void main(String[] args){
        
        Main_kattis theMain = new Main_kattis();

        try{
            // field matrix from input file
            String[][] field = theMain.readFile();

            String[][] fieldOsten = new String[field.length][];
            String[][] fieldVasten = new String[field.length][];
            for(int i = 0; i < field.length; i++){
                fieldOsten[i] = field[i].clone();
                fieldVasten[i] = field[i].clone();
            }
            
            ArrayList<FieldCoord> ostList = findSpace(field, "osten");
            ArrayList<FieldCoord> vastList = findSpace(fieldVasten, "vasten");

            Set<FieldCoord> ostenUniqueList = new HashSet<>(ostList);
            Set<FieldCoord> vastenUniqueList = new HashSet<>(vastList);

            // initialize the three counts
            int ostenCount = ostenUniqueList.size();
            int vastenCount = vastenUniqueList.size();
            int allmanningCount = 0;

            //System.out.println("BEFORE: "+ostenCount + " " + vastenCount + " " + allmanningCount);
            // now check through the list for matches - 
            // if match found reduce the two osten/vasten counts 
            // and increase allmanning
            for(FieldCoord vCoord : vastenUniqueList){
                //System.out.println("VCOORD: "+ vCoord.row + "," + vCoord.col);
                for(FieldCoord oCoord : ostenUniqueList){
                    //System.out.println("  - OCOORD: "+ oCoord.row + "," + oCoord.col);
                    if(vCoord.row == oCoord.row && vCoord.col == oCoord.col){
                        ostenCount = ostenCount-1;
                        vastenCount = vastenCount-1;
                        allmanningCount = allmanningCount+1;
                    }
                }
            }   

            System.out.println(ostenCount + " " + vastenCount + " " + allmanningCount);
        }

        catch(IOException e){
            System.out.println("Error occurred when reading file");
        }
    }


}
