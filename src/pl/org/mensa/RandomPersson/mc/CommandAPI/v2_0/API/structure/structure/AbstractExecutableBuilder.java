package pl.org.mensa.RandomPersson.mc.CommandAPI.v2_0.API.structure.structure;

import javax.annotation.Nonnull;

import pl.org.mensa.RandomPersson.mc.CommandAPI.v2_0.API.Executor;

public abstract interface AbstractExecutableBuilder<
	THISTYPE extends AbstractExecutableBuilder<THISTYPE>
> {
	public THISTYPE setExecutor(@Nonnull Executor executor);
}
