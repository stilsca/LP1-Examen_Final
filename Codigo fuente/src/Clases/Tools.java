package Clases;
import java.util.Calendar;

public class Tools {
    public static String toEUDate(Calendar a){
        String dia=a.get(Calendar.DAY_OF_MONTH)+"";
        dia=dia.length()==1?"0"+dia:dia;
        String mes=(a.get(Calendar.MONTH)+1)+"";
        mes=mes.length()==1?"0"+mes:mes;
        String anho=a.get(Calendar.YEAR)+"";
        String fe=anho+"-"+mes+"-"+dia;
        return fe;
    }
    
    
}
