package pl.org.mensa.RandomPersson.mc.CommandAPI.v2_0.API.structure.structure;

import javax.annotation.Nonnull;

import pl.org.mensa.RandomPersson.mc.CommandAPI.v2_0.util.ClassType;
import pl.org.mensa.RandomPersson.mc.CommandAPI.v2_0.API.ArgumentBuilder;
import pl.org.mensa.RandomPersson.mc.CommandAPI.v2_0.API.ParameterBuilder;

public abstract interface AbstractArgumentableBuilder<
	THISTYPE extends AbstractGenericBuilder<THISTYPE>
> {
	public ArgumentBuilder<THISTYPE> addArgument(@Nonnull String name) throws IllegalArgumentException;
	public <VARTYPE> ParameterBuilder<VARTYPE, THISTYPE> setParameter(@Nonnull String name, @Nonnull ClassType<VARTYPE> type) throws IllegalArgumentException;
}
