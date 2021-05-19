package tpsit.mensaaziendale;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Thread consumatore che prende i piatti dal bancone.
 * @author Dardan Matias Berisha
 */
public class Dipendente extends Thread{
    
    private int piatto;
    private Bancone b;
    
    /**
     * Costruttore.
     * @param name nome del dipendente.
     * @param b bancone sul quale il dipendente prende i piatti.
     */
    public Dipendente(String name, Bancone b) {
        super(name);
        this.b = b;
    }

    @Override
    public void run() {
        
        try {
            piatto = b.prendiPiatto(); //dipendente prende il piatto
        } catch (InterruptedException ex) {}
        
    }
    
    
    
}
