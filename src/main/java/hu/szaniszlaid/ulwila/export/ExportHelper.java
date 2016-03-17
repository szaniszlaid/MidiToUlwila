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
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;

import javax.swing.ProgressMonitor;
import javax.swing.SwingWorker;

public abstract class ExportHelper<T, V> extends SwingWorker<T, V> implements PropertyChangeListener {

	Component parent;
	ProgressMonitor progressMonitor;

	public ExportHelper(Component parent) {
		super();
		this.parent = parent;

		progressMonitor = new ProgressMonitor(parent, "Please wait until the process has finished", "", 0, 100);
		progressMonitor.setMillisToDecideToPopup(0);
	}

	public void generate() {
		addPropertyChangeListener(this);
		execute();
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
}
