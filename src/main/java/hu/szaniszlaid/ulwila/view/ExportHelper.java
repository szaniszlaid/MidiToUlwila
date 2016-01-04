package hu.szaniszlaid.ulwila.view;

import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.JComponent;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.BreakType;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

public class ExportHelper {
	
	public static void exportComponent(JComponent panel) {
		BufferedImage bi = getImage(panel);

		try (XWPFDocument doc = new XWPFDocument(); FileOutputStream out = new FileOutputStream("images.docx")) {
			XWPFParagraph p = doc.createParagraph();
			XWPFRun r = p.createRun();

			ByteArrayOutputStream os = new ByteArrayOutputStream();

			ImageIO.write(bi, "png", os);

			InputStream is = new ByteArrayInputStream(os.toByteArray());

			r.addPicture(is, XWPFDocument.PICTURE_TYPE_PNG, "asdf", Units.toEMU(100), Units.toEMU(100));

			r.addBreak(BreakType.PAGE);

			doc.write(out);
		} catch (IOException | InvalidFormatException e) {
			// TODO handling, logging
			e.printStackTrace();
		}

	}
	
	private static BufferedImage getImage(Component c) {
		BufferedImage bi = null;
		try {
			bi = new BufferedImage(c.getWidth(), c.getHeight(), BufferedImage.TYPE_INT_ARGB);
			Graphics2D g2d = bi.createGraphics();
			c.print(g2d);
			g2d.dispose();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return bi;
	}

}
