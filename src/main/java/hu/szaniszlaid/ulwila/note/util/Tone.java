/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.szaniszlaid.ulwila.note.util;

public enum Tone {
	C, CIS, D, DIS, E, F, FIS, G, GIS, A, AIS, H;


	public static boolean isSemiTone(Tone tone) {
		if (tone.equals(CIS) || tone.equals(DIS) || tone.equals(FIS) || tone.equals(GIS) || tone.equals(AIS)) {
			return true;
		} else {
			return false;
		}
	}

	public boolean isSemiTone() {
		if (this.equals(CIS) || this.equals(DIS) || this.equals(FIS) || this.equals(GIS) || this.equals(AIS)) {
			return true;
		} else {
			return false;
		}
	}
}
