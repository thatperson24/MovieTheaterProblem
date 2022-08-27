package main.java.seats;

import java.util.ArrayList;

public class Seat {
    //private ArrayList<Seat> closeSeats;
    private ArrayList<Seat> observers;
    private int row;
    private int col;
    private String state;

    /**
     * Seat Constructor.
     * @param row Row number.
     * @param col Column number.
     */
    public Seat(int row, int col) {
        observers = new ArrayList<Seat>();
        this.row = row;
        this.col = col;
        state = "available";
    }

    /**
     * Attaching new observer onto the object and adding the object to the observer's observer list.
     * @param obs New observer.
     */
    public void attachObserver(Seat obs) {
        observers.add(obs);
        obs.getObservers().add(this);
    }

    public ArrayList<Seat> getObservers() {
        return observers;
    }

    public int getNumObservers() {
        return observers.size();
    }

    public String getID() {
        return "" + (char)(row+65) + col; //Converting row number to A-J
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void notifyObs() {
        for(Seat s : observers) {
            s.update();
        }
    }

    public void update() {
        if(state.equals("available")) {
            state = "blocked";
        }
    }
}
