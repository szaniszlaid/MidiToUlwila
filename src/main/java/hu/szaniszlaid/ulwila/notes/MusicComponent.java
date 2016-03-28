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

	public static final int QUARTER_NOTE_WIDTH = 60;
	public static final int QUARTER_NOTE_HEIGHT = 60;

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
		setPreferredSize(new Dimension(getWidth(), getHeight()));
	}

	@Override
	protected void paintComponent(Graphics g) {
		paintComponent((Graphics2D) g);
		super.paintComponent(g);
	}

	protected void paintComponent(Graphics2D g) {
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		draw(g);
		// if component is selected draw selection shape around
		if (selected) {
			g.setColor(new Color(255, 157, 0, 128));
			g.fillRoundRect(MARGIN_LEFT / 2, MARGIN_TOP / 2, getWidth(), getHeight(), 20, 15);
		}
	}

	protected abstract void draw(Graphics2D g);

	public abstract double getMusicalLength();

	@Override
	public abstract Dimension getSize();

	@Override
	public int getWidth() {
		return getSize().width + MARGIN_LEFT + MARGIN_RIGHT;
	}

	@Override
	public int getHeight() {
		return getSize().height + MARGIN_TOP + MARGIN_BOTTOM;
	}

	@Override
	public void mousePressed(MouseEvent me) {
		requestFocus();
	}

	@Override
	public void focusGained(FocusEvent e) {
		selected = true;
		repaint();
		((JComponent) getParent()).scrollRectToVisible(getBounds());
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

	@Override
	public void mouseReleased(MouseEvent me) {
	}

	@Override
	public void mouseClicked(MouseEvent me) {
	}

	@Override
	public void mouseEntered(MouseEvent me) {
	}

	@Override
	public void mouseExited(MouseEvent me) {
	}
		
}
