package cr.evolution ;

import cr.enrollment.*;
import cr.main.*;

import java.util.*;
import java.lang.* ;
import java.io.* ;

public class Constraints { 

    // TreeMap<class room, capacity>
    private static TreeMap<String, Integer> roomCapacity = new TreeMap<String, Integer>() ;
    // TreeMap<capacity, class room group by capacity>
    private static TreeMap<Integer, ArrayList<String>> classRoomGroupByCapacity = new TreeMap<Integer, ArrayList<String>>() ;
    // TreeMap<course, capacity>
    private static TreeMap<String, Integer> courseCapacity = new TreeMap<String, Integer>() ;
    // TreeMap<capacity, course group by capacity>
    private static TreeMap<Integer, ArrayList<String>> courseGroupByCapacity = new TreeMap<Integer, ArrayList<String>>() ;
    // TreeMap<class room, set of times each class is occupied>
    private TreeMap<String, TreeSet<Integer>> classRoomTime = new TreeMap<String, TreeSet<Integer>>() ;
    // TreeMap<course, set of class time>
    private static TreeMap<String,TreeSet<Integer>> courseToTime = new TreeMap<String,TreeSet<Integer>>() ;

    public Constraints() { }

    public static void setRoomCapacity( final String fileName ) {
        
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
                roomCapacity.put( str[0], Parser.ATOI(str[1]) ) ;   
            }


            for( String classRoom: roomCapacity.keySet() ){
                if( !classRoomGroupByCapacity.containsKey(roomCapacity.get(classRoom)) ) {
                    // if it is first time for that capacity
                    ArrayList<String> classRoomGroup = new ArrayList<String>() ;
                    classRoomGroup.add(classRoom) ;
                    classRoomGroupByCapacity.put( roomCapacity.get(classRoom), classRoomGroup ) ;
                } else {
                    (classRoomGroupByCapacity.get(roomCapacity.get(classRoom))).add(classRoom) ;
                }
            }

            // for( Integer roomCapacity: classRoomGroupByCapacity.keySet() ){
            //     System.out.println() ;
            //     System.out.print(roomCapacity + ": ") ;
            //     for( String classRoom: classRoomGroupByCapacity.get(roomCapacity) ){
            //         System.out.print(classRoom+ ", ") ;
            //     }
            // }

        } catch(IOException e){}
    }

    public static void setCourseCapacity( final String fileName ) {
        
        ArrayList<String[]> temp = new ArrayList<String[]>() ;
        // Distance dist = new Distance() ;
        // dist.setCRoomMap( distanceFile ) ;

        try{
            temp = ReadFile.readFile( fileName ) ;
            
            // temp.size()-1 : it shown to be +1 than actual number I cannot figure out why
            for( int i=0; i<temp.size()-1; i++ ){
                String [] str = temp.get(i) ;
            
                /*
                    str[0]: course
                    str[1]: capacity
                */
                courseCapacity.put( str[0], Parser.ATOI(str[1]) ) ;   
            }


            for( String course: courseCapacity.keySet() ){
                if( !courseGroupByCapacity.containsKey(courseCapacity.get(course)) ) {
                    // if it is first time for that capacity
                    ArrayList<String> courseGroup = new ArrayList<String>() ;
                    courseGroup.add(course) ;
                    courseGroupByCapacity.put( courseCapacity.get(course), courseGroup ) ;
                } else {
                    (courseGroupByCapacity.get(courseCapacity.get(course))).add(course) ;
                }
            }

            // for( Integer courseCapacity: courseGroupByCapacity.keySet() ){
            //     System.out.println() ;
            //     System.out.print(courseCapacity + ": ") ;
            //     for( String course: courseGroupByCapacity.get(courseCapacity) ){
            //         System.out.print(course + ", ") ;
            //     }
            // }

        } catch(IOException e){}
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
   
    public static void setCourseToTime( final String fileName ) throws Exception {
        
        ArrayList<String[]> temp = new ArrayList<String[]>() ;
        temp = ReadFile.readFile( fileName ) ;
        
        for( int i=0; i<temp.size()-1; i++ ) {

            String [] split = temp.get(i) ;

             /* split[0]: course code          
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



    public static TreeMap<String, Integer> getRoomCapacity() {
        return roomCapacity ;
    }

    public static Integer getRoomCapacity( String room ) {
        return roomCapacity.get(room) ;
    }

    public static TreeMap<Integer, ArrayList<String>> getClassRoomGroupByCapacity() {
        return classRoomGroupByCapacity ;
    }

    public static TreeMap<String, Integer> getCourseCapacity() {
        return courseCapacity ;
    }

    public static Integer getCourseCapacity( String course ) {
        return courseCapacity.get(course) ;
    }

    public static TreeMap<Integer, ArrayList<String>> getCourseGroupByCapacity() {
        return courseGroupByCapacity ;
    }

    public TreeMap<String, TreeSet<Integer>> getClassRoomTime() {
        return classRoomTime ;
    }

    public TreeSet<Integer> getClassRoomTime( String classRoom ) {
        return classRoomTime.get(classRoom) ;
    }

    public static TreeMap<String, TreeSet<Integer>> getCourseToTime() {
        return courseToTime ;
    }

    public static TreeSet<Integer> getCourseToTime( String course ) {
        return courseToTime.get(course) ;
    }

}        

