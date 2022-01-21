package pl.org.mensa.RandomPersson.mc.CommandAPI.v2_0.util;

public abstract class ClassType<T> {
	public enum TypeEnum {
		BYTE,
		SHORT,
		INTEGER,
		LONG,
		FLOAT,
		DOUBLE,
		BOOLEAN,
		STRING;
	}
	
	protected ClassType() {}
	public abstract TypeEnum getType();
	public abstract T parse(java.lang.String string) throws NumberFormatException;
	
	public static final ClassType<java.lang.Byte> BYTE = new Byte();
	public static final ClassType<java.lang.Short> SHORT = new Short();
	public static final ClassType<java.lang.Integer> INTEGER = new Integer();
	public static final ClassType<java.lang.Long> LONG = new Long();
	public static final ClassType<java.lang.Float> FLOAT = new Float();
	public static final ClassType<java.lang.Double> DOUBLE = new Double();
	public static final ClassType<java.lang.Boolean> BOOLEAN = new Boolean();
	public static final ClassType<java.lang.String> STRING = new String();
	
	private static class Byte extends ClassType<java.lang.Byte> {
		public java.lang.Byte parse(java.lang.String string) {
			return java.lang.Byte.parseByte(string);
		}
		public TypeEnum getType() {
			return TypeEnum.BYTE;
		}
	}
	private static class Short extends ClassType<java.lang.Short> {
		public java.lang.Short parse(java.lang.String string) {
			return java.lang.Short.parseShort(string);
		}
		public TypeEnum getType() {
			return TypeEnum.SHORT;
		}
	}
	
	private static class Integer extends ClassType<java.lang.Integer> {
		public java.lang.Integer parse(java.lang.String string) {
			return java.lang.Integer.parseInt(string);
		}
		public TypeEnum getType() {
			return TypeEnum.INTEGER;
		}
	}
	
	private static class Long extends ClassType<java.lang.Long> {
		public java.lang.Long parse(java.lang.String string) {
			return java.lang.Long.parseLong(string);
		}
		public TypeEnum getType() {
			return TypeEnum.LONG;
		}
	}
	
	private static class Float extends ClassType<java.lang.Float> {
		public java.lang.Float parse(java.lang.String string) {
			return java.lang.Float.parseFloat(string);
		}
		public TypeEnum getType() {
			return TypeEnum.FLOAT;
		}
	}
	
	private static class Double extends ClassType<java.lang.Double> {
		public java.lang.Double parse(java.lang.String string) {
			return java.lang.Double.parseDouble(string);
		}
		public TypeEnum getType() {
			return TypeEnum.DOUBLE;
		}
	}
	
	private static class Boolean extends ClassType<java.lang.Boolean> {
		public java.lang.Boolean parse(java.lang.String string) {
			return java.lang.Boolean.parseBoolean(string);
		}
		public TypeEnum getType() {
			return TypeEnum.BOOLEAN;
		}
	}
	
	private static class String extends ClassType<java.lang.String> {
		public java.lang.String parse(java.lang.String string) {
			return string;
		}
		public TypeEnum getType() {
			return TypeEnum.STRING;
		}
	}
}
