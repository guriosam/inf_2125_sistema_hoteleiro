package system;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import entities.Apartamento;
import entities.Cliente;
import entities.Reserva;
import entities.TipoApartamento;
import utils.Util;

public class SistemaHoteleiro {

	private List<Reserva> reservas;
	private List<Apartamento> apartamentos;
	private List<Cliente> clientes;
	private HashMap<String, Double> caixaMensal;
	private Reserva reservaParaRemover;

	public SistemaHoteleiro() {
		this.reservas = new ArrayList<>();
		this.apartamentos = new ArrayList<>();
		this.clientes = new ArrayList<>();
		this.caixaMensal = new HashMap<>();

		preencherReservas();
		preencherApartamentos();
		preencherClientes();
	}

	private void preencherClientes() {
		clientes.add(new Cliente("Reginaldo Farias", 101, "(51) 96186617"));
		clientes.add(new Cliente("Samanta Souza", 102, "(21) 81534599"));
		clientes.add(new Cliente("Augusto Santos", 103, "(81) 92097644"));
		clientes.add(new Cliente("Cl�udio Silva", 104, "(48) 99764831"));
		clientes.add(new Cliente("Mariano Santana", 105, "(11) 94523456"));
	}

	private void preencherReservas() {
		reservas.add(new Reserva(103, 10, Util.somarDatas(Util.dataAtual(), 3), Util.somarDatas(Util.dataAtual(), 7), 2,
				false));
		reservas.add(new Reserva(104, 1, Util.dataAtual(), Util.somarDatas(Util.dataAtual(), 2), 3, true));
		reservas.add(new Reserva(101, 4, Util.somarDatas(Util.dataAtual(), -1), Util.somarDatas(Util.dataAtual(), 3), 4,
				true));

	}

	private void preencherApartamentos() {
		apartamentos.add(new Apartamento(1, TipoApartamento.PADRAO, 268, 2));
		apartamentos.add(new Apartamento(2, TipoApartamento.PADRAO, 268, 2));
		apartamentos.add(new Apartamento(3, TipoApartamento.BOSQUE, 315, 4));
		apartamentos.add(new Apartamento(4, TipoApartamento.BOSQUE, 315, 4));
		apartamentos.add(new Apartamento(5, TipoApartamento.BOSQUE, 315, 4));
		apartamentos.add(new Apartamento(6, TipoApartamento.VALE, 353, 4));
		apartamentos.add(new Apartamento(7, TipoApartamento.VALE, 353, 4));
		apartamentos.add(new Apartamento(8, TipoApartamento.VALE, 353, 4));
		apartamentos.add(new Apartamento(9, TipoApartamento.SUITE, 498, 2));
		apartamentos.add(new Apartamento(10, TipoApartamento.SUITE, 498, 2));
	}

	public void realizarReservaOuCheckIn(int codCliente, int codApart, String dataEntra, String dataSai,
			int numHospedes) {

		Reserva reserva = new Reserva(codCliente, codApart, dataEntra, dataSai, numHospedes, false);

		Cliente cliente = buscarCliente(codCliente);

		if (cliente == null) {
			System.out.println("Cliente n�o cadastrado.");
			return;
		}

		if (possuiReserva(codCliente)) {
			reserva = buscarReserva(codCliente);
			reserva.setFezCheckIn(true);
			System.out.println("Check-In Realizado!");
			return;
		}

		if (estaOcupadoOuReservado(codApart, dataEntra, dataSai)) {
			System.out.println("Desculpe, mas este quarto est� indispon�vel no per�odo requisitado.");
			return;
		}

		if (reservaParaRemover != null) {
			reservas.remove(reservaParaRemover);
		}

		if (Util.diasEntreDatas(dataEntra, dataSai, "/") <= 0) {
			System.out.println("As datas inseridas s�o inv�lidas.");
			return;
		}

		if (!checarCapacidadeQuarto(codApart, numHospedes)) {
			System.out.println("O quarto escolhido n�o comporta a quantidade de hospedes.");
			return;
		}

		cliente.getReservas().add(reserva);
		reservas.add(reserva);

	}

	private boolean checarCapacidadeQuarto(int codApart, int numHospedes) {

		for (Apartamento apt : apartamentos) {
			if (apt.getCodigo() == codApart) {
				if (numHospedes <= apt.getCapacidade() + 1) {
					return true;
				}
			}
		}
		return false;
	}

	public void realizarCheckOut(int codCliente) {

		Cliente cliente = buscarCliente(codCliente);
		if (cliente == null) {
			System.out.println("N�o existe cliente cadastrado com esse c�digo.");
			return;
		}
		Reserva checkout = buscarReserva(codCliente);

		if (checkout == null) {
			System.out.println("N�o existe nenhum cliente com esse c�digo hospedado nesse momento.");
			return;
		}

		int codQuarto = checkout.getCodigoQuarto();
		Apartamento quarto = buscarQuarto(codQuarto);

		if (quarto == null) {
			System.out.println("N�o existe nenhum quarto cadastrado com esse c�digo.");
			return;
		}

		double valorDiaria = quarto.getDiaria();

		int dias = Util.diasEntreDatas(checkout.getDataEntra(), checkout.getDataSai(), "/");
		double valorDevido = dias * valorDiaria;

		String dataAtual = Util.dataAtual();
		String[] data = dataAtual.split("/");

		double caixaDoHotel = caixaMensal.get(data[1]);
		caixaDoHotel += valorDevido;
		caixaMensal.put(data[1], caixaDoHotel);

		System.out.println("Relat�rio de Check-Out\n\n" + cliente.toString() + quarto.toString() + checkout.toString()
				+ "Valor Total da Estadia: " + valorDevido + "\n");

	}

	private Cliente buscarCliente(int codCliente) {
		for (Cliente cliente : clientes) {
			if (cliente.getCodigo() == codCliente) {
				return cliente;
			}
		}
		return null;
	}

	private Apartamento buscarQuarto(int codQuarto) {
		for (Apartamento apt : apartamentos) {
			if (apt.getCodigo() == codQuarto) {
				return apt;
			}
		}

		return null;
	}

	private boolean estaOcupadoOuReservado(int codApart, String dataEntra, String dataSai) {

		reservaParaRemover = null;

		for (Reserva r : reservas) {
			if (r.getCodigoQuarto() == codApart) {

				if (r.isFezCheckIn()) {
					return true;
				}

				if (Util.diasEntreDatas(Util.dataAtual(), r.getDataEntra(), "/") == 0) {
					reservaParaRemover = r;
					return false;
				}

				int diasEntreReservas = Util.diasEntreDatas(dataEntra, r.getDataEntra(), "/");

				int diasNovaReserva = Util.diasEntreDatas(dataEntra, dataSai, "/");

				if (diasEntreReservas <= diasNovaReserva) {
					return true;
				}

			}
		}

		return false;
	}

	private Reserva buscarReserva(int codCliente) {
		for (Reserva r : reservas) {
			if (r.getCodigoCliente() == codCliente) {
				return r;
			}
		}

		return null;
	}

	private boolean possuiReserva(int codCliente) {

		if (buscarReserva(codCliente) == null) {
			return false;
		}

		return true;

	}

	public void relatorioHotel() {

		String relatorio = "Relat�rio do Hotel:\n";
		relatorio += "N�mero de Quartos: " + apartamentos.size() + "\n";
		int ocupados = 0;
		for (Apartamento apt : apartamentos) {
			if (apt.isOcupado()) {
				ocupados++;
			}
		}

		String dataAtual = Util.dataAtual();
		String[] data = dataAtual.split("/");

		double caixaDoHotel = caixaMensal.get(data[1]);

		relatorio += "Quartos Ocupados: " + ocupados + "\n";
		relatorio += "Valor Total Recebido No M�s: " + caixaDoHotel + "\n";

		System.out.println(relatorio);

	}

}
