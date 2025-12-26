package salesse.lojavirtual.enums;

public enum StatusAccountRecieve {

	
	CHARGE("Pagar"),
	EXPIRED("Vencida"),
	OPEN("Aberta"),
	FINISHED("Quitada");
	
	private String description;

	private StatusAccountRecieve(String description) {
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
