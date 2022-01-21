package pl.org.mensa.RandomPersson.mc.CommandAPI.v2_0.API.structure.structure;

import javax.annotation.Nonnull;

import pl.org.mensa.RandomPersson.mc.CommandAPI.v2_0.API.FlagBuilder;
import pl.org.mensa.RandomPersson.mc.CommandAPI.v2_0.util.ClassType;

public abstract interface AbstractFlaggableBuilder<
	THISTYPE extends AbstractGenericBuilder<THISTYPE>
> {
	public FlagBuilder<Boolean, THISTYPE> addFlag(@Nonnull String name) throws IllegalArgumentException;
	public <VARTYPE> FlagBuilder<VARTYPE, THISTYPE> addFlag(@Nonnull String name, @Nonnull ClassType<VARTYPE> type) throws IllegalArgumentException;
}
