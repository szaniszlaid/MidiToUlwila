package hu.szaniszlaid.ulwila.notes;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JComponent;

public abstract class MusicComponent extends JComponent implements FocusListener, MouseListener {

	public static final int QUARTER_NOTE_WIDTH = 100;
	public static final int QUARTER_NOTE_HEIGHT = 100;

	private static final int MARGIN = 6;
	
	public static final int MARGIN_LEFT = MARGIN / 2;
	public static final int MARGIN_RIGHT = MARGIN / 2;
	public static final int MARGIN_TOP = MARGIN / 2;
	public static final int MARGIN_BOTTOM = MARGIN / 2;

	private boolean selected = false;

	public MusicComponent() {
		addFocusListener(this);
        setFocusable(true);
        addMouseListener(this);
	}

	@Override
	protected void paintComponent(Graphics g) {
		paintComponent((Graphics2D) g);
		super.paintComponent(g);
	}

	protected void paintComponent(Graphics2D g) {
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		Dimension dimension = draw(g);
		// add margin TODO refactor, clear code, efficiency ...?
		dimension.width += MARGIN;
		dimension.height += MARGIN;
		
		setPreferredSize(dimension);
		
		// if component is selected draw selection shape around
		if (selected) {
			g.setColor(new Color(255,157, 0 ,128));
			g.fillRoundRect(0, 0, dimension.width, dimension.height, 20, 15);
		}
		
		invalidate();
	}

	public abstract Dimension draw(Graphics2D g);
	
    public abstract double getMusicalLength();
	
		
	@Override
	public void mousePressed(MouseEvent me) {
	    requestFocus();
	}


	@Override
	public void focusGained(FocusEvent e) {
		selected = true;
		repaint();
	}

	@Override
	public void focusLost(FocusEvent e) {
		selected = false;
		repaint();
	}
	
	@Override
	public String toString() {
	    return "MusicComponent - length: " + getMusicalLength();
	}
	
	public void mouseReleased(MouseEvent me) {}
	public void mouseClicked(MouseEvent me) {}
	public void mouseEntered(MouseEvent me) {}
	public void mouseExited(MouseEvent me) {}
}
