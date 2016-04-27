package hu.szaniszlaid.ulwila.export;

import java.awt.Component;
import java.awt.Container;
import java.io.File;

public class ImageExport extends ExportHelper<Void, Void> {

	private Container panel;

	public ImageExport(Component parent, Container panel, File file) {
		super(parent, file);
		if (!file.getAbsolutePath().endsWith(".png")) {
			setOutputFile(new File(file.getAbsolutePath() + ".png"));
		}
		this.panel = panel;
	}

	@Override
	protected Void doInBackground() throws Exception {
		panel.setSize(panel.getPreferredSize());
		writeComponent(panel);
		return null;
	}

//	public static List<Component> getAllComponents(final Container c) {
//		Component[] comps = c.getComponents();
//		List<Component> compList = new ArrayList<Component>();
//		for (Component comp : comps) {
//			compList.add(comp);
//			if (comp instanceof Container)
//				compList.addAll(getAllComponents((Container) comp));
//		}
//		return compList;
//	}
}
