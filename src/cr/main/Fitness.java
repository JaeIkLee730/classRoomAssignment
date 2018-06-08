package cr.main;

import cr.enrollment.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.lang.* ;

public class Fitness {

    private static Integer totalDistance ;
    
    // TreeMap<course, set of class time>
    private static TreeMap<String,TreeSet<Integer>> courseToTime = new TreeMap<String,TreeSet<Integer>>() ;
    
    public Fitness() {}

    public static void setCourseToTime( final String fileName ) throws Exception {
        

        ArrayList<String[]> temp = new ArrayList<String[]>() ;


        temp = ReadFile.readFile( fileName ) ;
        
        for( int i=0; i<temp.size()-1; i++ ) {

            String [] split = temp.get(i) ;

             /* length-1: except sudent ID   
                split[0]: course code          
                split[1]: class times           */

            TreeSet<Integer> timesTemp = new TreeSet<Integer>() ;

            for( int j=1; j<split.length; j++ ){
                timesTemp.add( Parser.ATOI(split[j]) ) ;
            }
            courseToTime.put( split[0], timesTemp ) ;
        }

        // for( String course: courseToTime.keySet() ){
        //     System.out.println() ;
        //     System.out.print( course + ": ") ;
        //     for( Integer classTime: courseToTime.get(course) )
        //         System.out.print( classTime + ", " ) ;
        // }

    }

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
