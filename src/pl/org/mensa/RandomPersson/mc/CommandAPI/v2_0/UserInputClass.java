package pl.org.mensa.RandomPersson.mc.CommandAPI.v2_0;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.command.CommandSender;

import pl.org.mensa.RandomPersson.mc.CommandAPI.v2_0.util.ClassType;
import pl.org.mensa.RandomPersson.mc.CommandAPI.v2_0.util.ImmutableFastQueue;

public class UserInputClass implements pl.org.mensa.RandomPersson.mc.CommandAPI.v2_0.API.UserInput {
	CommandSender sender;
	
	ImmutableFastQueue<String> arguments;
	Map<String, String> parameters;
	Map<String, String> flags;
	
	public UserInputClass(
			CommandSender sender,
			List<String> arguments,
			Map<String, String> parameters,
			Map<String, String> flags
		) {
		
		this.sender = sender;
		
		this.arguments = ImmutableFastQueue.from(arguments);
		this.parameters = (parameters == null ? new HashMap<String, String>() : parameters);
		this.flags = (flags == null ? new HashMap<String, String>() : flags);
	}

	@Override
	public CommandSender getSender() {
		return this.sender;
	}
	
	public String popArgument() {
		try {
			return this.arguments.pop();
		} catch (IndexOutOfBoundsException exc) {
			return "";
		}
	}
	
	@Override
	public String getParameter(String name) {
		return this.parameters.get(name);
	}
	
	@Override
	public <T> T getParameter(String name, ClassType<T> parsable) {
		return (T) parsable.parse(this.parameters.get(name));
	}
	
	@Override
	public boolean hasFlag(String name) {
		return this.flags.containsKey(name);
	}
	@Override
	public <T> T getFlag(String name, ClassType<T> parsable) {
		return (T) parsable.parse(this.flags.get(name));
	}
}
