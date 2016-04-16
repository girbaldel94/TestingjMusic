package testingjmusic;

import static java.lang.Math.*;
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
        score.setTimeSignature(4,4); //compàs 4/4
        score.setKeySignature(0); //natural
        score.setKeyQuality(0); //major
        Part rpart = new Part("Piano R", PIANO, 0);
        Part lpart = new Part("Piano L", PIANO, 0);
        Phrase rphr = new Phrase(0.0);
        Phrase lphr = new Phrase(0.0);
        //int[] pitches = {C3, E3, G3, C4};
        Tone t = new Tone(C3,MAJOR_SCALE);
        int[] pitches = new int[4];
        int[] estructure = {1, 2, 4, 5, 1, 4, 5, 1};
        double[] rythms = {EN,QN,Q};
        int pitch, rythm, m = 0;
        double qn = 0;
        double prev = -1;
        while(qn<32){
            if(floor(qn)%4==0 && floor(qn)!=prev){
                pitches = t.getChord(estructure[m]);
                lphr.addNote(new Note(pitches[0]-12,WN));
                m++;
            }
            
            pitch = (int) (Math.random()*pitches.length);
            rythm = (int) (Math.random()*rythms.length);
            Note note = new Note(pitches[pitch], rythms[rythm]);
            rphr.addNote(note);
            prev = qn;
            qn+=rythms[rythm];         
        }
        
        rpart.addPhrase(rphr);
        lpart.addPhrase(lphr);
        score.addPart(rpart);
        score.addPart(lpart);
        Write.midi(score, "Crandom.mid");
        View.notation(score);
    }
}