/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.szaniszlaid.ulwila.View.notes;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JComponent;

import hu.szaniszlaid.ulwila.note.util.Octave;

/**
 *
 * @author Franklin
 */
public abstract class MusicElement extends JComponent{
    
    private int nWidth;
    private int nHeight;

    private int containerWidth;
    private int containerHeight;

    private int margin;

    private Color color;

   //TODO ez fog kikerülni note-ba private Pitch pitch;

    public MusicElement(NoteBuilder builder) {
        this.color = builder.getColor();
        this.containerHeight = builder.getContainerHeight();
        this.containerWidth = builder.getContainerWidth();
        this.nHeight = builder.getnHeigh();
        this.nWidth = builder.getnWidth();
        this.margin = builder.getMargin();
       
        reSize();
    }

    public MusicElement() {
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

    public Color getColor() {
        return color;
    }
    
    public MusicElement setContainerWidth(int width){
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
        Color color = Color.BLACK;
        Octave pitch = Octave.SECOND;

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

        public NoteBuilder setColor(Color color) {
            this.color = color;
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

        public Color getColor() {
            return color;
        }

        public int getMargin() {
            return margin;
        }

    }
    
}
