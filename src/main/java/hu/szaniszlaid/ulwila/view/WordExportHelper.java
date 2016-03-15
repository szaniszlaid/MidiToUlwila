package hu.szaniszlaid.ulwila.view;

import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.swing.ProgressMonitor;
import javax.swing.SwingWorker;

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

public class WordExportHelper implements PropertyChangeListener {

	final static char NON_BREAKING_SPACE = 0x00A0;

	Component parent;
	ProgressMonitor progressMonitor;
	UlwilaTrack ulwilaTrack;
	File file;
	Task task;

	public WordExportHelper(Component parent, UlwilaTrack ulwilaTrack, File file) {
		super();
		this.parent = parent;
		this.ulwilaTrack = ulwilaTrack;
		this.file = file;
	}

	public void generate() {		
		task = new Task(ulwilaTrack, file);
		task.addPropertyChangeListener(this);
		progressMonitor = new ProgressMonitor(parent, "Running a Long Task", "", 0, 100);
		progressMonitor.setProgress(0);
		task.execute();
	}

	class Task extends SwingWorker<Void, Void> {
		UlwilaTrack ulwilaTrack;
		File file;

		public Task(UlwilaTrack ulwilaTrack, File file) {
			this.ulwilaTrack = ulwilaTrack;
			this.file = file;
	}

		@Override
		public Void doInBackground() {
			try (XWPFDocument doc = new XWPFDocument(); FileOutputStream out = new FileOutputStream(file.getAbsolutePath() + ".docx")) {
				int ulwilaTrackSize = ulwilaTrack.getRows().size();
				for (int i = 0; i < ulwilaTrackSize; i++) {
					UlwilaRow ulwilaRow = ulwilaTrack.getRows().get(i);
					System.out.println(Integer.toString(i));

					setProgress(Math.round((float) (i + 1) / ulwilaTrackSize * 100));
					
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
								// separationCell.addParagraph().createRun().addTab();
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

			return null;
		}

		@Override
		public void done() {
			Toolkit.getDefaultToolkit().beep();
		}
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

	/**
	 * Invoked when task's progress property changes.
	 */
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		System.out.println(evt);
		if ("progress" == evt.getPropertyName()) {
			int progress = (Integer) evt.getNewValue();
			progressMonitor.setProgress(progress);
			String message = String.format("Completed %d%%.\n", progress);
			progressMonitor.setNote(message);
			System.out.println(message);
			if (progressMonitor.isCanceled() || task.isDone()) {
				Toolkit.getDefaultToolkit().beep();
				if (progressMonitor.isCanceled()) {
					task.cancel(true);
					System.out.println("Task canceled.\n");
				} else {
					System.out.println("Task completed.\n");
				}
				System.out.println("done");
			}
		}
	}
}
