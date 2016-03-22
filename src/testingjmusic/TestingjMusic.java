package testingjmusic;

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
        score.setTimeSignature(5,8); //compàs 5/8
        score.setKeySignature(2); //dos sostinguts
        score.setKeyQuality(0); //major
        Part part = new Part("Piano", PIANO, 0);
        Phrase phrase = new Phrase(0.0);
        int[] pitches = {D4, FS4, A4, D5};
        double[] rythms = {Q, QN, DQN};
        int pitch, rythm;
        int quavers = 0;
        while(quavers<40){
            pitch = (int) (Math.random()*pitches.length);
            rythm = (int) (Math.random()*rythms.length);
            Note note = new Note(pitches[pitch], rythms[rythm]);
            phrase.addNote(note);
            quavers+=rythm+1;
        }
        
        part.addPhrase(phrase);
        score.addPart(part);
        Write.midi(score, "Arandom.mid");
        View.notation(score);
    }
}