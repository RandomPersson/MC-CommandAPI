package pl.org.mensa.RandomPersson.mc.CommandAPI.v2_0.builder;

import java.util.Arrays;
import java.util.Collection;

import pl.org.mensa.RandomPersson.mc.CommandAPI.v2_0.API.FlagBuilder;
import pl.org.mensa.RandomPersson.mc.CommandAPI.v2_0.API.structure.structure.AbstractGenericBuilder;
import pl.org.mensa.RandomPersson.mc.CommandAPI.v2_0.util.ClassType;

public class FlagBuilderClass<
	VARTYPE,
	SUPTYPE extends AbstractGenericBuilder<SUPTYPE>
>
extends GenericBuilderClass<FlagBuilder<VARTYPE, SUPTYPE>>
implements FlagBuilder<VARTYPE, SUPTYPE> {
	protected final SUPTYPE supsegment;
	protected final ClassType<VARTYPE> type;
	protected VARTYPE default_value;
	
	public FlagBuilderClass(String name, SUPTYPE supsegment, ClassType<VARTYPE> type) {
		super(name, ((GenericBuilderClass<?>)supsegment).getPermission());
		this.supsegment = supsegment;
		this.type = type;
	}
	
	@Override
	public FlagBuilderClass<Boolean, FlagBuilder<VARTYPE, SUPTYPE>> addFlag(String name) throws IllegalArgumentException {
		return addFlag(name, ClassType.BOOLEAN);
	}
	@Override
	public <SUBVARTYPE> FlagBuilderClass<SUBVARTYPE, FlagBuilder<VARTYPE, SUPTYPE>> addFlag(String name, ClassType<SUBVARTYPE> type) throws IllegalArgumentException {
		throw new UnsupportedOperationException("Not implemented yet - flags only allowed in base command builder");
		
//		Validator.validateArgumentName(name, flags);
//		
//		FlagBuilderClass<SUBVARTYPE, FlagBuilderClass<VARTYPE, SUPTYPE>> flag = new FlagBuilderClass<SUBVARTYPE, FlagBuilderClass<VARTYPE, SUPTYPE>>(name, this, type);
//		
//		if (this.do_generate_permissions_for_arguments) {
//			flag.setPermission(this.permission + "." + name);
//		}
//		flag.setIsTabCompleterEnabled(this.is_tab_completer_enabled);
//		
//		flags.put(name, flag);
//		return flag;
	}
	
	public FlagBuilderClass<VARTYPE, SUPTYPE> addAlias(String alias) {
		this.aliases.add(alias);
		return this;
	}
	public FlagBuilderClass<VARTYPE, SUPTYPE> addAliases(String... aliases) {
		this.aliases.addAll(Arrays.asList(aliases));
		return this;
	}
	public FlagBuilderClass<VARTYPE, SUPTYPE> addAliases(Collection<String> aliases) {
		this.aliases.addAll(aliases);
		return this;
	}
	
	public FlagBuilderClass<VARTYPE, SUPTYPE> setDefaultValue(VARTYPE default_value) {
		this.default_value = default_value;
		
		return this;
	}
	
	public SUPTYPE back() {
		return supsegment;
	}
	
	public ClassType<VARTYPE> getType() {
		return type;
	}
	public VARTYPE getDefaultValue() {
		return default_value;
	}
}
