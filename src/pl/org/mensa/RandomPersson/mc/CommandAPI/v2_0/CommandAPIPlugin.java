package pl.org.mensa.RandomPersson.mc.CommandAPI.v2_0;

import java.util.ArrayList;
import java.util.Map;

import javax.annotation.Nonnull;

import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import pl.org.mensa.RandomPersson.mc.CommandAPI.v2_0.API.CommandBuilder;
import pl.org.mensa.RandomPersson.mc.CommandAPI.v2_0.builder.ArgumentBuilderClass;
import pl.org.mensa.RandomPersson.mc.CommandAPI.v2_0.builder.CommandBuilderClass;
import pl.org.mensa.RandomPersson.mc.CommandAPI.v2_0.builder.FlagBuilderClass;
import pl.org.mensa.RandomPersson.mc.CommandAPI.v2_0.util.Utils;

//TODO [7] JavaDocs for the API
public class CommandAPIPlugin extends JavaPlugin {
	
	private static CommandAPIPlugin plugin_instance;
	private static ArrayList<CommandBuilderClass> DEBUG_registered_commands = new ArrayList<CommandBuilderClass>();
	
	static {
		Utils.enableDebug();
	}
	
	public CommandAPIPlugin() {
		plugin_instance = this;
	}
	
	public static void DEBUG_PrintRegisteredCommandStructure() {
		for (CommandSegment<?> command : DEBUG_registered_commands) {
			Utils.debug("CMDAPI", 1, "Printing structure for command " + command.getName());
			DEBUG_PrintSingleCommandSegment(command);
			Utils.debug(-1, "CMDAPI", "Done printing structure for command " + command.getName());
		}
	}
	private static void DEBUG_PrintSingleCommandSegment(CommandSegment<?> segment) {
		Utils.debug("CMDAPI", "Aliases: " + Utils.mergeArray(segment.getAliases().toArray(new String[0]), 0, ", "));
		Utils.debug("CMDAPI", "Description: " + segment.getDescription());
		Utils.debug("CMDAPI", "Permission: " + segment.getPermission());
		Utils.debug("CMDAPI", "DoGeneratePermissionsForArguments: " + segment.getDoGeneratePermissionsForArguments());
		Utils.debug("CMDAPI", "IsTabCompleterEnabled: " + segment.getIsTabCompleterEnabled());
		
		for (Map.Entry<String, ? extends ArgumentBuilderClass<?>> argument : segment.getArguments().entrySet()) {
			Utils.debug("CMDAPI", 1, "Printing structure for argument " + argument.getKey());
			DEBUG_PrintSingleCommandSegment(argument.getValue());
			Utils.debug(-1, "CMDAPI", "");
		}
		
		if (segment.getParameter() == null) {
			Utils.debug("CMDAPI", "No parameter detected");
		}
		else {
			Utils.debug("CMDAPI", 1, "Printing structure for parameter " + ((CommandSegment<?>)segment.getParameter()).getName());
			DEBUG_PrintSingleCommandSegment(segment.getParameter());
			Utils.debug(-1, "CMDAPI", "");
		}
		
		for (Map.Entry<String, ? extends FlagBuilderClass<?, ?>> flag : segment.getFlags().entrySet()) {
			Utils.debug("CMDAPI", 1, "Printing structure for flag " + flag.getKey());
			DEBUG_PrintSingleCommandSegment(flag.getValue());
			Utils.debug(-1, "CMDAPI", "");
		}
	}
	
	public static void registerCommand(@Nonnull Plugin plugin, @Nonnull CommandBuilder command) {
		plugin_instance.getServer().getCommandMap().register(
			plugin.getDescription().getName().toLowerCase(),
			new CommandAdapter(
				((CommandSegment<?>)command).getName(),
				plugin,
				command
			)
		);
	}
	
	public static CommandBuilder buildCommand(@Nonnull String name, @Nonnull String plugin_name) { //TODO [7] accepting any plugin name is dangerous
		CommandBuilderClass command_builder = new CommandBuilderClass(name, plugin_name);
		DEBUG_registered_commands.add(command_builder);
		
		return command_builder;
	}
}
