package hu.szaniszlaid.ulwila.export;

import java.awt.Component;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

import javax.imageio.ImageIO;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

import hu.szaniszlaid.ulwila.notes.MusicComponent;
import hu.szaniszlaid.ulwila.notes.MusicNote;
import hu.szaniszlaid.ulwila.view.UlwilaBar;
import hu.szaniszlaid.ulwila.view.UlwilaComponent;
import hu.szaniszlaid.ulwila.view.UlwilaRow;
import hu.szaniszlaid.ulwila.view.UlwilaTrack;

public class WordExport extends ExportHelper<Void, Void>{

	final static char NON_BREAKING_SPACE = 0x00A0;

	UlwilaTrack ulwilaTrack;

	public WordExport(Component parent, UlwilaTrack ulwilaTrack, File file) {
		super(parent, file);
		this.ulwilaTrack = ulwilaTrack;
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
		try (XWPFDocument doc = new XWPFDocument(); FileOutputStream out = new FileOutputStream(getOutputFile().getAbsolutePath() + ".docx")) {
			int ulwilaTrackSize = ulwilaTrack.getRows().size();
			for (int i = 0; i < ulwilaTrackSize; i++) {
					UlwilaRow ulwilaRow = ulwilaTrack.getRows().get(i);

					setProgress((i + 1) * 100 / ulwilaTrackSize);

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
							run.addPicture(is, XWPFDocument.PICTURE_TYPE_PNG, "TODO", Units.toEMU(musicComponent.getWidth()), Units.toEMU(musicComponent.getHeight()));

							// Lyrics
							XWPFTableCell lyricsCell = lyricsRow.getCell(colIterator);
							lyricsCell.setText(ulwilaComponent.getLyrics());
							lyricsCell.getParagraphs().get(0).setAlignment(ParagraphAlignment.CENTER);

							// add separation column
							if (componentsIterator.hasNext()) {
								imageRow.addNewTableCell();
								lyricsRow.addNewTableCell();
								colIterator++;
							} else if (barsIterator.hasNext()) {
								XWPFTableCell separationCell = imageRow.addNewTableCell();
								// TODO change fix tab to transparent placeholder
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
				
				if (Thread.interrupted()) {
					break;
				}
			}

			doc.write(out);

		} catch (IOException | InvalidFormatException e) {
			// TODO handling, logging
			e.printStackTrace();
		}

		return null;
	}
}
