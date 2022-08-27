package main.java;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Scanner;

import main.java.seats.Seat;

public class Main {
    
    public static void main(String[] args) {

        LinkedHashMap<String, String> requests = new LinkedHashMap<String, String>();
        
        try {
            if(args.length == 0) {
                System.out.println("Error");
                System.out.println("Please run the simulation using \"gradle runSim -PfileName='<filename>'\"");
                System.exit(0);
            }
            //Input reading and inserting into hashmap
            File file = new File(args[0]);
            Scanner fr = new Scanner(file);
            
            while(fr.hasNextLine()) {
                String data = fr.nextLine();
                if(!data.equals("")) {
                    requests.put(data.substring(0,4),data.substring(5));
                }
            }
            fr.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error");
            System.out.println("The file provided could not be found");
            System.exit(0);
        }  

        Theater theater = new Theater();

        //Replacing requests' number of seats with the appropriate response
        for(String i : requests.keySet()) {
            requests.put(i, theater.findSeats(Integer.parseInt(requests.get(i))));
        }

        //Writing the results into a file.
        String result = "";

        for(String i : requests.keySet()) {
            System.out.println(i + " " + requests.get(i));
            result += i + " " + requests.get(i) + "\n\n";
        }

        try {
            FileWriter fw = new FileWriter("out.txt");
            PrintWriter pw = new PrintWriter(fw);
            pw.print(result);
            pw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}