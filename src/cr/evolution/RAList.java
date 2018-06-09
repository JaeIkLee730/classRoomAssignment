package cr.evolution ;

import cr.enrollment.*;
import cr.main.*;

import java.util.*;
import java.lang.* ;
import java.io.* ;

public class RAList {

    private ArrayList<RoomAssignment> list = new ArrayList<RoomAssignment>() ;

    public RAList(){}
    
    public RAList( RAList raList ) {
        this.list = raList.getRoomAssignmentList() ;
    }

    public RAList( ArrayList<RoomAssignment> list ){
        this.list = list ;
    }

    public void addRA( RoomAssignment RA){
        list.add(RA) ;
    }

    public void sortList() {
        Collections.sort(list) ;
    }

    public ArrayList<RoomAssignment> getRoomAssignmentList() {
        return list ;
    }

    public void showSortedDistance(){
        int i=0;
        for( RoomAssignment ra : list ){
            i++ ;
            System.out.println("Romm Assignment " + i + ": " + ra.getTotalDistance() ) ;
        }
    }

    public void showSortedDistanceBest(){

        System.out.println("Best Assignment result" + ": " + list.get(0).getTotalDistance() ) ;

    }

}