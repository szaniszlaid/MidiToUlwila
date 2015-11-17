package hu.szaniszlaid.ulwila.notes.semi;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Arc2D;
import java.util.ArrayList;
import java.util.List;

import hu.szaniszlaid.ulwila.note.util.Octave;
import hu.szaniszlaid.ulwila.note.util.Tone;
import hu.szaniszlaid.ulwila.notes.whole.HalfNote;

public class HalfSemiNote extends HalfNote {

    int width = QUARTER_NOTE_WIDTH;
    int height = QUARTER_NOTE_HEIGHT;

    public HalfSemiNote(Octave octave, Tone tone) {
        super(octave, tone);
    }

    @Override
    public Dimension drawNote(Graphics2D g) {
    	
        //Second quarter
        g.setColor(getLeftColor());
        Arc2D secQuarterLeft = new Arc2D.Double(getNthOffset(1) + MARGIN_LEFT, MARGIN_TOP, width, height, 90, 180, Arc2D.OPEN);
        g.fill(secQuarterLeft);
        g.setColor(Color.BLACK);
        Arc2D.Double secBorderLeft = new Arc2D.Double(getNthOffset(1) + MARGIN_LEFT, MARGIN_TOP, width, height, 90, 180, Arc2D.CHORD);
        g.draw(secBorderLeft);
        g.setColor(getRightColor());
        Arc2D secQuarterRight = new Arc2D.Double(getNthOffset(1) + MARGIN_LEFT, MARGIN_TOP, width, height, 90, -180, Arc2D.OPEN);
        g.fill(secQuarterRight);
        g.setColor(Color.BLACK);
        Arc2D secQuarterRightBorder = new Arc2D.Double(getNthOffset(1) + MARGIN_LEFT, MARGIN_TOP, width, height, 90, -180, Arc2D.CHORD);
        g.draw(secQuarterRightBorder);

    	// First quarter
        g.setColor(getLeftColor());
        Arc2D quarterLeft = new Arc2D.Double(MARGIN_LEFT, MARGIN_TOP, width, height, 90, 180, Arc2D.OPEN);
        g.fill(quarterLeft);
        g.setColor(Color.BLACK);
        Arc2D.Double borderLeft = new Arc2D.Double(MARGIN_LEFT, MARGIN_TOP, width, height, 90, 180, Arc2D.CHORD);
        g.draw(borderLeft);
        g.setColor(getRightColor());
        Arc2D quarterRight = new Arc2D.Double( MARGIN_LEFT, MARGIN_TOP, width, height, 90, -180, Arc2D.OPEN);
        g.fill(quarterRight);
        g.setColor(Color.BLACK);
        Arc2D quarterRightBorder = new Arc2D.Double( MARGIN_LEFT, MARGIN_TOP, width, height, 90, -180, Arc2D.CHORD);
        g.draw(quarterRightBorder);
        

		return new Dimension(width + getNthOffset(1), height);
    }

    @Override
    public List<Shape> getOctaveShapes() {
        int x = width / 2 - width / 10 + MARGIN_LEFT;
        int y = height / 2 - height / 10 + MARGIN_TOP;
        List<Shape> octaveShapes = new ArrayList<>();
        octaveShapes.add(new Arc2D.Double(x, y, width / 5, height / 5, 0, 360, Arc2D.CHORD));
        octaveShapes.add(new Arc2D.Double(x + getNthOffset(1), y, width / 5, height / 5, 0, 360, Arc2D.CHORD));
        return octaveShapes;

    }

}
