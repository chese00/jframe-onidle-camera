package com.casa.ejemplo.jframe;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * Hello world!
 *
 */
public class App 
{
	
	public static JFrame frame;
	private static String PATH1 = "D:/borrar/unoUno.png";
	public static final String EXPLORER_PROCESO = "explorer.exe";
	public static int i = 0;
	private static  boolean cerrarExplorer = false;
	
    public static void main( String[] args )
    { 
    	
    	try {
    		PATH1 = args[0].trim();
    		Util.PATH2 = args[1].trim();
    		Util.SONIDO = args[2].trim();
    		MiMouseListener.LIMITE = Integer.parseInt(args[3].trim());
    		Util.TIEMPO_PARA_CERRAR = Integer.parseInt(args[4].trim());
    		cerrarExplorer = Boolean.parseBoolean(args[5].trim());
    		
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	
    	if (cerrarExplorer) {
	    	try {
				if (Util.isProcessRunning(EXPLORER_PROCESO)) {
					  Util.killProcess(EXPLORER_PROCESO);
				}
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    	
    	frame = new JFrame();
        
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.add(new JLabel(new ImageIcon(PATH1)));
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.addKeyListener(new Keychecker());
        

        
        frame.addMouseListener(new MiMouseListener(frame)); 
        
        frame.setUndecorated(true);
        //frame.setAlwaysOnTop (true);
        frame.setVisible(true);
        
        AltTabStopper ats = new AltTabStopper(frame);
        ats.run();
    	
    }
}
