package start;

import system.Menu;
import system.SistemaHoteleiro;

public class HotelMain {

	public static void main(String[] args) {

		SistemaHoteleiro grandeHotel = new SistemaHoteleiro();

		Menu menu = new Menu(grandeHotel);
		menu.mostrarMensagemInicial();

	}

}
