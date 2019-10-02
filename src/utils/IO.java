package utils;

import java.util.Date;
import java.util.Scanner;

public class IO {

	private static Scanner scanner = new Scanner(System.in);

	public static int receberEntradaNumero() {

		String input = "";
		input = scanner.nextLine();

		System.out.println(input);
		try {
			int in = Integer.valueOf(input);
			return in;
		} catch (NumberFormatException e) {
			System.out.println("Entrada Inválida! Por favor tente novamente.\n");

			return receberEntradaNumero();
		}

	}

	public static String receberEntradaData() {

		String input = scanner.next() + "";

		try {

			scanner.close();
		} catch (NumberFormatException e) {
			System.out.println("Entrada Inválida! Por favor tente novamente.\n");
			scanner.close();

			return receberEntradaData();
		}

		return "";

	}

}
