package com.ebay.raptor.perf;

public class Util {

	public static int convertToMillis( String time){
		int millis=100;
		try{
			millis = Integer.parseInt(time);
		}catch( NumberFormatException nfe){
			System.out.println(""+ nfe.getMessage()  );
			nfe.printStackTrace();
		}
		return millis;
	}
}
