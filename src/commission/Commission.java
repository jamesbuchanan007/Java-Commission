package commission;

/******************************************************************************
 * Program Name: Commission.java
 * Program Description: Annual Commission
 * Author: James K. Buchanan
 * Date: 2/8/2015
 * Modification History:
 * Version      Date:                Author:         Description:
 * 1.0          2/8/15                 JKB          Initial Version
 * 
 * 1.1          2/15/15                JKB          Added Decimal Formatting and
 *                                                  Loop for commission increase
 * 
 * 1.2          2/22/15                JKB          Compares sales of two sales
 *                                                  persons
 * 
 * 1.3          3/4/15                 JKB          Added Time Stamp
 ******************************************************************************/

import java.util.Scanner; // Needed for the Scanner class
import java.text.DecimalFormat; //Formats Decimal Values

public class Commission 
{
    public static void main(String[] args) 
    {
        final int NUM_EMPLOYEES = 2;                    //Array Index for # of Salespersons
        String[] fName = new String[NUM_EMPLOYEES];     //Holds Salesperson's First Name
        double[] hours = new double[NUM_EMPLOYEES];     //Average Hours worked in a week
        double[] annSalary = new double[NUM_EMPLOYEES]; //Projected Annual Salary
        double[] curSales = new double[NUM_EMPLOYEES];  //Current Sales
        double[] proSales = new double[NUM_EMPLOYEES];  //50% of Current Sales over Current Sales
        double[] commYear = new double[NUM_EMPLOYEES];  //Projected Yearly Commission
        double[] salPlusComm = new double[NUM_EMPLOYEES]; //Projected Yearly Salary plus Commission
        double[] salTarget = new double[NUM_EMPLOYEES]; //Annual Sales Target
        double payRate = 25;        //Salesperson's Hourly Pay Rate
        double weeksYear = 52;      //# of Weeks in a Year
        double commRate = 3;        //Commission Percentage Rate
        double highest = 0;         // Highest Sales
        double lowest = 0;          // Lowest Sales
        double salesDiff;           // Sales Difference between Salespersons
        String highFirstN = null;          // First name of highest Salesperson
        String lowFirstN = null;           // First name of lowest Salesperson
        NiceDay niceDay = new NiceDay(); //creates a NiceDay object and 
                                         //calls from NiceDay subclass
        TimeStamp timeStamp = new TimeStamp();  //creates TimeStamp object and
                                                //calls from TimeStamp subclass
        
        
        //Formats the Monetary Output
        DecimalFormat money = new DecimalFormat ("$##,###,##0.00");
        
        //Create a Scanner object to read input.
        Scanner keyboard = new Scanner(System.in);
        
       
        // Creates an array for Salesperson
        for (int index = 0; index < NUM_EMPLOYEES; index++)
        {
            System.out.println("Salesperson #" + (index+1) + ":");
        
            //Obtain the salesperson's fist and last name.
            System.out.print("What is your first name? ");
            fName[index] = keyboard.next();
        
            //Obtain the average number of work hours per week.
            System.out.print("What are the average number of hours that you work"
                + " in a week? ");
            hours[index] = keyboard.nextDouble();
            
            //Obtain Target Sales
            System.out.print("What is your Target Sales? ");
            salTarget[index] = keyboard.nextDouble();
        
            //Obtain Current Sales
            System.out.print("What is your current sales? ");
            curSales[index] = keyboard.nextDouble();
        
            //Calculates the Yearly Commission with Incentive
            if (curSales[index] >= salTarget[index]) 
            {
                commYear[index] = 1.25 * curSales[index] * (commRate / 100); //Sales equal or over Target Sales
                System.out.println();
                System.out.println("Awesome job!!"
                + " You are " + (money.format((curSales[index]-salTarget[index])) + 
                        " over Target!!"));
            
                }else if (curSales[index] >= (salTarget[index] * 0.8)) { //Sales between 80% and Target Sales
                    commYear[index] = curSales[index] * (commRate / 100);
                    System.out.println();
                    System.out.println("Great job!!  Almost there!!" + 
                            " You are " + (money.format(Math.abs(curSales[index]-salTarget[index]))) + 
                        " from the Target!!");
                
                    }else{   //Less than 80% of Target Sales
                        commYear[index] = 0;   
                        System.out.println();
                        System.out.println("You have some work to do..." + 
                               "You are " + (money.format(Math.abs(curSales[index]-salTarget[index])) + 
                        " from the Target!!"));
                    }
        
            //Calculates the Projected Annual Salary.
            annSalary[index] = hours[index] * payRate * weeksYear;
        
            //Calculates the Projected Salary plus Commission.
            salPlusComm[index] = annSalary[index] + commYear[index];
        
            //Displays the resulting information.
            System.out.println(); //Space
            System.out.println("Hello, " + fName[index] + ".");
            System.out.println("Your projected gross income "
                + "for the year is " + (money.format(salPlusComm[index])) + ".");
            System.out.println();
        
            //Generates Simple Sales & Compensation Table
            System.out.println("Total Sales\tTotal Compensation");
        
            //Increments Sales by $5000 up to 50% of Total Sales
            proSales[index] = curSales[index] + (curSales[index] * 0.5);
        
            //Uses While Loop to not exceed 50% of Total Sales
             while (curSales[index] <= proSales[index])
            {
                //Determines whether to add the incentive/commission or not
                if (curSales[index] >= salTarget[index]) {
                commYear[index] = 1.25 * curSales[index] * (commRate / 100);
                        
                }else if (curSales[index] >= (salTarget[index] * 0.8)) {
                    commYear[index] = curSales[index] * (commRate / 100);
                                
                    }else{
                        commYear[index] = 0;  
                  
                    }
             
             //Displays New Possible Salary plus Commission
             salPlusComm[index] = annSalary[index] + commYear[index];
             System.out.println((money.format(curSales[index])) + "\t" +
                     (money.format(salPlusComm[index])));
             curSales[index] = (curSales[index] + 5000); // Counter for While Loop
         }
             
         System.out.println();  //Space between Salesperson Inputs
        
        }
        // Compares Salespersons' Annual Compensation
        for (int index = 0; index < NUM_EMPLOYEES; index++)
        {
             if (lowest == 0 && highest == 0 && highFirstN == null && lowFirstN == null)
            {
                lowest = salPlusComm[index];
                highest = salPlusComm[index];
                highFirstN = fName[index];
                lowFirstN = fName[index];
            }
            
            if (salPlusComm[index] > highest) 
            {
                highest = salPlusComm[index];   //Assigns highest number
                highFirstN = fName[index];
            }
                   
            if (salPlusComm[index] < lowest)
            {
                lowest = salPlusComm[index];       //Assigns lowest number
                lowFirstN = fName[index];
            }
                                       
        }
        //Gets the compensation difference
        salesDiff = highest - lowest;
        
        //Displays highest and lowest sales
        System.out.println(highFirstN + " has the Highest Sales: " + (money.format(highest)) + 
                " ");
        System.out.println(lowFirstN + " has the Lowest Sales: " + (money.format(lowest)) + 
                " ");
        System.out.print("Difference: " + (money.format(salesDiff)));
        
        System.out.println();       //Space
        
        timeStamp.TimeStamp();      //Displays Time Stamp
        
        niceDay.displayMessage();   //Displays niceDay message
               
    }
}
    

