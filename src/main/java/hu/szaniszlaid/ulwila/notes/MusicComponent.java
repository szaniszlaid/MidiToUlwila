package hu.szaniszlaid.ulwila.notes;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JComponent;

public abstract class MusicComponent extends JComponent {

    public static final int QUARTER_NOTE_WIDTH = 100;
    public static final int QUARTER_NOTE_HEIGHT = 100;

    private static final int MARGIN = 10;

    private Dimension dimension = new Dimension(QUARTER_NOTE_WIDTH, QUARTER_NOTE_HEIGHT);

    @Override
    public Dimension getPreferredSize() {
        return getDimension();
    };

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        paintComponent((Graphics2D) g);
    }

    protected void paintComponent(Graphics2D g) {
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        Dimension dimension = draw(g);
        //add margin TODO refactor, clear code, efficiency ...? 
        dimension.width += MARGIN;
        dimension.height += MARGIN;
        this.dimension = dimension;
        invalidate();
    }

    public abstract Dimension draw(Graphics2D g);

    public Dimension getDimension() {
        return dimension;
    }
}
