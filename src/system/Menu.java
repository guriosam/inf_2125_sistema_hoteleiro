package system;

import java.util.Date;

import entities.Apartamento;
import entities.Cliente;
import entities.Reserva;
import utils.IO;
import utils.DateUtil;

public class Menu {

	SistemaHoteleiro grandeHotel;

	public Menu(SistemaHoteleiro grandeHotel) {
		this.grandeHotel = grandeHotel;
	}
	

	public void mostrarMensagemInicial() {
		System.out.println("Bem vindo ao Grande Hotel Budapeste");
		System.out.println("O que você deseja realizar?\n");
		System.out.println("1 - Realizar Check-In.");
		System.out.println("2 - Realizar Check-Out.");
		System.out.println("3 - Reservar Quarto.");
		System.out.println("4 - Consultar Um Cliente.");
		System.out.println("5 - Consultar Um Quarto.");
		System.out.println("6 - Gerar Relatório do Hotel.");

		int input = IO.receberEntradaNumero();

		selecionarTela(input);
	}

	public void mostrarMensagemCheckIn() {
		System.out.println("Então você quer realizar um Check-In?");
		System.out.println("Vou precisar de algumas informações!");
		System.out.println("Você possui uma reserva?\n1 - Sim.\n2 - Não.\n");

		int reserva = IO.receberEntradaNumero();

		System.out.println("Me informe por favor o código do cliente.\n");

		int codCliente = IO.receberEntradaNumero();

		if (reserva == 1) {

			grandeHotel.realizarReservaOuCheckIn(codCliente, -1, "", "", -1, false);

		} else {

			informacoesAdicionais(codCliente, DateUtil.dataAtual(), false);

		}

		selecionarTela(-1);

	}

	private void informacoesAdicionais(int codCliente, String dataEntrada, boolean b) {
		System.out.println("Agora vou precisar do número do quarto.\n");

		int codQuarto = IO.receberEntradaNumero();

		System.out.println("Por favor me informe qual a data prevista de saída.");

		String dataSaida = IO.receberEntradaData();

		System.out.println("Quantas pessoas irão ficar hospedadas no quarto?");

		int qntHospedes = IO.receberEntradaNumero();

		System.out.println("Só um minuto enquanto verifico a disponibilidade...");

		grandeHotel.realizarReservaOuCheckIn(codCliente, codQuarto, DateUtil.dataAtual(), dataSaida, qntHospedes, b);

	}

	public void mostrarMensagemCheckOut() {
		System.out.println("Para realizar o Check-Out por favor digite o código do Cliente.");
		int input = IO.receberEntradaNumero();
		grandeHotel.realizarCheckOut(input);
	}

	public void mostrarMensagemReserva() {
		System.out.println("Para realizar uma reserva, por favor digite o código do cliente.");
		int codCliente = IO.receberEntradaNumero();

		System.out.println("Por favor me informe qual a data prevista de entrada.");

		String dataEntrada = IO.receberEntradaData();
		
		informacoesAdicionais(codCliente, dataEntrada, true);

	}

	public void mostrarMensagemConsultaCliente() {
		System.out.println("Informe o código do cliente você gostaria de consultar.");
		int codCliente = IO.receberEntradaNumero();

		Cliente c = grandeHotel.buscarCliente(codCliente);
		if (c != null) {
			System.out.println(c.consulta());

			Reserva r = grandeHotel.buscarReservaPorCliente(codCliente);

			if (r != null) {
				System.out.println("Reservas: ");
				System.out.println(r.toString());
			}

		}

	}

	public void mostrarMensagemConsultaQuarto() {
		System.out.println("Informe o código do quarto você gostaria de consultar.");
		int codQuarto = IO.receberEntradaNumero();

		Apartamento apt = grandeHotel.buscarQuarto(codQuarto, 0);
		if(apt == null){
			System.out.println("Não existe código com esse quarto.");
			return;
		}
		System.out.println(apt);
		
		if(apt.getCodCliente() == 0){
			System.out.println("O quarto está livre.");
		} else {
			System.out.println("O quarto está em uso nesse momento.");
		}
		
		Reserva r = grandeHotel.buscarReservaPorQuarto(codQuarto);
		if(r != null){
			System.out.println("Reservas: ");
			System.out.println(r);
		}
		
		
	}

	private void selecionarTela(int input) {

		switch (input) {
		case 1:
			mostrarMensagemCheckIn();
			break;
		case 2:
			mostrarMensagemCheckOut();
			break;
		case 3:
			mostrarMensagemReserva();
			break;
		case 4:
			mostrarMensagemConsultaCliente();
			break;
		case 5:
			mostrarMensagemConsultaQuarto();
			break;
		case 6:
			grandeHotel.relatorioHotel();
			break;
		default:

		}

		mostrarMensagemInicial();

	}

}
