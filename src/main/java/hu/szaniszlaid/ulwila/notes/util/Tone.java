package hu.szaniszlaid.ulwila.notes.util;



public enum Tone {
    C, CIS, D, DIS, E, F, FIS, G, GIS, A, AIS, H;
	
	public static final int BASEKEY = 60; //middle C note TODO make it modifiable from properties window

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
   
    /**
     * @see <a href="http://www.electronics.dit.ie/staff/tscarff/Music_technology/midi/midi_note_numbers_for_octaves.htm">MidiNumbers</a>
     */
	public int getMidiOffset() {
		switch (this) {
			case C:			
				return 0;
			case CIS:			
				return 1;
			case D:			
				return 2;
			case DIS:			
				return 3;
			case E:			
				return 4;
			case F:			
				return 5;
			case FIS:			
				return 6;
			case G:			
				return 7;
			case GIS:			
				return 8;
			case A:			
				return 9;
			case AIS:			
				return 10;
			case H:			
				return 11;				
			default: 
				throw new RuntimeException("Missing offset");
		} 		
	}
}
