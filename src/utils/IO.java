package utils;

import java.util.Date;
import java.util.Scanner;

public class IO {

	private static Scanner scanInt = new Scanner(System.in);
	private static Scanner scanData = new Scanner(System.in);

	public static int receberEntradaNumero() {

		String input = "";
		input = scanInt.nextLine();

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

		String input = scanData.nextLine();

		try {
			
			if (input.matches("\\d{2}/\\d{2}/\\d{4}")) {
				System.out.println(input);
				return input;
			}
			
		} catch (NumberFormatException e) {
			System.out.println("Entrada Inválida! Por favor tente novamente.\n");

			
		}

		return receberEntradaData();

	}

}
