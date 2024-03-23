package main;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class employeeDatabaseFile extends employeeModel 
{  
	private static BufferedReader buffer_line;	
	private static String line = ""; 
	private static int count = 0;
	private static int max = 999999999;
	private static String delimiter = "[,]";
	
    public employeeDatabaseFile() throws Exception {
        String csvFile = "employee.csv";
        employees = new employeeDetails[max];
        buffer_reader(csvFile);
    }

    @Override
    public employeeDetails[] getEmployeeModelList() {
        return employees;
    }
	
	
    private void buffer_reader(String file) throws Exception   
	{  
		try {
			buffer_line = new BufferedReader(new FileReader(file));
			while ((line = buffer_line.readLine()) != null) {
				Scanner dataScanner = new Scanner(line);
				dataScanner(dataScanner, count);count++;}
		} catch (FileNotFoundException e) {
		} 
	}
	
    private void dataScanner(Scanner scanner, int counter) {
        scanner.useDelimiter(delimiter);
        while (scanner.hasNext()) {
        	employeeDetails employee = new employeeDetails();
            employee.setEmpNo(scanner.next());
            employee.setLastName(scanner.next());
            employee.setFirstName(scanner.next());
            employee.setSssNo(scanner.next());
            employee.setPhilHealthNo(scanner.next());
            employee.setTinNo(scanner.next());
            employee.setPagibigNo(scanner.next());
            employee.setBasicSalary(Double.parseDouble(scanner.next()));
            employee.setRiceSubsidy(Double.parseDouble(scanner.next()));
            employee.setPhoneAllowance(Double.parseDouble(scanner.next()));
            employee.setClothingAllowance(Double.parseDouble(scanner.next()));
            employee.setHourlyRate(Double.parseDouble(scanner.next()));
            employees[count] = employee;
        }
        scanner.close();
    }

	
}