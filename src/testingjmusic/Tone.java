/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testingjmusic;

import java.util.ArrayList;
import jm.JMC;

/**
 *
 * @author Pol Girbal
 */
public class Tone implements JMC {
    private final ArrayList<Integer> pitches;
    private final int name;
    private final int[] mode;
    private final int[] scale;
    
    public Tone(int name, int[] mode){
        pitches = new ArrayList<>();
        generateNotes();
        this.name = name;
        this.mode = mode;
        scale = new int[14];
        generateScale();
    }
    
    private void generateNotes(){
        for (int i = 0; i < 48; i++) {
            pitches.add(C2+i);
        }
    }   
    
    private void generateScale() {
        int s = this.pitches.indexOf(name);
        for (int i = 0; i < 7; i++) {
            scale[i] = pitches.get((s+mode[i]) % pitches.size());
        } s+=12;
        for (int i = 0; i < 7; i++) {
            scale[i+7] = this.pitches.get(s+mode[i]);
        }

    }
    
    public int[] getChord(int degree) {
        int[] c = new int[4];
        c[0] = scale[degree-1];
        c[1] = scale[degree+1];
        c[2] = scale[degree+3];
        c[3] = scale[degree+6];
        return c;
    }
    
    public int getNote(int degree) {
        return scale[degree-1];
    }
}
