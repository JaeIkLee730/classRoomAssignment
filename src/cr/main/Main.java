package cr.main;

import cr.enrollment.*;
import cr.evolution.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.lang.* ;

// import mg.gui.*;
// import mg.machine.*;

class Main
{
	public static void main(String[] args)
	{
		try{
			/*	command line argument
				args[0]: distanceData.csv
				args[1]: classroomMapping.csv
				args[2]: sequence.csv	
				args[3]: assignment.csv	 	
				args[4]: courseTime.csv	 	
				args[5]: rough_room_capacity.csv	 	
				args[6]: rough_course_capacity.csv	 	
				args[7]: class_room_time.csv	 	*/

			// set distance information
			Distance dist = new Distance() ;
			dist.setDistMap( args[0] );
			dist.setCRoomMap( args[1] ) ;
			Constraints.setRoomCapacity( args[5] ) ;
			Constraints.setCourseCapacity( args[6] ) ;

			Constraints constraints = new Constraints() ;
			constraints.setCourseToTime( args[4] ) ;
			constraints.setClassRoomTime( args[7] ) ;

			RoomAssignment RA = new RoomAssignment() ;
			RA.setRoomAssignment( args[3], args[1] );

			// calculation
			// 
			CSList csl = new CSList() ;
			csl.setSequenceList( args[2], RA ) ;

			// Transition.showMap() ;

			RA.setTotalDistance( Fitness.fitness(RA) );
			// csl.printSequenceList() ;

			// RA.showMap() ;

			// Mutation.mutate(RA, constraints, 5) ;

			RAListList raListList = new RAListList() ;
			RAList raList = new RAList() ;

			// first Generation
			for( int i=0; i<100; i++ ){
				RoomAssignment newRA = RandomAssign.randomAssign(RA, constraints, 0) ;
				newRA.setTotalDistance( Fitness.fitness(newRA)) ;
				raList.addRA(newRA) ;
				System.out.println("total distance: " + newRA.getTotalDistance() );
			}	
			raList.sortList() ;
			raList.showSortedDistance() ;
			raListList.addRAList( raList ) ;

			Evolution evolution = new Evolution() ;
			RAList newRAList = new RAList() ;



			for( int i=1; i<=100; i++ ) {
				System.out.println(i + "'th Generation") ;
				// raListList.getRAList(i-1).showSortedDistance() ;
				newRAList = new RAList(evolution.evolute( raListList.getRAList(i-1), constraints )) ;
				raListList.addRAList( newRAList ) ;
			}
			
			raListList.showAllGenerationsBest() ;

		}catch(Exception e){
			System.out.println("File Name Error");
		}

	}
}
