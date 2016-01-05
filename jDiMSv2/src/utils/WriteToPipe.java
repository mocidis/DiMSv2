package utils;

import gui.FrameNotification;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class WriteToPipe {
	private static String coreExternalPipe = ParseConfiguration.getInstance().getExternalPipe();
	
	public WriteToPipe(String content) {
		// if file doesnt exists, then create it
		try {
			File file = new File(coreExternalPipe);
			if (!file.exists()) {
				file.createNewFile();
			}
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(content);
			bw.close();
		} catch (IOException ie) {
			new FrameNotification(ie.getMessage());
		}
	}
}
