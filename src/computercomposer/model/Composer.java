package computercomposer.model;

import java.io.File;
import java.util.HashMap;
import jm.JMC;
import jm.music.data.*;
import jm.util.Play;
import jm.util.View;
import jm.util.Write;

/**
 * This class generates a 2 voices melody
 * @author Pol Girbal i Jornet
 */
public final class Composer implements JMC{
    
    private Score score;
    private int numerator;
    private int denominator;
    private Part melody;
    private Part accompaniment;
    private Tonality tonality;
    private int[] pitches;
    private Form form;
    private String[] phrases; //Canviar per STRING
    private HashMap<String,Phrase> melodyTable;
    private HashMap<String,Phrase> accompTable;
    
    /**
     * Assigns name, time signature, tempo, instrument, tonality, bars and form
     * @param name The title of the composition
     * @param n The numerator of the time signature
     * @param d The denominator of the time signature
     * @param tempo The tempo of the composition
     * @param inst The instrument of the composition
     * @param p The pitch of the tonality
     * @param m The mode of the tonality
     * @param bars The number of bars for each phrase
     * @param f The form of the composition
     */
    public Composer(String name, int n, int d, int tempo, int inst, int p, int[] m, int bars, String[] f) {
        score = new Score(name);
        numerator = n;
        denominator = d;
        score.setTimeSignature(n, d);
        score.setTempo(tempo);
        melody = new Part("Melody", inst, 0);
        accompaniment = new Part("Accompaniment", inst, 0);
        tonality = new Tonality(p, m);
        form = new Form(bars);
        phrases = f;
        melodyTable = new HashMap<>();
        accompTable = new HashMap<>();
    }
    
    /**
     * A method that generates and displays a score with a 2 voices melody
     */
    public void compose(){
        for (String ph : phrases) {
            if (melodyTable.containsKey(ph)) {
                Phrase rphr = new Phrase();
                Phrase lphr = new Phrase();
                Note[] phrAr = melodyTable.get(ph).getNoteArray();
                Note[] phrAl = accompTable.get(ph).getNoteArray();
                for (Note noteR : phrAr) {
                    rphr.addNote(noteR);
                }
                for (Note noteL : phrAl) {
                    lphr.addNote(noteL);
                }
                melody.addPhrase(rphr);
                accompaniment.addPhrase(lphr);
            } else {
                if (ph.equals("B")) {
                    if(tonality.getMode() == MAJOR_SCALE) {
                        tonality.setPitch(tonality.getPitch()-3);
                        tonality.setMode(MINOR_SCALE);
                    } else if(tonality.getMode() == MINOR_SCALE) {
                        tonality.setPitch(tonality.getPitch()+3);
                        tonality.setMode(MAJOR_SCALE);
                    }
                }
                Phrase rphr = new Phrase();
                Phrase lphr = new Phrase();
                int [] estructure = form.getStruct();
                for (int j = 0; j < estructure.length; j++) {
                    pitches = tonality.getChord(estructure[j]);
                    /*switch (numerator) {
                        case 2:
                            lphr.addNote(new Note(pitches[0]-12,HN));
                            break;
                        case 3:
                            lphr.addNote(new Note(pitches[0]-12,DHN));
                            break;
                        case 4:
                            lphr.addNote(new Note(pitches[0]-12,WN));
                            break;
                        default:
                            break;
                    }*/
                    double rr;
                    double summ = 0;
                    while (summ < numerator) {
                        if ( j == 0 || j == estructure.length-1 ) {
                            switch (numerator) {
                                case 2:
                                    lphr.addNote(new Note(pitches[0]-12,HN));
                                    summ+=2;
                                    break;
                                case 3:
                                    lphr.addNote(new Note(pitches[0]-12,DHN));
                                    summ+=3;
                                    break;
                                case 4:
                                    lphr.addNote(new Note(pitches[0]-12,WN));
                                    summ+=4;
                                    break;
                                default:
                                    break;
                            }
                        } else {
                            rr = getRhythmAcomp();
                            if (summ+rr>numerator) rr = numerator - summ;
                            lphr.addNote(new Note(getPitch(pitches)-12,rr));
                            summ+=rr;
                        }
                    }
                    double r;
                    double sum = 0;
                    while (sum < numerator) {
                        if ( j == estructure.length-1 ) {
                            switch (numerator) {
                                case 2:
                                    rphr.addNote(new Note(pitches[0],HN));
                                    sum+=2;
                                    break;
                                case 3:
                                    rphr.addNote(new Note(pitches[0],DHN));
                                    sum+=3;
                                    break;
                                case 4:
                                    rphr.addNote(new Note(pitches[0],WN));
                                    sum+=4;
                                    break;
                                default:
                                    break;
                            }
                        } else {
                            r = getRhythm();
                            if (sum + r > numerator) r = numerator - sum;
                            rphr.addNote(new Note(getPitch(pitches),r));
                            sum += r;
                        }
                    }
                }
                melodyTable.put(ph, rphr);
                accompTable.put(ph, lphr);
                melody.addPhrase(rphr);
                accompaniment.addPhrase(lphr);
            } 
        }
        melodyTable.clear();
        accompTable.clear();
        score.addPart(melody);
        score.addPart(accompaniment);
        //Write.midi(score, "name.mid");
        View.notation(score,2,2);
        //Play.midi(score);

    }
    
    /**
     * A method that saves the score as a MIDI file.
     * @param f The name of the file
     */
    public void saveFile(File f) {
        String path = f.getPath();
        if(!path.endsWith(".mid")) path += ".mid";
        Write.midi(score,path);
    }
    
    /**
     * A method that returns a pitch from a given list of pitch values
     * @param pitches The list of pitch values
     * @return a random pitch value
     */
    private int getPitch(int[] pitches) {
        int p = (int) (Math.random()*pitches.length);
        return pitches[p];
    }
    
    /**
     * A method that returns a semi-random rhythm value
     * WN, HN, QN or EN
     * @return rhythm value
     */
    private double getRhythm() {
        Double r = Math.random();
        Double rhythm = 0.0;
        if (r < 0.01) rhythm = WN;
        else if (r >= 0.01 && r < 0.15) rhythm = HN;
        else if (r >= 0.15 && r < 0.6) rhythm = QN;
        else if (r >= 0.6 && r < 1.0) rhythm = EN;
        
        return rhythm;
    }
    
    /**
     * A method taht returns a semi-random rhythm value
     * QN, HN or WN
     * @return rhythm value
     */
    private double getRhythmAcomp() {
        Double r = Math.random();
        Double rhythm;
        if (r < 0.4) rhythm = QN;
        else if (r >= 0.4 && r < 0.7) rhythm = HN;
        else rhythm = WN;
        
        return rhythm;
    }
}