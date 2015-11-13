package hu.szaniszlaid.ulwila.view;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import hu.szaniszlaid.ulwila.notes.MusicComponent;

public class UlwilaRow{
	
	private List<UlwilaRhythm> rhytms;
	
	float timeSignature;
	private double musicalLenght = 0;
	
	public UlwilaRow(int numerator, int denominator) {
		timeSignature = (float) numerator / denominator;	
		setRhytms(new ArrayList<>());
	}
	
	public UlwilaRow(TimeSignature timeSignature) {
		this(timeSignature.getNumerator(), timeSignature.getDenominator());
	}

	
	public void add(MusicComponent comp){
		musicalLenght += comp.getMusicalLength();
		getCurrentRhytm().add(comp);		
	}
	
	public boolean canFit (MusicComponent musicComponent){
		//FIXME calculate metre
		return musicalLenght < 2 ? true : false;
	}
	
	
	private UlwilaRhythm getCurrentRhytm(){
		UlwilaRhythm rhytm;
		if (getRhytms().size() > 0) {
			rhytm = getRhytms().get(getRhytms().size() - 1);
			if (!rhytm.isFull()){
				return getRhytms().get(getRhytms().size() - 1);
			} 
		}
		rhytm = new UlwilaRhythm(timeSignature);
		getRhytms().add(rhytm);
		
		return rhytm;
	}

	public List<UlwilaRhythm> getRhytms() {
		return rhytms;
	}

	public void setRhytms(List<UlwilaRhythm> rhytms) {
		this.rhytms = rhytms;
	}
	
	public JPanel getRow(){
		JPanel row = new JPanel();
		for (UlwilaRhythm ulwilaUtem : rhytms) {
			row.add(ulwilaUtem.getRhytmPanel());
		}
		return row;
	}
}
