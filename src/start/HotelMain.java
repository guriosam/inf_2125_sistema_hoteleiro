package start;

import java.util.Scanner;

import system.Menu;
import system.SistemaHoteleiro;
import utils.Util;

public class HotelMain {

	public static void main(String[] args) {

		SistemaHoteleiro grandeHotel = new SistemaHoteleiro();

		Menu menu = new Menu(grandeHotel);
		menu.mostrarMensagemInicial();
		

	}

}
