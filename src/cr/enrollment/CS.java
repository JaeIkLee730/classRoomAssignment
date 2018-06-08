// CS: Class Sequence
// Class Sequence is a sequence of a 


package cr.enrollment ;

import java.util.* ;
import java.io.* ;


public class CS 
{
    // HashMap<period, class room>
    private HashMap<Integer, String> cRoom = new HashMap<Integer, String>() ;
    // HashMap<period, course>
    private HashMap<Integer, String> timeToCourse = new HashMap<Integer, String>() ;
    // TreeSet<course>
    private TreeSet<String> courseSet = new TreeSet<String>() ;

    // sequence of the courses for one student
    private Integer [] sequence = new Integer[30] ;
    
    private final int periodPerDay = 12 ;

    // Error: int [] sequence -> "sq[i]!=null" statement makes error
    public CS( Integer [] sq, HashMap<Integer, String> cRoom,
                                HashMap<Integer, String> timeToCourse, 
                                TreeSet<String> courseSet) { 
        for( int i=0; sq[i]!=null; i++ )
            sequence[i] = sq[i] ;
        this.cRoom = cRoom ;
        this.timeToCourse = timeToCourse ;
        this.courseSet = courseSet ;
    }

    public void setSequence( Integer [] sq ) {
        for( int i=0; sq[i]!=null; i++ )
            sequence[i] = sq[i] ;
    }

    public Integer [] getSequence(){
        return sequence ;
    }
    
    // save transition info
    public void setDistance() {
        for( int i=0; sequence[i+1]!=null; i++){
            if( (sequence[i]==(sequence[i+1]-1))
                &&(sequence[i]%periodPerDay!=0) ) {
                    Transition.addTransition( timeToCourse.get(sequence[i]), 
                                                timeToCourse.get(sequence[i+1]) ) ;
                }
        }
    }
/*
    public boolean isAdjacent( String course_1, String course_2 ) {
        
        Integer gap = 0 ;
        CSList csl = new CSList() ;
        HashMap<String, Integer> courseToTime = new HashMap<String, Integer>()
        courseToTime = csl.getCourseToTime() ;
        gap = courseToTime(course_1) - courseToTime(course_2) ;

        if( ( (courseToTime(course_2)%periodPerDay!=0) && gap==1 ) 
            || (courseToTime(course_1)%periodPerDay!=0) && gap==-1 )
            return true ;
        else
            return false ;
    }
*/

    public HashMap<Integer, String> getCRoom() {
        return cRoom ;
    }

}

