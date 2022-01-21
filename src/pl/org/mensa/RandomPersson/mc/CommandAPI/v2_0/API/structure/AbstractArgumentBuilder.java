package pl.org.mensa.RandomPersson.mc.CommandAPI.v2_0.API.structure;

import pl.org.mensa.RandomPersson.mc.CommandAPI.v2_0.API.structure.structure.AbstractAliasableBuilder;
import pl.org.mensa.RandomPersson.mc.CommandAPI.v2_0.API.structure.structure.AbstractArgumentableBuilder;
import pl.org.mensa.RandomPersson.mc.CommandAPI.v2_0.API.structure.structure.AbstractBackableBuilder;
import pl.org.mensa.RandomPersson.mc.CommandAPI.v2_0.API.structure.structure.AbstractExecutableBuilder;
import pl.org.mensa.RandomPersson.mc.CommandAPI.v2_0.API.structure.structure.AbstractFlaggableBuilder;
import pl.org.mensa.RandomPersson.mc.CommandAPI.v2_0.API.structure.structure.AbstractGenericBuilder;

public abstract interface AbstractArgumentBuilder<
	SUPTYPE extends AbstractGenericBuilder<SUPTYPE>,
	THISTYPE extends AbstractArgumentBuilder<SUPTYPE, THISTYPE>
>
extends
	AbstractGenericBuilder<THISTYPE>,
	AbstractAliasableBuilder<THISTYPE>,
	AbstractArgumentableBuilder<THISTYPE>,
	AbstractFlaggableBuilder<THISTYPE>,
	AbstractExecutableBuilder<THISTYPE>,
	AbstractBackableBuilder<SUPTYPE>
{}
