package pl.org.mensa.RandomPersson.mc.CommandAPI.v2_0.util;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.Nonnull;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Utils {
	public static final Logger logger = Logger.getLogger("Minecraft");
	public static final String prefix = "&2[&aCommandAPI&2]&e";
	
	private static Map<String, Integer> debug_rails = new HashMap<String, Integer>();
	
	public static void enableDebug() {
		debug_off = false;
	}
	private static boolean debug_off = true;
	public static String debug_tab_sizer = "  ";
	
	public static String colorize(@Nonnull String s) {
		return s==null ? "" : s.replaceAll("&", "§");
	}
	
	public static void sendMessage(@Nonnull CommandSender sender, @Nonnull String message) {
		sender.sendMessage(colorize(message));
	}
	public static void sendMessage(@Nonnull Player player, @Nonnull String message) {
		player.sendMessage(colorize(message));
	}
	
	public static void log(@Nonnull Object object) {
		logger.log(Level.INFO, colorize(object.toString()));
	}
	public static void log(Level level, @Nonnull Object object) {
		logger.log(level, colorize(object.toString()));
	}
	
	public static void debug(@Nonnull Object object) {
		if (debug_off) return;
		
		logger.log(Level.INFO, colorize(prefix + " " + object.toString() + "&8#"));
	}
	public static synchronized void debug(String debug_rail, Object object) {
		if (debug_off) return;
		
		debug(debug_rail, 0, object);
	}
	public static synchronized void debug(String debug_rail, Integer indent_shift, Object object) {
		if (debug_off) return;
		
		String indent = " ";
		Integer tab_amount = debug_rails.getOrDefault(debug_rail, 0);
		debug_rails.put(debug_rail, tab_amount + indent_shift);
		for (int i=0; i<tab_amount; ++i) {
			indent += debug_tab_sizer;
		}
		
		logger.log(Level.INFO, colorize("&2[&a" + debug_rail + "&2]&7" + indent + object.toString() + "&8#"));
	}
	public static synchronized void debug(Integer indent_shift, String debug_rail, Object object) {
		if (debug_off) return;
		
		String indent = " ";
		Integer tab_amount = debug_rails.getOrDefault(debug_rail, 0) + indent_shift;
		debug_rails.put(debug_rail, tab_amount);
		for (int i=0; i<tab_amount; ++i) {
			indent += debug_tab_sizer;
		}
		
		logger.log(Level.INFO, colorize("&2[&a" + debug_rail + "&2]&7" + indent + object.toString() + "&8#"));
	}
	public static synchronized void resetDebugRail(String rail) {
		debug_rails.put(rail, 0);
		Utils.debug(rail, "&3==== Debug rail has been reset ====");
	}
	
	public static String mergeArray(@Nonnull String[] strings) {
		return mergeArray(strings, 0);
	}
	public static String mergeArray(@Nonnull String[] strings, int startIndex) {
		return mergeArray(strings, startIndex, " ");
	}
	public static String mergeArray(@Nonnull String[] strings, int startIndex, @Nonnull String separator) {
		if (strings.length <= startIndex) return "";
		String msg = strings[startIndex];
		
		for (int i=startIndex+1; i<strings.length; ++i) {
			msg += separator + strings[i];
		}
		
		return msg;
	}
	
	
	public static String removeSingleBackslashes(@Nonnull String string) {
		int length = string.length();
		
		char[] chars = string.concat("" + '\u0000').toCharArray();
		
		for (int i=0; i<length; ++i) {
			if (chars[i] == '\\') {
				if (chars[i+1] == '\\') {
					chars[++i] = '\u0000';
				}
				else {
					chars[i] = '\u0000';
				}
				
			}
		}
		
		return new String(chars);
	}
}
