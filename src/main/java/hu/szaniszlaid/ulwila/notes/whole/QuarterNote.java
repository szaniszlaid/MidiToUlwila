package hu.szaniszlaid.ulwila.notes.whole;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Arc2D;
import java.util.ArrayList;
import java.util.List;

import hu.szaniszlaid.ulwila.notes.MusicNote;
import hu.szaniszlaid.ulwila.notes.util.Octave;
import hu.szaniszlaid.ulwila.notes.util.PaintStyle;
import hu.szaniszlaid.ulwila.notes.util.Tone;

public class QuarterNote extends MusicNote {

    public QuarterNote(Octave octave, Tone tone,PaintStyle paintStlye) {
        super(octave, tone, paintStlye);
    }

    @Override
    public void drawNote(Graphics2D g) {
        double x = (QUARTER_NOTE_WIDTH / 2 - QUARTER_NOTE_WIDTH / 3) + MARGIN_HORIZONTAL;
        double y = (QUARTER_NOTE_HEIGHT / 2 - QUARTER_NOTE_HEIGHT / 3) + MARGIN_VERTICAL;

        g.setColor(getColor());
        g.fillOval(0 + MARGIN_HORIZONTAL, 0 + MARGIN_VERTICAL, QUARTER_NOTE_WIDTH, QUARTER_NOTE_HEIGHT);
        g.setColor(Color.BLACK);
        g.drawOval(0 + MARGIN_HORIZONTAL, 0 + MARGIN_VERTICAL, QUARTER_NOTE_WIDTH, QUARTER_NOTE_HEIGHT);

        if (getTone() == Tone.C && getOctave() == Octave.FIRST) {
            g.setColor(Color.WHITE);
            Arc2D whiteCircle = new Arc2D.Double(x, y, QUARTER_NOTE_WIDTH / 1.5, QUARTER_NOTE_HEIGHT / 1.5, 0, 360, Arc2D.CHORD);
            g.fill(whiteCircle);
        }

    }

    @Override
    public List<Shape> getOctaveShapes() {
		int x = (QUARTER_NOTE_WIDTH / 2 - QUARTER_NOTE_WIDTH / 10) + MARGIN_HORIZONTAL;
        int y = (QUARTER_NOTE_HEIGHT / 2 - QUARTER_NOTE_HEIGHT / 10) + MARGIN_VERTICAL;

        List<Shape> octaveShapes = new ArrayList<>();
        octaveShapes.add(new Arc2D.Double(x, y, QUARTER_NOTE_WIDTH / 5, QUARTER_NOTE_HEIGHT / 5, 0, 360, Arc2D.CHORD));
        return octaveShapes;
    }

	@Override
	public double getMusicalLength() {
		return (double) 1/4;
	}

	@Override
	public Dimension getSize() {
        return new Dimension(QUARTER_NOTE_WIDTH, QUARTER_NOTE_HEIGHT);
	}

}
