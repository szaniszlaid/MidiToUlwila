package hu.szaniszlaid.ulwila.view;

import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

public class UlwilaTrack {

	private List<UlwilaRow> rows = new ArrayList<>();
	
	public void add(UlwilaRow row){
		rows.add(row);
	}
	
	public JPanel getPanel(){
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

		for (UlwilaRow row : rows) {
			panel.add(row.getRowPanel());
		}
		return panel;
	}
	
}
