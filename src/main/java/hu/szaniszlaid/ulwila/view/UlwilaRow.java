package hu.szaniszlaid.ulwila.view;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import hu.szaniszlaid.ulwila.notes.MusicComponent;

public class UlwilaRow{
	
	private List<UlwilaBar> rhytms;
	
	private TimeSignature timeSignature;
	
	private double musicalLenght = 0;
	
	public UlwilaRow(TimeSignature timeSignature) {
		this.timeSignature = timeSignature;
		setRhytms(new ArrayList<>());

	}

	
	public void add(MusicComponent comp){
		musicalLenght += comp.getMusicalLength();
		getCurrentRhytm().add(comp);		
	}
	
	public boolean canFit (MusicComponent musicComponent){
		//FIXME calculate metre
		return musicalLenght < barsPerRow(timeSignature) ? true : false;
	}
	
	
	private UlwilaBar getCurrentRhytm(){
		UlwilaBar rhytm;
		if (getRhytms().size() > 0) {
			rhytm = getRhytms().get(getRhytms().size() - 1);
			if (!rhytm.isFull()){
				return getRhytms().get(getRhytms().size() - 1);
			} 
		}
		rhytm = new UlwilaBar(timeSignature);
		getRhytms().add(rhytm);
		
		return rhytm;
	}

	public List<UlwilaBar> getRhytms() {
		return rhytms;
	}

	public void setRhytms(List<UlwilaBar> rhytms) {
		this.rhytms = rhytms;
	}
	
	public JPanel getRow(){
		JPanel row = new JPanel();
		for (UlwilaBar ulwilaUtem : rhytms) {
			row.add(ulwilaUtem.getRhytmPanel());
		}
		return row;
	}
	
	/**
	 * This method gets the count of how many bars can fit in one row, based on timeSignature.
	 * @return the bars count in the row
	 * */
	private Integer barsPerRow(TimeSignature timeSignature){
		if (timeSignature.getNumerator() == 2 && timeSignature.getDenominator() == 2){
			return 2;
		} else if (timeSignature.getNumerator() == 2 && timeSignature.getDenominator() == 4){
			return 2;
		} else if (timeSignature.getNumerator() == 3 && timeSignature.getDenominator() == 4){
			return 2;
		} else if (timeSignature.getNumerator() == 4 && timeSignature.getDenominator() == 4){
			return 2;
		} else if (timeSignature.getNumerator() == 6 && timeSignature.getDenominator() == 8){
			return 2;
		} else if (timeSignature.getNumerator() == 12 && timeSignature.getDenominator() == 8){
			return 2;
		} else {
			return 2;
		}
		
	}
}


