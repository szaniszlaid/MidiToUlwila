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

public class TripletNote extends MusicNote {

	private static int width = QUARTER_NOTE_WIDTH;
	private static int height = QUARTER_NOTE_HEIGHT;
	private boolean isFirst = false;

	public TripletNote(Octave octave, Tone tone, PaintStyle paintStlye) {
		super(octave, tone, paintStlye);
	}

	@Override
	public void drawNote(Graphics2D g) {		
		g.setColor(getColor());
		Arc2D eighth = new Arc2D.Double(getMarginLeft(), MARGIN_TOP, width, height, 90, 180, Arc2D.OPEN);
		g.fill(eighth);
		g.setColor(Color.BLACK);
		Arc2D.Double border = new Arc2D.Double(getMarginLeft(), MARGIN_TOP, width, height, 90, 180, Arc2D.CHORD);
		g.draw(border);
	}

	@Override
	public List<Shape> getOctaveShapes() {
		System.out.println( getMarginLeft());
		int x = width / 2 - width / 10 + getMarginLeft(); //FIXME 
		int y = height / 2 - height / 10 + MARGIN_TOP;
		List<Shape> octaveShapes = new ArrayList<>();
		octaveShapes.add(new Arc2D.Double(x, y, width / 5, height / 5, 90, 180, Arc2D.OPEN));
		return octaveShapes;
	}

	@Override
	public double getMusicalLength() {
		return (double) 1 / 12;
	}

	@Override
	public Dimension getSize() {
		return new Dimension(width / 2, height);
	}
	
	@Override
	public int getWidth() {
		return width / 2 + 1;
	}

	public boolean isFirst() {
		return isFirst;
	}

	public void setFirst(boolean isFirst) {
		this.isFirst = isFirst;
	}

	protected int getMarginLeft() {	
		if (isFirst) {
			return MARGIN_LEFT;
		} 
		return 0;
	}
}
