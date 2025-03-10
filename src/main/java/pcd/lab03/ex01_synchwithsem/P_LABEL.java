package pcd.lab03.ex01_synchwithsem;

public enum P_LABEL {
	PING(1, "ping"),
	PONG(2, "pong");

	private final int code;
	private final String name;

	P_LABEL(int code, String name) {
		this.code = code;
		this.name = name;
	}

	public int getCode() {
		return code;
	}

	public String getName() {
		return name;
	}
}
