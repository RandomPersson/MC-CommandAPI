package pl.org.mensa.RandomPersson.mc.CommandAPI.v2_0.API.structure.structure;

import javax.annotation.Nonnull;

public abstract interface AbstractBackableBuilder<
	SUPTYPE extends AbstractGenericBuilder<SUPTYPE>
> {
	public @Nonnull SUPTYPE back();
}
