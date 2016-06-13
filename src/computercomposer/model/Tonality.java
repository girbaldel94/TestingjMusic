package computercomposer.model;

import java.util.ArrayList;
import jm.JMC;

/**
 * This class generates all the notes that can be used for each tonality.
 * @author Pol Girbal i Jornet
 */
public class Tonality implements JMC {
    private ArrayList<Integer> pitches;
    private int pitch;
    private int[] mode;
    private int[] scale;
    
    /**
     * Assigns pitch and mode values to the tonality object upon creation.
     * @param pitch Constant note value of the tonality (e.g., C3)
     * @param mode Constant scale value of the tonality (e.g., MAJOR_SCALE)
     */
    public Tonality(int pitch, int[] mode){
        pitches = new ArrayList<>();
        generateNotes();
        this.pitch = pitch;
        this.mode = mode;
        scale = new int[14];
        generateScale();
    }
    
    /**
     * A method that generates all the notes from C2 to B5.
     */
    private void generateNotes(){
        for (int i = 0; i < 48; i++) {
            pitches.add(C2+i);
        }
    }   
    
    /**
     * A method that generates the notes that can be used by a tonality.
     */
    private void generateScale() {
        int s = pitches.indexOf(pitch);
        for (int i = 0; i < 7; i++) {
            scale[i] = pitches.get((s+mode[i]) % pitches.size());
        } s+=12;
        for (int i = 0; i < 7; i++) {
            scale[i+7] = pitches.get(s+mode[i]);
        }
    }
    
    /**
     * A method that returns a four notes chord of a given scale degree.
     * @param degree The scale degree
     * @return The four notes chord of given degree
     */
    public int[] getChord(int degree) {
        int[] c = new int[4];
        c[0] = scale[degree-1];
        c[1] = scale[degree+1];
        c[2] = scale[degree+3];
        c[3] = scale[degree+6];
        return c;
    }
    
    /**
     * A method that returns the note of a given scale degree
     * @param degree The scale degree
     * @return Constant pitch value of given degree
     */
    public int getNote(int degree) {
        return scale[degree-1];
    }

    /**
     * Returns the pitch
     * @return Constant pitch value
     */
    public int getPitch() {
        return pitch;
    }

    /**
     * Returns the mode
     * @return Constant scale value
     */
    public int[] getMode() {
        return mode;
    }
    
    /**
     * Assigns the pitch value
     * @param pitch Constant pitch value
     */
    public void setPitch(int pitch) {
        this.pitch = pitch;
    }
    
    /**
     * Assigns the mode
     * @param mode Constant scale value
     */
    public void setMode(int[] mode) {
        this.mode = mode;
    }
}
