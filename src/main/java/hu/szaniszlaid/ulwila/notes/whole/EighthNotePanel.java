/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.szaniszlaid.ulwila.notes.whole;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Arc2D;

import hu.szaniszlaid.ulwila.note.util.Octave;
import hu.szaniszlaid.ulwila.note.util.Tone;
import hu.szaniszlaid.ulwila.notes.MusicNote;

/**
 *
 * @author Franklin
 */
public class EighthNotePanel extends MusicNote {

	/**
	 * Creates new form QuarterNote
	 *
	 * @param color
	 * @param octave
	 */
	public EighthNotePanel(Octave octave, Tone tone) {
		super(new NoteBuilder(), octave, tone);
		setContainerWidth(getNWidth() / 2);
	}

	@Override
	public int drawNote(Graphics2D g) {
		g.setColor(getColor());
		Arc2D quarter = new Arc2D.Double(0, 0, getNWidth(), getNHeight(), 90, 180, Arc2D.OPEN);
		g.fill(quarter);
		g.setColor(Color.BLACK);
		Arc2D.Double border = new Arc2D.Double(0, 0, getNWidth(), getNHeight(), 90, 180, Arc2D.CHORD);
		g.draw(border);
		//FIXME
		return 100;
	}

	@Override
	public Shape getOctaveShape() {
		int x = getNWidth() / 2 - getNWidth() / 10;
		int y = getNHeight() / 2 - getNHeight() / 10;
		return new Arc2D.Double(x, y, getNWidth() / 5, getNHeight() / 5, 90, 180, Arc2D.OPEN);
	}
}
