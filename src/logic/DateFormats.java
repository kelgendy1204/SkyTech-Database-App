package logic;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;


public class DateFormats {

	private final static SimpleDateFormat innerSimpleDateFormat = 
			new SimpleDateFormat("hh:mm:ss a" );
	private final static SimpleDateFormat outerSimpleDateFormat = 
			new SimpleDateFormat("EEE, dd MMM yyyy");
	private final static SimpleDateFormat outerSimpleDateFormatNoDays = 
			new SimpleDateFormat("MMM yyyy");
	private final static SimpleDateFormat outerSimpleDateFormatNoMonths = 
			new SimpleDateFormat("yyyy");
	private final static SimpleDateFormat fullSimpleDateFormat = 
			new SimpleDateFormat("hh:mm:ss a - dd/MM/yyyy");
	private final static SimpleDateFormat SimpleDateFormat = 
			new SimpleDateFormat("dd-MM-yyyy");

	//innerSimpleDateFormat
	public static String getInnerSimpleDateFormat(){
		return innerSimpleDateFormat.format(new Date()).toString();
	}
	
	//outerSimpleDateFormat
	public static String getOuterSimpleDateFormat(Date date){
		return outerSimpleDateFormat.format(date).toString();
	}
	
	public static String getOuterSimpleDateFormatNoDays(Date date) {
		return outerSimpleDateFormatNoDays.format(date).toString();
	}

	public static String getFullSimpleDateFormat(Timestamp timestamp){
		if (timestamp!= null) {
			Date date = new Date(timestamp.getTime());
			return fullSimpleDateFormat.format(date).toString();
		} else {
			return null;
		}
	}
	
	public static String getOutersimpledateformatNoMonths(Date date) {
		return outerSimpleDateFormatNoMonths.format(date).toString();
	}
	
	public static String getSimpleDateFormat(Date date) {
		return SimpleDateFormat.format(date).toString();
	}

}
