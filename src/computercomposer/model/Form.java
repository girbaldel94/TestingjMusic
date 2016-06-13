package computercomposer.model;

import jm.JMC;

/**
 * This class generates a phrase of n bars
 * @author Pol Girbal i Jornet
 */
public class Form implements JMC{
    private final int[] phrase;
    private final int n;
    
    /**
     * Assigns the number of bars and initalizate a phrase
     * @param n number of bars
     */
    public Form (int n) {
        this.n = n;
        phrase = new int[n];
    }
    
    /**
     * A method that returns a tonic degree
     * @return tonic
     */
    private int getT(){
        Double r = Math.random();
        if (r <= 0.8) return 1;
        else if (r>0.8 && r<0.95) return 6;
        else return 3;        
    }

    /**
     * A method that returns a dominant degree
     * @return dominant
     */
    private int getD(){
        Double r = Math.random();
        if (r <= 0.8) return 5;
        else if (r>0.8 && r<0.95) return 7;
        else return 3; 
    }

    /**
     * A methot that returns a subdominant degree
     * @return subdominant
     */
    private int getSD(){
        Double r = Math.random();
        if (r <= 0.6) return 4;
        if (r > 0.6 && r < 0.9) return 2;
        else return 6; 
    }
    
    /**
     * A method that generates the phrase
     */
    private void generateStruct() {
        phrase[0] = 1;
        int prev = 1;
        for ( int i = 1; i < n-1; i++) {
            double op = Math.random();
            if(prev == 1 || prev == 6 || prev == 3) {
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
    
    /**
     * A method that returns the phrase
     * @return phrase
     */
    public int[] getStruct() {
        generateStruct();
        return phrase;
    }
}
