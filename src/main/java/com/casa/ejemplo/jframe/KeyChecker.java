package com.casa.ejemplo.jframe;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

class Keychecker extends KeyAdapter {

	public static String escuchando = "";
	
    @Override
    public void keyPressed(KeyEvent event) {
	    char ch = event.getKeyChar();
	    escuchando += ch;
	    System.out.println(event.getKeyChar());
	    System.out.println(escuchando);
	    
	    if (escuchando.contains("saliendo")) {
	    	Util.callExplorer();
	    	System.exit(1);
	    }
    }

}