/**
 * Created by jonathanrintala on 2019-05-21.
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.FileReader; 

public class Main_kattis {

    public String readFile(String filePath) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        //BufferedReader reader = new BufferedReader(new FileReader(filePath));

        // retrieve starting pos and matrix info
        String startPos = reader.readLine();
        int noOfFolds = (startPos.length() + 1)/2;
        int noOfTotPos = (int) Math.pow(4, noOfFolds);
        int lenOfSide = (int) Math.sqrt(noOfTotPos);

        // retrieve movement info
        String[] rightUpMoves = reader.readLine().split(" ");
        int rightMove = Integer.valueOf(rightUpMoves[0]);
        int upMove = Integer.valueOf(rightUpMoves[1]);

        // create the board and retrieve the final position according to movements
        StringBuilder[][] boardCreated = createBoard(noOfFolds, lenOfSide);
    
        //print2D(boardCreated);
        String positionRetrieved = findPos(boardCreated, startPos, rightMove, upMove);

        return positionRetrieved;
    }   

    // function to create ref board for keeping track of naming convention
    public StringBuilder[][] createBoard(int noOfFolds, int lenOfSide){
        
        // init a ref board with the right dimensions
        StringBuilder[][] refBoard = new StringBuilder[lenOfSide][lenOfSide];
        
        for(int i=0;i<lenOfSide;i++){
            for(int j=0;j<lenOfSide;j++){
                StringBuilder sb = new StringBuilder();
                refBoard[i][j] = sb;
            }
        }

        // iterate over the board as many times as the number of folds
        for(int i=1; i<=noOfFolds;i++){
            // at each level we basically just add on to existing string
            int noOfTotPosForFoldI = (int) Math.pow(4, i);
            int lenSideOfFold = (int) Math.sqrt(noOfTotPosForFoldI); 
            int factorIncDec = (int) ((double)refBoard.length/(double)lenSideOfFold);   
            
            // last fold is reached - do something
            if(factorIncDec == 1){
                for(int j=lenOfSide-1;j>0; j-=2){
                    for(int k=0;k<lenOfSide-1; k+=2){
                        refBoard[j][k].append("1"); 
                        refBoard[j][k+1].append("2");
                        refBoard[j-1][k].append("3");
                        refBoard[j-1][k+1].append("4");
                    }
                }

            } else{
                // handle range of pos that should be given same value
                // create help matrix with dimensions of factorIndDec
                for(int j=lenOfSide-1;j>factorIncDec; j-=factorIncDec*2){
                    //System.out.println("....");
                    for(int k=0;k<lenOfSide-factorIncDec; k+=factorIncDec*2){
                        for(int b=0;b<factorIncDec;b++){       
                            //System.out.println("K, B , sum(K,B): "+k+", "+b + ", "+(k+b));                                                                         
                            for(int c=0;c<factorIncDec;c++){
                                refBoard[j-c][k+b].append("1.");
                                refBoard[j-c][k+factorIncDec+b].append("2.");
                                refBoard[j-factorIncDec-c][k+b].append("3.");
                                refBoard[j-factorIncDec-c][k+factorIncDec+b].append("4.");
                            }
                        }
                    }
                }
            }
        }

        return refBoard;
    }

    public int[] findStart(StringBuilder[][] board, String searchedForSeq){
        int[] startCoords = new int[2];
        for(int i = 0; i<board.length; i++){
            for(int j = 0; j<board.length; j++){
                if(board[i][j].toString().equals(searchedForSeq)){
                    startCoords[0] = i;
                    startCoords[1] = j; 
                    return startCoords;
                }
            }
        }

        startCoords[0] = -1;
        return startCoords;
    }

    public String findPos(StringBuilder[][] board, String searchedFor, int rightMove, int upMove){
        // traverse map to find starting pos
        int[] startCoords = findStart(board, searchedFor);

        // assuming startpos exists
        int newxCoord = startCoords[0]-upMove;
        int newyCoord = startCoords[1]+rightMove;

        if(newxCoord>=0 && newxCoord<board.length && newyCoord>=0 && newyCoord<board.length){
            return board[newxCoord][newyCoord].toString();
        } else{
            return "outside";
        }
    }

    public static void main(String[] args){
        Main_kattis theMain = new Main_kattis();

        try{
            //long startTime = System.currentTimeMillis();

            //String filePath = args[0];
            String filePath = "";

            // field matrix from input file
            String finalPosition = theMain.readFile(filePath);
            System.out.println(finalPosition);

            //long endTime = System.currentTimeMillis();
            //long totalTime = endTime - startTime;
            //System.out.println(totalTime);

        }   
        catch(IOException e){
            System.out.println("Error occurred when reading file");
        }
    }


}
