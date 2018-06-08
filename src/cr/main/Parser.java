package cr.main;

import java.util.*;

public class Parser {

    public Parser() {}

    public static Integer ATOI(String sTmp) {
        String tTmp = "0", cTmp = "";
    
        sTmp = sTmp.trim();
        for(int i=0;i < sTmp.length();i++)
        {
        cTmp = sTmp.substring(i,i+1);
        if(cTmp.equals("0") ||
            cTmp.equals("1") ||
            cTmp.equals("2") ||
            cTmp.equals("3") ||
            cTmp.equals("4") ||
            cTmp.equals("5") ||
            cTmp.equals("6") ||
            cTmp.equals("7") ||
            cTmp.equals("8") ||
            cTmp.equals("9")) tTmp += cTmp;
        else if(cTmp.equals("-") && i==0)
            tTmp = "-";
        else
            break;
        }
        return ( Integer.parseInt(tTmp) );
    } 
}
