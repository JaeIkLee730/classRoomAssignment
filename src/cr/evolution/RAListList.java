package cr.evolution ;

import cr.enrollment.*;
import cr.main.*;

import java.util.*;
import java.lang.* ;
import java.io.* ;

public class RAListList {

    private ArrayList<RAList> list = new ArrayList<RAList>() ;

    public RAListList() {

    }

    public void addRAList( RAList list ) {
        this.list.add(list) ;
    }

    public ArrayList<RAList> getRAListList(){
        return list ;
    }

    public RAList getRAList( int index ){
        return list.get(index) ;
    }

    public void showAllGenerations(){
        for( RAList subList: list ){
            subList.showSortedDistance() ;
        }
    }

    public void showAllGenerationsBest(){
        for( RAList subList: list ){
            subList.showSortedDistanceBest() ;
        }
    }



}