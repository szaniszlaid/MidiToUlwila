package hu.szaniszlaid.ulwila.view;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import hu.szaniszlaid.ulwila.notes.MusicComponent;

public class UlwilaBar{
	private float notesLength = 0;
	private float timeSignature;
	
	private List<MusicComponent> musicComponents = new ArrayList<>();
	
	public UlwilaBar(float timeSignature){
		this.timeSignature = timeSignature;

	}
	
	public UlwilaBar(int numerator, int denominator) {
		this(numerator / (float) denominator);
	}
	
	
	public UlwilaBar(TimeSignature timeSignature) {
		this(timeSignature.getNumerator(), timeSignature.getDenominator());
	}
	
	public void add(MusicComponent comp) {
		if (isFull()){
			throw new RuntimeException("Cannot add more components to this bar, it is full.");
		} else {
			musicComponents.add(comp);
			notesLength += comp.getMusicalLength();
		}
	}
	
	public boolean isFull(){
		return notesLength >= timeSignature ? true : false;
	}
	
	public boolean isNotFull(){
		return !isFull();
	}
	
	public List<MusicComponent> getComponents(){
		return musicComponents;
	}
	
	public JPanel getPanel(){
		JPanel barPanel = new JPanel();
		//add separator to bar right side
		barPanel.setBorder(new EmptyBorder(0, 0, 0, 30));
		for (MusicComponent musicComponent : musicComponents) {
			barPanel.add(musicComponent);
		}		
		return barPanel;
	}
	
}
