package utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class Util {

	public static boolean verificarDatas(String date1, String date2, String splitter) {

		if (date1 == null || date2 == null) {
			return false;
		}

		String[] d1 = date1.split(splitter);

		Integer year1 = Integer.parseInt(d1[0]);

		Integer m1 = Integer.parseInt(d1[1]);

		Integer day1 = Integer.parseInt(d1[2]);

		Integer hour1 = Integer.parseInt(d1[3]);

		Integer minute1 = Integer.parseInt(d1[4]);

		Integer second1 = Integer.parseInt(d1[5]);

		String[] d2 = date2.split(splitter);

		Integer year2 = Integer.parseInt(d2[0]);

		Integer m2 = Integer.parseInt(d2[1]);

		Integer day2 = Integer.parseInt(d2[2]);

		Integer hour2 = Integer.parseInt(d2[3]);

		Integer minute2 = Integer.parseInt(d2[4]);

		Integer second2 = Integer.parseInt(d2[5]);

		if (year1.compareTo(year2) > 0) {
			return false;
		} else if (year1.compareTo(year2) == 0) {
			if (m1.compareTo(m2) > 0) {
				return false;
			} else if (m1.compareTo(m2) == 0) {
				if (day1.compareTo(day2) > 0) {
					return false;
				} else if (day1.compareTo(day2) == 0) {
					if (hour1.compareTo(hour2) > 0) {
						return false;
					} else if (hour1.compareTo(hour2) == 0) {
						if (minute1.compareTo(minute2) > 0) {
							return false;
						} else if (minute1.compareTo(minute2) == 0) {
							if (second1.compareTo(second2) > 0) {
								return false;
							}
						}
					}
				}
			}
		}

		return true;

	}

	public static int diasEntreDatas(String data1, String data2, String divisor) {

		long diff = 0;

		if (verificarDatas(data1, data2, divisor)) {

			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

			try {
				Date firstDate = sdf.parse(data1);
				Date secondDate = sdf.parse(data2);
				long diffInMillies = Math.abs(secondDate.getTime() - firstDate.getTime());
				diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);

				System.out.println(diff);
			} catch (ParseException e) {
				e.printStackTrace();
			}

			return (int) diff;
		}

		return (int) diff;
	}

	public static String dataAtual() {

		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date date = new Date();

		return dateFormat.format(date) + "";

	}

	public static String somarDatas(String data1, int dias) {

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Calendar c = Calendar.getInstance();
		try {
			c.setTime(sdf.parse(data1));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		// Incrementing the date by 1 day
		c.add(Calendar.DAY_OF_MONTH, dias);
		String novaData = sdf.format(c.getTime());

		return novaData;
	}
}
