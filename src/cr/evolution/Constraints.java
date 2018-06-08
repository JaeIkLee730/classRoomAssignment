package cr.evolution ;

import cr.enrollment.*;
import cr.main.*;

import java.util.*;
import java.lang.* ;
import java.io.* ;

public class Constraints { 

    // TreeMap<class room, capacity>
    private static TreeMap<String, Integer> capacity = new TreeMap<String, Integer>() ;

    public Constraints() { }

    public static void setCapacity( final String fileName ) {
        
        ArrayList<String[]> temp = new ArrayList<String[]>() ;
        // Distance dist = new Distance() ;
        // dist.setCRoomMap( distanceFile ) ;

        try{
            temp = ReadFile.readFile( fileName ) ;
            
            // temp.size()-1 : it shown to be +1 than actual number I cannot figure out why
            for( int i=0; i<temp.size()-1; i++ ){
                String [] str = temp.get(i) ;
            
                /*
                    str[0]: class room
                    str[1]: capacity
                */
                capacity.put( str[0], Parser.ATOI(str[1]) ) ;   
            }
            
            for( String classRoom: capacity.keySet() )
                System.out.println(classRoom + ": " + capacity.get(classRoom) );


        } catch(IOException e){}
    }

}

