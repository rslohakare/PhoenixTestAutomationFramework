package com.api.constant;

public enum ServiceLocation {

	SERVICELOCATION_A(1), SERVICELOCATION_B(2), SERVICELOCATION_C(3);

	int code;

	private ServiceLocation(int code) {
		this.code = code;

	}

	public int getCode() {
		return code;
	}
}
