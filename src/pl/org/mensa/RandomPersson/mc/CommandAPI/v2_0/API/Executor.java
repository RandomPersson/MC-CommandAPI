package pl.org.mensa.RandomPersson.mc.CommandAPI.v2_0.API;

import javax.annotation.Nonnull;

@FunctionalInterface
public interface Executor {
	public void execute(@Nonnull UserInput user_input);
}
