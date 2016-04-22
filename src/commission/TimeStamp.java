
package commission;

import java.util.Calendar;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
 
public class TimeStamp
{
  public void TimeStamp()
  {
    //Displays "Time Stamp"     
    System.out.println();   //Space
    
    System.out.println("Time Stamp");    
 
    // Displays "MMM DD, YYYY" formatting
    DateFormat dateInstance = SimpleDateFormat.getDateInstance();
    System.out.println(dateInstance.format(Calendar.getInstance().getTime()));
 
    // Displays "HH:MM:SS AM/PM" formatting
    DateFormat timeInstance = SimpleDateFormat.getTimeInstance();
    System.out.println(timeInstance.format(Calendar.getInstance().getTime()));
    
    System.out.println();   //Space    
  }
}

