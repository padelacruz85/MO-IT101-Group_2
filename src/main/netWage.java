package main;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;


public class netWage extends calculationFormat
{  
	private static String employee_data = "employee.csv";
	private static String time_data = "time.csv";
	private static BufferedReader buffer_line;
	private static String firstName = "";
	private static String lastName= "";
	private static String line = ""; 
	private static double total_time = 0;
	private static double total_deductions = 0;
	private static double gross_Salary = 0;
	private static double net_Salary = 0;
	private static int max_row = 0;
	private static int count = 0;
	private static int passFail=0;
	
	public static BufferedReader buffer_reader(String file)   
	{  
		try {
			buffer_line = new BufferedReader(new FileReader(file));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} 
        return buffer_line;
	}
	
	public double wageCalculation() {
		 try   
			{  
			@SuppressWarnings("resource")
			Scanner scan = new Scanner (System.in); 
	        System.out.print("""
	                -----------------------------
	                    ENTER EMPLOYEE NAME
	                -----------------------------
	                 """);
	        System.out.print("\nEnter First Name: ");firstName= scan.next();
	        System.out.print("\nEnter Last Name: ");lastName = scan.next();
				//GET EMPLOYEE DETAILS
				buffer_reader(time_data);
				while ((line = buffer_line.readLine()) != null) 
				{  
					count++;
					if(count != 0) {
						String[] row_data = line.split(","); 						
						if (row_data[1].equalsIgnoreCase(lastName) && row_data[2].equalsIgnoreCase(firstName)) {
							time_diff(row_data[3],row_data[4]);
						}
						else {continue;}}} 
				///Get Employee Hour Rate
				line = ""; 
				count = 0;
				//GET MAX ROW
				buffer_reader(employee_data);
				while ((line = buffer_line.readLine()) != null) {
					max_row++;
				}
				//GET EMPLOYEE DETAILS
				buffer_reader(employee_data);
				while ((line = buffer_line.readLine()) != null) 
				{  
					count++;
					if(count != 0) {
						String[] row_data = line.split(","); 
						if (row_data[1].equalsIgnoreCase(lastName) && row_data[2].equalsIgnoreCase(firstName)) {					     
						     gross_Salary = total_time * Double.parseDouble(row_data[11]);		
						     salaryDeductions net = new salaryDeductions(gross_Salary);
							 total_deductions = net.getTotalDeductions();
							 net_Salary = gross_Salary - total_deductions;
						        System.out.println("""
						                ------------------------------------------           
						                Employee ID: %s
						                First Name: %s
						                Last Name: %s
						                Hourly Rate: PHP%s
						                Total Hours: %s
						                Gross Wage: %s
						                Deductions: %s
						                Net Wage: PHP%s
						                ------------------------------------------
						                """.formatted(row_data[0].strip(),row_data[1],row_data[2], calcFormat.format(Double.parseDouble(row_data[11])), total_time,  calcFormat.format(net_Salary),  calcFormat.format(total_deductions), calcFormat.format(net_Salary)));passFail=1;}
						else if (count==max_row && passFail==0) {System.out.print(firstName.toUpperCase()+" "+lastName.toUpperCase()+ " has no employee record!\n");break;}
						else {continue;}}
				}
			}   
		catch (Throwable e) {e.printStackTrace();}
	return net_Salary;
	}
	
	public static void time_diff(String In, String Out) throws Throwable
    { 
        String time1 = In;String time2 = Out; 
        SimpleDateFormat simpleDateFormat= new SimpleDateFormat("HH:mm:ss");   
        Date date1 = simpleDateFormat.parse(time1); Date date2 = simpleDateFormat.parse(time2); 
        long differenceInMilliSeconds = Math.abs(date2.getTime() - date1.getTime());
        long differenceInHours = (differenceInMilliSeconds / (60 * 60 * 1000)) % 24;
        long differenceInMinutes = (differenceInMilliSeconds / (60 * 1000)) % 60;
        long differenceInSeconds = (differenceInMilliSeconds / 1000) % 60;
        total_time = (total_time + (differenceInHours + differenceInMinutes/60 + differenceInSeconds/3600)) - 1; 
    }

	

    
} 

