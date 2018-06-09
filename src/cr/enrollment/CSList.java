package cr.enrollment ;

import java.util.* ;
import java.io.* ;
import cr.main.* ;
import cr.evolution.* ;

public class CSList {

    // list of class sequences
    private ArrayList<CS> sequenceList= new ArrayList<CS>() ;
    
    public CSList() { }

    // file: sequence.csv
    public void setSequenceList( final String fileName, RoomAssignment RA ) throws Exception {    
        BufferedReader br = new BufferedReader( new FileReader(fileName) ) ;

        // ArrayList temporarily contains "course - class time - stu ID" information
        ArrayList<String[]> seqTmpList = new ArrayList<String[]>() ;

        // temporarily contains a class-times-equence of one student
        Integer [] stuSq = new Integer[30] ;
        
        // HashMap<time, class room>
        HashMap<Integer, String> cRoom = new HashMap<Integer, String>() ;
      
        // HashMap<time, course>
        HashMap<Integer, String> timeToCourse = new HashMap<Integer, String>() ;
        TreeSet<String> courseSet = new TreeSet<String>() ;

        int i = 0 ;

        seqTmpList = ReadFile.readFile( fileName ) ;
        
        // seqTmpList.size()-1 : because the last element is null
        for( int j=0; j<seqTmpList.size()-1; j++ ){

            String [] split = seqTmpList.get(j) ;

            /*  Actually individual data is taken here 
                length-1: except sudent ID   
                split[0]: course code           */
            
            // one line: course_code / class_time / ID 
            for( int k=1; k<split.length-1; k++ ) {
                TreeSet<Integer> timeByCourse = new TreeSet<Integer>() ;
                stuSq[i] = Parser.ATOI(split[k]) ;
                cRoom.put( stuSq[i], RA.getAssignedRoom(split[0]) ) ;
                timeToCourse.put( stuSq[i], split[0] ) ;
                courseSet.add( split[0] ) ;
                i++ ;
            }

            /*  if a student's data ends
                student ID of previous data is different from the current one
                Excluding case the previous student == null : first data
                j==-1 : the first data      
                split[split.length-1]: current split's student ID    */
            if( ( (seqTmpList.get(j+1))==null )
                || ( !(seqTmpList.get(j))[split.length-1]
                    .equals((seqTmpList.get(j+1))[seqTmpList.get(j+1).length-1]) ) ) {
                                        
                /*  <Sorting>
                    In CS.java, the constructor is
                    public CS( Integer [] sq ) { 
                        for( int i=0; sq[i]!=null; i++ )
                            sequence[i] = sq[i] ;
                    } 
                    So I make one more space with null  
                    the reason why it is Integer[i+1]   */

                int [] temp = new int[i] ;
                Integer [] sortedStuSq = new Integer[i+1] ;

                // Error: Integer [] temp -> "Arrays.sort(temp)" statement make error
                for( int k=0; k<i; k++ )
                    temp[k] = stuSq[k] ;
                Arrays.sort(temp) ;

                int k ;
                for( k=0; k<i; k++)
                    sortedStuSq[k] = temp[k] ;
                sortedStuSq[i] = null ;

                CS cs = new CS( sortedStuSq, cRoom, timeToCourse, courseSet ) ;
                // save transition info
                cs.setDistance( ) ;
                sequenceList.add(cs) ;

                stuSq = new Integer[30] ;
                cRoom = new HashMap<Integer, String>() ;

                i = 0 ;
            }
        }
        
        // closing Buffered Reader
        if( br != null) {
            try { br.close(); } catch(IOException e){}
        }
    }

    public void printSequenceList() {
        // input check
        for( CS _cs: sequenceList ){            
            for( Integer period : _cs.getSequence() ) {
                if( period==null )
                    break ;
                System.out.print(" " + period + " " );
            }
            System.out.println();
        }
    }
    
    public ArrayList<CS> getSequenceList () { return sequenceList ; }

}