package entities;

public class Apartamento {

	private int codigo;
	private TipoApartamento categoria;
	private double diaria;
	private int capacidade;
	private boolean ocupado;

	public Apartamento(int codigo, TipoApartamento categoria, double diaria, int capacidade) {
		super();
		this.codigo = codigo;
		this.categoria = categoria;
		this.diaria = diaria;
		this.capacidade = capacidade;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public TipoApartamento getCategoria() {
		return categoria;
	}

	public void setCategoria(TipoApartamento categoria) {
		this.categoria = categoria;
	}

	public double getDiaria() {
		return diaria;
	}

	public void setDiaria(double diaria) {
		this.diaria = diaria;
	}

	public int getCapacidade() {
		return capacidade;
	}

	public void setCapacidade(int capacidade) {
		this.capacidade = capacidade;
	}

	@Override
	public String toString() {

		return "Informações do Quarto:\n" + "Número: " + codigo + "\n" + "Tipo: " + categoria + "\n"
				+ "Valor da Diária: " + diaria + "\n";

	}

	public boolean isOcupado() {
		return ocupado;
	}

	public void setOcupado(boolean ocupado) {
		this.ocupado = ocupado;
	}

}
