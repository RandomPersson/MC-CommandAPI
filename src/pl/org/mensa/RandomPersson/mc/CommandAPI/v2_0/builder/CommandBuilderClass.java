package pl.org.mensa.RandomPersson.mc.CommandAPI.v2_0.builder;

import java.util.Arrays;
import java.util.Collection;

import org.bukkit.plugin.Plugin;

import pl.org.mensa.RandomPersson.mc.CommandAPI.v2_0.CommandAPIPlugin;
import pl.org.mensa.RandomPersson.mc.CommandAPI.v2_0.API.CommandBuilder;
import pl.org.mensa.RandomPersson.mc.CommandAPI.v2_0.API.Executor;
import pl.org.mensa.RandomPersson.mc.CommandAPI.v2_0.util.ClassType;

public class CommandBuilderClass
extends GenericBuilderClass<CommandBuilder>
implements CommandBuilder {
	public CommandBuilderClass(String name, String plugin_name) {
		super(name, plugin_name + ".command");
	}
	
	public ArgumentBuilderClass<CommandBuilder> addArgument(String name) throws IllegalArgumentException {
		Validator.validateArgumentName(name, arguments);
		
		ArgumentBuilderClass<CommandBuilder> argument = new ArgumentBuilderClass<CommandBuilder>(name, this);
		
		if (this.do_generate_permissions_for_arguments) {
			argument.setPermission(this.permission + "." + name);
		}
		argument.setIsTabCompleterEnabled(this.is_tab_completer_enabled);
		argument.setDoGeneratePermissionsForArguments(this.do_generate_permissions_for_arguments);
		
		arguments.put(name, argument);
		return argument;
	}
	//TODO [9][?] add infinite string parameter class (no par or arg afterwards)
	public <SUBVARTYPE> ParameterBuilderClass<SUBVARTYPE, CommandBuilder> setParameter(String name, ClassType<SUBVARTYPE> type) throws IllegalArgumentException {
		//TODO [9] Allow multiple parameters of different types
		if (parameter != null) {
			throw new IllegalArgumentException("One segment cannot have two parameters");
		}
		
		ParameterBuilderClass<SUBVARTYPE, CommandBuilder> parameter = new ParameterBuilderClass<SUBVARTYPE, CommandBuilder>(name, this, type);
		
		parameter.setIsTabCompleterEnabled(this.is_tab_completer_enabled);
		parameter.setDoGeneratePermissionsForArguments(this.do_generate_permissions_for_arguments);
		
		this.parameter = parameter;
		return parameter;
	}
	public FlagBuilderClass<Boolean, CommandBuilder> addFlag(String name) throws IllegalArgumentException {
		return addFlag(name, ClassType.BOOLEAN);
	}
	public <VARTYPE> FlagBuilderClass<VARTYPE, CommandBuilder> addFlag(String name, ClassType<VARTYPE> type) throws IllegalArgumentException {
		Validator.validateArgumentName(name, flags);
		
		FlagBuilderClass<VARTYPE, CommandBuilder> flag = new FlagBuilderClass<VARTYPE, CommandBuilder>(name, this, type);
		
		if (this.do_generate_permissions_for_arguments) {
			flag.setPermission(this.permission + "." + name);
		}
		flag.setIsTabCompleterEnabled(this.is_tab_completer_enabled);
		
		flags.put(name, flag);
		return flag;
	}
	
	public CommandBuilderClass addAlias(String alias) {
		this.aliases.add(alias);
		return this;
	}
	public CommandBuilderClass addAliases(String... aliases) {
		this.aliases.addAll(Arrays.asList(aliases));
		return this;
	}
	public CommandBuilderClass addAliases(Collection<String> aliases) {
		this.aliases.addAll(aliases);
		return this;
	}
	
	public CommandBuilderClass setExecutor(Executor executor) {
		this.executor = executor;
		return this;
	}
	
	public void register(Plugin plugin) {
		CommandAPIPlugin.registerCommand(plugin, this);
	}
//	public ? build() {
//		
//	}
}
