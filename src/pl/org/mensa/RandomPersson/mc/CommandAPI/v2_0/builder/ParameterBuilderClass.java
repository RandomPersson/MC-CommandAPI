package pl.org.mensa.RandomPersson.mc.CommandAPI.v2_0.builder;

import pl.org.mensa.RandomPersson.mc.CommandAPI.v2_0.API.ParameterBuilder;
import pl.org.mensa.RandomPersson.mc.CommandAPI.v2_0.API.structure.structure.AbstractGenericBuilder;
import pl.org.mensa.RandomPersson.mc.CommandAPI.v2_0.util.ClassType;

public class ParameterBuilderClass<
	VARTYPE,
	SUPTYPE extends AbstractGenericBuilder<SUPTYPE>
>
extends GenericBuilderClass<ParameterBuilder<VARTYPE, SUPTYPE>>
implements ParameterBuilder<VARTYPE, SUPTYPE> {
	protected final SUPTYPE supsegment;
	protected final ClassType<VARTYPE> type;
	protected boolean is_optional;
	protected VARTYPE default_value;
	
	public ParameterBuilderClass(String name, SUPTYPE supsegment, ClassType<VARTYPE> type) {
		super(name, ((GenericBuilderClass<?>)supsegment).getPermission());
		this.supsegment = supsegment;
		this.type = type;
	}
	
	public ArgumentBuilderClass<ParameterBuilder<VARTYPE, SUPTYPE>> addArgument(String name) throws IllegalArgumentException {
		Validator.validateArgumentName(name, arguments);
		
		ArgumentBuilderClass<ParameterBuilder<VARTYPE, SUPTYPE>> argument = new ArgumentBuilderClass<ParameterBuilder<VARTYPE, SUPTYPE>>(name, this);
		
		if (this.do_generate_permissions_for_arguments) {
			argument.setPermission(this.permission + "." + name);
		}
		argument.setIsTabCompleterEnabled(this.is_tab_completer_enabled);
		argument.setDoGeneratePermissionsForArguments(this.do_generate_permissions_for_arguments);
		
		arguments.put(name, argument);
		return argument;
	}
	public <SUBVARTYPE> ParameterBuilderClass<SUBVARTYPE, ParameterBuilder<VARTYPE, SUPTYPE>> setParameter(String name, ClassType<SUBVARTYPE> type) throws IllegalArgumentException {
		if (parameter != null) {
			throw new IllegalArgumentException("One segment cannot have two parameters");
		}
		
		ParameterBuilderClass<SUBVARTYPE, ParameterBuilder<VARTYPE, SUPTYPE>> parameter = new ParameterBuilderClass<SUBVARTYPE, ParameterBuilder<VARTYPE, SUPTYPE>>(name, this, type);
		
		parameter.setIsTabCompleterEnabled(this.is_tab_completer_enabled);
		parameter.setDoGeneratePermissionsForArguments(this.do_generate_permissions_for_arguments);
		
		this.parameter = parameter;
		return parameter;
	}
	
	public ParameterBuilderClass<VARTYPE, SUPTYPE> setOptional(boolean is_optional) {
		this.is_optional = is_optional;
		
		return this;
	}
	public ParameterBuilderClass<VARTYPE, SUPTYPE> setDefaultValue(VARTYPE default_value) {
		this.default_value = default_value;
		
		return this;
	}
	
	public SUPTYPE back() {
		return supsegment;
	}
	
	public ClassType<VARTYPE> getType() {
		return type;
	}
	public boolean isOptional() {
		return is_optional;
	}
	public VARTYPE getDefaultValue() {
		return default_value;
	}
}
