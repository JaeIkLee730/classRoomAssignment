package cr.main;

import java.util.ArrayList;
import java.io.*;

public class ReadFile
{	
	public static ArrayList<String[]> readFile( final String fileName ) throws IOException
	{
		ArrayList<String[]> seqTmpList = new ArrayList<String[]>() ;
		BufferedReader br = new BufferedReader( new FileReader(fileName) );
		
		// one line of the data.csv
		String line = null;

		// "readLine()" read data line by line
		while((line = br.readLine()) != null) {
			
			// line: data of class time of one subject of a student
			line = line.replace("[","") ;
			line = line.replace("]","") ;
			line = line.replace("\"","") ;
			line = line.replace(" ","") ;

			String[] split = line.split(",") ;
			seqTmpList.add(split) ;
		}

		// null increases the ArraysList size by 1
		seqTmpList.add(null) ;

		// close the bufferReader
		if(br != null)	try { br.close(); } 
						catch(IOException e){}
		
		return seqTmpList ;
	}

}
