package test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import main.java.Theater;
import main.java.seats.Seat;

public class tests {
    @Test
    public void edgeSeatObserverCount() {
        Theater theater = new Theater();
        int num = theater.getSeat(0,0).getNumObservers();
        assertEquals(num,3);
    }

    @Test
    public void middleSeatsCount() {
        Theater theater = new Theater();
        int num = theater.getSeat(5,5).getNumObservers();
        assertEquals(num,6);
    }

    @Test
    public void seatTakenTest() {
        Theater theater = new Theater();
        theater.findSeats(4);
        String state = theater.getSeat(0, 0).getState();
    
        assertEquals("taken", state);
    }

}
