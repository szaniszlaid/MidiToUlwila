package hu.szaniszlaid.ulwila.notes.semitone;

import java.awt.Color;
import java.awt.Graphics2D;
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
	public void drawOctave(Graphics2D g) {
		int centerX = getNWidth() / 2 - getNWidth() / 10;
		int centerY = getNHeight() / 2 - getNHeight() / 10;

		g.fillOval(centerX, centerY, getNWidth() / 5, getNHeight() / 5);
		g.setColor(Color.BLACK);
		g.drawOval(centerX, centerY, getNWidth() / 5, getNHeight() / 5);
		if (getTone().equals(Tone.CIS) && getOctave().equals(Octave.FIRST)) {
			g.setColor(Color.WHITE);
			Arc2D.Double borderLeft = new Arc2D.Double(centerX - 1, centerY, getNWidth() / 5, getNHeight() / 5, 90, 180, Arc2D.OPEN);
			g.draw(borderLeft);
		}
	}
}
