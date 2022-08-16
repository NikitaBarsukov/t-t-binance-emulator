package org.dev.barsukov.enums;

public enum MockAvailableEndpoints {
	EXCHANGE_INFO("exchangeInfo"),
	LEVERAGE("leverage"),
	ACCOUNT("account"),
	ASYNC("async"),
	ASYNC_ID("async/id"),
	ACCOUNT_RESTRICTIONS("apiRestrictions");

	private final String path;

	MockAvailableEndpoints(String path) {
		this.path = path;
	}

	public String getPath() {
		return path;
	}
}
