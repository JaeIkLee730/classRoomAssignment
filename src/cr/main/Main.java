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
				args[5]: capacity.csv	 	*/

			// set distance information
			Distance dist = new Distance() ;
			dist.setDistMap( args[0] );
			dist.setCRoomMap( args[1] ) ;
			Fitness.setCourseToTime( args[4] ) ;
			
			Constraints.setCapacity( args[5] ) ;

			RoomAssignment RA = new RoomAssignment() ;
			RA.setRoomAssignment( args[3], args[1] );

			

			// calculation
			// 
			CSList csl = new CSList() ;
			csl.setSequenceList( args[2], RA ) ;

			// Transition.showMap() ;

			RA.setTotalDistance( Fitness.fitness(RA) );
			// csl.printSequenceList() ;

			System.out.println("total distance: " + RA.getTotalDistance() );


		}catch(Exception e){
			System.out.println("File Name Error");
		}

	}
}
