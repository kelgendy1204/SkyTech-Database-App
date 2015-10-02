package logic;

import java.text.DecimalFormat;

public class NumbersHandling {
	
	public static final DecimalFormat decimalFormat = new DecimalFormat("#.##");
	
	public static boolean isInteger(String s) {
	    try { 
	        Integer.parseInt(s); 
	    } catch(NumberFormatException e) { 
	        return false; 
	    }
	    return true;
	}
	
	public static boolean isDouble(String s) {
		try { 
			Double.parseDouble(s); 
		} catch(NumberFormatException e) { 
			return false; 
		}
		return true;
	}
	
	public static boolean isPositive(double s) {
		if(s > 0) {
			return true;
		} else {
			return false;
		}
	}
	
	
}
