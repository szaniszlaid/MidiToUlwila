/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.szaniszlaid.ulwila.notes;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Shape;

import hu.szaniszlaid.ulwila.note.util.Octave;
import hu.szaniszlaid.ulwila.note.util.Tone;

/**
 *
 * @author Franklin
 */
public abstract class MusicNote extends MusicComponent {

	Octave octave;
	private Tone tone;

	public final int offsetX = getDimension().width / 3 * 2;

	public MusicNote(Octave octave, Tone tone) {
		super();
		this.octave = octave;
		this.tone = tone;
	}

	public MusicNote setOctave(Octave octave) {
		this.octave = octave;
		return this;
	}

	public Octave getOctave() {
		return octave;
	}

	public int getNthOffset(int n) {
		return n * (getDimension().width - (getDimension().width - offsetX));
	}

	@Override
	public Dimension draw(Graphics2D g) {
		Dimension dimension = drawNote(g);
		drawOctave(g);
		return dimension;
	}
	
	public abstract Dimension drawNote(Graphics2D g);

	protected void drawOctave(Graphics2D g) {
		Color octaveColor = Color.BLACK;
		switch (octave) {
			case FIRST:
				octaveColor = Color.BLACK;
				break;
			case SECOND:
				return;//FIXME refactor, disgusting
			case THIRD:
				octaveColor = Color.WHITE;
				break;
			case FOURTH:
				octaveColor = Color.BLACK;
				break;
		}

		
		g.setColor(octaveColor);
		g.fill(getOctaveShape());
		
		g.setColor(getOctaveBorderColor());
		g.draw(getOctaveShape());
	}
	
	private Color getOctaveBorderColor(){
		//set inverse border color if necessary
		if (tone.isSemiTone()){
			if (getRightColor().equals(Color.BLACK)){
				return Color.WHITE;
			} else {
				return Color.BLACK;				
			}
		} else {
			if (getColor().equals(Color.BLACK)){
				return Color.WHITE;
			} else {
				return Color.BLACK;				
			}
		}
	}

	public abstract Shape getOctaveShape();

	public Tone getTone() {
		return tone;
	}

	public void setTone(Tone tone) {
		this.tone = tone;
	}

	public Color getColor() {
		switch (getTone()) {
		case C:
			if (getOctave() == Octave.FOURTH) {
				return Color.WHITE;
			}
			return Color.BLACK;
		case D:
			return new Color(145, 75, 41);
		case E:
			return new Color(0, 0, 255);
		case F:
			return new Color(0, 170, 0);
		case G:
			return Color.RED;
		case A:
			return new Color(255, 153, 0);
		case H:
			return Color.YELLOW;
		default:
			throw new UnsupportedOperationException("Use getLeftColor() or getRightColor() method if tone is semi note");
		}
	}

	public Color getLeftColor() {
		switch (getTone()) {
		case CIS:
			return Color.BLACK;
		case DIS:
			return new Color(145, 75, 41);
		case FIS:
			return new Color(0, 170, 0);
		case GIS:
			return Color.RED;
		case AIS:
			return new Color(255, 153, 0);
		default:
			throw new UnsupportedOperationException("Use getColor() method if tone is whole note");
		}
	}

	public Color getRightColor() {
		switch (getTone()) {
		case CIS:
			return new Color(140, 80, 60);
		case DIS:
			return Color.BLUE;
		case FIS:
			return Color.RED;
		case GIS:
			return new Color(255, 153, 0);
		case AIS:
			return Color.YELLOW;
		default:
			throw new UnsupportedOperationException("Use getColor() method if tone is whole note");
		}
	}

}
