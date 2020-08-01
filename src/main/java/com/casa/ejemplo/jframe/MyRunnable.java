package com.casa.ejemplo.jframe;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.github.sarxos.webcam.Webcam;

public class MyRunnable implements Runnable {

    private int var;

    public MyRunnable(int var) {
        this.var = var;
    }

    public void run() {
		Webcam webcam = Webcam.getDefault();
		try {
			webcam.open();
			ImageIO.write(webcam.getImage(), "PNG", new File("capture" + App.i + ".png"));
			App.i++;
			System.out.println("Se ha tomado la foto");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			webcam.close();
		}
    }
}