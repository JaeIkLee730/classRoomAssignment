package cr.main ;

import java.util.* ;
import java.io.* ;
import cr.enrollment.* ;

public class StringPair implements Comparable {

	private String src ;
	private String dst ;

	public StringPair( String src, String dst ) {
		if ( dst.compareTo(src)>0 ) {
			this.src = dst ;
			this.dst = src ;
		}
		else {
			this.src = src ;
			this.dst = dst ;
		}
	}

	public StringPair( Set<String> s ) {
		String [] elem = s.toArray(new String[2]) ;
		if ( elem[0].compareTo(elem[1])>0 ) {
			this.src = elem[0] ;
			this.dst = elem[1] ;
		}
		else {
			this.src = elem[1] ;
			this.dst = elem[0] ;
		}
	}

	public int compareTo(Object obj) {

		StringPair p = (StringPair) obj ;

		if ( (this.src).compareTo(p.src)>0 ) 
			return 1 ;
		if ( (this.src).compareTo(p.src)<0 )
			return -1 ;
		// (this.src).compareTo(p.src)==0

		if ( (this.dst).compareTo(p.dst)>0 ) 
			return 1 ;
		if ( (this.dst).compareTo(p.dst)<0 )
			return -1 ;
		// (this.dst).compareTo(p.dst)==0

		return 0 ;
	}

	public String getSrc() { return src ; } 
	public String getDst() { return dst ; } 
}