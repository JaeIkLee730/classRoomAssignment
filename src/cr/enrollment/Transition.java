package cr.enrollment ;

import java.util.* ;
import java.io.* ;
import cr.main.* ;

public class Transition{

	// HashMap<course X course, number of moving student> : two adjacent course
	// Why doesn't a tree map work while a hash map works?
	private static TreeMap<StringPair, Integer> transition = new TreeMap<StringPair, Integer>() ;

    public Transition() {
	}

    public static void addTransition( String src, String dst) {
		
		// if key already exist, value+=1
		// if not, make new instance
		Integer cnt = transition.get( new StringPair(src,dst) );

		if (cnt == null) 
			cnt = new Integer(1) ;
		else
			cnt = new Integer(cnt.intValue()+1) ;

		// update
		transition.put( new StringPair(src,dst), cnt ) ;
	}

    public static TreeMap<StringPair, Integer> getTransition() {
        return transition ;
	}

	public static Integer getSpecificTransition( String src, String dst ) {
        return transition.get( new StringPair(src,dst) ) ;
	}
	
	public static void showMap() {
		for( StringPair sp: transition.keySet() ) {
			System.out.println( sp.getSrc() + ", " + sp.getDst() + ", " + transition.get(sp) ) ;
		}
	}

}