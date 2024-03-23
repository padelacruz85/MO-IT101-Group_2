package main;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import account.account;

public class login  
{
    private static employeeModel employeeDetail;
    private static String time_data = "time.csv";
    private static Scanner scan = new Scanner(System.in);
    private static String format = "%-20s%-20s%-20s%-20s";
    private static String menuItem = "";
    private static int valid = 0;
    private static int continuation = 1; 
    private static String username = "";
    private static String password = "";
    private static String firstName = "";
    private static String lastName = "";    
	private static final String delimiter = ",";
	private static final String seperator = "\n";
	
	public static void main(String[] args) throws Throwable 
    {
      Scanner input = new Scanner (System.in);        
      do{
    	  System.out.print("""
                  -----------------------------
                      ENTER CREDENTIAL
                  -----------------------------
                   """);
	      System.out.println("Username: ");username = input.nextLine();
	      System.out.println("Password: ");password = input.nextLine();
	      account login = new account(username, password);
	      if(login.checkPassword()) {
	    	  employeeDetail();
	    	  MainMenu();
	    	  valid=1;
	      }
	      else {
	          System.out.println("The username and password you entered are incorrect.");
	          
	      }
      	}while (valid == 0);
      input.close();   
    }
    
	
    private static void MainMenu() throws Throwable {
        do{ 
            System.out.print("""
            -------- SYSTEM MENU --------
            [1] Employee Details
            [2] Calculate Gross Wage
            [3] Calculate Net Wage
            [4] Time-IN/Time-OUT
            [0] EXIT
            -----------------------------
            SELECT: """);
            
            String option = scan.next();
     
            switch (option){
            	case "0":
            		System.exit(0);
                	break;
                case "1":
                    System.out.print("""
                    -------- SUB-MENU --------
                    [1] Complete Employee Details
                    [2] Find Employee Details
                    -------------------------
                    Choose: """);
                    menuItem = scan.next();
                    System.out.println("***********************************************");
                    menuItem(menuItem);
                    break;     
                case "2":
                    calculationFormat grossWage = new grossWage();              
                    grossWage.wageCalculation();         
                    break;
                case "3":
                    calculationFormat netWage = new netWage();              
                    netWage.wageCalculation();         
                    break;
                case "4":
                	timeInOut();       
                    break;
                default:
                    System.out.println("Invalid Input!");
                    break;
            }
           System.out.println("\nRETURN TO MAIN MENU? [0] NO, [1] YES");
            continuation = scan.nextInt();
            }while (continuation != 0);
        }
    

	private static void menuItem(String menu){
	    switch (menu){case "1" -> completeEmployeesDetail();case "2" -> individualEmployeeDetail();}
	}

	private static void employeeDetail() throws Exception {
    		employeeDetail = new employeeDatabaseFile();
	}


    private static void completeEmployeesDetail() {
        employeeDetails[] employeeList = employeeDetail.getEmployeeModelList();
        System.out.print("""
                -----------------------------
                    FULL EMPLOYEE DETAILS
                -----------------------------
                 """);
        System.out.print("""
                -------- SUB-MENU --------
                [1] Complete Employee Details
                [2] Find Employee Details
                -------------------------
                 """);
        for (int a = 0; a < employeeList.length; a++) {
        	employeeDetails employee = employeeList[a];
        	try {
            System.out.printf(format, employee.getEmpNo().strip(),employee.getLastName(),employee.getFirstName(),employee.getBasicSalary()+"\n");
        	} catch (Exception e) {break;}}
    }

    private static void individualEmployeeDetail() {
        employeeDetails[] employeeList = employeeDetail.getEmployeeModelList();
        System.out.print("""
                -----------------------------
                    ENTER EMPLOYEE NAME
                -----------------------------
                 """);
        System.out.print("\nEnter First Name: ");firstName= scan.next();
        System.out.print("\nEnter Last Name: ");lastName = scan.next();valid=0;
 
        for (int a = 0; a < employeeList.length; a++) {
        	employeeDetails employee = employeeList[a];
        	try {
        		if (firstName.equalsIgnoreCase(employee.getFirstName().strip()) && lastName.equalsIgnoreCase(employee.getLastName().strip())) {
        			System.out.printf(format, employee.getEmpNo().trim(),employee.getLastName(),employee.getFirstName(),employee.getBasicSalary()+"\n");valid=1;}
        	} catch (Exception e){
        		if (valid==0) {System.out.print(firstName.toUpperCase()+" "+lastName.toUpperCase()+ " has no employee record!\n");
        		}break;}}
    }
    
    
    private static void timeInOut() {
        employeeDetails[] employeeList = employeeDetail.getEmployeeModelList();
        System.out.println("***********************************************");
        System.out.println("          TIME-IN/TIME-OUT        ");
        System.out.println("***********************************************");
        System.out.print("\nEnter First Name: ");firstName= scan.next();
        System.out.print("\nEnter Last Name: ");lastName = scan.next();valid=0;
 
        for (int a = 0; a < employeeList.length; a++) {
        	employeeDetails employee = employeeList[a];
        	try {
        		if (firstName.equalsIgnoreCase(employee.getFirstName().strip()) && lastName.equalsIgnoreCase(employee.getLastName().strip())) {
        			writeCsvFile(employee.getEmpNo(), firstName.toUpperCase(), lastName.toUpperCase());valid=1;}
        	} catch (Exception e){
        		if (valid==0) {System.out.print(firstName.toUpperCase()+" "+lastName.toUpperCase()+ " has no employee record!\n");
        		}break;}}
    }    
    
	@SuppressWarnings({ "resource", "deprecation" })
	public static void writeCsvFile(String EmpNo, String Name, String Last)
    { 
		
		SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");  
	    Date date = new Date();  
	    
		try {
			FileWriter fileWriter = null;
			fileWriter = new FileWriter(time_data,true);
			fileWriter.append(EmpNo);fileWriter.append(delimiter);
	        fileWriter.append(Name);fileWriter.append(delimiter);
	        fileWriter.append(Last);fileWriter.append(delimiter);
	        fileWriter.append(formatter.format(date));fileWriter.append(delimiter);
	        fileWriter.append(formatter.format(date.getHours()+4));fileWriter.append(delimiter);
	        fileWriter.append(seperator);
            System.out.print(firstName.toUpperCase()+" "+lastName.toUpperCase()+ " time recorded!\n");
            fileWriter.flush();
            fileWriter.close();
		}catch (IOException e) {e.printStackTrace();}
		
    }

	
	
}