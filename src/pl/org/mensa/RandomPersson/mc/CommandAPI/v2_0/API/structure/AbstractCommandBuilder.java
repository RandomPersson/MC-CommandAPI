package pl.org.mensa.RandomPersson.mc.CommandAPI.v2_0.API.structure;

import org.bukkit.plugin.Plugin;

import pl.org.mensa.RandomPersson.mc.CommandAPI.v2_0.API.structure.structure.AbstractAliasableBuilder;
import pl.org.mensa.RandomPersson.mc.CommandAPI.v2_0.API.structure.structure.AbstractArgumentableBuilder;
import pl.org.mensa.RandomPersson.mc.CommandAPI.v2_0.API.structure.structure.AbstractExecutableBuilder;
import pl.org.mensa.RandomPersson.mc.CommandAPI.v2_0.API.structure.structure.AbstractFlaggableBuilder;
import pl.org.mensa.RandomPersson.mc.CommandAPI.v2_0.API.structure.structure.AbstractGenericBuilder;

public abstract interface AbstractCommandBuilder<
	THISTYPE extends AbstractCommandBuilder<THISTYPE>
>
extends
	AbstractGenericBuilder<THISTYPE>,
	AbstractAliasableBuilder<THISTYPE>,
	AbstractArgumentableBuilder<THISTYPE>,
	AbstractFlaggableBuilder<THISTYPE>,
	AbstractExecutableBuilder<THISTYPE>
{
	public void register(Plugin plugin); //TODO [7] register(plugin) or register() ?
}
