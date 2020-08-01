package com.casa.ejemplo.jframe;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import com.github.sarxos.webcam.Webcam;

public class Util {

	public static String SONIDO = "D:/borrar/Link_Contact1.wav";
	public static String PATH2 = "D:/borrar/Untitled.png";
	public static int TIEMPO_PARA_CERRAR = 2;
	private static final String TASKLIST = "tasklist";
	private static final String KILL = "taskkill /F /IM ";

	private static JFrame frameNuevo = new JFrame();

	
	public static void sonar(JFrame caller) {
		//System.out.println("Aquí un sonido");
		frameNuevo = new JFrame();
		frameNuevo.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frameNuevo.add(new JLabel(new ImageIcon(PATH2)));
		playSound(new File(SONIDO));
		frameNuevo.setUndecorated(true);

		// Must schedule the close before the dialog becomes visible
		ScheduledExecutorService s = Executors.newSingleThreadScheduledExecutor();
		s.schedule(new Runnable() {
			public void run() {

				frameNuevo.dispose();
			}
		}, TIEMPO_PARA_CERRAR, TimeUnit.SECONDS);

		frameNuevo.setVisible(true);
		frameNuevo.setAlwaysOnTop(true);
		System.out.println("<<");
		System.out.println("Se lanzó la ventana. Sonido.");
		System.out.println(">>");
		
		MyRunnable myrunnable = new MyRunnable(1);
		Thread t = new Thread(myrunnable);
		t.start();
		

	}

	private static void playSound(File f) {
		Runnable r = new Runnable() {
			private File f;

			public void run() {
				playSoundInternal(this.f);
			}

			public Runnable setFile(File f) {
				this.f = f;
				return this;
			}
		}.setFile(f);

		new Thread(r).start();
	}

	private static void playSoundInternal(File f) {
		try {
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(f);
			try {
				Clip clip = AudioSystem.getClip();
				clip.open(audioInputStream);
				try {
					clip.start();
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					clip.drain();
				} finally {
					clip.close();
				}
			} catch (LineUnavailableException e) {
				e.printStackTrace();
			} finally {
				audioInputStream.close();
			}
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static boolean isProcessRunning(String serviceName) throws Exception {

		Process p = Runtime.getRuntime().exec(TASKLIST);
		BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
		String line;
		while ((line = reader.readLine()) != null) {

			//System.out.println(line);
			if (line.contains(serviceName)) {
				return true;
			}
		}
		return false;
	}

	public static void killProcess(String serviceName) throws Exception {
		Runtime.getRuntime().exec(KILL + serviceName);
	}

	public static void callExplorer() {
		try {
			Process p = Runtime.getRuntime().exec(App.EXPLORER_PROCESO);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
