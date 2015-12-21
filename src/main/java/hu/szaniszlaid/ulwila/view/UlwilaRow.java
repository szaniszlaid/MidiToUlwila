package hu.szaniszlaid.ulwila.view;

import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

public class UlwilaRow{
	
	private List<UlwilaBar> bars = new ArrayList<>();
	
	private TimeSignature timeSignature;
	
	private int barCount = 0;
	
	public UlwilaRow(TimeSignature timeSignature) {
		this.timeSignature = timeSignature;
	}

	
	public void add(UlwilaBar bar){
		bars.add(bar);
		barCount++;
	}
	
	public boolean isFull(){
		return barCount >= barsPerRow(timeSignature) ? true : false;
	}
	
	public boolean isNotFull(){
		return !isFull();
	}
	
	public JPanel getRowPanel(){
		JPanel row = new JPanel();
		row.setLayout(new FlowLayout(FlowLayout.LEFT));
		for (UlwilaBar bar : bars) {
			row.add(bar.getPanel());
		}
		return row;
	}
	
	
//	private UlwilaBar getCurrentBar(){
//		UlwilaBar bar;
//		if (getBars().size() > 0) {
//			bar = getBars().get(getBars().size() - 1);
//			if (!bar.isFull()){
//				return getBars().get(getBars().size() - 1);
//			} 
//		}
//		bar = new UlwilaBar(timeSignature);
//		getBars().add(bar);
//		
//		return bar;
//	}

	public List<UlwilaBar> getBars() {
		return bars;
	}
	
//	public JPanel getRow(){
//		JPanel row = new JPanel();
//		row.setLayout(new FlowLayout(FlowLayout.LEFT));
//		for (UlwilaBar bar : bars) {
//			row.add(bar.getBarPanel());
//		}
//		return row;
//	}
	
//	public void addBar(UlwilaBar ulwilaBar) {	
	
		
//		for (MusicComponent component : musicTrack.getComponents()) {
//			UlwilaBar currentBar = ulwilaBars.get(ulwilaBars.size() - 1);
//			if (currentBar.isNotFull()) {
//				currentBar.add(component);
//			} else {
//				UlwilaBar newBar = new UlwilaBar(musicTrack.getTimeSignature());
//				newBar.add(component);
//				ulwilaBars.add(newBar);
//			}
//		}
//
//	}
	
	/**
	 * This method gets the count of how many bars can fit in one row, based on timeSignature.
	 * @return the bars count in the row
	 * */
	private Integer barsPerRow(TimeSignature timeSignature){
		if (timeSignature.getNumerator() == 2 && timeSignature.getDenominator() == 2){
			return 4;
		} else if (timeSignature.getNumerator() == 2 && timeSignature.getDenominator() == 4){
			return 3;
		} else if (timeSignature.getNumerator() == 3 && timeSignature.getDenominator() == 4){
			return 3;
		} else if (timeSignature.getNumerator() == 4 && timeSignature.getDenominator() == 4){
			return 2;
		} else if (timeSignature.getNumerator() == 6 && timeSignature.getDenominator() == 8){
			return 4;
		} else if (timeSignature.getNumerator() == 12 && timeSignature.getDenominator() == 8){
			return 4;
		} else {
			return 4;
		}
		
	}
}


