package hu.szaniszlaid.ulwila.export;

import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;

import javax.imageio.ImageIO;
import javax.swing.ProgressMonitor;
import javax.swing.SwingWorker;

import hu.szaniszlaid.ulwila.notes.MusicComponent;
import hu.szaniszlaid.ulwila.notes.MusicNote;

public abstract class ExportHelper<T, V> extends SwingWorker<T, V> implements PropertyChangeListener {

	Component parent;
	ProgressMonitor progressMonitor;
	private File output;

	public ExportHelper(Component parent, File ouptut) {
		super();
		this.parent = parent;
		this.output = ouptut;
		progressMonitor = new ProgressMonitor(parent, "Please wait until the process has finished", "", 0, 100);
		progressMonitor.setMillisToDecideToPopup(50);
	}

	public void generate() {
		addPropertyChangeListener(this);
		execute();
	}

	protected void writeComponent(Component component) {
		BufferedImage img = getImage(component);
		if (img != null) {
			if (Files.notExists(output.getParentFile().toPath())) {
				output.getParentFile().mkdirs();
			}

			try {
				ImageIO.write(img, "png", output);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Invoked when task's progress property changes.
	 */
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if ("progress" == evt.getPropertyName()) {
			int progress = (Integer) evt.getNewValue();
			progressMonitor.setProgress(progress);
			String message = String.format("Completed %d%%.\n", progress);
			progressMonitor.setNote(message);
			if (progressMonitor.isCanceled()) {
				cancel(true);
				Toolkit.getDefaultToolkit().beep();
			}
		} else if (StateValue.STARTED.equals(evt.getNewValue())) {
			progressMonitor.setProgress(1);
		}

	}

	@Override
	protected abstract T doInBackground() throws Exception;

	@Override
	public void done() {
		try {
			get(); // invoke get, to print RuntimeExceptions in background thread
			System.out.println("export successful");
			progressMonitor.setProgress(100);
			progressMonitor.close();
		} catch (CancellationException e) {
			System.out.println("Task canceled.\n");
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
		Toolkit.getDefaultToolkit().beep();
	}

	protected static BufferedImage getImage(Component c) {
		BufferedImage bi = null;
		try {
			bi = new BufferedImage(c.getWidth(), c.getHeight(), BufferedImage.TYPE_INT_ARGB); // TYPE_INT_ARGB TYPE_4BYTE_ABGR, TRANSLUCENT
			Graphics2D g2d = bi.createGraphics();
			g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

			c.print(g2d);
			g2d.dispose();
			// bi = blurImage(bi);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		return bi;
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


	// TODO write javadoc src=http://www.componenthouse.com/High-Quality-Image-Resize-with-Java-td21.html
	protected static BufferedImage blurImage(BufferedImage image) {
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

	public File getOutputFile() {
		return output;
	}

	public void setOutputFile(File file) {
		this.output = file;
	}
}
