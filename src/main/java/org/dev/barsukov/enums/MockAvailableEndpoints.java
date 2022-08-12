package org.dev.barsukov.enums;

public enum MockAvailableEndpoints {
	EXCHANGE_INFO("exchangeInfo"),
	LEVERAGE("leverage");

	private final String path;

	MockAvailableEndpoints(String path) {
		this.path = path;
	}

	public String getPath() {
		return path;
	}
}
