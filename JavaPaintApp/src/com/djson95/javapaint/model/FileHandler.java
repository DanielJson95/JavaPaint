package com.djson95.javapaint.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * <p>
 *     Den här klassen ansvarar för filhanteringen i applikationen.<br/>
 *     Den kan spara och ladda ritningar till fil.
 * </p>
 */
public class FileHandler {
	
	private static FileHandler instance;
	
	private FileHandler() {}
	
	public static FileHandler getInstance() {
		if (instance == null) {
			instance = new FileHandler();
		}
		return instance;
	}
	
	public void saveDrawing(Drawing drawing, File destination) throws IOException {
		try (FileOutputStream fos = new FileOutputStream(destination);
				ObjectOutputStream oos = new ObjectOutputStream(fos)) {
			oos.writeObject(drawing);
		}
	}
	
	public Drawing loadDrawing(File targetFile) throws IOException, ClassNotFoundException {
		try (FileInputStream fis = new FileInputStream(targetFile);
				ObjectInputStream ois = new ObjectInputStream(fis)) {
            return (Drawing) ois.readObject();
		}
	}
}
