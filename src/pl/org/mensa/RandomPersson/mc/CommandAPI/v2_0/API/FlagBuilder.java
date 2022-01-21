package pl.org.mensa.RandomPersson.mc.CommandAPI.v2_0.API;

import pl.org.mensa.RandomPersson.mc.CommandAPI.v2_0.API.structure.AbstractFlagBuilder;
import pl.org.mensa.RandomPersson.mc.CommandAPI.v2_0.API.structure.structure.AbstractGenericBuilder;

public interface FlagBuilder <
	VARTYPE,
	SUPTYPE extends AbstractGenericBuilder<SUPTYPE>
>
extends AbstractFlagBuilder<VARTYPE, SUPTYPE, FlagBuilder<VARTYPE, SUPTYPE>> {}
