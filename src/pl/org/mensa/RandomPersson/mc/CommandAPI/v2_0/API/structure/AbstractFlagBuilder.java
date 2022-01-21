package pl.org.mensa.RandomPersson.mc.CommandAPI.v2_0.API.structure;

import javax.annotation.Nonnull;

import pl.org.mensa.RandomPersson.mc.CommandAPI.v2_0.API.structure.structure.AbstractAliasableBuilder;
import pl.org.mensa.RandomPersson.mc.CommandAPI.v2_0.API.structure.structure.AbstractBackableBuilder;
import pl.org.mensa.RandomPersson.mc.CommandAPI.v2_0.API.structure.structure.AbstractFlaggableBuilder;
import pl.org.mensa.RandomPersson.mc.CommandAPI.v2_0.API.structure.structure.AbstractGenericBuilder;

public abstract interface AbstractFlagBuilder<
	VARTYPE,
	SUPTYPE extends AbstractGenericBuilder<SUPTYPE>,
	THISTYPE extends AbstractFlagBuilder<VARTYPE, SUPTYPE, THISTYPE>
>
extends
	AbstractGenericBuilder<THISTYPE>,
	AbstractAliasableBuilder<THISTYPE>,
	AbstractFlaggableBuilder<THISTYPE>,
	AbstractBackableBuilder<SUPTYPE>
{
	public THISTYPE setDefaultValue(@Nonnull VARTYPE default_value);
}
