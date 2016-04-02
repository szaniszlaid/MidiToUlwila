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

public class DottedQuarterNote extends MusicNote {

	public DottedQuarterNote(Octave octave, Tone tone, PaintStyle paintStlye) {
		super(octave, tone, paintStlye);
	}

	@Override
	public void drawNote(Graphics2D g) {
		double x = (QUARTER_NOTE_WIDTH / 2 - QUARTER_NOTE_WIDTH / 3) + MARGIN_LEFT;
		double y = (QUARTER_NOTE_HEIGHT / 2 - QUARTER_NOTE_HEIGHT / 3) + MARGIN_TOP;

		//Eight part
		g.setColor(getColor());
		Arc2D quarter = new Arc2D.Double(offsetX, MARGIN_TOP, QUARTER_NOTE_WIDTH, QUARTER_NOTE_HEIGHT, 90, 180, Arc2D.OPEN);
		g.fill(quarter);
		g.setColor(Color.BLACK);
		Arc2D.Double border = new Arc2D.Double(offsetX, MARGIN_TOP, QUARTER_NOTE_WIDTH, QUARTER_NOTE_HEIGHT, 90, 180,
				Arc2D.CHORD);
		g.draw(border);

		//Quarter part
		g.setColor(getColor());
		g.fillOval(0 + MARGIN_LEFT, 0 + MARGIN_TOP, QUARTER_NOTE_WIDTH, QUARTER_NOTE_HEIGHT);
		g.setColor(Color.BLACK);
		g.drawOval(0 + MARGIN_LEFT, 0 + MARGIN_TOP, QUARTER_NOTE_WIDTH, QUARTER_NOTE_HEIGHT);

		if (getTone() == Tone.C && getOctave() == Octave.FIRST) {
			g.setColor(Color.WHITE);
			Arc2D whiteCircle = new Arc2D.Double(x, y, QUARTER_NOTE_WIDTH / 1.5, QUARTER_NOTE_HEIGHT / 1.5, 0, 360, Arc2D.CHORD);
			g.fill(whiteCircle);
		}

	}

	@Override
	public List<Shape> getOctaveShapes() {
		int x = (QUARTER_NOTE_WIDTH / 2 - QUARTER_NOTE_WIDTH / 10);
		int y = (QUARTER_NOTE_HEIGHT / 2 - QUARTER_NOTE_HEIGHT / 10);

		List<Shape> octaveShapes = new ArrayList<>();
		octaveShapes.add(new Arc2D.Double(x + MARGIN_LEFT, y + MARGIN_TOP, QUARTER_NOTE_WIDTH / 5, QUARTER_NOTE_HEIGHT / 5, 0, 360, Arc2D.CHORD));

		octaveShapes.add(new Arc2D.Double(x + offsetX, y + MARGIN_TOP, QUARTER_NOTE_WIDTH / 5, QUARTER_NOTE_HEIGHT / 5, 90, 180, Arc2D.OPEN));
		return octaveShapes;
	}

	@Override
	public double getMusicalLength() {
		return 1.5 / 4;
	}

	@Override
	public Dimension getSize() {
		//quarter                 eight
		int width = QUARTER_NOTE_WIDTH + QUARTER_NOTE_WIDTH / 2 - (QUARTER_NOTE_WIDTH - offsetX);
		return new Dimension(width, QUARTER_NOTE_HEIGHT);
	}
}
