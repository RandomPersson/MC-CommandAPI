package pl.org.mensa.RandomPersson.mc.CommandAPI.v2_0;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import pl.org.mensa.RandomPersson.mc.CommandAPI.v2_0.API.Executor;
import pl.org.mensa.RandomPersson.mc.CommandAPI.v2_0.API.structure.structure.AbstractGenericBuilder;
import pl.org.mensa.RandomPersson.mc.CommandAPI.v2_0.builder.ArgumentBuilderClass;
import pl.org.mensa.RandomPersson.mc.CommandAPI.v2_0.builder.FlagBuilderClass;
import pl.org.mensa.RandomPersson.mc.CommandAPI.v2_0.builder.ParameterBuilderClass;
import pl.org.mensa.RandomPersson.mc.CommandAPI.v2_0.util.Utils;

public abstract class CommandSegment<
	THISTYPE extends AbstractGenericBuilder<THISTYPE>
> {
	protected final String name;
	
	protected Map<String, ArgumentBuilderClass<THISTYPE>> arguments;
	protected ParameterBuilderClass<?, THISTYPE> parameter;
	protected Map<String, FlagBuilderClass<?, THISTYPE>> flags;
	
	protected Set<String> aliases;
	protected String description;
	protected String permission_prefix;
	protected String permission;
	
	protected Boolean is_tab_completer_enabled;
	protected Boolean do_generate_permissions_for_arguments;
	
	protected Executor executor;
	
	public CommandSegment(String name, String permission_prefix) {
		this.name = name;
		
		arguments = new HashMap<String, ArgumentBuilderClass<THISTYPE>>();
		parameter = null; //TODO [5] default parameter - blank?
		flags = new HashMap<String, FlagBuilderClass<?, THISTYPE>>();
		
		aliases = new HashSet<String>();
		description = "This is description"; //TODO [9] description default value
		this.permission_prefix = permission_prefix;
		permission = permission_prefix + "." + this.name;
		
		is_tab_completer_enabled = true;
		do_generate_permissions_for_arguments = true;
		
		executor = (data -> Utils.debug("CMDAPI", "&bNo executor specified (" + permission + ")"));
	}
	
	public String getName() {
		return name;
	}
	
	public Map<String, ArgumentBuilderClass<THISTYPE>> getArguments() {
		return arguments;
	}
	public ParameterBuilderClass<?, THISTYPE> getParameter() {
		return parameter;
	}
	public Map<String, FlagBuilderClass<?, THISTYPE>> getFlags() {
		return flags;
	}
	
	
	public Set<String> getAliases() {
		return aliases;
	}
	public String getDescription() {
		return description;
	}
	public String getPermission() {
		return permission;
	}
	
	public Boolean getIsTabCompleterEnabled() {
		return is_tab_completer_enabled;
	}
	public Boolean getDoGeneratePermissionsForArguments() {
		return do_generate_permissions_for_arguments;
	}
	
	public Executor getExecutor() {
		return executor;
	}
}
