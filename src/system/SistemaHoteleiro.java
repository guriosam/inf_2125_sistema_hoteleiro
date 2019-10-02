package system;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import entities.Apartamento;
import entities.Cliente;
import entities.Reserva;
import entities.TipoApartamento;
import utils.DateUtil;

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

		preencherClientes();
		preencherApartamentos();
		preencherReservas();
	}

	private void preencherClientes() {
		clientes.add(new Cliente("Reginaldo Farias", 101, "(51) 96186617"));
		clientes.add(new Cliente("Samanta Souza", 102, "(21) 81534599"));
		clientes.add(new Cliente("Augusto Santos", 103, "(81) 92097644"));
		clientes.add(new Cliente("Cláudio Silva", 104, "(48) 99764831"));
		clientes.add(new Cliente("Mariano Santana", 105, "(11) 94523456"));
	}

	private void preencherReservas() {
		Reserva r = new Reserva(103, 10, DateUtil.somarDatas(DateUtil.dataAtual(), 3), DateUtil.somarDatas(DateUtil.dataAtual(), 7), 2,
				false);
		reservas.add(r);
		
		r = new Reserva(104, 1, DateUtil.dataAtual(), DateUtil.somarDatas(DateUtil.dataAtual(), 2), 3, true);
		reservas.add(r);
		Apartamento apt = buscarQuarto(1, 0);
		if (apt != null) {
			apt.setCodCliente(104);
			apt.setHospedeExtra(true);
		}
		r = new Reserva(101, 4, DateUtil.somarDatas(DateUtil.dataAtual(), -1), DateUtil.somarDatas(DateUtil.dataAtual(), 3), 4, true);
		reservas.add(r);

		apt = buscarQuarto(4, 0);
		if (apt != null) {
			apt.setCodCliente(101);
		}

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
			int numHospedes, boolean ehReserva) {

		Reserva reserva = new Reserva(codCliente, codApart, dataEntra, dataSai, numHospedes, ehReserva);

		Cliente cliente = buscarCliente(codCliente);

		if (cliente == null) {
			System.out.println("Cliente não cadastrado.");
			return;
		}

		if (possuiReserva(codCliente)) {
			reserva = buscarReservaPorCliente(codCliente);
			reserva.setFezCheckIn(true);
			System.out.println("Check-In Realizado!");
			return;
		}

		if (estaOcupadoOuReservado(codApart, dataEntra, dataSai)) {
			System.out.println("Desculpe, mas este quarto está indisponível no período requisitado.");
			return;
		}

		if (reservaParaRemover != null) {
			reservas.remove(reservaParaRemover);
		}

		if (DateUtil.diasEntreDatas(dataEntra, dataSai, "/") <= 0) {
			System.out.println("As datas inseridas são inválidas.");
			return;
		}

		if (!checarCapacidadeQuarto(codApart, numHospedes)) {
			System.out.println("O quarto escolhido não comporta a quantidade de hospedes.");
			return;
		}

		if (ehReserva) {
			System.out.println("Reserva efetuada com sucesso.");
		} else {
			reserva.setFezCheckIn(true);
			System.out.println("Check-In efetuado com sucesso.");
		}

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
			System.out.println("Não existe cliente cadastrado com esse código.");
			return;
		}
		Reserva checkout = buscarReservaPorCliente(codCliente);

		if (checkout == null) {
			System.out.println("Não existe nenhum cliente com esse código hospedado nesse momento.");
			return;
		}

		int codQuarto = checkout.getCodigoQuarto();
		Apartamento quarto = buscarQuarto(codQuarto, codCliente);

		if (quarto == null) {
			System.out.println("Não existe nenhum quarto alocado para esse código de cliente.");
			return;
		}

		double valorDiaria = quarto.getDiaria();

		int dias = DateUtil.diasEntreDatas(checkout.getDataEntra(), checkout.getDataSai(), "/");

		double valorDevido = dias * valorDiaria;

		String dataAtual = DateUtil.dataAtual();
		String[] data = dataAtual.split("/");

		double caixaDoHotel = caixaMensal.get(data[1]);
		caixaDoHotel += valorDevido;
		caixaMensal.put(data[1], caixaDoHotel);

		System.out.println("Relatório de Check-Out\n\n" + cliente.toString() + quarto.toString() + checkout.toString()
				+ "Valor Total da Estadia: " + valorDevido + "\n");

		quarto.getHistorico().add(checkout);
		cliente.getHistorico().add(checkout);
		reservas.remove(checkout);

	}

	public Cliente buscarCliente(int codCliente) {
		for (Cliente cliente : clientes) {
			if (cliente.getCodigo() == codCliente) {
				return cliente;
			}
		}
		return null;
	}

	public Apartamento buscarQuarto(int codQuarto, int codCliente) {
		for (Apartamento apt : apartamentos) {
			if (apt.getCodigo() == codQuarto) {
				if (codCliente == 0) {
					return apt;
				}

				if (codCliente == apt.getCodCliente()) {
					return apt;
				}
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

				if (DateUtil.diasEntreDatas(DateUtil.dataAtual(), r.getDataEntra(), "/") == 0) {
					reservaParaRemover = r;
					return false;
				}

				int diasEntreReservas = DateUtil.diasEntreDatas(dataEntra, r.getDataEntra(), "/");

				int diasNovaReserva = DateUtil.diasEntreDatas(dataEntra, dataSai, "/");

				if (diasEntreReservas <= diasNovaReserva) {
					return true;
				}

			}
		}

		return false;
	}

	public Reserva buscarReservaPorCliente(int codCliente) {
		for (Reserva r : reservas) {
			if (r.getCodigoCliente() == codCliente) {
				return r;
			}
		}

		return null;
	}
	
	public Reserva buscarReservaPorQuarto(int codQuarto) {
		for (Reserva r : reservas) {
			if (r.getCodigoQuarto() == codQuarto) {
				return r;
			}
		}

		return null;
	}

	private boolean possuiReserva(int codCliente) {

		if (buscarReservaPorCliente(codCliente) == null) {
			return false;
		}

		return true;

	}

	public void relatorioHotel() {

		String relatorio = "Relatório do Hotel:\n";
		relatorio += "Número de Quartos: " + apartamentos.size() + "\n";
		int ocupados = 0;
		for (Apartamento apt : apartamentos) {
			if (apt.getCodCliente() != 0) {
				ocupados++;
			}
		}

		String dataAtual = DateUtil.dataAtual();
		String[] data = dataAtual.split("/");

		double caixaDoHotel = caixaMensal.getOrDefault((data[1]), 0.0);

		relatorio += "Quartos Ocupados: " + ocupados + "\n";
		relatorio += "Valor Total Recebido No Mês: " + caixaDoHotel + "\n";

		System.out.println(relatorio);

	}

}
