package hu.szaniszlaid.ulwila.notes;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JComponent;

/**
 *
 * @author Franklin
 */
public abstract class MusicComponent extends JComponent{
    
    private int nWidth;
    private int nHeight;

    private int containerWidth;
    private int containerHeight;

    private int margin;

    public MusicComponent(NoteBuilder builder) {
        this.containerHeight = builder.getContainerHeight();
        this.containerWidth = builder.getContainerWidth();
        this.nHeight = builder.getnHeigh();
        this.nWidth = builder.getnWidth();
        this.margin = builder.getMargin();
       
        reSize();
    }

    public MusicComponent() {
        this(new NoteBuilder());
    }
    
    public final void reSize(){
        setPreferredSize(new Dimension(containerWidth + margin, containerHeight + margin));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        paintComponent((Graphics2D) g);
    }

    protected void paintComponent(Graphics2D g) {
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        draw(g);
    }

    public abstract void draw(Graphics2D g);

    public int getNWidth() {
        return nWidth;
    }

    public int getNHeight() {
        return nHeight;
    }
    
    public MusicComponent setContainerWidth(int width){
         this.containerWidth = width;
         reSize();
         return this;
    }

    
    public static class NoteBuilder {

        int nWidth = 100;
        int nHeigh = 100;
        int margin = 10;
        int containerWidth = nWidth + margin;
        int containerHeight = nHeigh + margin;

        public NoteBuilder setnHeigh(int nHeigh) {
            this.nHeigh = nHeigh;
            return this;
        }

        public NoteBuilder setnWidth(int nWidth) {
            this.nWidth = nWidth;
            return this;
        }

        public NoteBuilder setContainerWidth(int containerWidth) {
            this.containerWidth = containerWidth;
            return this;
        }

        public NoteBuilder setContainerHeight(int containerHeight) {
            this.containerHeight = containerHeight;
            return this;
        }

        
        public NoteBuilder setMargin(int margin) {
            this.margin = margin;
            return this;
        }

        public int getnHeigh() {
            return nHeigh;
        }

        public int getnWidth() {
            return nWidth;
        }

        public int getContainerWidth() {
            return containerWidth;
        }

        public int getContainerHeight() {
            return containerHeight;
        }

        public int getMargin() {
            return margin;
        }

    }
    
}
