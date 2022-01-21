package pl.org.mensa.RandomPersson.mc.CommandAPI.v2_0.API.structure;

import javax.annotation.Nonnull;

import pl.org.mensa.RandomPersson.mc.CommandAPI.v2_0.API.structure.structure.AbstractArgumentableBuilder;
import pl.org.mensa.RandomPersson.mc.CommandAPI.v2_0.API.structure.structure.AbstractBackableBuilder;
import pl.org.mensa.RandomPersson.mc.CommandAPI.v2_0.API.structure.structure.AbstractGenericBuilder;

public abstract interface AbstractParameterBuilder<
	VARTYPE,
	SUPTYPE extends AbstractGenericBuilder<SUPTYPE>,
	THISTYPE extends AbstractParameterBuilder<VARTYPE, SUPTYPE, THISTYPE>
>
extends
	AbstractGenericBuilder<THISTYPE>,
	AbstractArgumentableBuilder<THISTYPE>,
	AbstractBackableBuilder<SUPTYPE>
{
	public THISTYPE setOptional(boolean is_optional);
	public THISTYPE setDefaultValue(@Nonnull VARTYPE default_value);
}
