package main;

public class salaryDeductions {
	private double total;
    private double salary;
    private double sss = 0;
    private double withHoldingTax = 0;
    private double pag_ibig = 0;
    private double philhealth = 0;

    
    public salaryDeductions (double salary){
    	this.salary = salary;
    }

    public double getTotalDeductions() {
    	//sss
        int constant = 25000;
        if ((int) Math.round(salary) < constant) {
        	 sss = (int) (salary * 0.045);
        }else {
        	sss = 25000;
        }
        //withHoldingTax
        if (salary > 20833 && salary <= 33333) {
            withHoldingTax = (salary - 20833) * 0.15;
        } else if (salary >= 33333 && salary <= 66667) {
            withHoldingTax = 1875 + (salary - 33333) * 0.2;
        } else if (salary >= 66667 && salary <= 166667) {
            withHoldingTax = 8541 + (salary - 66667) * 0.25;
        } else if (salary >= 166667 && salary <= 666667) {
            withHoldingTax = 33541 + (salary - 166667) * 0.3;
        } else if (salary > 666667) {
            withHoldingTax = 183541 + (salary - 666667) * 0.35;
        }
        //pag-ibig
        final double minimunCompensation = 1500;
        if (salary > minimunCompensation) {
        	pag_ibig = salary * 0.02;
        }else {
        	pag_ibig = salary * 0.01;
        }
        //philhealth
        philhealth = salary * 0.05;
        
        total = sss + withHoldingTax + pag_ibig+ philhealth;
		return total;
        
    }
     
}