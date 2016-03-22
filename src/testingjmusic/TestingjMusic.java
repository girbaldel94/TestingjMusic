package testingjmusic;
/*
import jm.music.data.*;
import jm.JMC;
import jm.music.tools.*;
import jm.util.*;

public class TestingjMusic implements JMC{
    
    
     // @param args the command line arguments
     
    public static void main(String[] args) {
        int[] pitches = {D4, E4, FS4, G4, A4, B4, CS5, D5};
        Phrase scale = new Phrase();
        for (int i = 0; i < pitches.length; i++) {
            Note n = new Note(pitches[i], QUARTER_NOTE);
            scale.addNote(n);
        }
        for (int i = 1; i < pitches.length+1; i++) {
            Note n = new Note(pitches[pitches.length-i], QUARTER_NOTE);
            scale.addNote(n);
        }
        Part part = new Part();
        part.add(scale);
        Phrase scale2 = new Phrase();
        for (int i = 0; i < pitches.length; i++) {
            Note n = new Note(pitches[i], QUARTER_NOTE);
            scale2.addNote(n);
        }
        for (int i = 1; i < pitches.length+1; i++) {
            Note n = new Note(pitches[pitches.length-i], QUARTER_NOTE);
            scale2.addNote(n);
        }
        part.appendPhrase(scale2);
        Score sc = new Score(part);
        Write.midi(sc, "CScale.mid");        
        //View.notate(part, 20, 100);
        View.notation(sc);
        
    }
    
}
*/
import jm.JMC;
import jm.music.data.*;
import jm.util.View;
import jm.util.Write;

/**
 * 
 * @author Pol Girbal
 */
public final class TestingjMusic implements JMC{
	
    public static void main(String[] args){
        Score score = new Score("Random melody");
        Part part = new Part("Piano", PIANO, 0);
        Phrase phrase = new Phrase(0.0);
        int[] pitches = {D4, E4, FS4, G4, A4, B4, CS5, D5};
        int pitch; 
        for(int i=0; i<48; i++){
            pitch = (int) (Math.random()*pitches.length);
            Note note = new Note(pitches[pitch], QUAVER);
            phrase.addNote(note);
        }
        
        part.addPhrase(phrase);
        score.addPart(part);
        Write.midi(score, "Drandom.mid");
        View.notation(score);
    }
}