package pl.org.mensa.RandomPersson.mc.CommandAPI.v2_0.API;

import pl.org.mensa.RandomPersson.mc.CommandAPI.v2_0.API.structure.AbstractParameterBuilder;
import pl.org.mensa.RandomPersson.mc.CommandAPI.v2_0.API.structure.structure.AbstractGenericBuilder;

public interface ParameterBuilder<
	VARTYPE,
	SUPTYPE extends AbstractGenericBuilder<SUPTYPE>
>
extends AbstractParameterBuilder<VARTYPE, SUPTYPE, ParameterBuilder<VARTYPE, SUPTYPE>> {}
