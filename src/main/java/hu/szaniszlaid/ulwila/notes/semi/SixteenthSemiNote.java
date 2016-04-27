package hu.szaniszlaid.ulwila.notes.semi;

import java.awt.Color;
import java.awt.Graphics2D;

import hu.szaniszlaid.ulwila.notes.util.Octave;
import hu.szaniszlaid.ulwila.notes.util.PaintStyle;
import hu.szaniszlaid.ulwila.notes.util.Tone;
import hu.szaniszlaid.ulwila.notes.whole.SixteenthNote;

public class SixteenthSemiNote extends SixteenthNote {

	public SixteenthSemiNote(Octave octave, Tone tone, PaintStyle paintStlye) {
		super(octave, tone, paintStlye);
	}

	@Override
	public void drawNote(Graphics2D g) {
		g.setColor(getLeftColor());
		g.fillRect(0, 0, SIXTEENTH_WIDTH / 2, QUARTER_NOTE_HEIGHT);
		g.setColor(Color.BLACK);
		g.drawRect(0, 0, SIXTEENTH_WIDTH / 2, QUARTER_NOTE_HEIGHT);

		g.setColor(getRightColor());
		g.fillRect(SIXTEENTH_WIDTH / 2, 0, SIXTEENTH_WIDTH / 2, QUARTER_NOTE_HEIGHT);
		g.setColor(Color.BLACK);
		g.drawRect(SIXTEENTH_WIDTH / 2, 0, SIXTEENTH_WIDTH / 2, QUARTER_NOTE_HEIGHT);
	}

}
