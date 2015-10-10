package hu.szaniszlaid.ulwila.notes.semitone;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Arc2D;

import hu.szaniszlaid.ulwila.note.util.Octave;
import hu.szaniszlaid.ulwila.note.util.Tone;
import hu.szaniszlaid.ulwila.notes.whole.QuarterNotePanel;

public class QuarterSemiNote extends QuarterNotePanel {

	/**
	 * Creates new form QuarterNote
	 *
	 * @param color
	 * @param octave
	 */
	public QuarterSemiNote(Octave octave, Tone tone) {
		super(octave, tone);
		setContainerWidth(getNHeight());
	}

	@Override
	public void drawNote(Graphics2D g) {
		g.setColor(getLeftColor());
		Arc2D quarterLeft = new Arc2D.Double(0, 0, getNWidth(), getNHeight(), 90, 180, Arc2D.OPEN);
		g.fill(quarterLeft);
		g.setColor(Color.BLACK);
		Arc2D.Double borderLeft = new Arc2D.Double(0, 0, getNWidth(), getNHeight(), 90, 180, Arc2D.CHORD);
		g.draw(borderLeft);
		g.setColor(getRightColor());
		Arc2D quarterRight = new Arc2D.Double(0, 0, getNWidth(), getNHeight(), 90, -180, Arc2D.OPEN);
		g.fill(quarterRight);
		g.setColor(Color.BLACK);
		Arc2D quarterRightBorder = new Arc2D.Double(0, 0, getNWidth(), getNHeight(), 90, -180, Arc2D.CHORD);
		g.draw(quarterRightBorder);
	}

	@Override
	public Shape getOctaveShape() {
		int x = getNWidth() / 2 - getNWidth() / 10;
		int y = getNHeight() / 2 - getNHeight() / 10;
		return new Arc2D.Double(x, y, getNWidth() / 5, getNHeight() / 5, 0, 360, Arc2D.CHORD);

	}

	@Override
	protected void drawOctave(Graphics2D g) {
		super.drawOctave(g);
		if (getTone().equals(Tone.CIS) && getOctave().equals(Octave.FIRST)) {
			int x = getNWidth() / 2 - getNWidth() / 10;
			int y = getNHeight() / 2 - getNHeight() / 10;
			g.setColor(Color.WHITE);
			Arc2D.Double borderLeft = new Arc2D.Double(x, y, getNWidth() / 5, getNHeight() / 5, 90, 180, Arc2D.OPEN);
			g.draw(borderLeft);
		}
	}
}
