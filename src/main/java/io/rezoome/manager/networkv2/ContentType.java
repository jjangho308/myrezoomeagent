package io.rezoome.manager.networkv2;

public enum ContentType {
	APPLICATION_JSON("application/json");

	private final String value;

	private ContentType(String _value) {
		this.value = _value;
	}

	public String getValue() {
		return value;
	}
}
