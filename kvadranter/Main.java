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

    public static void print2D(String matrix[][]){
        int counter = 0;

        try{
            for (String[] row : matrix) {
                if(counter==0){}
                else{
                    System.out.print("\n");
                }
                for(int i =0;i<row.length;i++){
                    System.out.print(row[i] + " | ");
                }
                counter++;
            }
        }

        catch(Exception e){
            System.out.println("ERROR w map print");
        }
    }

    public String readFile(String filePath) throws IOException {
        //System.out.println("readfile initialized..");
    
        File file = new File(filePath); 
        Scanner scan = new Scanner(file);   
        //Scanner scan = new Scanner(System.in);   
        
        // retrieve starting pos and matrix info
        String startPos = scan.nextLine();
        int noOfFolds = (startPos.length() + 1)/2;
        int noOfTotPos = (int) Math.pow(4, noOfFolds);
        int lenOfSide = (int)Math.sqrt(noOfTotPos);

        // retrieve movement info
        String[] rightUpMoves = scan.nextLine().split(" ");
        int rightMove = Integer.valueOf(rightUpMoves[0]);
        int upMove = Integer.valueOf(rightUpMoves[1]);

        String[][] boardCreated = createReferenceBoard(noOfFolds, lenOfSide);
        //String[][] boardCreated = createBoard(noOfFolds, lenOfSide);
        
        //System.out.println("..... OUTPUT .....");
        //print2D(boardCreated);
        String positionRetrieved = findPos(boardCreated, startPos, rightMove, upMove);

        return positionRetrieved;
    }   

    // function to create ref board for keeping track of naming convention
    public String[][] createReferenceBoard(int noOfFolds, int lenOfSide){
        //System.out.println("creating board..");
        //System.out.println("no of folds: "+noOfFolds);
        //System.out.println("side length: "+lenOfSide);

        // init a ref board with the right dimensions
        String[][] refBoard = new String[lenOfSide][lenOfSide];
        for(int i=0;i<refBoard.length;i++){
            for(int j=0;j<refBoard.length;j++){
                refBoard[i][j] = "";
            }
        }

        // iterate over the board as many times as the number of folds
        for(int i=1; i<=noOfFolds;i++){
            // at each level we basically just add on to existing string
            int noOfTotPosForFoldI = (int) Math.pow(4, i);
            int lenSideOfFold = (int)Math.sqrt(noOfTotPosForFoldI);
            //System.out.println("FOLD LEVEL i"+i + " len of fold: "+lenSideOfFold);
                

            //int factorIncDec = (int)((double)refBoard.length/(double)lenSideOfFold);   
            int factorIncDec = (int)((double)refBoard.length/(double)lenSideOfFold);   
            
            if(factorIncDec == 1){
                //System.out.println("WE have reached the last fold.. DO something.." + factorIncDec);

                for(int j=refBoard.length-1;j>0; j-=2){
                    
                    for(int k=0;k<refBoard.length-1; k+=2){
                        //System.out.println("J: "+j + ", "+k);
                        // jump with bigger relative steps
                        // however still iterate over all pos within that outer box
                        // thus, adjust number of times we iterate within this
                        //System.out.println("JK"+j+","+k);
                        refBoard[j][k] += "1"; 
                        refBoard[j][k+1] += "2";
                        refBoard[j-1][k] += "3";
                        refBoard[j-1][k+1] += "4";
                    }
                }
            } else{
                //System.out.println("This is is still a fold that contains several levels..");
                // handle range of pos that should be given same value
                // create help matrix with dimensions of factorIndDec
                
                //String[][] helpMatrix = new String[factorIncDec][factorIncDec];
                for(int j=refBoard.length-1;j>factorIncDec; j-=factorIncDec*2){
                    //System.out.println("J"+j);
                    for(int k=0;k<refBoard.length-factorIncDec; k+=factorIncDec*2){

                        // jump with bigger relative steps
                        // however still iterate over all pos within that outer box
                        // thus, adjust number of times we iterate within this
                        // TODO: fix this iteration of ALL psitions that belong to ex. 1,1,1,1

                        //System.out.println("JK"+j+","+k);
                        //System.out.println("factorIncDec: "+factorIncDec);
                        //System.out.println("lenSideOfFold: "+lenSideOfFold);
                        for(int b=0;b<factorIncDec;b++){
                            //System.out.println("b"+b);
                            
                            for(int c=0;c<factorIncDec;c++){
                                //horizontal
                                //System.out.println("Jk 3: "+  (j-factorIncDec-c) + ", "+(k+b));
                                refBoard[j-c][k+b] += "1.";
                                refBoard[j-c][k+factorIncDec+b] += "2.";
                                refBoard[j-factorIncDec-c][k+b] += "3.";
                                refBoard[j-factorIncDec-c][k+factorIncDec+b] += "4.";
                            }
                            
                        }
                        
                    }
                }
            }

            //System.out.println("refBoard.length/lenSideOfFold: "+factorIncDec);
        
        }

        return refBoard;
    }

    public String findPos(String[][] board, String searchedFor, int rightMove, int upMove){

        // dummy values
        int startxCoord = 0;
        int startyCoord = 0;

        boolean startIsFound = false;
        //System.out.println("SEARCH FOR: "+searchedFor);
        // traverse map
        for(int i = 0; i<board.length;i++){
            for(int j = 0; j<board.length;j++){
                if(board[i][j].equals(searchedFor)){
                    startxCoord = i;
                    startyCoord = j;
                    startIsFound = true;
                    //System.out.println("FOUNDi t!!" + i + ", "+j);
                }
            }
        }

        if(startIsFound){
            int newxCoord = startxCoord-upMove;
            int newyCoord = startyCoord+rightMove;

            //System.out.println("NEW COORRD: "+newxCoord + "," +newyCoord);
            if(newxCoord>=0 && newxCoord<board.length && newyCoord>=0 && newyCoord<board.length){
                String newContent = board[newxCoord][newyCoord];

                return newContent;

            } else{
                return "outside";
            }
        }
        else{
            return "outside";
        }

    }

    public static void main(String[] args){
        System.out.println("Kvadranter script initialized..");
        Main theMain = new Main();

        try{
            long startTime = System.currentTimeMillis();
            //System.out.println("filepath: "+args[0]);
            String filePath = args[0];
            //String filePath = "";

            // field matrix from input file
            String finalPosition = theMain.readFile(filePath);
            System.out.println("FINAL POS: "+finalPosition);
            long endTime = System.currentTimeMillis();
            long totalTime = endTime - startTime;
            System.out.println(totalTime);
        }   
        catch(IOException e){
            System.out.println("Error occurred when reading file");
        }
    }


}
