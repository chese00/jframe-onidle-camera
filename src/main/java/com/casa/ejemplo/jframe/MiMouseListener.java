package com.casa.ejemplo.jframe;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;

public class MiMouseListener implements MouseListener {

	private JFrame frame;
	
	private static int veces = 0;
	public static int LIMITE = 3;
	
	public MiMouseListener(JFrame frame) {
		this.frame = frame;
	}
	
	
	
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		veces ++;
		if (veces == LIMITE) {
			Util.sonar(frame);
			veces = 0;
		}
		
	}	
	
}
