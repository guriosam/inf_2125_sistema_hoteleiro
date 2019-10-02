package entities;

public enum TipoApartamento {
	
	
	PADRAO("Standard"),
	BOSQUE("Apartamento Vista Bosque"),
	VALE("Apartamento Vista Vale"),
	SUITE("Suíte");
	
	private String tipo;
	
	TipoApartamento(String tipo){
		this.tipo = tipo;
	}

	public String getTipo() {
		return tipo;
	}

	
}
