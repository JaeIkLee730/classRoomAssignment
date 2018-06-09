package cr.main;

import cr.enrollment.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.lang.* ;

public class Fitness {

    private static Integer totalDistance ;
    
    public Fitness() {}

    public static Integer fitness( RoomAssignment RA ) {

        totalDistance = new Integer(0) ;

        for( StringPair sp: Transition.getTransition().keySet() ) {
            totalDistance+=( Distance.getDistance( 
                                RA.getAssignedRoom(sp.getSrc()),
                                RA.getAssignedRoom(sp.getDst()) )
                                    * Transition.getSpecificTransition(sp.getSrc(),sp.getDst()) ) ;
        }
        
        return totalDistance ;
    }

}
