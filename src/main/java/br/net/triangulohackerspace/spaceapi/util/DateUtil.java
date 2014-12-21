package br.net.triangulohackerspace.spaceapi.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

	public static String getNowDate() {
		String expectedPattern = "dd/MM/yyyy HH:mm:ss";
		DateFormat df = new SimpleDateFormat(expectedPattern);
		return df.format(new Date());
	}

}
