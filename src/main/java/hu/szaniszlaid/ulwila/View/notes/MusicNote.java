/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.szaniszlaid.ulwila.View.notes;

import java.awt.Color;
import java.awt.Graphics2D;

import hu.szaniszlaid.ulwila.note.util.Octave;

/**
 *
 * @author Franklin
 */
public abstract class MusicNote extends MusicComponent {

    Octave pitch;
    
    public final int offsetX = getNWidth() / 3 * 2;

    public MusicNote(NoteBuilder builder, Octave pitch) {
        super(builder);
        this.pitch = pitch;
    }

    public MusicNote setPitch(Octave pitch) {
        this.pitch = pitch;
        return this;
    }

    public Octave getPitch() {
        return pitch;
    }
    
    public int getNthOffset(int n){
        return n * (getNWidth() - (getNWidth() - offsetX));
    }

    @Override
    public void draw(Graphics2D g) {
        drawNote(g);
        switch (pitch) {
            case FIRST:
                drawColoredOctave(g, Color.BLACK);
                break;
            case SECOND:
                break;
            case THIRD:
                drawColoredOctave(g, Color.WHITE);
                break;
            case FOURTH:
                throw new UnsupportedOperationException();
               // break;
                
        }
    }

    public abstract void drawNote(Graphics2D g);

    private void drawColoredOctave(Graphics2D g, Color pithColor) {
        g.setColor(pithColor);
        drawOctave(g);
    }

    public abstract void drawOctave(Graphics2D g);

}
