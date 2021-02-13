package woodspring.springapple.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Convert {
	
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd'T'HH:mm:ss'Z'");
	
	public static String nowString() {		
		return sdf.format( new Date( System.currentTimeMillis()));
	}

}
