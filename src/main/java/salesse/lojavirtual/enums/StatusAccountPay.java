package salesse.lojavirtual.enums;

public enum StatusAccountPay {

	CHARGE("Pagar"), EXPIRED("Vencida"), OPEN("Aberta"), FINISHED("Quitada"), NEGOTIATED("Renegociada");

	private String description;

	private StatusAccountPay(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	@Override
	public String toString() {
		return this.description;
	}
}
