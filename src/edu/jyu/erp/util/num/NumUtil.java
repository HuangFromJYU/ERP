package edu.jyu.erp.util.num;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 生成订单号的工具类
 * 
 * @author Jason
 *
 */
public class NumUtil {
	public static int serNum = 1;
	public static int len = 5;
	// 48是0的ASCII码
	private static final byte[] zeros = { 48, 48, 48, 48, 48, 48 };

	public static void main(String[] args) {
		System.out.println(generatorOrderNum());
		System.out.println(generatorOrderNum());
		System.out.println(generatorOrderNum());
		System.out.println(generatorOrderNum());
		System.out.println(generatorOrderNum());
	}

	public static final String generatorOrderNum() {
		Date d = new Date();
		DateFormat df = new SimpleDateFormat("yyMMdd");
		// 年与日字符串 如170609 17代表年06代表月09代表日
		String fir = df.format(d);
		// 每次下订单这个数就加1
		int num = serNum++;
		int len2 = (num + "").length();
		// 在num的前面补n个0，n=len - len2
		String sec = new String(zeros, 0, len - len2);
		// fir + sec + num就是年月日+n个0+num的值，最后转成16进制，生成唯一的订单号
		return Long.toHexString(new Long(fir + sec + num)).toUpperCase();
	}
}
