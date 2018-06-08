// class has distance data between all 92 number of class rooms

package cr.enrollment ;

import java.util.* ;
import java.io.* ;
import cr.main.Parser ;

public class Distance {

    // HashMap<class room, mapping number>
    // class room name -> number
    private static HashMap<String, Integer> cRoomMap = new HashMap<String, Integer>() ;
    private static final int numOfClassRoom = 92 ;
    // path: classRoom to classRoom
    // Integer[class room mapping number][class room mapping number]
    private static Integer[][] distance = new Integer[numOfClassRoom][numOfClassRoom] ;
    
    public Distance() { }

    public void setDistMap( final String fileName ) throws Exception {
        
        BufferedReader br = new BufferedReader( new FileReader(fileName) ) ;
        String line = null;
        int row=0 ;

        while( (line = br.readLine())!= null )
        {
            line = line.replace(" ","") ;
            String[] split = line.split(",");

            // Actually individual data is taken here 
            for( int j=0; j<split.length; j++ )    
                distance[row][j] = Parser.ATOI( split[j] ) ;
            row++ ;
        }
        
        // closing Buffered Reader
        if( br != null) {
            try { br.close(); } catch(IOException e){}
        }

        // input check
        // for( int i=0; i<numOfClassRoom; i++){
        //     for( int j=0; j<numOfClassRoom; j++ )
        //         System.out.println( distance[i][j] );
        // }
    }

    public void setCRoomMap( final String fileName ) throws Exception {
        BufferedReader br = new BufferedReader( new FileReader(fileName) ) ;
        String line = null;

        while( (line = br.readLine())!= null )
        {
            line = line.replace(" ","") ;
            String[] split = line.split(",");

            // split[0]: class room name
            // split[1]: class room code in integer 0-92
            // Actually individual data is taken here 
            // Class Room Map: class room -> class room code in integer
            cRoomMap.put( split[0], Parser.ATOI(split[1]) ) ;
        }

        // closing Buffered Reader
        if( br != null) {
            try { br.close(); } catch(IOException e){}
        }
    }

    // src, dest: start class room and target class room in String
    public static Integer getDistance( String src, String dst ) {
        // src, dst : course

        return distance[ cRoomMap.get(src) ][ cRoomMap.get(dst) ] ;
    }

}