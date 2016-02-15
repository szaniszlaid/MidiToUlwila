package hu.szaniszlaid.ulwila.view;

import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import hu.szaniszlaid.ulwila.note.util.Octave;
import hu.szaniszlaid.ulwila.note.util.Tone;
import hu.szaniszlaid.ulwila.notes.MusicComponent;
import hu.szaniszlaid.ulwila.notes.whole.EighthNote;

public class ExportHelper {

	public static void exportComponents(UlwilaTrack ulwilaTrack) {

		try {

			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

			// html elements
			Document doc = docBuilder.newDocument();
			Element html = doc.createElement("html");
			doc.appendChild(html);

			// head elements
			Element head = doc.createElement("head");
			html.appendChild(head);

			// style elements
			Element style = doc.createElement("style");
			style.setTextContent("table, th, td { border: 1px solid black; border-collapse: collapse;}");
			head.appendChild(style);

			// body elements
			Element body = doc.createElement("body");
			html.appendChild(body);

			// table elements
			Element table = doc.createElement("table");
			body.appendChild(table);

			for (UlwilaRow row : ulwilaTrack.getRows()) {
//FIXME checkpoint
			}
			// tr elements
			Element tr = doc.createElement("tr");
			table.appendChild(tr);

			// td elements
			Element td1 = doc.createElement("td");
			tr.appendChild(td1);

			// td elements
			Element table2 = doc.createElement("table");
			td1.appendChild(table2);

			// td2 elements
			Element td2 = doc.createElement("td");
			td2.appendChild(doc.createTextNode("jklé"));
			tr.appendChild(td2);

			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File("file.html"));

			newXmlTransformer().transform(source, result);
			writeComponentToFile(new EighthNote(Octave.FIRST, Tone.G));

			System.out.println("File saved!");

		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch (TransformerException tfe) {
			tfe.printStackTrace();
		}
	}

	private static Transformer newXmlTransformer() {
		// write the content into xml file
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = null;

		try {
			transformer = transformerFactory.newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "8");
			transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
			transformer.setOutputProperty(OutputKeys.METHOD, "html");
		} catch (TransformerConfigurationException e) {
			e.printStackTrace();
		}


		return transformer;
	}

	private static BufferedImage getImage(Component c) {
		BufferedImage bi = null;
		try {
			bi = new BufferedImage(c.getWidth(), c.getHeight(), BufferedImage.TYPE_INT_ARGB); //TYPE_INT_ARGB TYPE_4BYTE_ABGR
			Graphics2D g2d = bi.createGraphics();
			g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
			g2d.setRenderingHint(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_ENABLE);
			g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
			g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			g2d.setRenderingHint(RenderingHints.KEY_TEXT_LCD_CONTRAST, 100);
			g2d.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
			g2d.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
			g2d.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
			g2d.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
			c.print(g2d);
			g2d.dispose();
			bi = blurImage(bi);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		return bi;
	}

	//TODO write javadoc src=http://www.componenthouse.com/High-Quality-Image-Resize-with-Java-td21.html
	public static BufferedImage blurImage(BufferedImage image) {
		float ninth = 1.0f / 6.0f;
		float[] blurKernel = {
				ninth, ninth, ninth,
				ninth, ninth, ninth,
				ninth, ninth, ninth
		};

		Map<RenderingHints.Key, Object> map = new HashMap<>();

		map.put(RenderingHints.KEY_INTERPOLATION,
				RenderingHints.VALUE_INTERPOLATION_BILINEAR);

		map.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
		map.put(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		RenderingHints hints = new RenderingHints(map);
		BufferedImageOp op = new ConvolveOp(new Kernel(3, 3, blurKernel), ConvolveOp.EDGE_NO_OP, hints);
		return op.filter(image, null);
	}

	private static boolean writeComponentToFile(MusicComponent c) {
		BufferedImage img = getImage(c);
		if (img == null) {
			return false;
		} else {
			File output = new File("pics/asdf.png");
			try {
				ImageIO.write(img, "png", output);
				return true;
			} catch (IOException e) {
				e.printStackTrace();
				return false;
			}
		}
	}

}
