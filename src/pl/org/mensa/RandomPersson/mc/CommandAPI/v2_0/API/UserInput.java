package pl.org.mensa.RandomPersson.mc.CommandAPI.v2_0.API;

import javax.annotation.Nonnull;

import org.bukkit.command.CommandSender;

import pl.org.mensa.RandomPersson.mc.CommandAPI.v2_0.util.ClassType;

public interface UserInput {
	public CommandSender getSender();
	
	public String getParameter(@Nonnull String name);
	public <VARTYPE> VARTYPE getParameter(@Nonnull String name, @Nonnull ClassType<VARTYPE> type);
	public boolean hasFlag(@Nonnull String name);
	public <VARTYPE> VARTYPE getFlag(@Nonnull String name, @Nonnull ClassType<VARTYPE> type);
}
