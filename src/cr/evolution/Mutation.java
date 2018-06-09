package cr.evolution ;

import cr.enrollment.*;
import cr.main.*;

import java.util.*;
import java.lang.* ;
import java.io.* ;

public class Mutation { 

    public Mutation() {

    }

    public static RoomAssignment 
    mutate( RoomAssignment originRA, Constraints constraints, final int ratio ){

        HashMap<String, String> assignment = originRA.getAssignment() ;
        List<String> list = new ArrayList();
        List<String> mutateList = new ArrayList<String>() ;
        
        // HashMap<course, class room>
        HashMap<String, String> newAssignment = assignment;
        // HashMap<course, class room>
        HashMap<String, String> randNumSet = new HashMap<String,String>() ;

        TreeMap<String, TreeSet<Integer>> classRoomTime = originRA.getClassRoomTime() ;

        list.addAll(assignment.keySet());
       
        Random generator = new Random(); 
        while( randNumSet.size()<=(list.size()*(ratio/10)) ){
            generator = new Random() ;
            int randNum = generator.nextInt(list.size()) ;
            randNumSet.put(list.get(randNum), assignment.get(list.get(randNum))) ;
        }

        List<String> sortedByCapacity = sortByCapacity(randNumSet);

        for( String course: randNumSet.keySet() ){   
            int courseCapacity = Constraints.getCourseCapacity(course) ;

            // Set<room>
            Set<String> possibleRoom = new TreeSet<String>() ;

            for( String room: Constraints.getRoomCapacity().keySet() ) {
                if( courseCapacity<=Constraints.getRoomCapacity(room) ){
                    possibleRoom.add(room) ;
                }
            }

            for( String room: possibleRoom ){
                TreeSet<Integer> classTime = originRA.getClassRoomTime(room) ;

                TreeSet<Integer> temp = Constraints.getCourseToTime(course) ;
                for( Integer time: classTime ){
                    temp.add(time) ;
                }

                if( temp.size()==classTime.size()+Constraints.getCourseToTime(course).size() ){
                    classRoomTime.put( room, temp ) ;
                    newAssignment.put( course, room ) ;
                }
            }

        }

        RoomAssignment newRA = new RoomAssignment( newAssignment ) ;
        newRA.setClassRoomTime( classRoomTime ) ;
        return newRA ;
    }

    public static List sortByCapacity(final Map assignment) {
        List<String> list = new ArrayList();
        list.addAll(assignment.keySet());
        Collections.sort(list,new Comparator() {
            public int compare(Object o1,Object o2) {
                String a = (String)o1 ;
                String b = (String)o2 ;
                Integer v1 = Constraints.getCourseCapacity(a);
                Integer v2 = Constraints.getCourseCapacity(b);
                return ((Comparable) v2).compareTo(v1);
            }
        });
        Collections.reverse(list); // 주석시 오름차순
        return list;
    }

}