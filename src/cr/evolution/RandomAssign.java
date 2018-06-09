package cr.evolution ;

import cr.enrollment.*;
import cr.main.*;

import java.util.*;
import java.lang.* ;
import java.io.* ;

public class RandomAssign { 

    public RandomAssign() { }

    public static RoomAssignment randomAssign( RoomAssignment originRA, 
                                                Constraints constraints, 
                                                final int ratio) {
                                                // ratio = 0~10

        ArrayList<String> course = new ArrayList<String>() ;
        
        // TreeMap<class room, set of times room is occupied>
        TreeMap<String, TreeSet<Integer>> classRoom = new TreeMap<String, TreeSet<Integer>>() ;
        Random generator = new Random(); 
        String randRoom = null;   

        // originRoomTime: set of times the room is already occupied    
        TreeSet<Integer> originRoomTime = new TreeSet<Integer>() ;
        TreeSet<Integer> courseTime = new TreeSet<Integer>() ;

        // HashMap<course, class room>
        HashMap<String, String> newAssignment = new HashMap<String, String>() ;


        for( String course_: originRA.getAssignment().keySet() ){
            course.add(course_);
            // classRoom.add(originRA.getAssignedRoom(course_) )
        }


        // Use the old one as it was as much as ...
        for( int i=0; i<course.size()*((10-ratio)/10); i++) {
            classRoom.put( originRA.getAssignedRoom(course.get(i)), 
                            Constraints.getCourseToTime(course.get(i)) ) ;
            newAssignment.put( course.get(i), originRA.getAssignedRoom(course.get(i)) ) ;
        }

        for( int i=course.size()*((10-ratio)/10); i<course.size(); i++ ){

            // course의 capacity에 해당하는 capacity를 가진 room들의 list를 불러온다
            ArrayList<String> csRoomGrp = Constraints.getClassRoomGroupByCapacity()
                                                    .get(Constraints.getCourseCapacity()
                                                    .get(course.get(i))) ;
            int numOfRoom = csRoomGrp.size() ;
            int randRoomNum = generator.nextInt(numOfRoom) ;
            randRoom = csRoomGrp.get(randRoomNum) ;

            if( !classRoom.containsKey(randRoom) ){
                courseTime = Constraints.getCourseToTime(course.get(i)) ;
                classRoom.put( randRoom, courseTime ) ;
                newAssignment.put( course.get(i), randRoom ) ;
            } else {

                do{
                    // randRoom: the room that is gonna be new class room for course.get(i)
                    randRoom = csRoomGrp.get(randRoomNum) ;
                    originRoomTime = classRoom.get(randRoom) ;
                    courseTime = Constraints.getCourseToTime(course.get(i)) ;

                    for( Integer time: courseTime) {
                        // 배치당하는 강의의 강의시간과 배치하려는 강의실의 강의시간중 겹치는 것이 있는지 확인
                        // set은 중복을 허용하지 않으므로 겹치는 것이 없다면
                        // 강의시간을 모두 추가한 originRoomTime의 set size와 강의실 시간표와 강의의 강의시간을 더한 수가 같을 것이다.
                        originRoomTime.add(time) ;
                    }
                    
                    if(originRoomTime.size()!=((classRoom.get(randRoom)).size())+ courseTime.size())
                        break ;
                    else{
                        generator = new Random() ;
                        randRoomNum = generator.nextInt(numOfRoom) ;
                        System.out.println("3") ;
                    }

                }while(true) ;

                classRoom.put( randRoom, originRoomTime ) ;
                newAssignment.put( course.get(i), randRoom ) ;

            }

        }
        /*  reassign the "class room" on each "course"
            - constraints
                - two class rooms are in the same capacity group
                - 
        */

        RoomAssignment newRA = new RoomAssignment( newAssignment ) ;
        newRA.setClassRoomTime( classRoom ) ;
        return newRA ;
    }

}

