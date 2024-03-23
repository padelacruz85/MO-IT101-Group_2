package main;

import java.text.DecimalFormat;

public abstract class calculationFormat {
    protected static final DecimalFormat calcFormat = new DecimalFormat("#.##");
    protected abstract double wageCalculation();
    
}
