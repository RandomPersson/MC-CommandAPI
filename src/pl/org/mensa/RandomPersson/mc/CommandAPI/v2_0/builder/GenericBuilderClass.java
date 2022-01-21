package pl.org.mensa.RandomPersson.mc.CommandAPI.v2_0.builder;

import pl.org.mensa.RandomPersson.mc.CommandAPI.v2_0.CommandSegment;
import pl.org.mensa.RandomPersson.mc.CommandAPI.v2_0.API.structure.structure.AbstractGenericBuilder;

@SuppressWarnings("unchecked")
public abstract class GenericBuilderClass<
	THISTYPE extends AbstractGenericBuilder<THISTYPE>
>
extends CommandSegment<THISTYPE>
implements AbstractGenericBuilder<THISTYPE> {
	public GenericBuilderClass(String name, String permission_prefix) {
		super(name, permission_prefix);
	}
	
	public THISTYPE setDescription(String description) {
		this.description = description;
		return (THISTYPE) this;
	}
	public THISTYPE setPermission(String permission) {
		this.permission = permission_prefix + "." + permission;
		return (THISTYPE) this;
	}
	public THISTYPE setPermissionExact(String full_permission) {
		this.permission = full_permission;
		return (THISTYPE) this;
	}
	
	public THISTYPE setIsTabCompleterEnabled(Boolean is_tab_completer_enabled) {
		this.is_tab_completer_enabled = is_tab_completer_enabled;
		return (THISTYPE) this;
	}
	public THISTYPE setDoGeneratePermissionsForArguments(Boolean do_generate_permissions_for_arguments) {
		this.do_generate_permissions_for_arguments = do_generate_permissions_for_arguments;
		return (THISTYPE) this;
	}
	
}
