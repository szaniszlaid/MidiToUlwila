package hu.szaniszlaid.ulwila.view;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import hu.szaniszlaid.ulwila.notes.MusicComponent;

public class Ulwilarhythm{
	private float notesLength = 0;
	private float timeSignature;
	
	private List<MusicComponent> musicComponents = new ArrayList<>();
	private JPanel rhytmPanel;
	
	public Ulwilarhythm(float timeSignature){
		this.timeSignature = timeSignature;
		rhytmPanel = new JPanel();
		rhytmPanel.setBorder(new EmptyBorder(0, 0, 0, 100));
	}
	
	public Ulwilarhythm(int numerator, int denominator) {
		this(numerator / denominator);
	}
	
	
	public Ulwilarhythm(TimeSignature timeSignature) {
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
