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
        System.out.println("readfile initialized..");
    
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
    
        System.out.println("..... OUTPUT .....");
        print2D(boardCreated);
    
        return startPos;
    }   

    // function to create ref board for keeping track of naming convention
    public String[][] createReferenceBoard(int noOfFolds, int lenOfSide){
        System.out.println("creating board..");
        System.out.println("no of folds: "+noOfFolds);
        System.out.println("side length: "+lenOfSide);

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
            System.out.println("FOLD LEVEL i"+i + " len of fold: "+lenSideOfFold);
                

            int factorIncDec = (int)((double)refBoard.length/(double)lenSideOfFold);   
            if(factorIncDec == 1){
                System.out.println("WE have reached the last fold.. DO something..");
            } else{
                System.out.println("This is is still a fold that contains several levels..");
                // handle range of pos that should be given same value
                // create help matrix with dimensions of factorIndDec
                
                //String[][] helpMatrix = new String[factorIncDec][factorIncDec];
                for(int j=refBoard.length-1;j>factorIncDec; j-=factorIncDec){
                    System.out.println("J"+j);
                    for(int k=0;k<refBoard.length-factorIncDec; k+=factorIncDec){

                        // jump with bigger relative steps
                        // however still iterate over all pos within that outer box
                        // thus, adjust number of times we iterate within this
                        System.out.println("JK"+j+","+k);
                        for(int b=0;b<factorIncDec;b++){
                            System.out.println("b"+b);

                            refBoard[j][k+b] += "1.";
                            refBoard[j-b][k] += "1.";
                            refBoard[j-b][k+b] += "1.";

                            refBoard[j][k+factorIncDec+b] += "2.";
                            refBoard[j-b][k+factorIncDec] += "2.";
                            refBoard[j-b][k+factorIncDec+b] += "2.";
                            
                            refBoard[j-factorIncDec][k+b] += "3.";
                            refBoard[j-factorIncDec-b][k] += "3.";
                            refBoard[j-factorIncDec-b][k+b] += "3.";

                            refBoard[j-factorIncDec][k+factorIncDec+b] += "4.";
                            refBoard[j-factorIncDec-b][k+factorIncDec] += "4.";
                            refBoard[j-factorIncDec-b][k+factorIncDec+b] += "4.";
                        }
                        
                    }
                }
            }

            System.out.println("refBoard.length/lenSideOfFold: "+factorIncDec);
            
        }

        return refBoard;
    }

    public String[][] createBoard(int noOfFolds, int lenOfSide){
        System.out.println("creating board..");
        System.out.println("no of folds: "+noOfFolds);
        System.out.println("side length: "+lenOfSide);

        // creating a board with the right dimensions
        String[][] board = new String[lenOfSide][lenOfSide];
        
        // fill board - traverse from bottom up, start in left corner, move in quadrants
        for(int i = lenOfSide-1; i>0;i-=2){
            for(int j=0;j<=lenOfSide-2;j+=2){
                System.out.println("ijx:"+i+","+j);
                board[i][j] = "s1";
                board[i][j+1] = "s2"    ;
                board[i-1][j] = "s3";
                board[i-1][j+1] = "s4";
            }
        }

        return board;
    }

    public static void main(String[] args){
        System.out.println("Kvadranter script initialized..");
        Main theMain = new Main();

        try{
            //System.out.println("filepath: "+args[0]);
            String filePath = args[0];
            //String filePath = "";

            // field matrix from input file
            String cryptoAndClearText = theMain.readFile(filePath);
            
        }   
        catch(IOException e){
            System.out.println("Error occurred when reading file");
        }
    }


}
