package hu.szaniszlaid.ulwila.view;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import org.jdesktop.swingx.VerticalLayout;

import hu.szaniszlaid.ulwila.midi.TimeSignature;

public class UlwilaTrack {

	private List<UlwilaRow> rows = new ArrayList<>();

	public void add(UlwilaRow row){
		rows.add(row);
	}

	public JPanel getPanel(){
		JPanel panel = new JPanel();
		panel.setLayout(new VerticalLayout());

		for (UlwilaRow row : rows) {
			panel.add(row.getRowPanel());
		}
		return panel;
	}

	public UlwilaTrack(List<UlwilaComponent> components, TimeSignature timesignature) {
		UlwilaRow lastRow = new UlwilaRow(timesignature);
		UlwilaBar lastBar = new UlwilaBar(timesignature);

		lastRow.add(lastBar);
		add(lastRow);

		for (UlwilaComponent ulwilaComponent : components) {

			if (lastBar.isNotFull()) {
				lastBar.add(ulwilaComponent);
			} else {
				lastBar = new UlwilaBar(timesignature);
				lastBar.add(ulwilaComponent);
				if (lastRow.isNotFull()) {
					lastRow.add(lastBar);
				} else {
					lastRow = new UlwilaRow(timesignature);
					lastRow.add(lastBar);
					add(lastRow);
				}

			}


		}
	}

	public List<UlwilaRow> getRows() {
		return rows;
	}

}
