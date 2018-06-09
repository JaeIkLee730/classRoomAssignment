package cr.evolution ;

import cr.enrollment.*;
import cr.main.*;

import java.util.*;
import java.lang.* ;
import java.io.* ;

public class Evolution {

    public Evolution() {}

    public RAList evolute( RAList raList, Constraints constraints ) {

        ArrayList<RoomAssignment> oldGeneration = raList.getRoomAssignmentList() ;
        ArrayList<RoomAssignment> tempGeneration = new ArrayList<RoomAssignment>() ; 
        ArrayList<RoomAssignment> newGeneration = new ArrayList<RoomAssignment>() ; 

        for( int i=0; i<100; i++ ) {
            tempGeneration.add(oldGeneration.get(i)) ;
        }

        // // create 3n/2 of new Chromosome - top 50% : mutation
        // for( int j=0; j<50; j++ ){
        //     for( int i=0; i<3; i++ ){
        //         RoomAssignment newRA = RandomAssign.randomAssign(oldGeneration.get(i), constraints, 5) ;
        //         newRA.setTotalDistance( Fitness.fitness(newRA)) ;
        //         tempGeneration.add(newRA) ;
        //         // System.out.println("total distance: " + newRA.getTotalDistance() );
        //         // totally 100 Chromosomes + new 300 Chromosomes are in tempGeneration
        //     }	
        // }

        for( int j=0; j<100; j++ ){
            for( int i=0; i<3; i++ ){
                RoomAssignment newRA = RandomAssign.randomAssign(oldGeneration.get(i), constraints, 5) ;
                newRA.setTotalDistance( Fitness.fitness(newRA)) ;
                tempGeneration.add(newRA) ;
                // System.out.println("total distance: " + newRA.getTotalDistance() );
                // totally 100 Chromosomes + new 300 Chromosomes are in tempGeneration
            }	
        }

        Collections.sort(tempGeneration) ;

        // select top 0.75n
        for( int i=0; i<75; i++ ){
            newGeneration.add(tempGeneration.get(i)) ;
        }

        Random generator = new Random(); 
        int randNum = generator.nextInt(250) + 75 ;

        
        // select random 0.25n create new
        for( int i=randNum; i<randNum+25; i++ ){
            newGeneration.add(tempGeneration.get(i)) ;
        }

        RAList newRAList = new RAList(newGeneration) ;
        newRAList.sortList() ;

        return newRAList ;


    }

}