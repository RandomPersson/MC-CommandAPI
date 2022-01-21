package pl.org.mensa.RandomPersson.mc.CommandAPI.v2_0.util;

public class SimpleRepeatableTreeMap<K, V> {
	
	
	
	@SuppressWarnings("hiding")
	public class Node<K, V, SUPTYPE extends Node<?, ?, ?>> {
		private K key;
		private V value;
		private SUPTYPE supnode;
		
		public Node(K key, V value, SUPTYPE supnode) {
			this.key = key;
			this.value = value;
			this.supnode = supnode;
		}
		
		public K getKey() {
			return key;
		}
		public V getValue() {
			return value;
		}
		
//		public <K, V> Node<K, V, Node<K, V, SUPTYPE>> addNode(K key, V value) {
//			return new Node<K, V, Node<K, V, SUPTYPE>>(key, value, this);
//		}
		
		public SUPTYPE back() {
			return supnode;
		}
	}
}
