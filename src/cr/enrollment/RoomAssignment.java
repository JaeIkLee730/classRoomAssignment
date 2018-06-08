/*
    Gene: <course.division, class room> pair
    Chromosome: group of the all pair
*/


package cr.enrollment ;

import java.util.* ;
import java.io.* ;
import cr.main.* ;


public class RoomAssignment {

    // practical Chromosome
    // HashMap<course.division, class room>
    private HashMap<String, String> assignment = new HashMap<String, String>() ;
    private Integer totalDistance ;

    public RoomAssignment() { }

    public void setRoomAssignment( final String assignmentFile, final String distanceFile ) throws Exception{
        
        ArrayList<String[]> temp = new ArrayList<String[]>() ;
        // Distance dist = new Distance() ;
        // dist.setCRoomMap( distanceFile ) ;

        try{
            temp = ReadFile.readFile( assignmentFile ) ;
            
            for( int i=0; i<temp.size()-1; i++ ){
                String [] str = temp.get(i) ;
                String [] pair = new String[3] ;
                
                for( int j=0; j<str.length; j++ ) {
                    pair[j] = str[j] ;
                }

                /*
                    pair[0]: course.division
                    pair[1]: class room
                */
                assignment.put( pair[0], pair[1] ) ;   
            }

        } catch(IOException e){}

    }

    public HashMap<String, String> getRoomAssignment() {
        return assignment ;
    }

    public String getAssignedRoom( String course ) {
        return assignment.get( course ) ;
    }

    public void setTotalDistance( Integer td ) {
        this.totalDistance = td ;
    }

    public Integer getTotalDistance() {
        return this.totalDistance ;
    }

}