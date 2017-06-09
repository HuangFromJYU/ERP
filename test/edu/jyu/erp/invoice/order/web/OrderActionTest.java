package edu.jyu.erp.invoice.order.web;

import java.util.ArrayList;
import java.util.List;

public class OrderActionTest {
	public static void main(String[] args) {
		List<String> al = new ArrayList<String>();
		al.add("1");
		al.add("1");
		al.add("1");
		al.add("3");
		al.add("1");
		al.add("4");
		al.add("1");
		al.add("5");
		
		System.out.println(al);
		for(int i = 0;i<al.size();i++){
			if(al.get(i).equals("1")){
				al.remove(i);
			}
		}
		System.out.println(al);
	}
}
