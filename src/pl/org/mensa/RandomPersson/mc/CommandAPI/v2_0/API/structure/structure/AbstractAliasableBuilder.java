package pl.org.mensa.RandomPersson.mc.CommandAPI.v2_0.API.structure.structure;

import java.util.Collection;

import javax.annotation.Nonnull;

public abstract interface AbstractAliasableBuilder<
	THISTYPE extends AbstractGenericBuilder<THISTYPE>
> {
	public THISTYPE addAlias(@Nonnull String alias);
	public THISTYPE addAliases(@Nonnull String... aliases);
	public THISTYPE addAliases(@Nonnull Collection<String> aliases);
}
