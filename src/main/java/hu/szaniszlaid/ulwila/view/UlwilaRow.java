package hu.szaniszlaid.ulwila.view;

import java.awt.Component;

import javax.swing.JPanel;

import hu.szaniszlaid.ulwila.notes.MusicComponent;

public class UlwilaRow extends JPanel{
	
	int elementsCount = 0;	
	double timeSignature;
	private double musicalLenght = 0;
	
	public UlwilaRow(int numerator, int denominator) {
		timeSignature = numerator / denominator;
	}
	
	public UlwilaRow(TimeSignature timeSignature) {
		this(timeSignature.getNumerator(), timeSignature.getDenominator());
	}
	
	
	@Override
	public Component add(Component comp) {
		if (comp instanceof MusicComponent){
			return addMusicComponent((MusicComponent) comp);
		} else {
			throw new UnsupportedOperationException();
		}
	}
	
	public Component addMusicComponent(MusicComponent comp){
		elementsCount++;
		musicalLenght += comp.getMusicalLength();
		System.out.println(musicalLenght);
		return super.add(comp);
	}
	
	public boolean canFit (MusicComponent musicComponent){
		//TODO calculate metre
		return musicalLenght < 1 ? true : false;
	}
}
