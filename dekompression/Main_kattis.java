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


class QueueElement {
    private final int elementPosition;
    private final String elementContent;

    QueueElement(int elementPosition, String elementContent){
        this.elementPosition = elementPosition;
        this.elementContent = elementContent;
    }

    int getElementPosition(){
        return this.elementPosition;
    }

    String getElementContent(){
        return this.elementContent;
    }

}

public class Main_kattis {

    // initialize queue variables
    boolean prevWasNumber = false;
    boolean prevWasLetter = false;
    boolean prevWasParanthesis = false;
    boolean prevWasStart = true;
    boolean prevWasEndParanthesis = false;

    Map<Integer, Integer> pairedParanthesis;

    // for storing input after adjusting number of starting/ending paranthesis
    String[] inputDataNew;
    
    // help function for checking if character is numeric
    // source: https://stackoverflow.com/questions/1102891/how-to-check-if-a-string-is-numeric-in-java
    public static boolean isNumeric(String str){
        for (char c : str.toCharArray())
        {
            if (!Character.isDigit(c)) return false;
        }
        return true;
    }

    // help function for repeating strings
    public static String repeat(String str, int times){
        String outputStr = "";
        for(int i = 0;i < times;i++){
            outputStr += str;
        }
        return outputStr;
    }

    public String[] readFile() throws IOException {

        Scanner scan = new Scanner(System.in);   
        
        String[] inputData = scan.nextLine().trim().split(""); 
        
        // pair up start to end paranthesis - store globally for access in each loop
        pairedParanthesis = pairUpParanthesis(inputData);
        Queue<QueueElement> inputDataQueue = new LinkedList<QueueElement>();
        
        for(int i=0; i<inputDataNew.length;i++){
            QueueElement qEl = new QueueElement(i, inputDataNew[i]);
            inputDataQueue.add(qEl);
        }

        String output = expandLevel(inputDataQueue);

        System.out.println(output);
        
        return inputDataNew;
    }

    public Map<Integer, Integer> pairUpParanthesis(String[] inputData){
        // find the corresponding end paranthesis to each starting one
        ArrayList<Integer> startPlacements = new ArrayList<Integer>();
        ArrayList<Integer> startPlacementsCopy = new ArrayList<Integer>();
        ArrayList<Integer> endPlacements = new ArrayList<Integer>();
        Map<Integer, Integer> startEnd = new HashMap<Integer, Integer>();
        
        for(int i = 0; i<inputData.length; i++){
            if(inputData[i].equals("(")){
                startPlacements.add(i);
                startPlacementsCopy.add(i);
            } else if(inputData[i].equals(")")){
                endPlacements.add(i);
            }
        }
        
        int shortestLenToIterateOver;
        int noParanthesisToAdd = Math.abs(startPlacements.size() - endPlacements.size());
        inputDataNew = Arrays.copyOf(inputData, inputData.length + noParanthesisToAdd);
        for(int i = 0; i<noParanthesisToAdd;i++){
            inputDataNew[inputData.length+i] = ")";
        }

        if(startPlacements.size() <= endPlacements.size()){
            shortestLenToIterateOver = startPlacements.size();
        } else{
            for(int i = inputData.length; i<inputData.length+noParanthesisToAdd;i++){
                //System.out.println("add end..");
                endPlacements.add(i);
            }
            shortestLenToIterateOver = endPlacements.size();
        }

        // check all combinations for the shortest distances between start/end
        // repeat until no start paranthesis left
        for(int i = 0; i < startPlacementsCopy.size(); i++){
            int shortestDistance = 1000;
            int shortestStartPos = 0;
            int shortestEndPos = 1000;

            for(int startP : startPlacements){
                //System.out.println("startPlacement: " + startP);
                for(int endP : endPlacements){
                    // look at distance between the combination
                    int dist = endP - startP;
                    // if this distance is shorter than prev shortest - record it
                    if(dist<shortestDistance && 0 < dist){
                        shortestStartPos = startP;
                        shortestEndPos = endP;
                        shortestDistance = dist;
                    }
                }
            }

            // after each loop through - add the found combination to startEnd
            startEnd.put(shortestStartPos, shortestEndPos);

            // and remove these entries from startPlacements & endPlacements
            startPlacements.remove(new Integer(shortestStartPos));
            endPlacements.remove(new Integer(shortestEndPos));
        }

        return startEnd;
    }

    public String expandLevel(Queue<QueueElement> inputDataQueue){
        String prevNumbers = "";
        String letterSequence = "";
        
        // handle rest of elements in queue structure
        while(!inputDataQueue.isEmpty()){
            QueueElement nextQueueElement = inputDataQueue.poll();
            String nextElement = nextQueueElement.getElementContent();
            int nextElementPosition = nextQueueElement.getElementPosition();

            if(isNumeric(nextElement)){
                if(prevWasNumber){
                    prevNumbers += nextElement;
                } else if(prevWasLetter){
                    prevNumbers = nextElement;
                } else if(prevWasParanthesis){
                    prevNumbers = nextElement;
                } else if(prevWasStart){
                    prevNumbers = nextElement;
                }  else if(prevWasEndParanthesis){
                    prevNumbers=nextElement;
                }

                prevWasNumber = true;
                prevWasLetter = false;
                prevWasParanthesis = false;
                prevWasStart = false;
                prevWasEndParanthesis = false;
                
            } else if(!nextElement.equals("(") && !nextElement.equals(")")){
                // is letter

                // if prev element was int => produce letter sequence with int*letter
                if(prevWasNumber){
                    letterSequence += repeat(nextElement, Integer.valueOf(prevNumbers));
                } else if(prevWasLetter){
                    letterSequence += nextElement;
                } else if(prevWasParanthesis){
                    letterSequence += nextElement;
                } else if(prevWasStart){
                    letterSequence += nextElement;
                } else if(prevWasEndParanthesis){
                    letterSequence+=nextElement;
                }
                
                // and add to datastructure
                prevWasNumber = false;
                prevWasLetter = true;
                prevWasParanthesis = false;
                prevWasStart = false;
                prevWasEndParanthesis = false;

            } else if(nextElement.equals("(")){
                // limit number of elements that are send with down in recursive call!
                // only send those who belong to the specific paranthesis
                // this is done by using paranthesis pairs identified in pairParanthesis()

                // is paranthesis -  check which one - adjust level variable accordingly
                // recursive call to same func
                if(prevWasParanthesis | prevWasLetter | prevWasEndParanthesis){
                    prevNumbers = "1";
                }

                prevWasNumber = false;
                prevWasLetter = false;
                prevWasParanthesis = true;
                prevWasStart = false;
                prevWasEndParanthesis = false;
               
                Queue<QueueElement> inputDataQueueInParanthesis = new LinkedList<QueueElement>();
                Queue<QueueElement> inputDataQueueRemaining = new LinkedList<QueueElement>();

                // check here the position of the end paranthesis that is related and restrict accord
                boolean keyIsFound = false;
                for (Map.Entry<Integer, Integer> entry : pairedParanthesis.entrySet()) {
                    // end position
                    int endPosOfParanthesis;
                    int startPosOfParanthesis = nextElementPosition;;

                    if(nextElementPosition == entry.getKey()){
                        keyIsFound = true;
                        
                        // end position - if exists
                        endPosOfParanthesis = entry.getValue();
                        int numberOfElementsInParanthesis = endPosOfParanthesis - startPosOfParanthesis;
                        
                        // pop as many elements in to the new data queue as required
                        for(int i = 0; i<numberOfElementsInParanthesis;i++){
                            inputDataQueueInParanthesis.add(inputDataQueue.poll());
                        }

                        // pop remaining elements into the remaining data queue
                        while(!inputDataQueue.isEmpty()){
                            inputDataQueueRemaining.add(inputDataQueue.poll());
                        }
                    }
                }

                if(keyIsFound == false){
                    // number of starting paranthesis and ending ones are not matching
                    // this paranthesis lack a match => simply just let everything after it be contained in it
                    while(!inputDataQueue.isEmpty()){
                        inputDataQueueInParanthesis.add(inputDataQueue.poll());
                    }
                }  

                return letterSequence + repeat(expandLevel(inputDataQueueInParanthesis), Integer.valueOf(prevNumbers)) + expandLevel(inputDataQueueRemaining);
            
            } else if(nextElement.equals(")")){
                // is paranthesis -  check which one - adjust level variable accordingly
                // recursive call to same func
                prevWasNumber = false;
                prevWasLetter = false;
                prevWasEndParanthesis = true;
                prevWasParanthesis = false;
                prevWasStart = false;
                
                return letterSequence;
            }
        }

        return letterSequence;
    }

    public static void main(String[] args){
        Main_kattis theMain = new Main_kattis();

        try{
            String[] inputData = theMain.readFile();

        }
        catch(IOException e){
            System.out.println("Error occurred when reading file");
        }
    }


}