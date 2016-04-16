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
        pitches.add(C3);
        pitches.add(CS3);
        pitches.add(D3);
        pitches.add(DS3);
        pitches.add(E3);
        pitches.add(F3);
        pitches.add(FS3);
        pitches.add(G3);
        pitches.add(GS3);
        pitches.add(A3);
        pitches.add(AS3);
        pitches.add(B3);
        pitches.add(C4);
        pitches.add(CS4);
        pitches.add(D4);
        pitches.add(DS4);
        pitches.add(E4);
        pitches.add(F4);
        pitches.add(FS4);
        pitches.add(G4);
        pitches.add(GS4);
        pitches.add(A4);
        pitches.add(AS4);
        pitches.add(B4);
        pitches.add(C5);
        pitches.add(CS5);
        pitches.add(D5);
        pitches.add(DS5);
        pitches.add(E5);
        pitches.add(F5);
        pitches.add(FS5);
        pitches.add(G5);
        pitches.add(GS5);
        pitches.add(A5);
        pitches.add(AS5);
        pitches.add(B5);
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
