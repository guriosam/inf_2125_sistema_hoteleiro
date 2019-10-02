package entities;

import java.util.ArrayList;
import java.util.List;

public class Cliente {

	private String nome;
	private int codigo;
	private String telefone;
	private List<Reserva> historico;

	public Cliente(String nome, int codigo, String telefone) {
		this.nome = nome;
		this.codigo = codigo;
		this.telefone = telefone;
		this.historico = new ArrayList<>();
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public List<Reserva> getHistorico() {
		return historico;
	}

	@Override
	public String toString() {
		return "Nome: " + nome + "\n" + "Código: " + codigo + "\n";
	}

	public String consulta() {
		String resultado = toString() + "Telefone: " + telefone + "\n";

		//resultado += "Reservas:\n";
		//for (Reserva r : reservas) {
		//	if (!r.isFezCheckIn()) {
		//		resultado += r.toString();
		//	}
		//}

		resultado += "Histórico de Ocupações:\n";
		for (Reserva r : historico) {
			if (r.isFezCheckIn()) {
				resultado += r.toString();
			}
		}

		return resultado;

	}

}
