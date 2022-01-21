package pl.org.mensa.RandomPersson.mc.CommandAPI.v2_0.builder;

import java.util.Arrays;
import java.util.Collection;

import pl.org.mensa.RandomPersson.mc.CommandAPI.v2_0.API.ArgumentBuilder;
import pl.org.mensa.RandomPersson.mc.CommandAPI.v2_0.API.Executor;
import pl.org.mensa.RandomPersson.mc.CommandAPI.v2_0.API.structure.structure.AbstractGenericBuilder;
import pl.org.mensa.RandomPersson.mc.CommandAPI.v2_0.util.ClassType;

public class ArgumentBuilderClass<
	SUPTYPE extends AbstractGenericBuilder<SUPTYPE>
>
extends GenericBuilderClass<ArgumentBuilder<SUPTYPE>>
implements ArgumentBuilder<SUPTYPE> {
	SUPTYPE supsegment;
	
	public ArgumentBuilderClass(String name, SUPTYPE supsegment) {
		super(name, ((GenericBuilderClass<?>)supsegment).getPermission());
		this.supsegment = supsegment;
	}
	
	public ArgumentBuilderClass<ArgumentBuilder<SUPTYPE>> addArgument(String name) throws IllegalArgumentException {
		Validator.validateArgumentName(name, arguments);
		
		ArgumentBuilderClass<ArgumentBuilder<SUPTYPE>> argument = new ArgumentBuilderClass<ArgumentBuilder<SUPTYPE>>(name, this);
		
		if (this.do_generate_permissions_for_arguments) {
			argument.setPermission(this.permission + "." + name);
		}
		argument.setIsTabCompleterEnabled(this.is_tab_completer_enabled);
		argument.setDoGeneratePermissionsForArguments(this.do_generate_permissions_for_arguments);
		
		arguments.put(name, argument);
		return argument;
	}
	public <VARTYPE> ParameterBuilderClass<VARTYPE, ArgumentBuilder<SUPTYPE>> setParameter(String name, ClassType<VARTYPE> type) throws IllegalArgumentException {
		if (parameter != null) {
			throw new IllegalArgumentException("One segment cannot have two parameters");
		}
		
		ParameterBuilderClass<VARTYPE, ArgumentBuilder<SUPTYPE>> parameter = new ParameterBuilderClass<VARTYPE, ArgumentBuilder<SUPTYPE>>(name, this, type);
		
		parameter.setIsTabCompleterEnabled(this.is_tab_completer_enabled);
		parameter.setDoGeneratePermissionsForArguments(this.do_generate_permissions_for_arguments);
		
		this.parameter = parameter;
		return parameter;
	}
	@Override
	public FlagBuilderClass<Boolean, ArgumentBuilder<SUPTYPE>> addFlag(String name) throws IllegalArgumentException {
		return addFlag(name, ClassType.BOOLEAN);
	}
	@Override
	public <VARTYPE> FlagBuilderClass<VARTYPE, ArgumentBuilder<SUPTYPE>> addFlag(String name, ClassType<VARTYPE> type) throws IllegalArgumentException {
		throw new UnsupportedOperationException("Not implemented yet - flags only allowed in base command builder");
		
//		Validator.validateArgumentName(name, flags);
//		
//		FlagBuilderClass<VARTYPE, ArgumentBuilderClass<SUPTYPE>> flag = new FlagBuilderClass<VARTYPE, ArgumentBuilderClass<SUPTYPE>>(name, this, type);
//		
//		if (this.do_generate_permissions_for_arguments) {
//			flag.setPermission(this.permission + "." + name);
//		}
//		flag.setIsTabCompleterEnabled(this.is_tab_completer_enabled);
//		
//		flags.put(name, flag);
//		return flag;
	}
	
	public ArgumentBuilderClass<SUPTYPE> addAlias(String alias) {
		this.aliases.add(alias);
		return this;
	}
	public ArgumentBuilderClass<SUPTYPE> addAliases(String... aliases) {
		this.aliases.addAll(Arrays.asList(aliases));
		return this;
	}
	public ArgumentBuilderClass<SUPTYPE> addAliases(Collection<String> aliases) {
		this.aliases.addAll(aliases);
		return this;
	}
	
	public ArgumentBuilderClass<SUPTYPE> setExecutor(Executor executor) {
		this.executor = executor;
		return this;
	}
	
	public SUPTYPE back() {
		return supsegment;
	}
}
