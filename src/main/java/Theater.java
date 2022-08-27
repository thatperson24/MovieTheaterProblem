package main.java;

import main.java.seats.Seat;

public class Theater {
    private Seat[][] seats;
    private int numRows;
    private int numCol;

    /**
     * Constructor for theater.
     */
    public Theater() {
        numRows = 10;
        numCol = 20;
        seats = constructTheater();
    }

    public Seat getSeat(int row, int col) {
        return seats[row][col];
    }

    /**
     * Creates a new 2D array with seat objects that are linked to each other.
     * @return 2D array of seats.
     */
    private Seat[][] constructTheater() {
        //New theater of size 10x20
        Seat[][] tempTheater = new Seat[numRows][numCol];

        //Creating new seats for each [row][col]
        for(int row = 0; row < numRows; row++) {
            for(int col = 0; col < numCol; col++) {
                Seat tempSeat = new Seat(row,col);
                //Attaching left 3 seats to the new seat as observers and adding itself to their observer list.
                for(int i = 1; i <= 3; i++) {
                    if(col - i > -1) {
                        tempSeat.attachObserver(tempTheater[row][col-i]);
                    }
                }
                tempTheater[row][col] = tempSeat;
            }
        }
        return tempTheater;
    }

    /**
     * Find the seat placement for the request.
     * @param numSeats Number of Seats requested.
     * @return String of seat placements.
     */
    public String findSeats(int numSeats) {
        //Default message if request can't be completed.
        String result = "The request was not able to be fulfilled. ";
        
        //Start and end index of newly taken seats.
        int startIndex = 0;
        int endIndex = 0;
        int takenRow = 0;

        //Check for which row has the available space and takes those spots.
        for(int row = 0; row < numRows; row++) {
            if(getRowAvailable(row) >= numSeats) {
                takenRow = row;
                result = "";
                startIndex = numCol - getRowAvailable(row);
                
                endIndex = startIndex + numSeats;

                //taking spots.
                for(int col = startIndex; col < endIndex; col++) {
                    if(seats[row][col].getState().equals("available")) {
                        seats[row][col].setState("taken");
                        result += seats[row][col].getID() + ",";
                    }
                }
                break;
            } 
        }

        //Setting edges around spot to "blocked."
        if(!result.substring(0,3).equals("The")) {
            seats[takenRow][startIndex].notifyObs();
            seats[takenRow][endIndex-1].notifyObs();
            blockNextRow(takenRow);
        }
        return result.substring(0,result.length()-1); //Getting rid of the final comma or space at the end of the message
    }

    /**
     * Gets the number of spots available in the row.
     * @param row Row number.
     * @return Number of rows seats available.
     */
    public int getRowAvailable(int row) {
        int count = 0;
        for(int col = 0; col < numCol; col++) {
            if(seats[row][col].getState().equals("available")) {
                count++;
            }
        }
        return count;
    }

    /**
     * Sets the next row entirely to "blocked."
     * @param row Previous row number.
     */
    public void blockNextRow(int row) {
        for(int col = 0; col < numCol; col++) {
            if(row+1 < numRows) {
                seats[row+1][col].setState("blocked");
            }
        }
    }
}
