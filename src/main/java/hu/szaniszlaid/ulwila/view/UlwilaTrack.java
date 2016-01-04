package hu.szaniszlaid.ulwila.view;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import org.jdesktop.swingx.VerticalLayout;

import hu.szaniszlaid.ulwila.midi.TimeSignature;
import hu.szaniszlaid.ulwila.notes.MusicComponent;

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
	
	public UlwilaTrack(List<MusicComponent> components, TimeSignature timesignature){
        UlwilaRow lastRow = new UlwilaRow(timesignature);
        UlwilaBar lastBar = new UlwilaBar(timesignature);
        
        lastRow.add(lastBar);
        add(lastRow);
        
        for (int i = 0; i < components.size(); i++) {
            MusicComponent musicComponent = components.get(i);
            
            if (lastBar.isNotFull()) {
                lastBar.add(musicComponent);
            } else {
                lastBar = new UlwilaBar(timesignature);
                lastBar.add(musicComponent);
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
	
}
