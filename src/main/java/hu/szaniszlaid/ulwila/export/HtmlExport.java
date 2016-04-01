package hu.szaniszlaid.ulwila.export;

import java.awt.Component;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Collection;
import java.util.HashSet;
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

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import hu.szaniszlaid.ulwila.notes.MusicComponent;
import hu.szaniszlaid.ulwila.notes.MusicNote;
import hu.szaniszlaid.ulwila.view.UlwilaBar;
import hu.szaniszlaid.ulwila.view.UlwilaComponent;
import hu.szaniszlaid.ulwila.view.UlwilaRow;
import hu.szaniszlaid.ulwila.view.UlwilaTrack;

public class HtmlExport extends ExportHelper<Void, Void> {

	private UlwilaTrack ulwilaTrack;
	private File directory;

	public HtmlExport(Component parent, UlwilaTrack ulwilaTrack, File directory) {
		super(parent);
		this.ulwilaTrack = ulwilaTrack;
		this.directory = directory;
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

	@Override
	public Void doInBackground() {
		String directoryName = directory.getName();

		try {

			writeComponents(collectComponents(ulwilaTrack), directory);

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

			int trackSize = ulwilaTrack.getRows().size();
			for (int i = 0; i < trackSize; i++) {
				UlwilaRow row = ulwilaTrack.getRows().get(i);

				setProgress((i + 1) * 100 / trackSize);

				// div represents a row with table of bars
				Element div = doc.createElement("div");
				body.appendChild(div);

				for (UlwilaBar bar : row.getBars()) {
					// bar
					Element barTable = doc.createElement("table");
					barTable.setAttribute("style", "display: inline-block");
					div.appendChild(barTable);

					// notes row in bar
					Element noteRow = doc.createElement("tr");
					barTable.appendChild(noteRow);

					// lyrics row in bar
					Element lyricsRow = doc.createElement("tr");
					barTable.appendChild(lyricsRow);

					for (UlwilaComponent c : bar.getComponents()) {
						// note
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

			createXmlTransformer().transform(source, result);

		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch (TransformerException tfe) {
			tfe.printStackTrace();
		}

		return null;
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

	private static Transformer createXmlTransformer() {
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

	private void writeComponents(Collection<MusicComponent> components, File folder) {
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
