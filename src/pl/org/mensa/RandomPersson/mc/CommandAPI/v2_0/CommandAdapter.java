package pl.org.mensa.RandomPersson.mc.CommandAPI.v2_0;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.Validate;
import org.bukkit.command.CommandException;
import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginIdentifiableCommand;
import org.bukkit.plugin.Plugin;

import pl.org.mensa.RandomPersson.mc.CommandAPI.v2_0.API.CommandBuilder;
import pl.org.mensa.RandomPersson.mc.CommandAPI.v2_0.builder.ArgumentBuilderClass;
import pl.org.mensa.RandomPersson.mc.CommandAPI.v2_0.builder.FlagBuilderClass;
import pl.org.mensa.RandomPersson.mc.CommandAPI.v2_0.util.ClassType;
import pl.org.mensa.RandomPersson.mc.CommandAPI.v2_0.util.Utils;

public class CommandAdapter
extends org.bukkit.command.Command
implements PluginIdentifiableCommand {
	private final String id;
	private final Plugin plugin;
	private final CommandSegment<?> command;
	
	public CommandAdapter(String name, Plugin plugin, CommandBuilder command) {
		super(
			name,
			((CommandSegment<?>)command).getDescription(),
			((CommandSegment<?>)command).getDescription(),
			new ArrayList<String>(((CommandSegment<?>)command).getAliases())
		);
		
		this.id = plugin.getDescription().getName().toLowerCase() + ".command." + name;
		this.plugin = plugin;
		this.command = (CommandSegment<?>) command;
		
		this.setPermissionMessage(Utils.colorize("&4No permission."));
	}
	
	public Plugin getPlugin() {
		return plugin;
	}
	
	@Override
	public boolean execute(CommandSender sender, String command_label, String[] args) {
		Utils.resetDebugRail("CMDAPI");
		
		Utils.debug("CMDAPI", 1, "&aCommandAdapter.execute(" + sender.getName() + ", " + command_label + ", " + Utils.mergeArray(args) + ")");
		
		if (!plugin.isEnabled()) {
			Utils.debug("CMDAPI", -1 ,"&cPlugin is disabled - terminating");
			return false;
		}
		Utils.debug("CMDAPI", "Plugin is enabled - proceeding");
		
		if (!testPermission(sender)) {
			Utils.debug("CMDAPI", -1, "&cSender doesn't have permission - terminating");
			return true;
		}
		Utils.debug("CMDAPI", "Sender has permission - proceeding");
		
		try {
			Utils.debug("CMDAPI", 1, "&8CommandAdapter.execute on node " + command.getName());
			execute(command, new UserInputBuilder(sender), args, 0);
			Utils.debug(-1, "CMDAPI", "&8CommandAdapter.execute on node " + command.getName() + " DONE");
		} catch (Throwable ex) {
			throw new CommandException("Unhandled exception executing command '" + command_label + "' in plugin " + this.plugin.getDescription().getFullName(), ex);
		}
		finally {
			Utils.debug(-1, "CMDAPI", "&8CommandAdapter.execute DONE");
		}
		
		return true;
	}
	private void execute(CommandSegment<?> node, UserInputBuilder input, String[] args, int arg_iterator) {
		//TODO [OPTIMIZATION] first check argument number etc, then validate
		//TODO [OPTIMIZATION] flag processing seems slow
		//TODO [3] process default value (par + flag)
		
		// if input empty - execute current argument
		
		if (args.length >= arg_iterator) {
			Utils.debug("CMDAPI", "No input specified - running executor");
			node.getExecutor().execute(input.build());
			return;
		}
		
		while (args[arg_iterator].isEmpty()) {
			if (args.length >= ++arg_iterator) {
				Utils.debug("CMDAPI", "No input specified - running executor");
				node.getExecutor().execute(input.build());
				return;
			}
		}
		
		String current_arg = args[arg_iterator];
		Utils.debug("CMDAPI", "Processing input " + arg_iterator + ": " + current_arg);
		
		
		
		// match flag - add to input builder
		
		//TODO [3] flag default value
		if (current_arg.startsWith("-")) {
			Utils.debug("CMDAPI", "Detected charasteristics of a flag");
			
			String[] flag_split = current_arg.substring(1).split(":", 2);
			String flag_name = flag_split[0];
			String flag_value_string;
			if (flag_split.length >= 2) {
				flag_value_string = flag_split[1];
			}
			else {
				flag_value_string = ""; //TODO default
			}
			Utils.debug("CMDAPI", "flag_name = " + flag_name);
			Utils.debug("CMDAPI", "flag_value_string = " + flag_value_string);
			if (flag_value_string.startsWith("\"") || flag_value_string.startsWith("'")) {
				flag_value_string = flag_value_string.substring(1);
				
				if (flag_value_string.endsWith("\"") && !flag_value_string.endsWith("\\\"") || flag_value_string.endsWith("'") && !flag_value_string.endsWith("\\'")) {
					flag_value_string = flag_value_string.substring(0, flag_value_string.length()-1);
				}
				else {
					boolean found_input_end = false;
					
					for (int flag_value_iterator = ++arg_iterator; flag_value_iterator < args.length; ++flag_value_iterator) {
						String curr_flag_arg = args[flag_value_iterator];
						String flag_value_suffix = curr_flag_arg.substring(curr_flag_arg.length() - (curr_flag_arg.length() < 2 ? 1 : 2), curr_flag_arg.length());
						
						Utils.debug("CMDAPI", "flag_value_suffix = &8#&e" + flag_value_suffix);
						if (flag_value_suffix.endsWith("\"") || flag_value_suffix.endsWith("'")) {
							if (flag_value_suffix.equals("\\\"") || flag_value_suffix.equals("\\'")) {
								flag_value_string += " " + curr_flag_arg.substring(0, curr_flag_arg.length()-2) + flag_value_suffix.charAt(1);
								continue;
							}
							
							flag_value_string += " " + curr_flag_arg.substring(0, curr_flag_arg.length()-1);
							found_input_end = true;
							break;
						}
						else {
							flag_value_string += " " + curr_flag_arg;
						}
					}
					
					if (!found_input_end) {
						Utils.sendMessage(input.getSender(), "&cCould not find the end of input for flag " + flag_name + " (missing \")");
						Utils.debug("CMDAPI", "&cCould not find the end of input for flag " + flag_name + " (missing \")");
						return;
					}
				}
			}
			
			flag_value_string = flag_value_string.replaceAll("\\\"", "\"").replaceAll("\\'", "'");
			
			for (Map.Entry<String, ? extends FlagBuilderClass<?, ?>> flag_entry : node.getFlags().entrySet()) {
				if (flag_entry.getKey().equals(flag_name)) {
					Utils.debug("CMDAPI", "Identified input as flag " + flag_name + " with input " + flag_value_string);
					Utils.debug("CMDAPI", "&7Validating...");
					ClassType<?> flag_type = flag_entry.getValue().getType();
					
					try {
						flag_type.parse(flag_value_string);
					} catch(NumberFormatException exc) {
						Utils.debug("CMDAPI", "&cValidation failed: unable to parse " + flag_type.getType() + " as " + flag_type.toString());
						Utils.sendMessage(input.getSender(), "&cIncorrent input - flag " + flag_name + " requires " + flag_type.getType());
						return; //TODO [flag check] should there be a return here? (nope, it shouldn't)
					}
					
					Utils.debug("CMDAPI", "&7Validation success: input '" + flag_value_string + "' is a correct " + flag_type.getType());
					input.addFlag(flag_name, flag_value_string);
					return; //TODO [flag check] should there be a return here? (nope, it shouldn't)
				}
			}
			
			Utils.debug("CMDAPI", "&cCould not match any flag to " + flag_name);
		}
		else {
			
			
			// match argument - add to input builder and process argument
			
			Utils.debug("CMDAPI", "&8Possible arguments:");
			for (Map.Entry<String, ? extends ArgumentBuilderClass<?>> argument_entry : node.getArguments().entrySet()) {
				Utils.debug("CMDAPI", "&8> " + argument_entry.getKey());
			}
			
			for (Map.Entry<String, ? extends ArgumentBuilderClass<?>> argument_entry : node.getArguments().entrySet()) { //TODO [6] separate execute methods for each segment type
				if (argument_entry.getKey().equals(current_arg)) {
					ArgumentBuilderClass<?> current_arg_class = argument_entry.getValue();
					Utils.debug("CMDAPI", "Identified input as argument " + current_arg);
					input.addArgument(current_arg);
					Utils.debug("CMDAPI", 1, "&8CommandAdapter.execute on node " + current_arg_class.getName());
					execute(current_arg_class, input, args, arg_iterator+1);
					Utils.debug(-1, "CMDAPI", "&8CommandAdapter.execute on node " + current_arg_class.getName() + " DONE");
					return;
				}
			}
		}
		
		
		
		// match parameter - add to input builder and process parameter
		
		if (node.getParameter() != null) {
			Utils.debug("CMDAPI", "&8Node has a parameter: " + node.getParameter().getName());
			
			Utils.debug("CMDAPI", "Interpreting input as parameter due to no flag or argument match");
			
			//TODO [2] add VARTYPE to UserInput
			Utils.debug("CMDAPI", "&7Validating..");
			ClassType<?> parameter_type = node.getParameter().getType();
			
			try {
				parameter_type.parse(current_arg);
			} catch(NumberFormatException exc) {
				Utils.debug("CMDAPI", "&cValidation failed: unable to parse " + current_arg + " as " + parameter_type.toString());
				Utils.sendMessage(input.getSender(), "&cIncorrent input - parameter " + node.getParameter().getName() + " requires " + parameter_type.getType());
				return;
			}
			
			Utils.debug("CMDAPI", "Identified as parameter " + node.getParameter().getName() + " with value " + current_arg);
			input.addParameter(node.getParameter().getName(), current_arg);

			Utils.debug("CMDAPI", 1, "&8CommandAdapter.execute on node " + node.getParameter().getName());
			execute(node.getParameter(), input, args, arg_iterator+1);
			Utils.debug(-1, "CMDAPI", "&8CommandAdapter.execute on node " + node.getParameter().getName() + " DONE");
			return;
		}
		else {
			Utils.sendMessage(input.getSender(), "&cUnexpected argument: " + current_arg);
			Utils.debug("CMDAPI", "&cCould not identify input " + current_arg);
			//TODO [8] improve error message (segment description / auto help)
			return;
		}
	}
	
	@Override
	public java.util.List<String> tabComplete(CommandSender sender, String alias, String[] args) throws CommandException, IllegalArgumentException {
		Validate.notNull(sender, "Sender cannot be null");
		Validate.notNull(args, "Arguments cannot be null");
		Validate.notNull(alias, "Alias cannot be null");
		
		List<String> completions = null;
//		try {
//			if (tab_completer != null) {
//				completions = tab_completer.onTabComplete(sender, this, alias, args);
//			}
//			if (completions == null && bukkit_executor instanceof TabCompleter) {
//				completions = ((TabCompleter) bukkit_executor).onTabComplete(sender, this, alias, args);
//			}
//		} catch (Throwable ex) {
//			StringBuilder message = new StringBuilder();
//			message.append("Unhandled exception during tab completion for command '/").append(alias).append(' ');
//			for (String arg : args) {
//				message.append(arg).append(' ');
//			}
//			message.deleteCharAt(message.length() - 1).append("' in plugin ").append(plugin.getDescription().getFullName());
//			throw new CommandException(message.toString(), ex);
//		}
//		
//		if (completions == null) {
//			return super.tabComplete(sender, alias, args);
//		}
		return completions;
	}
	
	@Override
	public String toString() {
		return this.id;
	}
}
