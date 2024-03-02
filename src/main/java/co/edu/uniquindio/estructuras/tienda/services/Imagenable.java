package co.edu.uniquindio.estructuras.tienda.services;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;

public interface Imagenable {
	public static byte[] getImageBytes(Image image) throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", baos);
		return baos.toByteArray();
	}

	public default Image getImage() {
		ByteArrayInputStream bais = new ByteArrayInputStream(getImgBytes());
		return new Image(bais);
	}

	public default Image getImage(double requestedWidth, double requestedHeight, boolean preserveRatio,
			boolean smooth) {
		ByteArrayInputStream bais = new ByteArrayInputStream(getImgBytes());
		return new Image(bais, requestedWidth, requestedHeight, preserveRatio, smooth);
	}

	byte[] getImgBytes();
}
