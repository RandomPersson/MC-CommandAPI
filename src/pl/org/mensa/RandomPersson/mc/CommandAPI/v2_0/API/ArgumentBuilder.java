package pl.org.mensa.RandomPersson.mc.CommandAPI.v2_0.API;

import pl.org.mensa.RandomPersson.mc.CommandAPI.v2_0.API.structure.AbstractArgumentBuilder;
import pl.org.mensa.RandomPersson.mc.CommandAPI.v2_0.API.structure.structure.AbstractGenericBuilder;

public interface ArgumentBuilder<
	SUPTYPE extends AbstractGenericBuilder<SUPTYPE>
>
extends AbstractArgumentBuilder<SUPTYPE, ArgumentBuilder<SUPTYPE>> {}
