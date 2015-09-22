/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.szaniszlaid.ulwila.notesGraphics;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Arc2D;

/**
 *
 * @author Franklin
 */
public class NotesGraphics {
    
    static final int MARGIN = 15;
    static final int WIDTH = 100;
    static final int HEIGH = 100;
    Graphics2D g2d;

    public NotesGraphics(Graphics g) {
        this.g2d = (Graphics2D) g;
         g2d.setRenderingHint (RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    }
    
    
    public void drawEightNote(int count){
        g2d.setColor(Color.red);
        Arc2D quarter = new Arc2D.Double(getXposition(count), getYPosition(count), WIDTH, HEIGH, 90, 180, Arc2D.OPEN);
        g2d.fill(quarter);
        g2d.setColor(Color.BLACK);
        Arc2D.Double border = new Arc2D.Double(getXposition(count), getYPosition(count), WIDTH, HEIGH, 90, 180, Arc2D.CHORD);
        g2d.draw(border);
    }
    
    public void drawQuarterNote(int count) {
        g2d.setColor(Color.green);
        g2d.fillOval(getXposition(count), getYPosition(count), WIDTH, HEIGH);
        g2d.setColor(Color.BLACK);
        g2d.drawOval(getXposition(count), getYPosition(count), WIDTH, HEIGH);
        
    }
    
    public void drawHalfNote(int count){
        g2d.setColor(Color.orange);
        g2d.fillOval(getXposition(count) + (WIDTH/2), getYPosition(count), WIDTH, HEIGH);
        g2d.setColor(Color.BLACK);
        g2d.drawOval(getXposition(count) + (WIDTH/2), getYPosition(count), WIDTH, HEIGH);
        g2d.setColor(Color.orange);
        g2d.fillOval(getXposition(count), getYPosition(count), WIDTH, HEIGH);
        g2d.setColor(Color.BLACK);
        g2d.drawOval(getXposition(count), getYPosition(count), WIDTH, HEIGH);

    }
    
    private int getXposition(int count){
        int col = count % 3;
        return (col * WIDTH) + MARGIN * col;
    }
    
    private int getYPosition(int count){
         int row = (int) Math.round((count) / 3);
         return (row * HEIGH) + MARGIN * row;
    }
}
