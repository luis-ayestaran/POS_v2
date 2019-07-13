package com.masterdev.student.utils.printer;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.Vector;

public class printer {
	
	private Config printer = new Config();
	private GregorianCalendar gg = new GregorianCalendar();
	private SimpleDateFormat dd = new SimpleDateFormat("dd/MM/YYYY");
	private SimpleDateFormat ddd = new SimpleDateFormat("HH:mm");
 
	public printer() {}
	
	public void print(Vector<Vector<String>> productos, String subtotal, String descuento, String total, String pago, String cambio) {
		String Header =
	             "         .:: SHOP's NAME ::.      \n;"
				+ "\n;"
	            +" * * * *  Ticket de Compra  * * * *;"
	            + "Fecha:"+dd.format(gg.getTime())+"	 	 	 	 Hora:"+ddd.format(gg.getTime())+"\n;"
	            + "\n;"
	            + "* * * * * * * * * * * * * * * * * * * * * *\n;"
	            + "\n;"
	            + "     LISTA DE PRODUCTOS      \n;"
	            + "- - - - - - - - - - - - - - - - - - - - - - -\n;"
	            + "Descripción del producto  \n;"
	            + "Artic.    C.Unit.    Pzas.    Costo\n;"
	            + "- - - - - - - - - - - - - - - - - - - - - - -\n;";
		String a = "";
				for(int i=0;i<productos.size();i++) {
					for(int j=0;j<productos.get(i).size(); j++) {
						if(j == 1 || j == productos.get(i).size() - 1)
							a = a + "$";
						a = a + productos.get(i).get(j);
						if(j != productos.get(i).size() - 3)
							a = a + "   ";
						else
							a = a + "";
					}
					a = a+"\n;";
				}
		String h = Header+a;
		String amt  =
				"- - - - - - - - - - - - - - - - - - - - - - -\n;"
	             + "Subtotal:                $"+subtotal+"\n;"
	             + "Descuento:             $"+descuento+"\n;"
	             + "Total:                    $"+total+"\n;"
	             + "Efectivo:               $"+pago+"\n;"
	             + "Cambio:                 $"+cambio+"\n;"
	             + "\n;"
	             + "* * * * * * * * * * * * * * * * * * * * * *\n;"
	             + "\n;"
	             + "Cambios y devoluciones, dentro\n;"
	             + "de los 4 días posteriores y antes\n;"
	             + "    de que concluya el mes\n;"
	             + "\n;"
	             + "* * * * * * * * * * * * * * * * * * * * * *\n;"
	             + "\n;"
	             + "    ¡Gracias por su compra!   \n;"
	             + "_________________________\n;";
		String bill = h+amt;
		printer.printcard(bill);
		System.out.println(bill);
		
	}
}
