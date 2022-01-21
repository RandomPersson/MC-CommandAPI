package pl.org.mensa.RandomPersson.mc.CommandAPI.v2_0.util;

import java.util.HashMap;
import java.util.Map;

//TODO [9] Utilize
public class Lang {
	private static final Map<String, String> lang = new HashMap<String, String>();
	
	public static final String format(String message_code, String... VAR_replacements) {
		String formatted_message = lang.get(message_code);
		
		for (int i=0; i<VAR_replacements.length; ++i) {
			formatted_message.replaceAll("%VAR" + i + "%", VAR_replacements[i]);
		}
		
		return formatted_message;
	}
	
	static {
		lang.put("Argument names cannot contain special characters", "Argument names cannot contain special characters");
		lang.put("Parameter names cannot contain special characters", "Parameter names cannot contain special characters");
		lang.put("Flag names cannot contain special characters", "Flag names cannot contain special characters");
	}
}
