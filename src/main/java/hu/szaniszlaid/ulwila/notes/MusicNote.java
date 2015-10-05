/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.szaniszlaid.ulwila.notes;

import java.awt.Color;
import java.awt.Graphics2D;

import hu.szaniszlaid.ulwila.note.util.Octave;
import hu.szaniszlaid.ulwila.note.util.Tone;

/**
 *
 * @author Franklin
 */
public abstract class MusicNote extends MusicComponent {

    Octave octave;
    private Tone tone;
    
    public final int offsetX = getNWidth() / 3 * 2;

    public MusicNote(NoteBuilder builder, Octave octave, Tone tone) {
        super(builder);
        this.octave = octave;
        this.tone = tone;
    }

    public MusicNote setOctave(Octave octave) {
        this.octave = octave;
        return this;
    }

    public Octave getOctave() {
        return octave;
    }
    
    public int getNthOffset(int n){
        return n * (getNWidth() - (getNWidth() - offsetX));
    }

    @Override
    public void draw(Graphics2D g) {
        drawNote(g);
        switch (octave) {
            case FIRST:
                drawColoredOctave(g, Color.BLACK);
                break;
            case SECOND:
                break;
            case THIRD:
                drawColoredOctave(g, Color.WHITE);
                break;                
        }
    }

    public abstract void drawNote(Graphics2D g);

    private void drawColoredOctave(Graphics2D g, Color octaveColor) {
        g.setColor(octaveColor);
        drawOctave(g);
    }

    public abstract void drawOctave(Graphics2D g);

    public Tone getTone() {
        return tone;
    }

    public void setTone(Tone tone) {
        this.tone = tone;
    }

}
