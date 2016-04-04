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

import hu.szaniszlaid.ulwila.notes.util.PaintStyle;

public abstract class MusicComponent extends JComponent implements FocusListener, MouseListener {

	public static final int QUARTER_NOTE_WIDTH = 120;
	public static final int QUARTER_NOTE_HEIGHT = 120;

	private static final int MARGIN = 16;

	public static final int MARGIN_HORIZONTAL = MARGIN / 2;

	public static final int MARGIN_VERTICAL = MARGIN / 2;

	private boolean selected = false;

	private PaintStyle paintStyle;

	public MusicComponent(PaintStyle paintStyle) {
		this.setPaintStyle(paintStyle);
		addFocusListener(this);
		setFocusable(true);
		addMouseListener(this);
		setPreferredSize(new Dimension(getWidth(), getHeight()));
		setAlignmentX(CENTER_ALIGNMENT);
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
			g.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 15);
		}
	}

	protected int getHorizontalMargin() {
		return MARGIN_HORIZONTAL;
	}

	protected int getVerticalMargin() {
		return MARGIN_VERTICAL;
	}

	protected abstract void draw(Graphics2D g);

	public abstract double getMusicalLength();

	@Override
	public abstract Dimension getSize();

	@Override
	public int getWidth() {
		return getSize().width + getHorizontalMargin() * 2;
	}

	@Override
	public int getHeight() {
		return getSize().height + getVerticalMargin() * 2;
	}

	public PaintStyle getPaintStyle() {
		return paintStyle;
	}

	public void setPaintStyle(PaintStyle paintStyle) {
		this.paintStyle = paintStyle;
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
