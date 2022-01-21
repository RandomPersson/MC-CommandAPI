package pl.org.mensa.RandomPersson.mc.CommandAPI.v2_0;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.command.CommandSender;

public class UserInputBuilder {
	private CommandSender sender;
	
	private List<String> arguments;
	private Map<String, String> parameters;
	private Map<String, String> flags;
	
	public UserInputBuilder(CommandSender sender) {
		this.sender = sender;
		this.arguments = new ArrayList<String>();
		this.parameters = new HashMap<String, String>();
		this.flags = new HashMap<String, String>();
	}
	
	public CommandSender getSender() {
		return sender;
	}
	
	public UserInputBuilder addArgument(String name) {
		this.arguments.add(name);
		return this;
	}
	public UserInputBuilder addParameter(String name, String value) {
		this.parameters.put(name, value);
		return this;
	}
	public UserInputBuilder addFlag(String name, String value) {
		this.flags.put(name, value);
		return this;
	}
	
	public UserInputClass build() {
		return new UserInputClass(this.sender, this.arguments, this.parameters, this.flags);
	}
}
