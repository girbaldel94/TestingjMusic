package computercomposer.controller;

import computercomposer.model.Composer;
import java.io.File;
import java.util.HashMap;
import jm.JMC;

/**
 * This class is the Controller between Model and Visor
 * @author Pol Girbal i Jornet
 */
public class Controller implements JMC {
    private Composer composer;
    private String name;
    private int n;
    private int d;
    private int tempo;
    private int inst;
    private int p;
    private int[] m;
    private int bars;
    private String[] f;
    
    /**
     * Assigns the name, the tonality, the form, the instrument, the number of bars, the time signature and the tempo
     * @param name The title of the composition
     * @param major The mode of the tonality
     * @param pitch The pitch of the tonality
     * @param form The form of the composition
     * @param inst The instrument of the composition
     * @param bars The number of bars for each phrase
     * @param timesig The time signature of the composition
     * @param tempo The tempo of the composition
     */
    public Controller(String name, Boolean major, String pitch, String form, String inst, String bars, String timesig, String tempo){
        this.name = name;
        n = Integer.valueOf(timesig.substring( 0, 1));
        d = Integer.valueOf(timesig.substring(4));
        this.tempo = getTempo(tempo);
        this.inst = getInst(inst);
        p = getPitch(pitch);
        m = getMode(major);
        this.bars = Integer.valueOf(bars);
        f = form.split("");
        composer = new Composer(name,n,d,this.tempo,this.inst,p,m,this.bars,f);
    }
    
    /**
     * A method that calls the saveFile of the Composer class
     * @param f File to save
     */
    public void saveFile(File f) {
        composer.saveFile(f);
    }
    
    /**
     * A method that calls the compose method of the Composer class
     */
    public void compose() {
        composer.compose();
    }
    
    /**
     * A method that returns the instrument
     * @param i instrument
     * @return The instrument value
     */
    private int getInst(String i) {
        switch (i) {
            case "Piano":
                return PIANO;
            case "Guitarra":
                return GUITAR;
            case "Violí":
                return VIOLIN;
            case "Clarinet":
                return CLARINET;
            default:
                return -1;
        }
        
    }
    
    /**
     * A method that returns the pitch of tonality
     * @param pitch Pitch of tonality
     * @return pitch value
     */
    private int getPitch(String pitch) {
        switch (pitch) {
            case "La":
                return A3;
            case "La b":
                return AF3;
            case "La #":
                return AS3;
            case "Si":
                return B3;
            case "Si b":
                return BF3;
            case "Do":
                return C3;
            case "Do #":
                return CS3;
            case "Re":
                return D3;
            case "Re b":
                return DF3;
            case "Re #":
                return DS3;
            case "Mi":
                return E3;
            case "Mi b":
                return EF3;
            case "Fa":
                return F3;
            case "Fa #":
                return FS3;
            case "Sol":
                return G3;
            case "Sol b":
                return GF3;
            case "Sol #":
                return GS3;    
            default:
                return -1;
        }
    }
    
    /**
     * A method that returns the tempo
     * @param tempo The tempo of the composition
     * @return tempo value
     */
    private int getTempo(String tempo) {
        //Largo, Adagio, Andante, Moderato, Allegretto, Allegro
        HashMap<String, Integer> tempos = new HashMap<>();
        tempos.put("Largo", 60);
        tempos.put("Adagio", 70);
        tempos.put("Andante", 80);
        tempos.put("Moderato", 100);
        tempos.put("Allegretto", 120);
        tempos.put("Allegro", 140);
        return tempos.get(tempo);
    }
    
    /**
     * A method that returns the mode of the tonality
     * @param major mode of the tonality
     * @return mode value
     */
    private int[] getMode(boolean major) {
        if(major) return MAJOR_SCALE;
        else return MINOR_SCALE;
    }
    
}
