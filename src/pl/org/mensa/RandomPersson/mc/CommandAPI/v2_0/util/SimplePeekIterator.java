package pl.org.mensa.RandomPersson.mc.CommandAPI.v2_0.util;

import java.util.Iterator;

import javax.annotation.Nonnull;

public class SimplePeekIterator<TYPE> implements Iterator<TYPE> {
	private final Iterator<TYPE> iterator;
	private boolean peeked;
	private TYPE cached_peek;
	
	public SimplePeekIterator(@Nonnull Iterator<TYPE> iterator) {
		this.iterator = iterator;
		this.peeked = false;
	}
	
	@Override
	public boolean hasNext() {
		return (this.peeked || this.iterator.hasNext());
	}
	
	@Override
	public TYPE next() {
		if (this.peeked) {
			this.peeked = false;
			return this.cached_peek;
		}
		
		return this.iterator.next();
	}
	
	public TYPE peek() {
		if (peeked) {
			return this.cached_peek;
		}
		
		this.peeked = true;
		return (this.cached_peek = this.iterator.next());
	}
}
