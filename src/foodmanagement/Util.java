/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package foodmanagement;

import java.util.Scanner;
import java.util.Calendar;
import java.sql.Date;
import java.util.StringTokenizer;

/**
 *
 * @author oanhn
 */
public class Util {
    private static Scanner sc = new Scanner(System.in);
    
    //--------------PART ONE: FROM COMPUTER-------------------------------------
    //remember to check if it is a negative value or not both of int and double
    public static int getInt(String inputMsg, String errorMsg){
        int n;
        while (true) {            
            try {
                System.out.println(inputMsg);
                n = Integer.parseInt(sc.nextLine());
                return n;
            } catch (Exception e) {
                System.out.println(errorMsg);
            }
        }
    }
    
    public static double getDouble(String inputMsg, String errorMsg){
        double n;
        double lowerBound = 0.0;
        while(true){
            try {
                System.out.println(inputMsg);
                n = Double.parseDouble(sc.nextLine());
                if(n < lowerBound)
                    System.out.println("The weight is lower than 0.");
                else
                    return n;
            } catch (Exception e) {
                System.out.println(errorMsg);
            }
        
        }
    }
    
    public static String getString(String inputMsg, String errorMsg){
        String id;
        while(true){
            
                System.out.println(inputMsg);
                id = sc.nextLine().trim();
                if(id.length()== 0 || id.isEmpty())
                    System.out.println(errorMsg);
                else
                    return id;         
        }
    }
    
    public static String getID(String inputMsg, String errorMsg, String format){
        String id;
        boolean match;
        while (true) {   
            System.out.print(inputMsg);
            id = sc.nextLine().trim().toUpperCase();
            match = id.matches(format);
            if(id.length() == 0 || id.isEmpty() || match == false)
                System.out.println(errorMsg);
            else 
                return id;       
        }
    }
    
    public static boolean isLeap(int year){
        boolean result = false;
        if((year%400 == 0)||((year%4 == 0) && (year%100 != 0)))
            result = true;
        return result;       
    }
    
    public static boolean valid(int year, int month, int day){
        if(year < 0 || month < 0 || month > 12 || day < 0 || day > 31)
            return false;
        int maxDay = 31;
        if(month == 4 || month == 6 || month == 9 || month == 11)
            maxDay =30;
        else if(month == 2){
            if(isLeap(year))
                maxDay = 29;
            else
                maxDay = 28;
        }
        return day <= maxDay;     
    }
    //from String to Date
    public static long toDate(String ymd){
        StringTokenizer stk = new StringTokenizer(ymd,"/-");
        int year,month,day;
        try {
             year = Integer.parseInt(stk.nextToken());
             month =  Integer.parseInt(stk.nextToken());
             day = Integer.parseInt(stk.nextToken());
            
        } catch (Exception e) {
            
            return -2;
        }
        
        if(!valid(year, month, day))
            return -1;
        Calendar cal = Calendar.getInstance();
        cal.set(year,month-1,day);
        long time = cal.getTime().getTime();
        return time;
    }
    //date validation
    public static Date validateDate(String inputMsg){
        String strDate;
        Date date;
        
        while(true){
            System.out.println(inputMsg);
            strDate = sc.nextLine().trim();
            long time = toDate(strDate);
            if (time == -1) {
                System.out.println("Invalid expired date! Please enter again.");
            }
            else if (time == -2)
                System.out.println("Invalid input. Please input correct format yyyy/m/d "
                        + " or yyyy-m-d " + "or input correct number"
                );
            else {
                date = new Date(time);
                return date;
            }
            
        }
            
    }
    
    public static Date getDate(){
        Date now = new Date(System.currentTimeMillis());
        while (true) {            
            Date enteredDate = validateDate("Please enter an expired date with format yyyy/m/d or yyyy-m-d which is greater than " + "today [" + now + "]");
            int result = enteredDate.compareTo(now);
            if (result < 0)
                System.out.println("Please enter another date greater than or equal with today!");
            else
                return enteredDate;
        }    
        
    }
    
    //--------------PART TWO: FROM FILE-------------------------
          
    public static Date getDateFromFile(String input){
        long time = toDate(input);
        Date date = new Date(time);
        return date;
        
    }
    public static boolean dateValidateFromFile(Date date){
        boolean r = false;
        Date now = new Date(System.currentTimeMillis());
        int result = now.compareTo(date);
        if(result < 0||result ==0){
            r=true;
            return r;
        }
            return r;
    }
    public static boolean doubleValidateFromFile(double num){
        boolean r=true;
        if(num<0){
            r=false;
            return r;
        }
            
        return r;
    }
    
}
