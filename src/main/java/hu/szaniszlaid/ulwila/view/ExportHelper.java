package hu.szaniszlaid.ulwila.view;

import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

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

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import hu.szaniszlaid.ulwila.notes.MusicComponent;
import hu.szaniszlaid.ulwila.notes.MusicNote;

public class ExportHelper {

	final static char NON_BREAKING_SPACE = 0x00A0;

	public static void exportToWord(UlwilaTrack ulwilaTrack, File file) {

		try (XWPFDocument doc = new XWPFDocument(); FileOutputStream out = new FileOutputStream(file.getName() + ".docx")) {
			for (UlwilaRow ulwilaRow : ulwilaTrack.getRows()) {

				XWPFTable table = doc.createTable();
				table.getCTTbl().getTblPr().unsetTblBorders();
				table.createRow();

				XWPFTableRow imageRow = table.getRow(0);
				XWPFTableRow lyricsRow = table.getRow(1);

				int colIterator = 0;

				Iterator<UlwilaBar> barsIterator = ulwilaRow.getBars().iterator();

				while (barsIterator.hasNext()) {
					UlwilaBar ulwilaBar = barsIterator.next();

					Iterator<UlwilaComponent> componentsIterator = ulwilaBar.getComponents().iterator();

					while (componentsIterator.hasNext()) {
						UlwilaComponent ulwilaComponent = componentsIterator.next();
						MusicComponent musicComponent = ulwilaComponent.getMusicComponent();

						XWPFTableCell cell = imageRow.getCell(colIterator);

						XWPFParagraph paragraph = cell.getParagraphs().get(0);
						XWPFRun run = paragraph.createRun();

						ByteArrayOutputStream os = new ByteArrayOutputStream();

						BufferedImage bi = getImage(ulwilaComponent.getMusicComponent());
						ImageIO.write(bi, "png", os);
						InputStream is = new ByteArrayInputStream(os.toByteArray());
						run.addPicture(is, XWPFDocument.PICTURE_TYPE_PNG, "TODO", Units.toEMU(musicComponent.getWidth()),
								Units.toEMU(musicComponent.getHeight()));

						//Lyrics
						XWPFTableCell lyricsCell = lyricsRow.getCell(colIterator);
						lyricsCell.setText(ulwilaComponent.getLyrics());
						lyricsCell.getParagraphs().get(0).setAlignment(ParagraphAlignment.CENTER);

						//add separation column
						if (componentsIterator.hasNext()) {
							imageRow.addNewTableCell();
							lyricsRow.addNewTableCell();
							colIterator++;
						} else if (barsIterator.hasNext()) {
							XWPFTableCell separationCell = imageRow.addNewTableCell();
							//TODO change fix tab to transparent placeholder
							//separationCell.addParagraph().createRun().addTab();
							separationCell.setText("" + NON_BREAKING_SPACE + NON_BREAKING_SPACE + NON_BREAKING_SPACE + NON_BREAKING_SPACE);
							imageRow.addNewTableCell();
							lyricsRow.addNewTableCell();
							lyricsRow.addNewTableCell();
							colIterator++;
							colIterator++;
						}

					}
				}

				doc.createParagraph();
			}

			doc.write(out);
			System.out.println("export successful");
		} catch (IOException | InvalidFormatException e) {
			// TODO handling, logging
			e.printStackTrace();
		}

	}

	public void exportToHtml(UlwilaTrack ulwilaTrack, File directory) {

		String directoryName = directory.getName();

		try {

			writeComponenstToFileCollection(collectComponents(ulwilaTrack), directory);

			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

			// html elements
			Document doc = docBuilder.newDocument();
			Element html = doc.createElement("html");
			doc.appendChild(html);

			// head elements
			Element head = doc.createElement("head");
			html.appendChild(head);

			// set table style
			Element style = doc.createElement("style");
			style.setTextContent("table{\n" + "display:table;\n" + "margin-top:5px;\n" + "margin-right:50px;\n" + "}\n");
			head.appendChild(style);

			// body elements
			Element body = doc.createElement("body");
			html.appendChild(body);

			for (UlwilaRow row : ulwilaTrack.getRows()) {
				// egy sor amibe ütem táblázatok vannak vannak
				Element belsoDiv = doc.createElement("div");
				body.appendChild(belsoDiv);

				for (UlwilaBar bar : row.getBars()) {
					// ütem táblázat
					Element barTable = doc.createElement("table");
					barTable.setAttribute("style", "display: inline-block");
					belsoDiv.appendChild(barTable);

					// hangjegyek sora
					Element noteRow = doc.createElement("tr");
					barTable.appendChild(noteRow);

					// szöveg sora
					Element lyricsRow = doc.createElement("tr");
					barTable.appendChild(lyricsRow);

					for (UlwilaComponent c : bar.getComponents()) {
						// hangjegykép
						Element noteElement = doc.createElement("td");
						Element imageElement = doc.createElement("img");
						imageElement.setAttribute("align", "center");
						imageElement.setAttribute("src", directoryName + "/" + generateMusicComponentFileName(c.getMusicComponent()));
						noteElement.appendChild(imageElement);
						noteRow.appendChild(noteElement);

						// lyrics
						Element lyricsElement = doc.createElement("td");
						lyricsElement.setAttribute("align", "center");
						if (c.getLyrics() != null) {
							lyricsElement.appendChild(doc.createTextNode(c.getLyrics()));
						}
						lyricsRow.appendChild(lyricsElement);
					}

				}
			}

			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File(directory.getParent(), directory.getName() + ".html"));

			newXmlTransformer().transform(source, result);

			System.out.println("File saved!");

		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch (TransformerException tfe) {
			tfe.printStackTrace();
		}
	}

	private Set<MusicComponent> collectComponents(UlwilaTrack ulwilaTrack) {
		Set<MusicComponent> components = new HashSet<>();
		for (UlwilaRow row : ulwilaTrack.getRows()) {
			for (UlwilaBar bar : row.getBars()) {
				for (UlwilaComponent c : bar.getComponents()) {
					components.add(c.getMusicComponent());
				}
			}
		}

		return components;
	}

	protected String generateMusicComponentFileName(MusicComponent component) {
		StringBuilder fileName = new StringBuilder();
		fileName.append(Double.toString(component.getMusicalLength()));
		fileName.append("_");
		if (component instanceof MusicNote) {
			MusicNote note = (MusicNote) component;
			fileName.append(note.getTone());
			fileName.append("_");
			fileName.append(note.getOctave());
		}
		fileName.append(".png");
		return fileName.toString();
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
			bi = new BufferedImage(c.getWidth(), c.getHeight(), BufferedImage.TYPE_INT_ARGB); // TYPE_INT_ARGB TYPE_4BYTE_ABGR
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
			// bi = blurImage(bi);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		return bi;
	}

// TODO write javadoc src=http://www.componenthouse.com/High-Quality-Image-Resize-with-Java-td21.html
	public static BufferedImage blurImage(BufferedImage image) {
		float ninth = 1.0f / 6.0f;
		float[] blurKernel = { ninth, ninth, ninth, ninth, ninth, ninth, ninth, ninth, ninth };

		Map<RenderingHints.Key, Object> map = new HashMap<>();

		map.put(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);

		map.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
		map.put(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		RenderingHints hints = new RenderingHints(map);
		BufferedImageOp op = new ConvolveOp(new Kernel(3, 3, blurKernel), ConvolveOp.EDGE_NO_OP, hints);
		return op.filter(image, null);
	}

	private void writeComponenstToFileCollection(Collection<MusicComponent> components, File folder) {
		for (MusicComponent musicComponent : components) {
			BufferedImage img = getImage(musicComponent);
			if (img != null) {
				if (Files.notExists(folder.toPath())) {
					folder.mkdirs();
				}
				File output = new File(folder, generateMusicComponentFileName(musicComponent));
				try {
					ImageIO.write(img, "png", output);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
