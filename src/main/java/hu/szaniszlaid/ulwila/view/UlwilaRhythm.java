package hu.szaniszlaid.ulwila.view;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import hu.szaniszlaid.ulwila.notes.MusicComponent;

public class UlwilaRhythm{
	private float notesLength = 0;
	private float timeSignature;
	
	private List<MusicComponent> musicComponents = new ArrayList<>();
	private JPanel rhytmPanel;
	
	public UlwilaRhythm(float timeSignature){
		this.timeSignature = timeSignature;
		rhytmPanel = new JPanel();
		rhytmPanel.setBorder(new EmptyBorder(0, 0, 0, 100));
	}
	
	public UlwilaRhythm(int numerator, int denominator) {
		this(numerator / denominator);
	}
	
	
	public UlwilaRhythm(TimeSignature timeSignature) {
		this(timeSignature.getNumerator(), timeSignature.getDenominator());
	}
	
	public void add(MusicComponent comp) {
		musicComponents.add(comp);
		notesLength += comp.getMusicalLength();
	}
	
	public boolean isFull(){
		return notesLength >= timeSignature ? true : false;
	}
	
	public List<MusicComponent> getComponents(){
		return musicComponents;
	}
	
	public JPanel getRhytmPanel(){
		for (MusicComponent musicComponent : musicComponents) {
			rhytmPanel.add(musicComponent);
		}		
		return rhytmPanel;
	}
	
}
