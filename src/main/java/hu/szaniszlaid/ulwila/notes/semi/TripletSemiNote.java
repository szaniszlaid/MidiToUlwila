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
import hu.szaniszlaid.ulwila.notes.whole.TripletNote;

public class TripletSemiNote extends TripletNote{

	public TripletSemiNote(Octave octave, Tone tone, PaintStyle paintStlye) {
		super(octave, tone, paintStlye);
	}
	
	 @Override
	    public void drawNote(Graphics2D g) {		 
		 
	        g.setColor(getRightColor());
	        Arc2D left = new Arc2D.Double(getHorizontalMargin(), MARGIN_VERTICAL, QUARTER_NOTE_WIDTH, QUARTER_NOTE_HEIGHT, 90, 180, Arc2D.CHORD);
	        g.fill(left);
	        g.setColor(getLeftColor());
	        Arc2D right = new Arc2D.Double(getHorizontalMargin(), MARGIN_VERTICAL, QUARTER_NOTE_WIDTH, QUARTER_NOTE_HEIGHT, 110, 140, Arc2D.CHORD);
	        g.fill(right);
	        g.setColor(Color.BLACK);
	        Arc2D.Double border = new Arc2D.Double(getHorizontalMargin(), MARGIN_VERTICAL, QUARTER_NOTE_WIDTH, QUARTER_NOTE_HEIGHT, 90, 180, Arc2D.CHORD);
	        g.draw(border);
	    }

	    @Override
	    public List<Shape> getOctaveShapes() {
	        int centerX = (QUARTER_NOTE_WIDTH / 2 - QUARTER_NOTE_WIDTH / 10) + getHorizontalMargin();
	        int centerY = (QUARTER_NOTE_HEIGHT / 2 - QUARTER_NOTE_HEIGHT / 10) + MARGIN_VERTICAL;
	        List<Shape> octaveShapes = new ArrayList<>();
	        octaveShapes.add(new Arc2D.Double(centerX, centerY, QUARTER_NOTE_WIDTH / 5, QUARTER_NOTE_HEIGHT / 5, 90, 180, Arc2D.OPEN));
	        return octaveShapes;

	    }

}
