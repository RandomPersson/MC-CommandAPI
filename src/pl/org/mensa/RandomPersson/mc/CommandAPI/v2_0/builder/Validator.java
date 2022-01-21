package pl.org.mensa.RandomPersson.mc.CommandAPI.v2_0.builder;

import java.util.Map;

import javax.annotation.Nonnull;

import org.apache.commons.lang3.StringUtils;

public class Validator {
	private static final String allowed_characters = "etaoinshrdlcumwfgypbvkxjqz0123456789_ETAOINSHRDLCUMWFGYPBVKXJQZ";
	
	public static void validateArgumentName(@Nonnull String name, @Nonnull Map<String, ?> argument_list) {
		if (StringUtils.containsOnly(name, allowed_characters)) {
			throw new IllegalArgumentException("Arguments names cannot contain special characters (" + name + ")");
		}
		if (argument_list.containsKey(name)) {
			throw new IllegalArgumentException("An argument called " + name + " already exists");
		}
	}
	public static void validateParameterName(@Nonnull String name) {
		if (StringUtils.containsOnly(name, allowed_characters)) {
			throw new IllegalArgumentException("Parameter names cannot contain special characters (" + name + ")");
		}
	}
	public static void validateFlagName(@Nonnull String name, @Nonnull Map<String, ?> flag_list) {
		if (StringUtils.containsOnly(name, allowed_characters)) {
			throw new IllegalArgumentException("Flag names cannot contain special characters (" + name + ")");
		}
		if (flag_list.containsKey(name)) {
			throw new IllegalArgumentException("A flag called " + name + " already exists");
		}
	}
}
