package pl.org.mensa.RandomPersson.mc.CommandAPI.v2_0.util;

import java.util.List;

public class ImmutableFastQueue<T> {
	private T elements[];
	private int size = 0;
	private int current_index;
	
	public static <T> ImmutableFastQueue<T> from(List<T> list) {
		return new ImmutableFastQueue<T>(list);
	}
	
	@SuppressWarnings("unchecked")
	private ImmutableFastQueue(List<T> elements) {
		this.elements = (T[]) elements.toArray();
		this.size = elements.size();
		this.current_index = 0;
	}
	
	public int size() {
		return this.size;
	}
	
	public T pop() throws IndexOutOfBoundsException {
		return this.elements[current_index++];
	}
}
