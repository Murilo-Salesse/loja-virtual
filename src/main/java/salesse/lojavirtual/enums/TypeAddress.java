package salesse.lojavirtual.enums;

public enum TypeAddress {

	
	CHARGE("Cobrança"),
	DELIVERY("Entrega");
	
	private String description;

	private TypeAddress(String description) {
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
