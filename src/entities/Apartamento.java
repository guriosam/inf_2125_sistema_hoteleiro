package entities;

import java.util.ArrayList;
import java.util.List;

public class Apartamento {

	private int codigo;
	private TipoApartamento categoria;
	private double diaria;
	private int capacidade;
	private int codCliente;
	private boolean hospedeExtra;
	private List<Reserva> historico;

	public Apartamento(int codigo, TipoApartamento categoria, double diaria, int capacidade) {
		super();
		this.codigo = codigo;
		this.categoria = categoria;
		this.diaria = diaria;
		this.capacidade = capacidade;
		this.setHistorico(new ArrayList<>());
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
		if (hospedeExtra) {
			return diaria * 1.3;
		}

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
		
		String consulta = "Informações do Quarto:\n" + "Número: " + codigo + "\n" + "Tipo: " + categoria.getTipo() + "\n"
				+ "Valor da Diária: " + diaria + "\n";

		for(Reserva r : historico){
			consulta += r + "\n";
		}
		
		return consulta;

	}

	public List<Reserva> getHistorico() {
		return historico;
	}

	public void setHistorico(List<Reserva> historico) {
		this.historico = historico;
	}

	public int getCodCliente() {
		return codCliente;
	}

	public void setCodCliente(int codCliente) {
		this.codCliente = codCliente;
	}

	public boolean isHospedeExtra() {
		return hospedeExtra;
	}

	public void setHospedeExtra(boolean hospedeExtra) {
		this.hospedeExtra = hospedeExtra;
	}

}
