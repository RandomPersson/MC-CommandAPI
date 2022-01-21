package pl.org.mensa.RandomPersson.mc.CommandAPI.v2_0.API.structure.structure;

public abstract interface AbstractGenericBuilder<
	THISTYPE extends AbstractGenericBuilder<THISTYPE>
> {
	public THISTYPE setDescription(String description);
	public THISTYPE setPermission(String permission);
	public THISTYPE setPermissionExact(String full_permission);
	
	public THISTYPE setIsTabCompleterEnabled(Boolean is_tab_completer_enabled);
	public THISTYPE setDoGeneratePermissionsForArguments(Boolean do_generate_permissions_for_arguments);
}
