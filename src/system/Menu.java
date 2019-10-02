package system;

import java.util.Date;

import utils.IO;
import utils.Util;

public class Menu {

	SistemaHoteleiro grandeHotel;

	public Menu(SistemaHoteleiro grandeHotel) {
		this.grandeHotel = grandeHotel;
	}

	public void mostrarMensagemInicial() {
		System.out.println("Bem vindo ao Grande Hotel Budapeste");
		System.out.println("O que voc� deseja realizar?\n");
		System.out.println("1 - Realizar Check-In.");
		System.out.println("2 - Realizar Check-Out.");
		System.out.println("3 - Reservar Quarto.");
		System.out.println("4 - Consultar Um Cliente.");
		System.out.println("5 - Consultar Um Quarto.");
		System.out.println("6 - Gerar Relat�rio do Hotel.");

		int input = IO.receberEntradaNumero();

		selecionarTela(input);
	}

	public void mostrarMensagemCheckIn() {
		System.out.println("Ent�o voc� quer realizar um Check-In?");
		System.out.println("Vou precisar de algumas informa��es!");
		System.out.println("Voc� possui uma reserva?\n1 - Sim.\n2 - N�o.\n");

		int reserva = IO.receberEntradaNumero();

		System.out.println("Me informe por favor o c�digo do cliente.\n");

		int codCliente = IO.receberEntradaNumero();

		if (reserva == 1) {

			grandeHotel.realizarReservaOuCheckIn(codCliente, -1, "", "", -1);

		} else {

			System.out.println("Agora vou precisar do n�mero do quarto.\n");

			int codQuarto = IO.receberEntradaNumero();

			System.out.println("Por favor me informe qual a data prevista de sa�da.");

			String dataSaida = IO.receberEntradaData();

			System.out.println("Quantas pessoas ir�o ficar hospedadas no quarto?");

			int qntHospedes = IO.receberEntradaNumero();

			System.out.println("S� um minuto enquanto verifico a disponibilidade...");

			grandeHotel.realizarReservaOuCheckIn(codCliente, codQuarto, Util.dataAtual(), dataSaida, qntHospedes);

		}

		selecionarTela(1);

	}

	public void mostrarMensagemCheckOut() {
		
	}

	public void mostrarMensagemReserva() {
		// TODO Auto-generated method stub

	}

	public void mostrarMensagemConsultaCliente() {
		// TODO Auto-generated method stub

	}

	public void mostrarMensagemConsultaQuarto() {
		// TODO Auto-generated method stub

	}

	public void gerarRelatorio() {
		// TODO Auto-generated method stub

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
			gerarRelatorio();
			break;
		default:
			System.out.println("Op��o Inv�lida! Por favor, tente novamente.");
			mostrarMensagemInicial();

		}

	}

}
