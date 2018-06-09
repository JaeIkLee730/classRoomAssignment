/*
    Gene: <course.division, class room> pair
    Chromosome: group of the all pair
*/


package cr.enrollment ;

import java.util.* ;
import java.io.* ;
import cr.main.* ;


public class RoomAssignment implements Comparable<RoomAssignment> {

    // practical Chromosome
    // HashMap<course.division, class room>
    private HashMap<String, String> assignment = new HashMap<String, String>() ;
        // TreeMap<class room, set of times each class is occupied>
        private TreeMap<String, TreeSet<Integer>> classRoomTime
                = new TreeMap<String, TreeSet<Integer>>() ;
    private Integer totalDistance ;

    public RoomAssignment() { }
   
    public RoomAssignment( RoomAssignment RA ) { 
        this.assignment = RA.getAssignment() ;
        this.totalDistance = RA.getTotalDistance() ;
    }

    public RoomAssignment( HashMap<String,String> assignment ) { 
        this.assignment = assignment ;
    }

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

    public HashMap<String, String> getAssignment() {
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

    public void setClassRoomTime( final String fileName ) throws Exception {
        ArrayList<String[]> temp = new ArrayList<String[]>() ;
        temp = ReadFile.readFile( fileName ) ;
        TreeSet<Integer> timesTemp = new TreeSet<Integer>() ;

        for( int i=0; i<temp.size(); i++ ) {

            if( (temp.get(i))[0].equals("NULL") ) {
                classRoomTime.put( (temp.get(i-1))[0], timesTemp ) ;
                break ;
            }

            String [] split = temp.get(i) ;

             /* split[0]: class room          
                split[1]: class times           */

            // if (first element || current reading class room ends)

            if( (i!=0)&&!(temp.get(i))[0].equals((temp.get(i-1))[0]) ) {
                classRoomTime.put( (temp.get(i-1))[0], timesTemp ) ;
                timesTemp = new TreeSet<Integer>() ;
            }

            for( int j=1; j<split.length; j++ ){
                timesTemp.add( Parser.ATOI(split[j]) ) ;
            }
        }

        // for( String classRoom: classRoomTime.keySet() ){
        //     System.out.println() ;
        //     System.out.print( classRoom + ": ") ;
        //     for( Integer classTime: classRoomTime.get(classRoom) )
        //         System.out.print( classTime + ", " ) ;
        // }

    }

    public void setClassRoomTime( TreeMap<String, TreeSet<Integer>> classRoomTime ){
        this.classRoomTime = classRoomTime ;
    }

    

    public TreeMap<String, TreeSet<Integer>> getClassRoomTime() {
        return classRoomTime ;
    }

    public TreeSet<Integer> getClassRoomTime( String classRoom ) {
        return classRoomTime.get(classRoom) ;
    }

    public void showMap() {
        System.out.println() ;
        System.out.println() ;
        System.out.println() ;
        System.out.println() ;
        for( String course: assignment.keySet() ) {
            System.out.println( course + ": " + assignment.get(course) ) ;
        }
    }

    public int compareTo( RoomAssignment o ) {
        RoomAssignment ra = (RoomAssignment) o;
        return (this.totalDistance < ra.totalDistance ? -1 :(this.totalDistance == ra.totalDistance ? 0 : 1));
    }

}