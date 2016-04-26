/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testingjmusic;

import jm.JMC;

/**
 *
 * @author Pol Girbal
 */
public class Form implements JMC{
    private final int[] phrase;
    private final int n;
    
    public Form (int n) {
        this.n = n;
        phrase = new int[n];
    }
    
    private int getT(){
        Double r = Math.random();
        if (r <= 0.8) return 1;
        else return 6;        
    }

    private int getD(){
        Double r = Math.random();
        if (r <= 0.8) return 5;
        else return 7; 
    }

    private int getSD(){
        Double r = Math.random();
        if (r <= 0.6) return 4;
        if (r > 0.6 && r < 0.9) return 2;
        else return 6; 
    }
    
    private void generateStruct() {
        phrase[0] = 1;
        int prev = 1;
        for ( int i = 1; i < n-1; i++) {
            double op = Math.random();
            if(prev == 1 || prev == 6) {
                if (op <= 0.1) phrase[i] = getT();
                else if (op > 0.1 && op <= 0.5) phrase[i] = getD();
                else if (op > 0.5 && op <= 1.0) phrase[i] = getSD();  
            } else if (prev == 5 || prev == 7) {
                phrase[i] = getT();
            } else if (prev == 2 || prev == 4) {
                if (op <= 0.1) phrase[i] = getT();
                else if (op > 0.1 && op <= 0.8) phrase[i] = getD();
                else if (op > 0.8 && op <= 1.0) phrase[i] = getSD();
            }
            prev = phrase[i];
        }
        phrase[n-2] = 5;
        phrase[n-1] = 1;
    }
    
    public int[] getStruct() {
        generateStruct();
        return phrase;
    }
}
