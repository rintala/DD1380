/**
 * Created by jonathanrintala on 2019-05-21.
 */

import java.io.IOException;
import java.util.Scanner;
import java.io.File; 

public class Main_kattis_new_algo {

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
        File file = new File(filePath);
        Scanner scan = new Scanner(file);   
        //Scanner scan = new Scanner(System.in);   
        
        // retrieve starting pos and matrix info
        String startPos = scan.nextLine();
        int noOfFolds = (startPos.length() + 1)/2;
        int noOfTotPos = (int) Math.pow(4, noOfFolds);
        int lenOfSide = (int) Math.sqrt(noOfTotPos);

        // retrieve movement info
        String[] rightUpMoves = scan.nextLine().split(" ");
        int rightMove = Integer.valueOf(rightUpMoves[0]);
        int upMove = Integer.valueOf(rightUpMoves[1]);

        // create lists for base and alternating - to use in creating board
        int[][] factorLists = constructFactorLists(lenOfSide);

        for(int i=0; i<2; i++){
            System.out.println("next list: ");
            for(int j=0; j<lenOfSide; j++){
                System.out.println(factorLists[i][j]);
            }
        }
        // create the board and retrieve the final position according to movements
        String[][] boardCreated = createBoard(noOfFolds, lenOfSide);
        print2D(boardCreated);
        String positionRetrieved = findPos(boardCreated, startPos, rightMove, upMove);

        return positionRetrieved;
    }   

    public int[][] constructFactorLists(int lenOfSide){
        // create one list with even jumps (baseList) and one with odd/even (alternatingList))
        // - alternate between these as req

        int[][] factorLists = new int[2][lenOfSide];

        factorLists[0][0] = 0;
        factorLists[1][0] = 0;

        for(int i = 1; i<lenOfSide; i++){
            int toAdd;

            if(i%2 == 0){
                toAdd = 3;
            } else{
                toAdd = 1;
            }
            factorLists[0][i] = factorLists[0][i-1]+toAdd;
            factorLists[1][i] = i;
        }
        return factorLists;
    }
    // function to create ref board for keeping track of naming convention
    public String[][] createBoard(int noOfFolds, int lenOfSide){
        
        // init a ref board with the right dimensions
        String[][] refBoard = new String[lenOfSide][lenOfSide];
    
        for(int i=0;i<lenOfSide;i++){
            for(int j=0;j<lenOfSide;j++){
                refBoard[i][j] = "";
            }
        }

        // iterate over the board as many times as the number of folds
        for(int i=1; i<=noOfFolds;i++){
            System.out.println("NO OF FOLDS: "+i);
            // at each level we basically just add on to existing string
            int noOfTotPosForFoldI = (int) Math.pow(4, i);
            int lenSideOfFold = (int) Math.sqrt(noOfTotPosForFoldI); 
            int factorIncDec = (int) ((double)refBoard.length/(double)lenSideOfFold);   
            
            // last fold is reached - do something
            if(factorIncDec == 1){
                for(int j=lenOfSide-1;j>0; j-=2){
                    for(int k=0;k<lenOfSide-1; k+=2){
                        refBoard[j][k] += "1"; 
                        refBoard[j][k+1] += "2";
                        refBoard[j-1][k] += "3";
                        refBoard[j-1][k+1] += "4";
                    }
                }

            } else{
                // handle range of pos that should be given same value
                // create help matrix with dimensions of factorIndDec
                for(int j=lenOfSide-1;j>factorIncDec; j-=factorIncDec*2){
                    System.out.println("....");
                    for(int f=0; f<lenSideOfFold; f++){ 
                        int factorToAdd = 1;
                        //System.out.println("K, B , sum(K,B): "+k+", "+b + ", "+(k+b));                                                                         
                        for(int c=0;c<factorIncDec;c++){
                            refBoard[j-c][factorToAdd] += "1.";
                            refBoard[j-c][factorIncDec+factorToAdd] += "2.";
                            refBoard[j-factorIncDec-c][factorToAdd] += "3.";
                            refBoard[j-factorIncDec-c][factorIncDec+factorToAdd] += "4.";
                        }
                    }
                }
            }
    
        }

        return refBoard;
    }

    public int[] findStart(String[][] board, String searchedForSeq){
        int[] startCoords = new int[2];
        for(int i = 0; i<board.length; i++){
            for(int j = 0; j<board.length; j++){
                if(board[i][j].equals(searchedForSeq)){
                    startCoords[0] = i;
                    startCoords[1] = j;
                    return startCoords;
                }
            }
        }

        startCoords[0] = -1;
        return startCoords;
    }

    public String findPos(String[][] board, String searchedFor, int rightMove, int upMove){
        // traverse map to find starting pos
        int[] startCoords = findStart(board, searchedFor);

        int newxCoord = startCoords[0]-upMove;
        int newyCoord = startCoords[1]+rightMove;

        if(newxCoord>=0 && newxCoord<board.length && newyCoord>=0 && newyCoord<board.length){
            return board[newxCoord][newyCoord];
        } else{
            return "outside";
        }
    }

    public static void main(String[] args){
        Main_kattis_new_algo theMain = new Main_kattis_new_algo();

        try{
            long startTime = System.currentTimeMillis();

            String filePath = args[0];
            //String filePath = "";

            // field matrix from input file
            String finalPosition = theMain.readFile(filePath);
            System.out.println(finalPosition);

            long endTime = System.currentTimeMillis();
            long totalTime = endTime - startTime;
            System.out.println(totalTime);

        }   
        catch(IOException e){
            System.out.println("Error occurred when reading file");
        }
    }


}
