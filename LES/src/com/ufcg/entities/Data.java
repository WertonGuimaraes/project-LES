package com.ufcg.entities;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import android.util.Log;

public class Data {

	private Calendar data;

	public Data() {
		Calendar calendar = new GregorianCalendar();
		this.data = new Data(calendar.get(Calendar.DAY_OF_MONTH),
										  calendar.get(Calendar.MONTH),
										  calendar.get(Calendar.YEAR)).getData();
	}

	public Data(int dia, int mes, int ano) {
		this.data = new GregorianCalendar(ano, mes, dia);
	}
	
	public static long convertDateToMilissegundos(Calendar data){
		return data.getTimeInMillis();
	}
	
	public long convertDateToMilissegundos(){
		return data.getTimeInMillis();
	}
	
	public Calendar getData() {
		return data;
	}
	
	private static Calendar somarData(int dias, Calendar data) {
		Calendar dataAux = new GregorianCalendar(data.get(Calendar.YEAR),
						data.get(Calendar.MONTH),data.get(Calendar.DAY_OF_MONTH));
		
		Calendar cal = Calendar.getInstance();  
		cal.setTime(dataAux.getTime());  
		cal.add(Calendar.DATE, dias);  
		return cal;
	}
	
	public static boolean isSemana(Date date){
		Data dataAtual = new Data();
		long estremo1 = convertDateToMilissegundos(somarData(-6, dataAtual.getData()));
		long estremo2 = convertDateToMilissegundos(somarData(1, dataAtual.getData()));
		long dataParaComparacao = date.getTime();
		
		return estremo1 <= dataParaComparacao &&  dataParaComparacao< estremo2;
	}
	
	public static boolean is2SemanasPassada(Date date){
		Data dataAtual = new Data();
		long estremo1 = convertDateToMilissegundos(somarData(-20, dataAtual.getData()));
		long estremo2 = convertDateToMilissegundos(somarData(-6, dataAtual.getData()));
		long dataParaComparacao = date.getTime();
		
		return estremo1 <= dataParaComparacao &&  dataParaComparacao< estremo2;
	}
}
