package hu.szaniszlaid.ulwila.notes.util;

public enum Octave {
    FIRST, SECOND, THIRD, FOURTH;
    
    public int getMidiOffset() {
    	switch (this) {
		case FIRST:			
			return -12;
		case SECOND:			
			return 0;		
		case THIRD:			
			return 12;		
		case FOURTH:			
			return 24;		
		default: 
			throw new RuntimeException("Unknown octave");
	} 		
    }
}
