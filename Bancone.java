package tpsit.mensaaziendale;

import static java.lang.Thread.sleep;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.Semaphore;

/**
 * Bancone su cui vengono depositati e prelevati i piatti.
 * @author Dardan Matias Berisha
 */
public class Bancone {
    
    public ArrayList<Integer> piattiSulBancone = new ArrayList<>();
    
    private int postiPiattiSulBancone;
    
    private Semaphore semVuoto = new Semaphore(0); //semaforo che regola quando il cuoco può depositare un piatto
    private Semaphore semPieno = new Semaphore(0); //semaforo che regola quando il dipendente può prendere il piatto, inizia da 0 perché inizialmente deve aspettare un piatto

    public Bancone(int postiPiattiSulBancone) {
        this.postiPiattiSulBancone = postiPiattiSulBancone;
    }

    
    /**
     * Metodo usato dal cuoco per depositare sul bancone i piatti creati.
     * @param piatto piatto da depositare sul bancone.
     */
    public void depositaPiatto(int piatto) throws InterruptedException{
        
        if(piattiSulBancone.size() == postiPiattiSulBancone){
            semVuoto.acquire();
        }
        
        System.out.println("Prodotto [" + piatto + "] piatto");
        
        dormi(100, 200);
        piattiSulBancone.add(piatto);
        semPieno.release();
        
    }
    
    /**
     * Metodo usato dal dipendente per prendere il piatto dal bancone.
     * @return il piatto da prendere sul bancone.
     */
    public int prendiPiatto() throws InterruptedException{
        
        semPieno.acquire();
        
        dormi(300, 700);
        
        int tmp = piattiSulBancone.remove(0);
        System.out.println("Consumato [" + tmp + "] piatto");
        semVuoto.release();
        
        return tmp;
    }
    
    public void dormi(int tempoMin, int tempoMax){
        try {
            sleep(new Random().nextInt(tempoMax)+tempoMin+1);
        } catch (InterruptedException ex) {}
    }
    
}
