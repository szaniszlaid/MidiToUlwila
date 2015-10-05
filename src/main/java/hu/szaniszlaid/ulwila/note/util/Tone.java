/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.szaniszlaid.ulwila.note.util;

import java.awt.Color;

/**
 *
 * @author Franklin
 */
public enum Tone {
    C {

        @Override
        public Color getColor() {
            return Color.BLUE;
        }
    },D {

        @Override
        public Color getColor() {
            return Color.RED;
        }
    },E {

        @Override
        public Color getColor() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    },F {

        @Override
        public Color getColor() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    },G {

        @Override
        public Color getColor() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    },A {

        @Override
        public Color getColor() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    },H {

        @Override
        public Color getColor() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    }, CIS {

        @Override
        public Color getColor() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    },DIS {

        @Override
        public Color getColor() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    },FIS {

        @Override
        public Color getColor() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    },GIS {

        @Override
        public Color getColor() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    },AIS {

        @Override
        public Color getColor() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    };
    
    public static boolean isSemiTone(Tone tone){
        if (tone.equals(CIS) || tone.equals(DIS) ||tone.equals(FIS) ||tone.equals(GIS) ||tone.equals(AIS)){
            return true;
        } else {
            return false;
        }
    }
    
    public abstract Color getColor();
}


