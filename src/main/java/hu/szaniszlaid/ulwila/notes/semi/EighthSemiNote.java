package hu.szaniszlaid.ulwila.notes.semi;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Arc2D;
import java.util.ArrayList;
import java.util.List;

import hu.szaniszlaid.ulwila.notes.util.Octave;
import hu.szaniszlaid.ulwila.notes.util.PaintStyle;
import hu.szaniszlaid.ulwila.notes.util.Tone;
import hu.szaniszlaid.ulwila.notes.whole.EighthNote;

public class EighthSemiNote extends EighthNote {

	int width = QUARTER_NOTE_WIDTH;
	int height = QUARTER_NOTE_HEIGHT;

	public EighthSemiNote(Octave octave, Tone tone, PaintStyle paintStlye) {
		super(octave, tone, paintStlye);
	}

	@Override
	public void drawNote(Graphics2D g) {

		g.setColor(getRightColor());
		Arc2D right = new Arc2D.Double(0, 0, width, height, 90, 180, Arc2D.CHORD);
		g.fill(right);
		g.setColor(Color.BLACK);
		g.draw(right);
		g.setColor(getLeftColor());
		Arc2D left = new Arc2D.Double(0, 0, width, height, 110, 140, Arc2D.CHORD);
		g.fill(left);
		g.setColor(Color.BLACK);
		g.draw(left);
	}

	@Override
	public List<Shape> getOctaveShapes() {
		int centerX = (width / 2 - width / 10);
		int centerY = (height / 2 - height / 10);
		List<Shape> octaveShapes = new ArrayList<>();
		octaveShapes.add(new Arc2D.Double(centerX, centerY, width / 5, height / 5, 90, 180, Arc2D.OPEN));
		return octaveShapes;

	}
}
