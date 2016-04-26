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
        int numerator = 4;
        int denominator = 4;
        score.setTimeSignature(numerator,denominator); //compàs 
        score.setKeySignature(0); //natural
        score.setKeyQuality(0); //major
        score.setTempo(140);
        Part rpart = new Part("Piano R", PIANO, 0);
        Part lpart = new Part("Piano L", PIANO, 0);
        
        Tone t = new Tone(C3,MAJOR_SCALE);
        int[] pitches;
        
        Form f = new Form(8);
        String[] form = {"A","B","A"};
        for (int i = 0; i < form.length; i++) {
            if (i == 0) {
                Phrase rphr = new Phrase();
                Phrase lphr = new Phrase();
                int [] estructure = f.getStruct();
                for (int j = 0; j < estructure.length; j++) {
                    System.out.println("FOR 2");
                    pitches = t.getChord(estructure[j]);
                    lphr.addNote(new Note(pitches[0]-12,WN));
                    double r;
                    double sum = 0;
                    while (sum < numerator) {
                        System.out.println("WHILE");
                        r = getRhythm();
                        if (sum + r > numerator) r = numerator - sum;
                        rphr.addNote(new Note(getPitch(pitches),r));
                        sum += r;
                    }
                }
                rpart.addPhrase(rphr);
                lpart.addPhrase(lphr);
            } else if (i != 0 && form[i].equals("A")) {
                Phrase rphr = new Phrase();
                Phrase lphr = new Phrase();
                Note[] phrAr = rpart.getPhrase(0).getNoteArray();
                Note[] phrAl = lpart.getPhrase(0).getNoteArray();
                for (Note noteR : phrAr) {
                    rphr.addNote(noteR);
                }
                for (Note noteL : phrAl) {
                    lphr.addNote(noteL);
                }
                rpart.addPhrase(rphr);
                lpart.addPhrase(lphr);
            } else if (form[i].equals("B")) {
                Phrase rphr = new Phrase();
                Phrase lphr = new Phrase();
                Tone rt = new Tone(A2,MINOR_SCALE);
                System.out.println("FOR 1");
                int [] estructure = f.getStruct();
                for (int j = 0; j < estructure.length; j++) {
                    System.out.println("FOR 2");
                    pitches = rt.getChord(estructure[j]);
                    lphr.addNote(new Note(pitches[0]-12,WN));
                    double r;
                    double sum = 0;
                    while (sum < numerator) {
                        System.out.println("WHILE");
                        r = getRhythm();
                        if (sum + r > numerator) r = numerator - sum;
                        rphr.addNote(new Note(getPitch(pitches),r));
                        sum += r;
                    }
                }
                rpart.addPhrase(rphr);
                lpart.addPhrase(lphr);
            }
        }

        score.addPart(rpart);
        score.addPart(lpart);
        Write.midi(score, "Cform.mid");
        View.notation(score);
    }
    
    private static int getPitch(int[] pitches) {
        int pitch = (int) (Math.random()*pitches.length);
        return pitches[pitch];
    }
    
    private static double getRhythm() {
        Double r = Math.random();
        Double rhythm = 0.0;
        if (r < 0.01) rhythm = WN;
        else if (r >= 0.01 && r < 0.15) rhythm = HN;
        else if (r >= 0.15 && r < 0.6) rhythm = QN;
        else if (r >= 0.6 && r < 1.0) rhythm = EN;
        
        return rhythm;
    }
}