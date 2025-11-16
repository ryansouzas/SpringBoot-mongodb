package com.example.projectSM.resources.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class URL {

	public static String decodeParam(String text) {
		try {
			return URLDecoder.decode(text, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			return "";
		}
	}

	public static Instant convertDate(String textDate, Instant defaultValue ) {
		try {
			DateTimeFormatter dtf = DateTimeFormatter.ISO_DATE;
			return LocalDate.parse(textDate, dtf)
					.atStartOfDay(ZoneId.of("GMT"))
					.toInstant(); 
		}catch(Exception e){
			return defaultValue;
		}
		
	}
	
	public static Instant toInstant(String date) {
	    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	    return LocalDate.parse(date, dtf)
	            .atStartOfDay(ZoneId.of("GMT"))
	            .toInstant();
	}

}
