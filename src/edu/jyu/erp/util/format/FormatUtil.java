package edu.jyu.erp.util.format;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FormatUtil {
	public static final String formatDate(Long time){
		if(time  == null ) return "";
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		return  df.format(new Date(time)); 
	}
	public static final String formatTime(Long time){
		if(time  == null ) return "";
		DateFormat df = new SimpleDateFormat("HH:mm:ss");
		return  df.format(new Date(time)); 
	}
	public static final String formatDateTime(Long time){
		if(time  == null ) return "";
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return  df.format(new Date(time)); 
	}
	public static final String formatMoney(Double money){
		if(money == null) return "非法格式";
		 DecimalFormat df = new DecimalFormat("#.00");
	     return df.format(money);
	}
	
}
