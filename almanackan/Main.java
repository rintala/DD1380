/**
 * Created by jonathanrintala on 2019-05-22.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.*;
import java.io.File; 
import java.io.FileReader; 

import java.util.LinkedList; 
import java.util.Queue; 

class Event{
    String month;
    int day;
    String time;
    String description;
    int monthIdx;
    int timeVal;

    public Event(String month, int day, String time, String description, int monthIdx, int timeVal){
        this.month = month;
        this.day = day;
        this.time = time;
        this.monthIdx = monthIdx;
        this.timeVal = timeVal;
        this.description = description;
    }

    int getMonthIdx(){
        return monthIdx;
    }

    int getDay(){
        return day;
    }

    String getMonth(){
        return month;
    }

    String getTime(){
        return time;
    }

    int getTimeVal(){
        return timeVal;
    }

    String getDescription(){
        return description;
    }

    String getDescription(int idx){
        String letter = "";

        return String.valueOf(description.charAt(idx));
    }
    
}

class EventComparator implements Comparator<Event>{
    ArrayList<Event> eventCal = new ArrayList<Event>();

    public EventComparator(ArrayList<Event> eventCalIn) {
        this.eventCal = eventCalIn;
    }

    public int compare(Event a, Event b) {
        if (a.getMonthIdx() > b.getMonthIdx()) {
            return 1;
        } else if(a.getMonthIdx() == b.getMonthIdx()){

            if(a.getDay() > b.getDay()){
                return 1;
            } else if(a.getDay() == b.getDay()){
                if(a.getTimeVal() > b.getTimeVal()){
                    return 1;
                } else if(a.getTimeVal() == b.getTimeVal()){
                    if(a.getDescription(0).compareTo(b.getDescription(0)) > 0){
                        return 1;
                    } else{
                        return -1;
                    }
                } else{
                    return -1;
                }
            } else{
                return -1;
            }
        } else{
            return -1;
        }
    }
}

public class Main {

    String[] monthsInOrder = {"jan", "feb", "mar", "apr", "maj", "jun", "jul", "aug", "sep", "okt", "nov", "dec"};
    
    public ArrayList<Event> readFile(String filePath) throws IOException {  
    
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        //BufferedReader reader = new BufferedReader(new FileReader(filePath));
        
        // read number of events from input
        int noOfEvents = Integer.parseInt(reader.readLine());
        
        // initialize calender array of custom event objects
        ArrayList<Event> eventCal = new ArrayList<Event>();
        
        // iterate over all events from input and add events
        for(int i = 0; i<noOfEvents; i++){
            String[] eventData = reader.readLine().split("\\s+");
            String month = eventData[0];
            int day = Integer.valueOf(eventData[1]);
            String time = eventData[2];
            String desc = eventData[3];
            
            for(int k = 4; k<eventData.length;k++){
                desc+= " " +eventData[k];
            }

            int timeVal = Integer.valueOf(eventData[2].replace(":", ""));
            int monthIdx = -1;
            for(int m = 0; m<monthsInOrder.length; m++){
                if(monthsInOrder[m].equals(month)){
                    monthIdx = m;
                }
            }

            // add all of the info to the event object
            Event newEvent = new Event(month, day, time, desc, monthIdx, timeVal);
            
            // attach the event object to the calendar array
            eventCal.add(newEvent);
        }
        return eventCal;
    }

    public static void main(String[] args){

        Main theMain = new Main();

        try{
            //String filePath = args[0];
            String filePath = "";

            // field matrix from input file
            ArrayList<Event> cal = theMain.readFile(filePath);

            // create custom comparator for events
            EventComparator eComp = new EventComparator(cal);
            
            // sort calendar of events
            Collections.sort(cal, eComp);

            // print out resulting calendar info
            for (Event event : cal) { 
                System.out.println(event.getMonth() + " " +event.getDay() + " "+ event.getTime() + " " + event.getDescription()); 
            }
        }   

        catch(IOException e){
            System.out.println("Error occurred when reading file");
        }
    }


}
